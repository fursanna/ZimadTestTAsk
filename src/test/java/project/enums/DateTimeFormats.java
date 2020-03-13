package project.enums;

public enum DateTimeFormats {
    RFC3339("yyyy-MM-dd'T'HH:mm:ssZ"),
    YYYY_MM_DD("yyyy-MM-dd");

    private final String format;

    DateTimeFormats(String format) {
        this.format = format;
    }

    @Override
    public String toString() {
        return format;
    }
}

