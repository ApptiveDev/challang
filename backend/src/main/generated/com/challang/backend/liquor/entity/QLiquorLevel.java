package com.challang.backend.liquor.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QLiquorLevel is a Querydsl query type for LiquorLevel
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QLiquorLevel extends EntityPathBase<LiquorLevel> {

    private static final long serialVersionUID = -355100318L;

    public static final QLiquorLevel liquorLevel = new QLiquorLevel("liquorLevel");

    public final com.challang.backend.util.entity.QBaseEntity _super = new com.challang.backend.util.entity.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QLiquorLevel(String variable) {
        super(LiquorLevel.class, forVariable(variable));
    }

    public QLiquorLevel(Path<? extends LiquorLevel> path) {
        super(path.getType(), path.getMetadata());
    }

    public QLiquorLevel(PathMetadata metadata) {
        super(LiquorLevel.class, metadata);
    }

}

