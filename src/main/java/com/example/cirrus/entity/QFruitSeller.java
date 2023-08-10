package com.example.cirrus.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QFruitSeller is a Querydsl query type for FruitSeller
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFruitSeller extends EntityPathBase<FruitSeller> {

    private static final long serialVersionUID = 1873736257L;

    public static final QFruitSeller fruitSeller = new QFruitSeller("fruitSeller");

    public final StringPath firstName = createString("firstName");

    public final NumberPath<Long> fruitSellerId = createNumber("fruitSellerId", Long.class);

    public final StringPath lastName = createString("lastName");

    public final NumberPath<Integer> phoneNumber = createNumber("phoneNumber", Integer.class);

    public QFruitSeller(String variable) {
        super(FruitSeller.class, forVariable(variable));
    }

    public QFruitSeller(Path<? extends FruitSeller> path) {
        super(path.getType(), path.getMetadata());
    }

    public QFruitSeller(PathMetadata metadata) {
        super(FruitSeller.class, metadata);
    }

}

