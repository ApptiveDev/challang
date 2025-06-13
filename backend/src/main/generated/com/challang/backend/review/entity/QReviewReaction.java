package com.challang.backend.review.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QReviewReaction is a Querydsl query type for ReviewReaction
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QReviewReaction extends EntityPathBase<ReviewReaction> {

    private static final long serialVersionUID = -2097006797L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QReviewReaction reviewReaction = new QReviewReaction("reviewReaction");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QReview review;

    public final EnumPath<ReactionType> type = createEnum("type", ReactionType.class);

    public final com.challang.backend.user.entity.QUser user;

    public QReviewReaction(String variable) {
        this(ReviewReaction.class, forVariable(variable), INITS);
    }

    public QReviewReaction(Path<? extends ReviewReaction> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QReviewReaction(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QReviewReaction(PathMetadata metadata, PathInits inits) {
        this(ReviewReaction.class, metadata, inits);
    }

    public QReviewReaction(Class<? extends ReviewReaction> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.review = inits.isInitialized("review") ? new QReview(forProperty("review"), inits.get("review")) : null;
        this.user = inits.isInitialized("user") ? new com.challang.backend.user.entity.QUser(forProperty("user")) : null;
    }

}

