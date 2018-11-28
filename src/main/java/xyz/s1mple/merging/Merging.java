package xyz.s1mple.merging;

import xyz.s1mple.merging.exceptions.MergeOnFieldIllegalAccessException;
import xyz.s1mple.merging.exceptions.MergeWithDifferentClassTypeException;

import java.lang.reflect.Field;

public interface Merging<T extends Merging> {

    default void mergeWith(T newEntity) {
        if (this.getClass() != newEntity.getClass()) {
            throw new MergeWithDifferentClassTypeException(
                    String.format("<%s> can not merge with other class type <%s>", this.getClass().getName(), newEntity.getClass().getName()));
        }

        Field[] fields = this.getClass().getDeclaredFields();

        for (Field field : fields) {
            MergeOn mergeOn = field.getAnnotation(MergeOn.class);
            field.setAccessible(true);

            try {
                if (mergeOn == null || mergeOn.level().isRequired()) {
                    Object newFieldValue = field.get(newEntity);
                    if (newFieldValue != null) {
                        field.set(this, newFieldValue);
                    }
                    continue;
                }
                if (mergeOn.level().isMandatory()) {
                    field.set(this, field.get(newEntity));
                }
            } catch (IllegalAccessException ex) {
                throw new MergeOnFieldIllegalAccessException(field.getName(), ex);
            }
        }
    }
}
