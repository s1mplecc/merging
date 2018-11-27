package xyz.s1mple.merging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import xyz.s1mple.merging.exceptions.MergeWithDifferentClassTypeException;

import java.lang.reflect.Field;

public interface Merging {
    Logger log = LoggerFactory.getLogger(Merging.class);

    default <U> void mergeWith(U newModel) {
        if (this.getClass() != newModel.getClass()) {
            throw new MergeWithDifferentClassTypeException(
                    String.format("<%s> can not merge with other class type <%s>", this.getClass().getName(), newModel.getClass().getName()));
        }

        Field[] oldFields = this.getClass().getDeclaredFields();
        Field[] newFields = newModel.getClass().getDeclaredFields();

        if (oldFields.length == 0) {
            log.warn("class <{}> has no declared fields", this.getClass().getName());
            return;
        }

        for (int i = 0; i < oldFields.length; i++) {
            Field oldField = oldFields[i];
            Field newField = newFields[i];
            oldField.setAccessible(true);
            newField.setAccessible(true);

            try {
                oldField.set(this, newField.get(newModel));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        System.out.println(this);
    }

}
