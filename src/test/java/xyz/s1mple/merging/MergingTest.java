package xyz.s1mple.merging;

import org.junit.Test;

public class MergingTest {
    @Test(expected = Merging.MergeWithDifferentClassTypeException.class)
    public void should_throw_MergingException_when_merge_with_different_class_type() {
        new Person().mergeWith("aaa");
    }
}