package com.querydsl.core.support;

import com.querydsl.core.QueryMetadata;
import com.querydsl.core.types.*;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;

/**
 *
 * @param <T>
 * @param <Q>
 */
public abstract class FetchableSubQueryBase<T, Q extends FetchableSubQueryBase<T, Q>> extends FetchableQueryBase<T, Q>
        implements ExtendedSubQuery<T> {


    private final SubQueryExpression<T> mixin;

    public FetchableSubQueryBase(QueryMixin<Q> queryMixin) {
        super(queryMixin);
        mixin = new SubQueryExpressionImpl<T>((Class)Object.class, queryMixin.getMetadata());
    }

    @Override
    public BooleanExpression contains(Expression<? extends T> right) {
        return Expressions.predicate(Ops.IN, right, this);
    }

    @Override
    public BooleanExpression contains(T constant) {
        return contains(Expressions.constant(constant));
    }

    @Override
    public BooleanExpression exists() {
        QueryMetadata metadata = getMetadata();
        if (metadata.getProjection() == null) {
            queryMixin.setProjection(Expressions.ONE);
        }
        return Expressions.predicate(Ops.EXISTS, this);
    }

    @Override
    public BooleanExpression eq(Expression<? extends T> expr) {
        return Expressions.predicate(Ops.EQ, this, expr);
    }

    @Override
    public BooleanExpression eq(T constant) {
        return eq(Expressions.constant(constant));
    }

    @Override
    public BooleanExpression ne(Expression<? extends T> expr) {
        return Expressions.predicate(Ops.NE, this, expr);
    }

    @Override
    public BooleanExpression ne(T constant) {
        return eq(Expressions.constant(constant));
    }


    @Override
    public BooleanExpression notExists() {
        return exists().not();
    }

    @Override
    public BooleanExpression lt(Expression<? extends T> expr) {
        return Expressions.predicate(Ops.LT, this, expr);
    }

    @Override
    public BooleanExpression lt(T constant) {
        return lt(Expressions.constant(constant));
    }

    @Override
    public BooleanExpression gt(Expression<? extends T> expr) {
        return Expressions.predicate(Ops.GT, this, expr);
    }

    @Override
    public BooleanExpression gt(T constant) {
        return gt(Expressions.constant(constant));
    }

    @Override
    public BooleanExpression loe(Expression<? extends T> expr) {
        return Expressions.predicate(Ops.LOE, this, expr);
    }

    @Override
    public BooleanExpression loe(T constant) {
        return loe(Expressions.constant(constant));
    }

    @Override
    public BooleanExpression goe(Expression<? extends T> expr) {
        return Expressions.predicate(Ops.GOE, this, expr);
    }

    @Override
    public BooleanExpression goe(T constant) {
        return goe(Expressions.constant(constant));
    }

    @Override
    public final int hashCode() {
        return mixin.hashCode();
    }

    @Override
    public final QueryMetadata getMetadata() {
        return queryMixin.getMetadata();
    }

    @Override
    public <R, C> R accept(Visitor<R, C> v, C context) {
        return mixin.accept(v, context);
    }

    @Override
    public Class<T> getType() {
        Expression<?> projection = queryMixin.getMetadata().getProjection();
        return (Class) (projection != null ? projection.getType() : Void.class);
    }
}
