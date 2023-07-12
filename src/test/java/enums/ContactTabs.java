package enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ContactTabs {
    INFO("//*[@ng-reflect-_id='1']"),
    PHONES("//*[@ng-reflect-_id='2']"),
    EMAILS("//*[@ng-reflect-_id='3']"),
    ADDRESSES("//*[@ng-reflect-_id='4']");

    public final String value;
}

