package com.alibaba.fastjson2;

import com.alibaba.fastjson2.writer.ObjectWriter;
import com.alibaba.fastjson2.reader.ObjectReader;
import com.alibaba.fastjson2.reader.ObjectReaderProvider;
import com.alibaba.fastjson2.util.TypeUtils;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.Instant;
import java.util.*;
import java.util.function.Function;

public class JSONArray extends ArrayList<Object> {
    private static final long serialVersionUID = 1L;

    static ObjectWriter<JSONArray> arrayWriter;
    static ObjectReader<JSONArray> arrayReader;
    static ObjectReader<JSONObject> objectReader;

    /**
     * default
     */
    public JSONArray() {
        super();
    }

    /**
     * @param initialCapacity the initial capacity of the list
     */
    public JSONArray(int initialCapacity) {
        super(initialCapacity);
    }

    /**
     * @param collection the collection whose elements are to be placed into this list
     */
    public JSONArray(Collection<?> collection) {
        super(collection);
    }

    /**
     * @param items the array whose elements are to be placed into this list
     */
    public JSONArray(Object... items) {
        super(items.length);
        for (Object item : items) {
            add(item);
        }
    }

    /**
     * Returns the {@link JSONArray} at the specified location in this list.
     *
     * @param index index of the element to return
     * @return {@link JSONArray} or null
     */
    public JSONArray getJSONArray(int index) {
        Object value = get(index);

        if (value instanceof JSONArray) {
            return (JSONArray) value;
        }

        if (value instanceof String) {
            String str = (String) value;

            if (str.isEmpty() || str.equalsIgnoreCase("null")) {
                return null;
            }

            JSONReader reader = JSONReader.of(str);
            if (arrayReader == null) {
                arrayReader = reader.getObjectReader(JSONArray.class);
            }
            return arrayReader.readObject(reader, 0);
        }

        if (value instanceof Collection) {
            return new JSONArray((Collection<?>) value);
        }

        return null;
    }

    /**
     * Returns the {@link JSONObject} at the specified location in this list.
     *
     * @param index index of the element to return
     * @return {@link JSONObject} or null
     */
    public JSONObject getJSONObject(int index) {
        Object value = get(index);

        if (value instanceof JSONObject) {
            return (JSONObject) value;
        }

        if (value instanceof String) {
            String str = (String) value;

            if (str.isEmpty() || str.equalsIgnoreCase("null")) {
                return null;
            }

            JSONReader reader = JSONReader.of(str);
            if (objectReader == null) {
                objectReader = reader.getObjectReader(JSONObject.class);
            }
            return objectReader.readObject(reader, 0);
        }

        if (value instanceof Map) {
            return new JSONObject((Map) value);
        }

        return null;
    }

    /**
     * Returns the {@link String} at the specified location in this list.
     *
     * @param index index of the element to return
     * @return {@link String} or null
     */
    public String getString(int index) {
        Object value = get(index);

        if (value == null) {
            return null;
        }

        if (value instanceof String) {
            return (String) value;
        }

        return JSON.toJSONString(value);
    }

    /**
     * Returns the {@link Double} at the specified location in this list.
     *
     * @param index index of the element to return
     * @return {@link Double} or null
     */
    public Double getDouble(int index) {
        Object value = get(index);

        if (value == null) {
            return null;
        }

        if (value instanceof Double) {
            return (Double) value;
        }

        if (value instanceof Number) {
            return ((Number) value).doubleValue();
        }

        if (value instanceof String) {
            String str = (String) value;

            if (str.isEmpty() || str.equalsIgnoreCase("null")) {
                return null;
            }

            return Double.parseDouble(str);
        }

        throw new JSONException("can not cast " + value.getClass() + " to Double");
    }

    /**
     * Returns a double value at the specified location in this list.
     *
     * @param index index of the element to return
     * @return double
     */
    public double getDoubleValue(int index) {
        Object value = get(index);

        if (value == null) {
            return 0D;
        }

        if (value instanceof Number) {
            return ((Number) value).doubleValue();
        }

        if (value instanceof String) {
            String str = (String) value;

            if (str.isEmpty() || str.equalsIgnoreCase("null")) {
                return 0D;
            }

            return Double.parseDouble(str);
        }

        throw new JSONException("can not cast " + value.getClass() + " to double");
    }

    /**
     * Returns the {@link Float} at the specified location in this list.
     *
     * @param index index of the element to return
     * @return {@link Float} or null
     */
    public Float getFloat(int index) {
        Object value = get(index);

        if (value == null) {
            return null;
        }

        if (value instanceof Float) {
            return (Float) value;
        }

        if (value instanceof Number) {
            return ((Number) value).floatValue();
        }

        if (value instanceof String) {
            String str = (String) value;

            if (str.isEmpty() || str.equalsIgnoreCase("null")) {
                return null;
            }

            return Float.parseFloat(str);
        }

        throw new JSONException("can not cast " + value.getClass() + " to Float");
    }

