package enums;

import lombok.Getter;

@Getter
public enum UserCredentials {

    VALID_EMAIL("test@gmail.com"),
    VALID_PASSWORD("test@gmail.com"),
    INVALID_EMAIL("111testgmail.com"),
    INVALID_PASSWORD("111test@gmail.com");

    public final String value;

    UserCredentials(String value) {
        this.value = value;
    }
}
