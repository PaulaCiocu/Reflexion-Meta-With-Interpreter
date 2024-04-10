class Property {
    private PropertyType type;
    private Object value;

    public Property(PropertyType type, Object value) {
        this.type = type;
        this.value = value;
    }

    public Object getValue() {
        return value;
    }

    public PropertyType getType() {
        return type;
    }

    @Override
    public String toString() {
        return type.toString() + " " + value.toString();
    }

}
