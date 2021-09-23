package taxi.model;

public enum CarMarks {
    AUDI("audi"),
    BMW("bmw"),
    FORD("ford"),
    HONDA("honda"),
    KIA("kia"),
    LADA("lada"),
    MAZDA("mazda");

    private final String value;

    CarMarks(String value) {
        this.value = value;
    }
}
