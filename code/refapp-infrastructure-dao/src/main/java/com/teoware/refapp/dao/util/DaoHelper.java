package com.teoware.refapp.dao.util;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;

import com.teoware.refapp.dao.util.Converters.Converter;

public final class DaoHelper {

    private static final Map<Class<?>, Converter<?>> converters = Converters.getConverters();

    private DaoHelper() {
    }

    public static void processParameter(PreparedStatement statement, Object param, int index) throws SQLException {
        if (param == null) {
            statement.setObject(index, param);
        } else if (isEnum(param)) {
            statement.setString(index, param.toString());
        } else {
            setUsingConverter(statement, param, index);
        }
    }

    private static void setUsingConverter(PreparedStatement statement, Object param, int index) throws SQLException {
        Converter<?> converter = converters.get(param.getClass());
        if (converter == null) {
            statement.setObject(index, param);
        } else {
            converter.setParam(statement, param, index);
        }
    }

    private static boolean isEnum(Object object) {
        return object.getClass().isEnum();
    }

    public static Object[] generateArray(Object... objects) {
        return objects;
    }
}
