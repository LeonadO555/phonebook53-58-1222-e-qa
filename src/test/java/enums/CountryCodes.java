package enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum CountryCodes {

    GERMANY("+49", " Germany (+49) "),
    FRANCE("+33", " France (+33) "),
    CANADA("+1", " Canada (+1) "),
    UKRAINE("+380", " Ukraine (+380) ");

    public final String code;
    public final String description;
}