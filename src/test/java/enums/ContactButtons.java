package enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ContactButtons {
    EDIT("//*[@id='btn-edit-contact']"),
    ADD_PHONE_NUMBER("//*[@id='btn-add-phone']"),
    ADD_EMAIL("//*[@id='btn-add-phone']"),
    ADD_ADDRESS("//*[@id='btn-add-phone']");

    public final String value;
}
