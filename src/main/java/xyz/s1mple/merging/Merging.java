package xyz.s1mple.merging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import xyz.s1mple.merging.exceptions.MergeWithDifferentClassTypeException;

import java.lang.reflect.Field;

public interface Merging {
    Logger log = LoggerFactory.getLogger(Merging.class);

    default <T extends Merging> void mergeWith(T newEntity) {
        if (this.getClass() != newEntity.getClass()) {
            throw new MergeWithDifferentClassTypeException(
                    String.format("<%s> can not merge with other class type <%s>", this.getClass().getName(), newEntity.getClass().getName()));
        }

        Field[] fields = this.getClass().getDeclaredFields();

        if (fields.length == 0) {
            log.warn("class <{}> has no declared fields", this.getClass().getName());
            return;
        }

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
                log.error(ex.getMessage());
            }
        }
    }
}
