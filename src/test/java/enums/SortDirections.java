package enums;

import lombok.Getter;

@Getter
public enum SortDirections {
    up("//*[@ng-reflect-icon-up='true']"),
    down("//*[@ng-reflect-icon-up='false']");
    public final String direction;

    SortDirections(String direction) {

        this.direction = direction;
    }
}
