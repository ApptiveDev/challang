package com.challang.backend.tag.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QLiquorTag is a Querydsl query type for LiquorTag
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QLiquorTag extends EntityPathBase<LiquorTag> {

    private static final long serialVersionUID = -213267076L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QLiquorTag liquorTag = new QLiquorTag("liquorTag");

    public final com.challang.backend.util.entity.QBaseEntity _super = new com.challang.backend.util.entity.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath isCore = createBoolean("isCore");

    public final com.challang.backend.liquor.entity.QLiquor liquor;

    public final QTag tag;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QLiquorTag(String variable) {
        this(LiquorTag.class, forVariable(variable), INITS);
    }

    public QLiquorTag(Path<? extends LiquorTag> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QLiquorTag(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QLiquorTag(PathMetadata metadata, PathInits inits) {
        this(LiquorTag.class, metadata, inits);
    }

    public QLiquorTag(Class<? extends LiquorTag> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.liquor = inits.isInitialized("liquor") ? new com.challang.backend.liquor.entity.QLiquor(forProperty("liquor"), inits.get("liquor")) : null;
        this.tag = inits.isInitialized("tag") ? new QTag(forProperty("tag")) : null;
    }

}

