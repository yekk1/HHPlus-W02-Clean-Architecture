package com.sparta.hhplusw02cleanarchitecture.infrastructure.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QLectureInventoryEntity is a Querydsl query type for LectureInventoryEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QLectureInventoryEntity extends EntityPathBase<LectureInventoryEntity> {

    private static final long serialVersionUID = -106329076L;

    public static final QLectureInventoryEntity lectureInventoryEntity = new QLectureInventoryEntity("lectureInventoryEntity");

    public final NumberPath<Integer> amount = createNumber("amount", Integer.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> item_id = createNumber("item_id", Long.class);

    public final NumberPath<Long> lecture_id = createNumber("lecture_id", Long.class);

    public QLectureInventoryEntity(String variable) {
        super(LectureInventoryEntity.class, forVariable(variable));
    }

    public QLectureInventoryEntity(Path<? extends LectureInventoryEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QLectureInventoryEntity(PathMetadata metadata) {
        super(LectureInventoryEntity.class, metadata);
    }

}

