package training.args;

public class ArgsException extends Exception {
    public enum ErrorCode {
        OK, MISSING_STRING, MISSING_INTEGER, INVALID_INTEGER, MISSING_DOUBLE, INVALID_DOUBLE, UNEXPECTED_ARGUMENT, INVALID_ARGUMENT_NAME, INVALID_FORMAT
    }

    private char errorArgumentId = '\0';
    private ErrorCode errorCode = ErrorCode.OK;
    private String errorParameter;

    public ArgsException() {
    }

    public char getErrorArgumentId() {
        return errorArgumentId;
    }

    public void setErrorArgumentId(char errorArgumentId) {
        this.errorArgumentId = errorArgumentId;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorParameter() {
        return errorParameter;
    }

    public void setErrorParameter(String errorParameter) {
        this.errorParameter = errorParameter;
    }

    public ArgsException(String message) {
        super(message);
    }

    public ArgsException(ErrorCode errorCode, char errorArgumentId, String errorParameter) {
        this.errorCode = errorCode;
        this.errorArgumentId = errorArgumentId;
        this.errorParameter = errorParameter;
    }

    public String errorMessage() throws Exception {
        switch (errorCode) {
        case UNEXPECTED_ARGUMENT:
            return String.format("Argument -%c unexpected.", errorArgumentId);
        case MISSING_DOUBLE:
            return String.format("Could not find double parameter for -%c.", errorArgumentId);
        case INVALID_DOUBLE:
            return String.format("Argument -%c expects a double but was '%s'.", errorArgumentId, errorParameter);
        case MISSING_INTEGER:
            return String.format("Could not find integer parameter for -%c.", errorArgumentId);
        case INVALID_INTEGER:
            return String.format("Argument -%c expects an integer but was '%s'.", errorArgumentId, errorParameter);
        case MISSING_STRING:
            return String.format("Could not find string parameter for -%c.", errorArgumentId);
        case OK:
            throw new Exception("TILT: should not get here.");
        }

        return "";
    }
}
