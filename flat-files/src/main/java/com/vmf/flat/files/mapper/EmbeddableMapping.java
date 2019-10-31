package com.vmf.flat.files.mapper;

import com.vmf.flat.files.Column;
import com.vmf.flat.files.Embeddable;
import com.vmf.flat.files.exception.EmbeddableAnnotationNotFound;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Agente que mapeia os tipos da porção embutida.
 */
public class EmbeddableMapping {

    private final Field field;
    private final Collection<FixedColumnTypeMap> typeMapCollection;

    public EmbeddableMapping(Field field, Collection<FixedColumnTypeMap> typeMapCollection) {
        this.field = field;
        this.typeMapCollection = typeMapCollection;
    }

    public Collection<FixedColumnTypeMap> getTypeMapCollection() {
        return typeMapCollection;
    }

    public Field getField() {
        return field;
    }

    public static EmbeddableMapping create(Field field) {
        field.setAccessible(true);
        Class<?> embeddableClass = field.getType();
        if (!embeddableClass.isAnnotationPresent(Embeddable.class))
            throw new EmbeddableAnnotationNotFound(String.format("Class '%s' must be annotated with @Embeddable.", embeddableClass.getName()));

        List<FixedColumnTypeMap> typeMapCollection = new ArrayList<>();
        for (Field nestedField : embeddableClass.getDeclaredFields()) {
            if (nestedField.isAnnotationPresent(Column.class)) {
                FixedColumnTypeMap typeMap = FixedColumnTypeMap.from(nestedField);
                typeMapCollection.add(typeMap);
            }
        }
        return new EmbeddableMapping(field, typeMapCollection);
    }
}
