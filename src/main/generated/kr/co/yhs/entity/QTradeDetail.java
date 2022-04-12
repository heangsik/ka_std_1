package kr.co.yhs.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QTradeDetail is a Querydsl query type for TradeDetail
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTradeDetail extends EntityPathBase<TradeDetail> {

    private static final long serialVersionUID = 1823829519L;

    public static final QTradeDetail tradeDetail = new QTradeDetail("tradeDetail");

    public final DateTimePath<java.time.LocalDateTime> finishAt = createDateTime("finishAt", java.time.LocalDateTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> parentId = createNumber("parentId", Long.class);

    public final DateTimePath<java.time.LocalDateTime> startAt = createDateTime("startAt", java.time.LocalDateTime.class);

    public final StringPath title = createString("title");

    public final NumberPath<Long> tradeAmount = createNumber("tradeAmount", Long.class);

    public final DateTimePath<java.time.LocalDateTime> tradeDt = createDateTime("tradeDt", java.time.LocalDateTime.class);

    public final StringPath userId = createString("userId");

    public QTradeDetail(String variable) {
        super(TradeDetail.class, forVariable(variable));
    }

    public QTradeDetail(Path<? extends TradeDetail> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTradeDetail(PathMetadata metadata) {
        super(TradeDetail.class, metadata);
    }

}

