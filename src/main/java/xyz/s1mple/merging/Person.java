package xyz.s1mple.merging;

import java.util.List;

public class Person implements Merging {
    @MergeOn
    private String name;
    private int age;
    private List<String> friends;
}
