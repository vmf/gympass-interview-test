package com.vmf.flat.files.mapper;

import com.vmf.flat.files.Column;
import com.vmf.flat.files.ValueExtraction;
import com.vmf.flat.files.exception.ExtractorNotFoundException;
import com.vmf.flat.files.exception.FatalFailureException;
import com.vmf.flat.files.extractor.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.time.Duration;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

/**
 * Representa o mapeamento de tipo da coluna(propriedade) do flat file.
 */
public class FixedColumnTypeMap {
    private final Field field;
    private final ValueExtractor<?> columnExtractor;
    private final Column column;

    private static final Map<Class<?>, Class<? extends ValueExtractor>> GENERIC_VALUE_EXTRACTORS = buildGenericValueExtractors();

    private FixedColumnTypeMap(Field field,
                               ValueExtractor<?> columnExtractor,
                               Column column) {
        this.field = field;
        this.columnExtractor = columnExtractor;
        this.column = column;
    }

    public Field getField() {
        return field;
    }

    public ValueExtractor<?> getColumnValueExtractor() {
        return columnExtractor;
    }

    public int getIndex() {
        return column.index();
    }

    public static FixedColumnTypeMap from(Field field) {
        field.setAccessible(true);
        ValueExtractor<?> extractor = getColumnValueExtractor(field);
        Column column = field.getAnnotation(Column.class);
        return new FixedColumnTypeMap(field, extractor, column);
    }

    private static ValueExtractor<?> getColumnValueExtractor(Field field) {
        try {
            Class<? extends ValueExtractor> extractorClass = getFixedColumnExtractorClass(field);
            Constructor<? extends ValueExtractor> constructor = extractorClass.getDeclaredConstructor();
            return constructor.newInstance();
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new FatalFailureException("Falha na criação do extrator.", e);
        }
    }

    private static Class<? extends ValueExtractor> getFixedColumnExtractorClass(Field field) {
        if (field.isAnnotationPresent(ValueExtraction.class)) {
            ValueExtraction parser = field.getAnnotation(ValueExtraction.class);
            return parser.extractorClass();
        }

        for (Map.Entry<Class<?>, Class<? extends ValueExtractor>> entrySet : GENERIC_VALUE_EXTRACTORS.entrySet()) {
            if (field.getType().isAssignableFrom(entrySet.getKey()))
                return entrySet.getValue();
        }
        throw new ExtractorNotFoundException(String.format("Não foi possível encontrar um extrator para o tipo %s", field.getType().getName()));
    }

    private static Map<Class<?>, Class<? extends ValueExtractor>> buildGenericValueExtractors() {
        Map<Class<?>, Class<? extends ValueExtractor>> map = new HashMap<>();

        map.put(String.class, StringValueExtractor.class);
        map.put(Integer.class, IntValueExtractor.class);
        map.put(Double.class, DoubleValueExtractor.class);
        map.put(LocalTime.class, LocalTimeExtractor.class);
        map.put(Duration.class, DurationValueExtractor.class);

        return map;
    }
}
