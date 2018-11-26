package xyz.s1mple.merging;

public enum Level {
    /* 无论新值为何止，都会覆盖旧值 */
    Mandatory,
    /* 如果新值不为null，覆盖旧值，否则不覆盖 */
    Required,
    /* 忽略，不做合并处理 */
    Ignore;

    public boolean isRequired() {
        return this.equals(Required);
    }

    public boolean isMandatory() {
        return this.equals(Mandatory);
    }

    public boolean isIgnore() {
        return this.equals(Ignore);
    }
}