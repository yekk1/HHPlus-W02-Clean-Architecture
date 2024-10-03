package com.sparta.hhplusw02cleanarchitecture.infrastructure.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QLectureHistoryEntity is a Querydsl query type for LectureHistoryEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QLectureHistoryEntity extends EntityPathBase<LectureHistoryEntity> {

    private static final long serialVersionUID = 603853956L;

    public static final QLectureHistoryEntity lectureHistoryEntity = new QLectureHistoryEntity("lectureHistoryEntity");

    public final DateTimePath<java.time.LocalDateTime> applied_at = createDateTime("applied_at", java.time.LocalDateTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> item_id = createNumber("item_id", Long.class);

    public final NumberPath<Long> lecture_id = createNumber("lecture_id", Long.class);

    public final NumberPath<Long> user_id = createNumber("user_id", Long.class);

    public QLectureHistoryEntity(String variable) {
        super(LectureHistoryEntity.class, forVariable(variable));
    }

    public QLectureHistoryEntity(Path<? extends LectureHistoryEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QLectureHistoryEntity(PathMetadata metadata) {
        super(LectureHistoryEntity.class, metadata);
    }

}

