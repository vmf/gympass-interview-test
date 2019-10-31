package com.vmf.flat.files.mapper;

import com.vmf.flat.files.exception.FlatFileMappingException;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Consumer;

/**
 * Agente que consome as linhas do arquivo e cria os modelos de forma genérica.
 * @param <T> Tipo do modelo
 */
public class FileConsumer<T> implements Consumer<String> {

    private final Collection<T> collection = new ArrayList<>();
    private final FlatFileMapping<T> fileMapping;

    public FileConsumer(FlatFileMapping<T> fileMapping) {
        this.fileMapping = fileMapping;
    }

    @Override
    public void accept(String s) {
        try {
            collection.add(mapLine(s));
        } catch (Exception e) {
            throw new FlatFileMappingException("Erro fatal durante o mapeamento.", e);
        }
    }

    @Override
    public Consumer<String> andThen(Consumer<? super String> after) {
        return null;
    }

    public Collection<T> getCollection() {
        return collection;
    }

    private T mapLine(String line) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        String[] values = line.split("\\s+");
        T model = newModel();

        setValuesToModel(values, fileMapping.getTypeMapCollection(), model);

        for (EmbeddableMapping embeddableMapping : fileMapping.getEmbeddableMappingCollection()) {
            Constructor<?> constructor = embeddableMapping.getField().getType().getDeclaredConstructor();
            Object embeddedObject = constructor.newInstance();
            setValuesToModel(values, embeddableMapping.getTypeMapCollection(), embeddedObject);

            embeddableMapping.getField().set(model, embeddedObject);
        }
        return model;
    }

    private void setValuesToModel(String[] values, Collection<FixedColumnTypeMap> typeMapCollection, Object model) throws IllegalAccessException {
        for (FixedColumnTypeMap fixedColumnTypeMap : typeMapCollection) {
            String value = values[fixedColumnTypeMap.getIndex()];

            ValueExtractor<?> valueExtractor = fixedColumnTypeMap.getColumnValueExtractor();
            ExtractionContext extractionContext = new ExtractionContext(value);
            Object extractedValue = valueExtractor.extract(extractionContext);

            fixedColumnTypeMap.getField().set(model, extractedValue);
        }
    }

    private T newModel() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Constructor<T> constructor = fileMapping.getModelClass().getDeclaredConstructor();
        return constructor.newInstance();
    }
}
