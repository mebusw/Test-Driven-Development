package training.args;

import java.text.ParseException;
import java.util.*;

import training.orders.ExceedOrderAmountException;

/**
 * Refactoring. from section 14.3 of book "Clean Code"
 * 
 */
public class Args {
    private String schema;
    private String[] args;
    private boolean valid;
    private Set<Character> unexpectedArguments = new TreeSet<Character>();
    private Map<Character, ArgumentMarshaler> marshalers = new HashMap<Character, ArgumentMarshaler>();
    private Set<Character> argsFound = new HashSet<Character>();

    private int currentArgument;
    private char errorArgumentId = '\0';

    enum ErrorCode {
        OK, MISSING_STRING, MISSING_INTEGER, INVALID_INTEGER
    }

    private ErrorCode errorCode = ErrorCode.OK;
    private String errorParameter;

    public Args(String schema, String[] args) throws ParseException, ArgsException {
        this.schema = schema;
        this.args = args;
        valid = parse();
    }

    public boolean isValid() {
        return valid;
    }

    private boolean parse() throws ParseException, ArgsException {
        if (schema.length() == 0 && args.length == 0)
            return true;
        parseSchema();
        parseArguments();
        return unexpectedArguments.size() == 0;
    }

    private boolean parseSchema() throws ParseException {
        for (String element : schema.split(",")) {
            if (element.length() > 0) {
                parseSchemaElement(element.trim());
            }
        }
        return true;
    }

    private void parseSchemaElement(String element) throws ParseException {
        char elementId = element.charAt(0);
        String elementTail = element.substring(1);
        validateSchemaElementId(elementId);
        if (isBooleanSchemaElement(elementTail)) {
            marshalers.put(elementId, new BooleanArgumentMashaler());
        } else if (isStringSchemaElement(elementTail)) {
            marshalers.put(elementId, new StringArgumentMashaler());
        } else if (isIntegerSchemaElement(elementTail)) {
            marshalers.put(elementId, new IntegerArgumentMashaler());
        } else {
            throw new ParseException(String.format("Argument: %c has invalid format: %s.", elementId, elementTail), 0);
        }
    }

    private void validateSchemaElementId(char elementId) throws ParseException {
        if (!Character.isLetter(elementId)) {
            throw new ParseException("Bad character:" + elementId + " in Args format: " + schema, 0);
        }
    }

    private boolean isBooleanSchemaElement(String elementTail) {
        return elementTail.length() == 0;
    }

    private boolean isStringSchemaElement(String elementTail) {
        return elementTail.equals("*");
    }

    private boolean isIntegerSchemaElement(String elementTail) {
        return elementTail.equals("#");
    }

    private boolean parseArguments() throws ArgsException {
        for (currentArgument = 0; currentArgument < args.length; currentArgument++) {
            String arg = args[currentArgument];
            parseArgument(arg);
        }
        return true;
    }

    private void parseArgument(String arg) throws ArgsException {
        if (arg.startsWith("-"))
            parseElements(arg);
    }

    private void parseElements(String arg) throws ArgsException {
        for (int i = 1; i < arg.length(); i++)
            parseElement(arg.charAt(i));
    }

    private void parseElement(char argChar) throws ArgsException {
        if (setArgument(argChar)) {
            argsFound.add(argChar);
        } else {
            unexpectedArguments.add(argChar);
            valid = false;
        }
    }

    private boolean setArgument(char argChar) throws ArgsException {
        ArgumentMarshaler m = marshalers.get(argChar);
        try {
            if (m instanceof BooleanArgumentMashaler)
                setBooleanArg(m);
            else if (m instanceof StringArgumentMashaler)
                setStringArg(m);
            else if (m instanceof IntegerArgumentMashaler)
                setIntArg(m);
            else
                return false;
            return true;
        } catch (ArgsException e) {
            valid = false;
            errorArgumentId = argChar;
            throw e;
        }
    }

    private void setStringArg(ArgumentMarshaler m) throws ArgsException {
         currentArgument++;
        try {
            m.set(args[currentArgument]);
        } catch (ArrayIndexOutOfBoundsException e) {
            valid = false;
            errorCode = ErrorCode.MISSING_STRING;
            throw new ArgsException();
        }
    }

    private void setBooleanArg(ArgumentMarshaler m) {
        try {
            m.set("true");
        } catch (ArgsException e) {

        }
    }

    private void setIntArg(ArgumentMarshaler m) throws ArgsException {
         currentArgument++;
        String parameter = null;
        try {
            parameter = args[currentArgument];
            m.set(parameter);
        } catch (ArrayIndexOutOfBoundsException e) {
            valid = false;
            errorCode = ErrorCode.MISSING_INTEGER;
            throw new ArgsException();
        } catch (ArgsException e) {
            valid = false;
            errorParameter = parameter;
            errorCode = ErrorCode.INVALID_INTEGER;
            throw e;

        }
    }

    public int cardinality() {
        return argsFound.size();
    }

    public String usage() {
        if (schema.length() > 0)
            return "-[" + schema + "]";
        else
            return "";
    }

    public String errorMessage() throws Exception {
        if (unexpectedArguments.size() > 0) {
            return unexpectedArgumentMessage();
        } else {
            switch (errorCode) {
            case MISSING_INTEGER:
                return String.format("Could not find integer parameter for -%c", errorArgumentId);
            case INVALID_INTEGER:
                return String.format("Argument -%c expects an integer but was '%s'.", errorArgumentId, errorParameter);
            case MISSING_STRING:
                return String.format("Could not find string parameter for -%c", errorArgumentId);
            case OK:
                throw new Exception("TILT: should not get here.");
            }
        }
        return "";
    }

    private String unexpectedArgumentMessage() {
        StringBuffer message = new StringBuffer("Arguments(s)  -");
        for (char c : unexpectedArguments) {
            message.append(c);
        }
        message.append("  unexpected");
        return message.toString();
    }

    public boolean getBoolean(char arg) {
        ArgumentMarshaler am = marshalers.get(arg);
        boolean b = false;
        try {
            b = am != null && (Boolean) am.get();
        } catch (ClassCastException e) {
            b = false;
        }
        return b;
    }

    public String getString(char arg) {
        ArgumentMarshaler am = marshalers.get(arg);
        try {
            return am == null ? "" : (String) am.get();
        } catch (ClassCastException e) {
            return "";
        }
    }

    public int getInt(char arg) {
        ArgumentMarshaler am = marshalers.get(arg);
        try {
            return am == null ? 0 : (Integer) am.get();
        } catch (ClassCastException e) {
            return 0;
        }

    }

    public boolean has(char arg) {
        return argsFound.contains(arg);
    }

}

abstract class ArgumentMarshaler {
    public abstract void set(String s) throws ArgsException;

    public abstract Object get();
}

class BooleanArgumentMashaler extends ArgumentMarshaler {
    private boolean booleanValue = false;

    @Override
    public void set(String s) {
        booleanValue = true;
    }

    @Override
    public Object get() {
        return booleanValue;
    }

}

class StringArgumentMashaler extends ArgumentMarshaler {
    private String stringValue = "";

    @Override
    public void set(String s) {
        stringValue = s;
    }

    @Override
    public Object get() {
        return stringValue;
    }
}

class IntegerArgumentMashaler extends ArgumentMarshaler {
    private int intValue = 0;

    @Override
    public void set(String s) throws ArgsException {
        System.out.println(s);
        try {
            intValue = Integer.parseInt(s);
        } catch (NumberFormatException e) {
            throw new ArgsException();
        }
    }

    @Override
    public Object get() {
        return intValue;
    }
}