/*
 * Hibernate, Relational Persistence for Idiomatic Java
 *
 * License: GNU Lesser General Public License (LGPL), version 2.1 or later.
 * See the lgpl.txt file in the root directory or <http://www.gnu.org/licenses/lgpl-2.1.html>.
 */
package cn.sexycode.myjpa.query.criteria.internal.predicate;

import cn.sexycode.myjpa.query.criteria.internal.CriteriaBuilderImpl;
import cn.sexycode.myjpa.query.criteria.internal.ParameterContainer;
import cn.sexycode.myjpa.query.criteria.internal.ParameterRegistry;
import cn.sexycode.myjpa.query.criteria.internal.compile.RenderingContext;
import cn.sexycode.myjpa.query.criteria.internal.expression.ExpressionImpl;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Steve Ebersole
 */
public class NegatedPredicateWrapper extends ExpressionImpl<Boolean> implements PredicateImplementor, Serializable {
    private final PredicateImplementor predicate;

    private final BooleanOperator negatedOperator;

    private final List<Expression<Boolean>> negatedExpressions;

    @SuppressWarnings("unchecked")
    public NegatedPredicateWrapper(PredicateImplementor predicate) {
        super(predicate.criteriaBuilder(), Boolean.class);
        this.predicate = predicate;
        this.negatedOperator = predicate.isJunction()
                ? CompoundPredicate.reverseOperator(predicate.getOperator())
                : predicate.getOperator();
        this.negatedExpressions = negateCompoundExpressions(predicate.getExpressions(), predicate.criteriaBuilder());
    }

    private static List<Expression<Boolean>> negateCompoundExpressions(List<Expression<Boolean>> expressions,
            CriteriaBuilderImpl criteriaBuilder) {
        if (expressions == null || expressions.isEmpty()) {
            return Collections.emptyList();
        }

        final List<Expression<Boolean>> negatedExpressions = new ArrayList<Expression<Boolean>>();
        for (Expression<Boolean> expression : expressions) {
            if (Predicate.class.isInstance(expression)) {
                negatedExpressions.add(((Predicate) expression).not());
            } else {
                negatedExpressions.add(criteriaBuilder.not(expression));
            }
        }
        return negatedExpressions;
    }

    @Override
    public BooleanOperator getOperator() {
        return negatedOperator;
    }

    @Override
    public boolean isJunction() {
        return predicate.isJunction();
    }

    @Override
    public boolean isNegated() {
        return !predicate.isNegated();
    }

    @Override
    public List<Expression<Boolean>> getExpressions() {
        return negatedExpressions;
    }

    @Override
    public Predicate not() {
        return new NegatedPredicateWrapper(this);
    }

    @Override
    public void registerParameters(ParameterRegistry registry) {
        if (ParameterContainer.class.isInstance(predicate)) {
            ((ParameterContainer) predicate).registerParameters(registry);
        }
    }

    @Override
    public String render(boolean isNegated, RenderingContext renderingContext) {
        if (isJunction()) {
            return CompoundPredicate.render(this, renderingContext);
        } else {
            return predicate.render(isNegated, renderingContext);
        }
    }

    @Override
    public String render(RenderingContext renderingContext) {
        return render(isNegated(), renderingContext);
    }
}