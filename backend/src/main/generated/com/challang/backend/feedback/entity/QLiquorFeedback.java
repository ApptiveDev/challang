package com.challang.backend.feedback.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QLiquorFeedback is a Querydsl query type for LiquorFeedback
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QLiquorFeedback extends EntityPathBase<LiquorFeedback> {

    private static final long serialVersionUID = -573317016L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QLiquorFeedback liquorFeedback = new QLiquorFeedback("liquorFeedback");

    public final com.challang.backend.util.entity.QBaseEntity _super = new com.challang.backend.util.entity.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.challang.backend.liquor.entity.QLiquor liquor;

    public final EnumPath<FeedbackType> type = createEnum("type", FeedbackType.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public final com.challang.backend.user.entity.QUser user;

    public QLiquorFeedback(String variable) {
        this(LiquorFeedback.class, forVariable(variable), INITS);
    }

    public QLiquorFeedback(Path<? extends LiquorFeedback> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QLiquorFeedback(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QLiquorFeedback(PathMetadata metadata, PathInits inits) {
        this(LiquorFeedback.class, metadata, inits);
    }

    public QLiquorFeedback(Class<? extends LiquorFeedback> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.liquor = inits.isInitialized("liquor") ? new com.challang.backend.liquor.entity.QLiquor(forProperty("liquor"), inits.get("liquor")) : null;
        this.user = inits.isInitialized("user") ? new com.challang.backend.user.entity.QUser(forProperty("user")) : null;
    }

}

