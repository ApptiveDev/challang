package com.challang.backend.preference.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QLiquorPreferenceLevel is a Querydsl query type for LiquorPreferenceLevel
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QLiquorPreferenceLevel extends EntityPathBase<LiquorPreferenceLevel> {

    private static final long serialVersionUID = 1698756304L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QLiquorPreferenceLevel liquorPreferenceLevel = new QLiquorPreferenceLevel("liquorPreferenceLevel");

    public final com.challang.backend.util.entity.QBaseEntity _super = new com.challang.backend.util.entity.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.challang.backend.liquor.entity.QLiquorLevel liquorLevel;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public final com.challang.backend.user.entity.QUser user;

    public QLiquorPreferenceLevel(String variable) {
        this(LiquorPreferenceLevel.class, forVariable(variable), INITS);
    }

    public QLiquorPreferenceLevel(Path<? extends LiquorPreferenceLevel> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QLiquorPreferenceLevel(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QLiquorPreferenceLevel(PathMetadata metadata, PathInits inits) {
        this(LiquorPreferenceLevel.class, metadata, inits);
    }

    public QLiquorPreferenceLevel(Class<? extends LiquorPreferenceLevel> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.liquorLevel = inits.isInitialized("liquorLevel") ? new com.challang.backend.liquor.entity.QLiquorLevel(forProperty("liquorLevel")) : null;
        this.user = inits.isInitialized("user") ? new com.challang.backend.user.entity.QUser(forProperty("user")) : null;
    }

}

