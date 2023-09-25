package enums;

public enum ContactInfoTabs {
    INFO("Info"),
    PHONES("Phones"),
    EMAILS("E-mails"),
    ADDRESSES("Addresses");

    public final String value;

    ContactInfoTabs(String value) {
        this.value = value;
    }
}