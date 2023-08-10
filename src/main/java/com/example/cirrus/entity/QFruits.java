package com.example.cirrus.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QFruits is a Querydsl query type for Fruits
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFruits extends EntityPathBase<Fruits> {

    private static final long serialVersionUID = -111006575L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QFruits fruits = new QFruits("fruits");

    public final StringPath countryOfOrigin = createString("countryOfOrigin");

    public final QFruitSeller fruitSeller;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public final NumberPath<Float> price = createNumber("price", Float.class);

    public QFruits(String variable) {
        this(Fruits.class, forVariable(variable), INITS);
    }

    public QFruits(Path<? extends Fruits> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QFruits(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QFruits(PathMetadata metadata, PathInits inits) {
        this(Fruits.class, metadata, inits);
    }

    public QFruits(Class<? extends Fruits> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.fruitSeller = inits.isInitialized("fruitSeller") ? new QFruitSeller(forProperty("fruitSeller")) : null;
    }

}

