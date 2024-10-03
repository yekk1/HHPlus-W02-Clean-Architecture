package com.sparta.hhplusw02cleanarchitecture.infrastructure.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QLectureItemEntity is a Querydsl query type for LectureItemEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QLectureItemEntity extends EntityPathBase<LectureItemEntity> {

    private static final long serialVersionUID = -56393463L;

    public static final QLectureItemEntity lectureItemEntity = new QLectureItemEntity("lectureItemEntity");

    public final NumberPath<Integer> capacity = createNumber("capacity", Integer.class);

    public final DatePath<java.time.LocalDate> date = createDate("date", java.time.LocalDate.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> lecture_id = createNumber("lecture_id", Long.class);

    public QLectureItemEntity(String variable) {
        super(LectureItemEntity.class, forVariable(variable));
    }

    public QLectureItemEntity(Path<? extends LectureItemEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QLectureItemEntity(PathMetadata metadata) {
        super(LectureItemEntity.class, metadata);
    }

}

