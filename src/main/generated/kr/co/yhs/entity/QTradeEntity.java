package kr.co.yhs.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QTradeEntity is a Querydsl query type for TradeEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTradeEntity extends EntityPathBase<TradeEntity> {

    private static final long serialVersionUID = 1860778401L;

    public static final QTradeEntity tradeEntity = new QTradeEntity("tradeEntity");

    public final DateTimePath<java.time.LocalDateTime> finishAt = createDateTime("finishAt", java.time.LocalDateTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final DateTimePath<java.time.LocalDateTime> startAt = createDateTime("startAt", java.time.LocalDateTime.class);

    public final StringPath status = createString("status");

    public final StringPath title = createString("title");

    public final NumberPath<Long> totalInvastingAmount = createNumber("totalInvastingAmount", Long.class);

    public QTradeEntity(String variable) {
        super(TradeEntity.class, forVariable(variable));
    }

    public QTradeEntity(Path<? extends TradeEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTradeEntity(PathMetadata metadata) {
        super(TradeEntity.class, metadata);
    }

}

