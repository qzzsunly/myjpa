package cn.sexycode.myjpa.data.repository.query;

import cn.sexycode.myjpa.AvailableSettings;
import cn.sexycode.myjpa.query.MybatisQuery;
import cn.sexycode.util.core.properties.PropertiesUtil;
import org.springframework.data.jpa.repository.query.AbstractJpaQuery;
import org.springframework.data.jpa.repository.query.JpaParametersParameterAccessor;
import org.springframework.data.jpa.repository.query.JpaQueryMethod;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class MyjpaQueryImpl extends AbstractJpaQuery {
    /**
     * Creates a new {@link AbstractJpaQuery} from the given {@link JpaQueryMethod}.
     *
     * @param method
     * @param em
     */
    public MyjpaQueryImpl(JpaQueryMethod method, EntityManager em) {
        super(method, em);
    }

    @Override
    protected Query doCreateQuery(JpaParametersParameterAccessor accessor) {
        MybatisQuery query = this.getEntityManager().createNamedQuery(getQueryMethod().getNamedQueryName()).unwrap(
                MybatisQuery.class);
        query.setParameterValues(accessor.getValues());
        return query;
    }

    @Override
    protected Query doCreateCountQuery(JpaParametersParameterAccessor accessor) {
        EntityManager entityManager = getEntityManager();
        MybatisQuery namedQuery = this.getEntityManager().createNamedQuery(
                getQueryMethod().getNamedQueryName() + PropertiesUtil
                        .getString(AvailableSettings.MYBATIS_QUERY_COUNT_SUFFIX, entityManager.getProperties(),
                                AvailableSettings.Defaults.DEFAULT_MYBATIS_QUERY_COUNT_SUFFIX)).unwrap(
                MybatisQuery.class);
        namedQuery.setParameterValues(accessor.getValues());
        return namedQuery;
    }
}
