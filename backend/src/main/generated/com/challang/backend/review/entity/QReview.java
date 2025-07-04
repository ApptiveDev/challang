package com.challang.backend.review.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QReview is a Querydsl query type for Review
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QReview extends EntityPathBase<Review> {

    private static final long serialVersionUID = -319716630L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QReview review = new QReview("review");

    public final com.challang.backend.util.entity.QBaseEntity _super = new com.challang.backend.util.entity.QBaseEntity(this);

    public final StringPath content = createString("content");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Integer> dislikeCount = createNumber("dislikeCount", Integer.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath imageUrl = createString("imageUrl");

    public final NumberPath<Integer> likeCount = createNumber("likeCount", Integer.class);

    public final com.challang.backend.liquor.entity.QLiquor liquor;

    public final NumberPath<Double> rating = createNumber("rating", Double.class);

    public final ListPath<ReviewTag, QReviewTag> reviewTags = this.<ReviewTag, QReviewTag>createList("reviewTags", ReviewTag.class, QReviewTag.class, PathInits.DIRECT2);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public final com.challang.backend.user.entity.QUser writer;

    public QReview(String variable) {
        this(Review.class, forVariable(variable), INITS);
    }

    public QReview(Path<? extends Review> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QReview(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QReview(PathMetadata metadata, PathInits inits) {
        this(Review.class, metadata, inits);
    }

    public QReview(Class<? extends Review> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.liquor = inits.isInitialized("liquor") ? new com.challang.backend.liquor.entity.QLiquor(forProperty("liquor"), inits.get("liquor")) : null;
        this.writer = inits.isInitialized("writer") ? new com.challang.backend.user.entity.QUser(forProperty("writer")) : null;
    }

}