    /**
     * Returns a float value at the specified location in this list.
     *
     * @param index index of the element to return
     * @return float
     */
    public float getFloatValue(int index) {
        Object value = get(index);

        if (value == null) {
            return 0F;
        }

        if (value instanceof Number) {
            return ((Number) value).floatValue();
        }

        if (value instanceof String) {
            String str = (String) value;

            if (str.isEmpty() || str.equalsIgnoreCase("null")) {
                return 0F;
            }

            return Float.parseFloat(str);
        }

        throw new JSONException("can not cast " + value.getClass() + " to float");
    }

    /**
     * Returns the {@link Long} at the specified location in this list.
     *
     * @param index index of the element to return
     * @return {@link Long} or null
     */
    public Long getLong(int index) {
        Object value = get(index);
        if (value == null) {
            return null;
        }

        if (value instanceof Long) {
            return ((Long) value);
        }

        if (value instanceof Number) {
            return ((Number) value).longValue();
        }

        if (value instanceof String) {
            String str = (String) value;
            if (str.isEmpty() || str.equalsIgnoreCase("null")) {
                return null;
            }
            return Long.parseLong(str);
        }

        throw new JSONException("can not cast " + value.getClass() + " to Long");
    }

    /**
     * Returns a long value at the specified location in this list.
     *
     * @param index index of the element to return
     * @return long
     */
    public long getLongValue(int index) {
        Object value = get(index);

        if (value == null) {
            return 0;
        }

        if (value instanceof Number) {
            return ((Number) value).longValue();
        }

        if (value instanceof String) {
            String str = (String) value;

            if (str.isEmpty() || str.equalsIgnoreCase("null")) {
                return 0;
            }

            return Long.parseLong(str);
        }

        throw new JSONException("can not cast " + value.getClass() + " to long");
    }

    /**
     * Returns the {@link Integer} at the specified location in this list.
     *
     * @param index index of the element to return
     * @return {@link Integer} or null
     */
    public Integer getInteger(int index) {
        Object value = get(index);
        if (value == null) {
            return null;
        }

        if (value instanceof Integer) {
            return ((Integer) value);
        }

        if (value instanceof Number) {
            return ((Number) value).intValue();
        }

        if (value instanceof String) {
            String str = (String) value;
            if (str.isEmpty() || str.equalsIgnoreCase("null")) {
                return null;
            }
            return Integer.parseInt(str);
        }

        throw new JSONException("can not cast " + value.getClass() + " to Integer");
    }

    /**
     * Returns an int value at the specified location in this list.
     *
     * @param index index of the element to return
     * @return int
     */
    public int getIntValue(int index) {
        Object value = get(index);

        if (value == null) {
            return 0;
        }

        if (value instanceof Number) {
            return ((Number) value).intValue();
        }

        if (value instanceof String) {
            String str = (String) value;

            if (str.isEmpty() || str.equalsIgnoreCase("null")) {
                return 0;
            }

            return Integer.parseInt(str);
        }

        throw new JSONException("can not cast " + value.getClass() + " to int");
    }

    /**
     * Returns the {@link Short} at the specified location in this list.
     *
     * @param index index of the element to return
     * @return {@link Short} or null
     */
    public Short getShort(int index) {
        Object value = get(index);

        if (value == null) {
            return null;
        }

        if (value instanceof Short) {
            return (Short) value;
        }

        if (value instanceof Number) {
            return ((Number) value).shortValue();
        }

        if (value instanceof String) {
            String str = (String) value;

            if (str.isEmpty() || str.equalsIgnoreCase("null")) {
                return null;
            }

            return Short.parseShort(str);
        }

        throw new JSONException("can not cast " + value.getClass() + " to short");
    }

    /**
     * Returns a short value at the specified location in this list.
     *
     * @param index index of the element to return
     * @return short
     */
    public short getShortValue(int index) {
        Object value = get(index);

        if (value == null) {
            return 0;
        }

        if (value instanceof Number) {
            return ((Number) value).shortValue();
        }

        if (value instanceof String) {
            String str = (String) value;

            if (str.isEmpty() || str.equalsIgnoreCase("null")) {
                return 0;
            }

            return Short.parseShort(str);
        }

        throw new JSONException("can not cast " + value.getClass() + " to short");
    }

    /**
     * Returns the {@link Byte} at the specified location in this list.
     *
     * @param index index of the element to return
     * @return {@link Byte} or null
     */
    public Byte getByte(int index) {
        Object value = get(index);

        if (value == null) {
            return null;
        }

        if (value instanceof Number) {
            return ((Number) value).byteValue();
        }

        if (value instanceof String) {
            String str = (String) value;

            if (str.isEmpty() || str.equalsIgnoreCase("null")) {
                return null;
            }

            return Byte.parseByte(str);
        }

        throw new JSONException("can not cast " + value.getClass() + " to byte");
    }

