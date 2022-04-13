package kr.co.yhs.dto.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class InverstmentDto {
    @Pattern(regexp = "^[0-9]*$")
    @NotEmpty
    private String Inverstmentamount;
    @Pattern(regexp = "^[0-9]*$")
    @NotEmpty
    private String productId;

}
