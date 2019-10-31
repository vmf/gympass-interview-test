package com.vmf.flat.files.mapper;

import com.vmf.flat.files.Column;
import com.vmf.flat.files.Embedded;
import com.vmf.flat.files.FlatFile;
import com.vmf.flat.files.exception.FlatFileAnnotationNotFound;

import java.lang.reflect.Field;
import java.util.*;

/**
 * Agente que mapeia o flat file.
 * @param <T> Tipo do modelo
 */
public class FlatFileMapping<T> {
    private final Class<T> modelClass;
    private final Collection<FixedColumnTypeMap> typeMapCollection;
    private final Collection<EmbeddableMapping> embeddableMappingCollection;
    private final FlatFile flatFile;

    private FlatFileMapping(Class<T> modelClass,
                            Collection<FixedColumnTypeMap> typeMapCollection,
                            Collection<EmbeddableMapping> embeddableMappingCollection,
                            FlatFile flatFile) {
        this.modelClass = modelClass;
        this.typeMapCollection = typeMapCollection;
        this.embeddableMappingCollection = embeddableMappingCollection;
        this.flatFile = flatFile;
    }

    public Class<T> getModelClass() {
        return modelClass;
    }

    public Collection<FixedColumnTypeMap> getTypeMapCollection() {
        return typeMapCollection;
    }

    public Collection<EmbeddableMapping> getEmbeddableMappingCollection() {
        return embeddableMappingCollection;
    }

    public boolean ignoreFirstLine() {
        return flatFile.ignoreFirstLine();
    }

    public static <T> FlatFileMapping create(Class<T> modelClass) {
        if (modelClass == null) throw new NullPointerException("Class cannot be null.");
        if (!modelClass.isAnnotationPresent(FlatFile.class))
            throw new FlatFileAnnotationNotFound(String.format("Class '%s' must be annotated with @FlatFile.", modelClass.getName()));

        List<FixedColumnTypeMap> typeMapCollection = new ArrayList<>();
        Collection<EmbeddableMapping> embeddableMappingCollection = new ArrayList<>();
        for (Field field : modelClass.getDeclaredFields()) {
            if (field.isAnnotationPresent(Column.class)) {
                FixedColumnTypeMap typeMap = FixedColumnTypeMap.from(field);
                typeMapCollection.add(typeMap);
            } else if (field.isAnnotationPresent(Embedded.class)) {
                EmbeddableMapping mapping = EmbeddableMapping.create(field);
                embeddableMappingCollection.add(mapping);
            }
        }
        return new FlatFileMapping(modelClass, typeMapCollection, embeddableMappingCollection, modelClass.getAnnotation(FlatFile.class));
    }
}
