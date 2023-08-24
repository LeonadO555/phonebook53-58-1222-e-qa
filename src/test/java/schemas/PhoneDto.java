package schemas;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_DEFAULT)

public class PhoneDto {
    @JsonProperty("id")
    private int id;

    @JsonProperty("countryCode")
    private String countryCode;

    @JsonProperty("phoneNumber")
    private String phoneNumber;

    @JsonProperty("contactId")
    private int contactId;
}
