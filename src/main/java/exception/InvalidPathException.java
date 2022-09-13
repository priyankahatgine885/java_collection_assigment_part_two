package exception;

public class InvalidPathException extends Exception{
    public InvalidPathException(String message) {
        super(message);
    }
    public InvalidPathException(String message, Throwable cause) {
        super(message, cause);
    }
    public InvalidPathException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
    public InvalidPathException(Throwable cause) {
        super(cause);
    }
}