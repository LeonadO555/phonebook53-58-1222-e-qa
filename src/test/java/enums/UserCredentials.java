package enums;

public enum UserCredentials {
    VALID_EMAIL("test@gmail.com"),
    VALID_PASSWORD("test@gmail.com"),
    INVALID_EMAIL("222gmail.com"),
    INVALID_PASSWORD("test222@gmail.com");

    public final String value;

    UserCredentials(String value) {
        this.value = value;
    }
}
