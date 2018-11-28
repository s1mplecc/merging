package xyz.s1mple.merging.exceptions;

public class MergeOnFieldIllegalAccessException extends RuntimeException {
    public MergeOnFieldIllegalAccessException(String message) {
        super(message);
    }

    public MergeOnFieldIllegalAccessException(String message, Throwable cause) {
        super(message, cause);
    }
}
