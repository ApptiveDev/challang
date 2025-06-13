package com.challang.backend.liquor.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QLiquor is a Querydsl query type for Liquor
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QLiquor extends EntityPathBase<Liquor> {

    private static final long serialVersionUID = -1420833982L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QLiquor liquor = new QLiquor("liquor");

    public final com.challang.backend.util.entity.QBaseEntity _super = new com.challang.backend.util.entity.QBaseEntity(this);

    public final NumberPath<Double> averageRating = createNumber("averageRating", Double.class);

    public final StringPath color = createString("color");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath imageUrl = createString("imageUrl");

    public final QLiquorLevel level;

    public final ListPath<com.challang.backend.tag.entity.LiquorTag, com.challang.backend.tag.entity.QLiquorTag> liquorTags = this.<com.challang.backend.tag.entity.LiquorTag, com.challang.backend.tag.entity.QLiquorTag>createList("liquorTags", com.challang.backend.tag.entity.LiquorTag.class, com.challang.backend.tag.entity.QLiquorTag.class, PathInits.DIRECT2);

    public final NumberPath<Double> maxAbv = createNumber("maxAbv", Double.class);

    public final NumberPath<Double> minAbv = createNumber("minAbv", Double.class);

    public final StringPath name = createString("name");

    public final StringPath origin = createString("origin");

    public final NumberPath<Integer> reviewCount = createNumber("reviewCount", Integer.class);

    public final StringPath tastingNote = createString("tastingNote");

    public final QLiquorType type;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QLiquor(String variable) {
        this(Liquor.class, forVariable(variable), INITS);
    }

    public QLiquor(Path<? extends Liquor> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QLiquor(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QLiquor(PathMetadata metadata, PathInits inits) {
        this(Liquor.class, metadata, inits);
    }

    public QLiquor(Class<? extends Liquor> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.level = inits.isInitialized("level") ? new QLiquorLevel(forProperty("level")) : null;
        this.type = inits.isInitialized("type") ? new QLiquorType(forProperty("type")) : null;
    }

}

