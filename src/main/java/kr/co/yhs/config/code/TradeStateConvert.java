package kr.co.yhs.config.code;

import kr.co.yhs.config.code.TRADE_STATE;

import javax.persistence.AttributeConverter;

public class TradeStateConvert implements AttributeConverter<TRADE_STATE, String>{
    @Override
    public String convertToDatabaseColumn(TRADE_STATE attribute) {
        return attribute.cd;
    }

    @Override
    public TRADE_STATE convertToEntityAttribute(String dbData) {
        return  TRADE_STATE.find(dbData);
    }
}
