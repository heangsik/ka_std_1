package kr.co.yhs.dto.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.annotations.ApiModelProperty;
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
    @ApiModelProperty(value = "투자 요청 금액", example = "1000", required = true)
    private String InverstmentAmount;
    @Pattern(regexp = "^[0-9]*$")
    @NotEmpty
    @ApiModelProperty(value = "투자 거래 ID", example = "1", required = true)
    private String productId;

    public long getProductIdLong()
    {
        return Long.parseLong(productId);
    }
    public long getInverstmentAmountLong()
    {
        return Long.parseLong(InverstmentAmount);
    }

}
