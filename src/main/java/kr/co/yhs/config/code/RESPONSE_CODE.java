package kr.co.yhs.config.code;

import lombok.Getter;

@Getter
public enum RESPONSE_CODE {
    R_00("00", "성공"),
    R_10("10", "필수값 오류"),
    R_11("11", "필수값 오류"),
    R_20("20", "불가능 거래"),
    R_21("21", "해당 거래 없음"),
    R_22("22", "거래 금액 초과(sold-out)"),
    R_23("23", "거래 내역 없음"),
    R_90("90", "시스템 오류");
    String code;
    String desc;
    RESPONSE_CODE(String code, String desc)
    {
        this.code = code;
        this.desc = desc;
    }

}