    /**
     * Returns a byte value at the specified location in this list.
     *
     * @param index index of the element to return
     * @return byte
     */
    public byte getByteValue(int index) {
        Object value = get(index);

        if (value == null) {
            return 0;
        }

        if (value instanceof Number) {
            return ((Number) value).byteValue();
        }

        if (value instanceof String) {
            String str = (String) value;

            if (str.isEmpty() || str.equalsIgnoreCase("null")) {
                return 0;
            }

            return Byte.parseByte(str);
        }

        throw new JSONException("can not cast " + value.getClass() + " to byte");
    }

    /**
     * Returns the {@link Boolean} at the specified location in this list.
     *
     * @param index index of the element to return
     * @return {@link Boolean} or null
     */
    public Boolean getBoolean(int index) {
        Object value = get(index);

        if (value == null) {
            return null;
        }

        if (value instanceof Boolean) {
            return (Boolean) value;
        }

        if (value instanceof Number) {
            return ((Number) value).intValue() == 1;
        }

        if (value instanceof String) {
            String str = (String) value;

            if (str.isEmpty() || str.equalsIgnoreCase("null")) {
                return null;
            }

            return str.equalsIgnoreCase("true") || str.equals("1");
        }

        throw new JSONException("can not convert to boolean : " + value);
    }

    /**
     * Returns a boolean value at the specified location in this list.
     *
     * @param index index of the element to return
     * @return boolean
     */
    public boolean getBooleanValue(int index) {
        Object value = get(index);

        if (value == null) {
            return false;
        }

        if (value instanceof Boolean) {
            return (Boolean) value;
        }

        if (value instanceof Number) {
            return ((Number) value).intValue() == 1;
        }

        if (value instanceof String) {
            String str = (String) value;
            return str.equalsIgnoreCase("true") || str.equals("1");
        }

        throw new JSONException("can not convert to boolean : " + value);
    }

    /**
     * Returns the {@link BigInteger} at the specified location in this list.
     *
     * @param index index of the element to return
     * @return {@link BigInteger} or null
     */
    public BigInteger getBigInteger(int index) {
        Object value = get(index);

        if (value == null) {
            return null;
        }

        if (value instanceof Number) {
            if (value instanceof BigInteger) {
                return (BigInteger) value;
            }

            if (value instanceof BigDecimal) {
                return ((BigDecimal) value).toBigInteger();
            }

            long longValue = ((Number) value).longValue();
            return BigInteger.valueOf(longValue);
        }

        if (value instanceof String) {
            String str = (String) value;

            if (str.isEmpty() || str.equalsIgnoreCase("null")) {
                return null;
            }

            return new BigInteger(str);
        }

        throw new JSONException("can not cast " + value.getClass() + " to Long");
    }

    /**
     * Returns the {@link BigDecimal} at the specified location in this list.
     *
     * @param index index of the element to return
     * @return {@link BigDecimal} or null
     */
    public BigDecimal getBigDecimal(int index) {
        Object value = get(index);

        if (value == null) {
            return null;
        }

        if (value instanceof Number) {
            if (value instanceof BigDecimal) {
                return (BigDecimal) value;
            }

            if (value instanceof BigInteger) {
                return new BigDecimal((BigInteger) value);
            }

            if (value instanceof Float
                || value instanceof Double) {
                // Floating point number have no cached BigDecimal
                return new BigDecimal(value.toString());
            }

            long longValue = ((Number) value).longValue();
            return BigDecimal.valueOf(longValue);
        }

        if (value instanceof String) {
            String str = (String) value;

            if (str.isEmpty() || str.equalsIgnoreCase("null")) {
                return null;
            }

            return new BigDecimal(str);
        }

        throw new JSONException("can not cast " + value.getClass() + " to Long");
    }

    /**
     * Returns the {@link Date} at the specified location in this list.
     *
     * @param index index of the element to return
     * @return {@link Date} or null
     */
    public Date getDate(int index) {
        Object value = get(index);

        if (value == null) {
            return null;
        }

        if (value instanceof Date) {
            return (Date) value;
        }

        if (value instanceof Number) {
            long millis = ((Number) value).longValue();
            if (millis == 0) {
                return null;
            }
            return new Date(millis);
        }

        return TypeUtils.toDate(value);
    }

