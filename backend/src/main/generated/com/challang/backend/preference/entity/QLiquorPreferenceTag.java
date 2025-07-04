package com.challang.backend.preference.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QLiquorPreferenceTag is a Querydsl query type for LiquorPreferenceTag
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QLiquorPreferenceTag extends EntityPathBase<LiquorPreferenceTag> {

    private static final long serialVersionUID = 1838644710L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QLiquorPreferenceTag liquorPreferenceTag = new QLiquorPreferenceTag("liquorPreferenceTag");

    public final com.challang.backend.util.entity.QBaseEntity _super = new com.challang.backend.util.entity.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.challang.backend.tag.entity.QTag tag;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public final com.challang.backend.user.entity.QUser user;

    public QLiquorPreferenceTag(String variable) {
        this(LiquorPreferenceTag.class, forVariable(variable), INITS);
    }

    public QLiquorPreferenceTag(Path<? extends LiquorPreferenceTag> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QLiquorPreferenceTag(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QLiquorPreferenceTag(PathMetadata metadata, PathInits inits) {
        this(LiquorPreferenceTag.class, metadata, inits);
    }

    public QLiquorPreferenceTag(Class<? extends LiquorPreferenceTag> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.tag = inits.isInitialized("tag") ? new com.challang.backend.tag.entity.QTag(forProperty("tag")) : null;
        this.user = inits.isInitialized("user") ? new com.challang.backend.user.entity.QUser(forProperty("user")) : null;
    }

}

