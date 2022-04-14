package kr.co.yhs.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QTradeDetailEntity is a Querydsl query type for TradeDetailEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTradeDetailEntity extends EntityPathBase<TradeDetailEntity> {

    private static final long serialVersionUID = -739559726L;

    public static final QTradeDetailEntity tradeDetailEntity = new QTradeDetailEntity("tradeDetailEntity");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> parentId = createNumber("parentId", Long.class);

    public final NumberPath<Long> tradeAmount = createNumber("tradeAmount", Long.class);

    public final DateTimePath<java.time.LocalDateTime> tradeDt = createDateTime("tradeDt", java.time.LocalDateTime.class);

    public final StringPath userId = createString("userId");

    public QTradeDetailEntity(String variable) {
        super(TradeDetailEntity.class, forVariable(variable));
    }

    public QTradeDetailEntity(Path<? extends TradeDetailEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTradeDetailEntity(PathMetadata metadata) {
        super(TradeDetailEntity.class, metadata);
    }

}

