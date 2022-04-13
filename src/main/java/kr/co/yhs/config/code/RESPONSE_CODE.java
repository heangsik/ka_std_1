package kr.co.yhs.config.code;

import lombok.Getter;

@Getter
public enum RESPONSE_CODE {
    R_00("00", "성공"),
    R_10("10", "필수값 오류"),
    R_20("20", "불가능 거래");
    String code;
    String desc;
    RESPONSE_CODE(String code, String desc)
    {
        this.code = code;
        this.desc = desc;
    }

}
