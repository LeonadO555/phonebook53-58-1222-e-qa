package enums;

import lombok.Getter;

@Getter
public enum SortValues {
    Email("//*[@ngbtooltip='click to sort']");
    public final String value;

    SortValues(String value) {
        this.value = value;
    }
}
