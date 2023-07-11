package enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum CountryCodes {

    GERMANY("Germany (+49)"),
    FRANCE("France (+33)"),
    CANADA("Canada (+1)"),
    UKRAINE("Ukraine (+380)");


    public final String value;
}
