package xyz.s1mple.merging;

public interface Merging {
    default <U> void mergeWith(U newModel) {
        if (this.getClass() != newModel.getClass()) {
            throw new MergeWithDifferentClassTypeException(
                    String.format("<%s> can not merge with other class type <%s>", this.getClass().getName(), newModel.getClass().getName()));
        }
    }

    class MergeWithDifferentClassTypeException extends RuntimeException {
        public MergeWithDifferentClassTypeException(String message) {
            super(message);
        }
    }
}
