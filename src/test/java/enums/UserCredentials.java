package enums;


import lombok.Getter;

@Getter

public enum UserCredentials {
    VALID_EMAIL("test@mail.com"),
    VALID_PASSWORD("test@gmail.com"),
    INVALID_EMAIL("tgfg@gmail.com"),
    INVALID_PASSWORD("test222@gmail.com");

    public final String value;

    UserCredentials(String value) {
        this.value = value;
    }


}




