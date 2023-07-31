package enums;

import lombok.Getter;

@Getter
public enum Languages {
    ENGLISH("English"),
    RUSSIAN("Russian"),
    GERMAN("German"),
    UKRAINE("Ukraine");

    public final String value;

    Languages(String value) {
        this.value = value;
    }
}
