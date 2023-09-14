package enums;

import lombok.Getter;

public enum SortDirection {
    @Getter
    UP("//*[@ng-reflect-icon-up='true']"),
    DOWN("//*[@ng-reflect-icon-up='false']");

    public final String value;

    SortDirection(String value) {
        this.value = value;
    }
}
