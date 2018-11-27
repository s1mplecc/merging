package xyz.s1mple.merging.model;

import lombok.Data;
import lombok.ToString;
import xyz.s1mple.merging.MergeOn;
import xyz.s1mple.merging.Merging;

import java.util.List;

@Data
@ToString
public class Entity implements Merging {
    @MergeOn
    private String string;
    private int anInt;
    private List<String> stringList;
    private String[] strings;
}
