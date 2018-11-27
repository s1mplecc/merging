package xyz.s1mple.merging.model;

import lombok.Data;
import lombok.ToString;
import org.assertj.core.util.Sets;
import xyz.s1mple.merging.Level;
import xyz.s1mple.merging.MergeOn;
import xyz.s1mple.merging.Merging;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

@ToString
@Data
public class Entity implements Merging {
    @MergeOn(level = Level.Ignored)
    private String string = "string";
    private int anInt = 1;
    @MergeOn(level = Level.Mandatory)
    private List<String> stringList = Arrays.asList("a", "b", "c");
    @MergeOn(level = Level.Required)
    private String[] strings = new String[]{"sArr1", "sArr2"};
    private Email email = new Email("zz@163.com");
    private Set<Email> emails = Sets.newLinkedHashSet(new Email("zz@163.com"), new Email("zz@gmail.com"));

    @Data
    public static class Email {
        private String value;

        public Email(String value) {
            this.value = value;
        }
    }
}
