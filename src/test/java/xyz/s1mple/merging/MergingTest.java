package xyz.s1mple.merging;

import org.junit.Test;
import xyz.s1mple.merging.exceptions.MergeWithDifferentClassTypeException;
import xyz.s1mple.merging.model.Entity;
import xyz.s1mple.merging.model.SubEntity;

import static org.assertj.core.api.Assertions.assertThat;

public class MergingTest {
    @Test(expected = MergeWithDifferentClassTypeException.class)
    public void should_throw_MergingException_when_merge_with_different_class_type() {
        new Entity().mergeWith(new SubEntity());
    }

    @Test
    public void should_merge_new_entity_with_different_merge_level() {
        Entity newEntity = new Entity();
        newEntity.setString("newString");
        newEntity.setAnInt(2);
        newEntity.setStringList(null);
        newEntity.setStrings(new String[]{"newS1", "newS2", "newS3"});
        newEntity.setEmail(new Entity.Email("newEmail@163.com"));
        newEntity.setEmails(null);

        Entity entity = new Entity();
        entity.mergeWith(newEntity);

        assertThat(entity.getString()).isEqualTo("string");
        assertThat(entity.getAnInt()).isEqualTo(2);
        assertThat(entity.getStringList()).isNull();
        assertThat(entity.getStrings()).containsSequence("newS1", "newS2", "newS3");
        assertThat(entity.getEmail().getValue()).isEqualTo("newEmail@163.com");
        assertThat(entity.getEmails()).hasSize(2);
        assertThat(entity.getEmails()).contains(new Entity.Email("zz@163.com"));
        assertThat(entity.getEmails()).contains(new Entity.Email("zz@gmail.com"));
    }
}