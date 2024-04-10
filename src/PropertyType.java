class PropertyType {
    private String name;
    private Class<?> type;

    public PropertyType(String name, Class<?> type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;

    }

    public Class<?> getType() {
        return type;
    }


}
