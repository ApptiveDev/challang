package com.challang.backend.preference.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QLiquorPreferenceType is a Querydsl query type for LiquorPreferenceType
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QLiquorPreferenceType extends EntityPathBase<LiquorPreferenceType> {

    private static final long serialVersionUID = 1163434606L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QLiquorPreferenceType liquorPreferenceType = new QLiquorPreferenceType("liquorPreferenceType");

    public final com.challang.backend.util.entity.QBaseEntity _super = new com.challang.backend.util.entity.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.challang.backend.liquor.entity.QLiquorType liquorType;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public final com.challang.backend.user.entity.QUser user;

    public QLiquorPreferenceType(String variable) {
        this(LiquorPreferenceType.class, forVariable(variable), INITS);
    }

    public QLiquorPreferenceType(Path<? extends LiquorPreferenceType> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QLiquorPreferenceType(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QLiquorPreferenceType(PathMetadata metadata, PathInits inits) {
        this(LiquorPreferenceType.class, metadata, inits);
    }

    public QLiquorPreferenceType(Class<? extends LiquorPreferenceType> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.liquorType = inits.isInitialized("liquorType") ? new com.challang.backend.liquor.entity.QLiquorType(forProperty("liquorType")) : null;
        this.user = inits.isInitialized("user") ? new com.challang.backend.user.entity.QUser(forProperty("user")) : null;
    }

}

