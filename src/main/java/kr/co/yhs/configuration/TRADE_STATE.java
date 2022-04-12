package kr.co.yhs.configuration;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum TRADE_STATE {
    ST01("ready", "ST01"),
    ST02("start", "ST02");
    String desc;
    String cd;

    TRADE_STATE(String desc, String cd) {
        this.desc = desc;
        this.cd = cd;
    }

    public static TRADE_STATE find(String cd) {
        return Arrays.stream(TRADE_STATE.values()).filter(u -> cd.equals(u.cd)).findFirst().orElse(TRADE_STATE.ST01);
    }
}
