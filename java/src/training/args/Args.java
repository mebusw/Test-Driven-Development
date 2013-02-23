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
    private Map<Character, ArgumentMarshaler> booleanArgs = new HashMap<Character, ArgumentMarshaler>();
    private Map<Character, ArgumentMarshaler> stringArgs = new HashMap<Character, ArgumentMarshaler>();
    private Map<Character, ArgumentMarshaler> intArgs = new HashMap<Character, ArgumentMarshaler>();
    private Set<Character> argsFound = new HashSet<Character>();

    private int currentArgument;
    private char errorArgument = '\0';

    enum ErrorCode {
        OK, MISSING_STRING, MISSING_INTEGER, INVALID_INTEGER
    }

    private ErrorCode errorCode = ErrorCode.OK;
    private String errorParameter;

    public Args(String schema, String[] args) throws ParseException {
        this.schema = schema;
        this.args = args;
        valid = parse();
    }

    public boolean isValid() {
        return valid;
    }

    private boolean parse() throws ParseException {
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
            parseBooleanSchemaElement(elementId);
        } else if (isStringSchemaElement(elementTail)) {
            parseStringSchemaElement(elementId);
        }
    }

    private void validateSchemaElementId(char elementId) throws ParseException {
        if (!Character.isLetter(elementId)) {
            throw new ParseException("Bad character:" + elementId + " in Args format: " + schema, 0);
        }
    }

    private boolean isStringSchemaElement(String elementTail) {
        return elementTail.equals("*");
    }

    private boolean isBooleanSchemaElement(String elementTail) {
        return elementTail.length() == 0;
    }

    private void parseStringSchemaElement(char elementId) {
        stringArgs.put(elementId, new StringArgumentMashaler());
    }

    private void parseBooleanSchemaElement(char elementId) {
        booleanArgs.put(elementId, new BooleanArgumentMashaler());
    }

    private void parseIntergerSchemaElement(char elementId) {
        booleanArgs.put(elementId, new IntergerArgumentMashaler());
    }

    private boolean parseArguments() {
        for (currentArgument = 0; currentArgument < args.length; currentArgument++) {
            String arg = args[currentArgument];
            parseArgument(arg);
        }
        return true;
    }

    private void parseArgument(String arg) {
        if (arg.startsWith("-"))
            parseElements(arg);
    }

    private void parseElements(String arg) {
        for (int i = 1; i < arg.length(); i++)
            parseElement(arg.charAt(i));
    }

    private void parseElement(char argChar) {
        if (setArgument(argChar)) {
            argsFound.add(argChar);
        } else {
            unexpectedArguments.add(argChar);
            valid = false;
        }
    }

    private boolean setArgument(char argChar) {
        boolean set = true;
        if (isBoolean(argChar))
            setBooleanArg(argChar, true);
        else if (isString(argChar))
            try {
                setStringArg(argChar, "");
            } catch (ArgsException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        else
            set = false;
        return set;
    }

    private void setStringArg(char argChar, String string) throws ArgsException {
        currentArgument++;
        try {
            stringArgs.get(argChar).set(args[currentArgument]);
        } catch (ArrayIndexOutOfBoundsException e) {
            valid = false;
            errorArgument = argChar;
            errorCode = ErrorCode.MISSING_STRING;
            throw new ArgsException();
        }
    }

    private boolean isString(char argChar) {
        return stringArgs.containsKey(argChar);
    }

    private void setBooleanArg(char argChar, boolean value) {
        try {
            booleanArgs.get(argChar).set("true");
        } catch (ArgsException e) {

        }
    }

    private boolean isBoolean(char argChar) {
        return booleanArgs.containsKey(argChar);
    }

    private void setIntArg(char argChar) throws ArgsException {
        currentArgument++;
        String parameter = null;
        try {
            parameter = args[currentArgument];
            intArgs.get(argChar).set(parameter);
        } catch (ArrayIndexOutOfBoundsException e) {
            valid = false;
            errorArgument = argChar;
            errorCode = ErrorCode.MISSING_INTEGER;
            throw new ArgsException();
        } catch (ArgsException e) {
            valid = false;
            errorArgument = argChar;
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
            case MISSING_STRING:
                return String.format("Could not find string parameter for -%c", errorArgument);
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
        ArgumentMarshaler am = booleanArgs.get(arg);
        return am != null && (Boolean) am.get();
    }

    public String getString(char arg) {
        ArgumentMarshaler am = stringArgs.get(arg);
        return am == null ? "" : (String) am.get();
    }

    public int getInt(char arg) {
        ArgumentMarshaler am = intArgs.get(arg);
        return am == null ? 0 : (Integer) am.get();
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

class IntergerArgumentMashaler extends ArgumentMarshaler {
    private int intValue = 0;

    @Override
    public void set(String s) throws ArgsException {
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