    /**
     * Returns the {@link Instant} at the specified location in this list.
     *
     * @param index index of the element to return
     * @return {@link Instant} or null
     */
    public Instant getInstant(int index) {
        Object value = get(index);

        if (value == null) {
            return null;
        }

        if (value instanceof Instant) {
            return (Instant) value;
        }

        if (value instanceof Number) {
            long millis = ((Number) value).longValue();
            if (millis == 0) {
                return null;
            }
            return Instant.ofEpochMilli(millis);
        }

        return TypeUtils.toInstant(value);
    }

    /**
     * Serialize to JSON {@link String}
     *
     * @return JSON {@link String}
     */
    @Override
    public String toString() {
        try (JSONWriter writer = JSONWriter.of()) {
            if (arrayWriter == null) {
                arrayWriter = writer.getObjectWriter(JSONArray.class, JSONArray.class);
            }
            arrayWriter.write(writer, this, null, null, 0);
            return writer.toString();
        }
    }

    /**
     * Serialize to JSON {@link String}
     *
     * @return JSON {@link String}
     */
    public String toJSONString() {
        return toString();
    }

    /**
     * Convert this {@link JSONArray} to the target type
     *
     * @param type converted goal type
     */
    public <T> T toJavaObject(Type type) {
        ObjectReaderProvider provider = JSONFactory.getDefaultObjectReaderProvider();
        ObjectReader objectReader = provider.getObjectReader(type);
        return (T) objectReader.createInstance(this);
    }

    /**
     * Convert all the members of this {@link JSONArray} into the target List,
     * warning that each member of the {@link JSONArray} must implement the Map interface
     *
     * <code>
     * String json = "[{\"id\": 1, \"name\": \"fastjson\"}, {\"id\": 2, \"name\": \"fastjson2\"}]";
     * JSONArray array = JSON.parseArray(json);
     * List<User> users = array.toJavaList(User.class);
     * </code>
     *
     * @param clazz converted goal class
     */
    public <T> List<T> toJavaList(Class<T> clazz) {
        List<T> list = new ArrayList<>(size());
        ObjectReaderProvider provider = JSONFactory.getDefaultObjectReaderProvider();
        ObjectReader objectReader = provider.getObjectReader(clazz);

        for (Object item : this) {
            T classItem;
            if (item instanceof Map) {
                classItem = (T) objectReader.createInstance((Map) item);
                System.out.println(item);
            } else {
                throw new JSONException("TODO");
            }
            list.add(classItem);
        }

        return list;
    }

    /**
     * Returns the result of the {@link Type} converter conversion of the element at the specified position in this list.
     *
     * @param index index of the element to return
     * @param type  converted goal type
     * @return <T> or null
     * @throws JSONException If no suitable conversion method is found
     */
    public <T> T getObject(int index, Type type) {
        Object value = get(index);
        if (value == null) {
            return null;
        }

        Class<?> valueClass = value.getClass();
        ObjectReaderProvider provider = JSONFactory.getDefaultObjectReaderProvider();
        Function typeConvert = provider.getTypeConvert(valueClass, type);
        if (typeConvert != null) {
            return (T) typeConvert.apply(value);
        }

        if (value instanceof Map) {
            Map map = (Map) value;

            ObjectReader objectReader = provider.getObjectReader(type);
            return (T) objectReader.createInstance(map);
        }

        if (value instanceof Collection) {
            ObjectReader objectReader = provider.getObjectReader(type);
            return (T) objectReader.createInstance((Collection) value);
        }

        Class clazz = TypeUtils.getMapping(type);
        if (clazz.isInstance(value)) {
            return (T) value;
        }

        throw new JSONException("can not convert from " + valueClass + " to " + type);
    }

    /**
     * Chained addition of elements
     *
     * <code>
     * JSONArray array = new JSONArray().fluentAdd(1).fluentAdd(2).fluentAdd(3);
     * </code>
     *
     * @param element element to be appended to this list
     */
    public JSONArray fluentAdd(Object element) {
        add(element);
        return this;
    }

    /**
     * Pack multiple elements as {@link JSONArray}
     *
     * @param items element set
     */
    public static JSONArray of(Object... items) {
        return new JSONArray(items);
    }

    /**
     * Pack an element as {@link JSONArray}
     *
     * @param item target element
     */
    public static JSONArray of(Object item) {
        JSONArray array = new JSONArray(1);
        array.add(item);
        return array;
    }

    /**
     * Pack two elements as {@link JSONArray}
     *
     * @param first  first element
     * @param second second element
     */
    public static JSONArray of(Object first, Object second) {
        JSONArray array = new JSONArray(2);
        array.add(first);
        array.add(second);
        return array;
    }

    /**
     * Pack three elements as {@link JSONArray}
     *
     * @param first  first element
     * @param second second element
     * @param third  third element
     */
    public static JSONArray of(Object first, Object second, Object third) {
        JSONArray array = new JSONArray(3);
        array.add(first);
        array.add(second);
        array.add(third);
        return array;
    }
}
