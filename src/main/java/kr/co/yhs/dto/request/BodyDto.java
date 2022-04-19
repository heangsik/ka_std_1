package kr.co.yhs.dto.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class BodyDto {
    @ApiModelProperty(value = "투자 요청 금액", example = "1000", required = true)
    private long investmentAmount;
    @ApiModelProperty(value = "상품ID", example = "1", required = true)
    private String productId;

}
