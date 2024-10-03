package com.sparta.hhplusw02cleanarchitecture.infrastructure.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QLectureEntity is a Querydsl query type for LectureEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QLectureEntity extends EntityPathBase<LectureEntity> {

    private static final long serialVersionUID = -1394801258L;

    public static final QLectureEntity lectureEntity = new QLectureEntity("lectureEntity");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath instructor = createString("instructor");

    public final StringPath title = createString("title");

    public QLectureEntity(String variable) {
        super(LectureEntity.class, forVariable(variable));
    }

    public QLectureEntity(Path<? extends LectureEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QLectureEntity(PathMetadata metadata) {
        super(LectureEntity.class, metadata);
    }

}

