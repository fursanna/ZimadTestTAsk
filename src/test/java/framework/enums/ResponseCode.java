package framework.enums;

public enum ResponseCode {
    OK(200),
    NO_CONTENT(204);

    private final int code;

    ResponseCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}