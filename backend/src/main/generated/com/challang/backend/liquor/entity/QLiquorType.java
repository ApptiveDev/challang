package com.challang.backend.liquor.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QLiquorType is a Querydsl query type for LiquorType
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QLiquorType extends EntityPathBase<LiquorType> {

    private static final long serialVersionUID = -1673765476L;

    public static final QLiquorType liquorType = new QLiquorType("liquorType");

    public final com.challang.backend.util.entity.QBaseEntity _super = new com.challang.backend.util.entity.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QLiquorType(String variable) {
        super(LiquorType.class, forVariable(variable));
    }

    public QLiquorType(Path<? extends LiquorType> path) {
        super(path.getType(), path.getMetadata());
    }

    public QLiquorType(PathMetadata metadata) {
        super(LiquorType.class, metadata);
    }

}

