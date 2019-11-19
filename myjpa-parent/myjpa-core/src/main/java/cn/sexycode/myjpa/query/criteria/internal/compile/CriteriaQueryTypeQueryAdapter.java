package cn.sexycode.myjpa.query.criteria.internal.compile;

import cn.sexycode.myjpa.session.Session;
import cn.sexycode.sql.mapping.LockMode;
import cn.sexycode.sql.mapping.LockOptions;
import cn.sexycode.sql.model.RowSelection;

import javax.persistence.*;
import javax.persistence.criteria.ParameterExpression;
import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZonedDateTime;
import java.util.*;
import java.util.stream.Stream;

/**
 * <strong>Make this go away in 6.0</strong> :)
 * <p/>
 * Needed because atm we render a JPA Criteria query into a HQL/JPQL query String and some metadata, and then
 * compile into a Query.  This class wraps the compiled HQL/JPQL query and adds an extra layer of metadata.
 * <p/>
 * But the move to SQM in 6.0 allows us to do away with the "wrapping".
 * <p>
 * Essentially
 *
 * @author Steve Ebersole
 */
public class CriteriaQueryTypeQueryAdapter<X> implements TypedQuery<X> {
	private final Session entityManager;
	private final TypedQuery<X> jpqlQuery;
	private final Map<ParameterExpression<?>, ExplicitParameterInfo<?>> explicitParameterInfoMap;

	public CriteriaQueryTypeQueryAdapter(
            Session entityManager,
            TypedQuery<X> jpqlQuery,
			Map<ParameterExpression<?>, ExplicitParameterInfo<?>> explicitParameterInfoMap) {
		this.entityManager = entityManager;
		this.jpqlQuery = jpqlQuery;
		this.explicitParameterInfoMap = explicitParameterInfoMap;
	}
    @Override
	public List<X> getResultList() {
		return jpqlQuery.getResultList();
	}

	/*@Override
	public X uniqueResult() {
		return jpqlQuery.uniqueResult();
	}

	@Override
	public Optional<X> uniqueResultOptional() {
		return jpqlQuery.uniqueResultOptional();
	}

	@Override
	public Stream<X> stream() {
		return jpqlQuery.stream();
	}

	@Override
	public List<X> list() {
		return jpqlQuery.list();
	}*/

	/*@Override
	public TypedQuery<X> setCacheMode(CacheMode cacheMode) {
		jpqlQuery.setCacheMode( cacheMode );
		return this;
	}

	@Override
	public boolean isCacheable() {
		return jpqlQuery.isCacheable();
	}*/

	@Override
    public X getSingleResult() {
		return jpqlQuery.getSingleResult();
	}

	/*@Override
	public ParameterMetadata getParameterMetadata() {
		return jpqlQuery.getParameterMetadata();
	}

	@Override
	public String[] getNamedParameters() {
		return jpqlQuery.getNamedParameters();
	}*/
    @Override
	public int getMaxResults() {
		return jpqlQuery.getMaxResults();
	}
    @Override
	public TypedQuery<X> setMaxResults(int maxResult) {
		jpqlQuery.setMaxResults( maxResult );
		return this;
	}
    @Override
	public int getFirstResult() {
		return jpqlQuery.getFirstResult();
	}
    @Override
	public TypedQuery<X> setFirstResult(int i) {
		jpqlQuery.setFirstResult( i );
		return this;
	}
    @Override
	public Map<String, Object> getHints() {
		return jpqlQuery.getHints();
	}

	@Override
    public TypedQuery<X> setHint(String name, Object value) {
		jpqlQuery.setHint( name, value );
		return this;
	}

	/*@Override
	public QueryImplementor<X> applyGraph(RootGraph graph, GraphSemantic semantic) {
		jpqlQuery.applyGraph( graph, semantic );
		return this;
	}*/

	protected boolean isNativeQuery() {
		return false;
	}

	/*@Override
	public String getQueryString() {
		return jpqlQuery.getQueryString();
	}

	@Override
	public FlushMode getHibernateFlushMode() {
		return jpqlQuery.getHibernateFlushMode();
	}*/

	@Override
	public FlushModeType getFlushMode() {
		return jpqlQuery.getFlushMode();
	}

/*
	@Override
	public CacheMode getCacheMode() {
		return jpqlQuery.getCacheMode();
	}

	@Override
	public Type[] getReturnTypes() {
		return jpqlQuery.getReturnTypes();
	}

	@Override
	public LockOptions getLockOptions() {
		return jpqlQuery.getLockOptions();
	}

	@Override
	public RowSelection getQueryOptions() {
		return jpqlQuery.getQueryOptions();
	}
*/

	@Override
	public TypedQuery<X> setFlushMode(FlushModeType flushModeType) {
		jpqlQuery.setFlushMode( flushModeType );
		return this;
	}

/*	@Override
	public TypedQuery setFlushMode(FlushMode flushMode) {
		jpqlQuery.setFlushMode( flushMode );
		return this;
	}

	@Override
	public QueryImplementor<X> setHibernateFlushMode(FlushMode flushMode) {
		jpqlQuery.setHibernateFlushMode( flushMode );
		return this;
	}

	@Override
	public QueryImplementor setCacheable(boolean cacheable) {
		jpqlQuery.setCacheable( cacheable );
		return this;
	}

	@Override
	public String getCacheRegion() {
		return jpqlQuery.getCacheRegion();
	}

	@Override
	public QueryImplementor setCacheRegion(String cacheRegion) {
		jpqlQuery.setCacheRegion( cacheRegion );
		return this;
	}

	@Override
	public Integer getTimeout() {
		return jpqlQuery.getTimeout();
	}

	@Override
	public QueryImplementor setTimeout(int timeout) {
		jpqlQuery.setTimeout( timeout );
		return this;
	}

	@Override
	public Integer getFetchSize() {
		return jpqlQuery.getFetchSize();
	}

	@Override
	public QueryImplementor setLockOptions(LockOptions lockOptions) {
		jpqlQuery.setLockOptions( lockOptions );
		return this;
	}

	@Override
	public QueryImplementor setLockMode(String alias, LockMode lockMode) {
		jpqlQuery.setLockMode( alias, lockMode );
		return this;
	}

	@Override
	public String getComment() {
		return jpqlQuery.getComment();
	}

	@Override
	public QueryImplementor setComment(String comment) {
		jpqlQuery.setComment( comment );
		return this;
	}

	@Override
	public QueryImplementor addQueryHint(String hint) {
		jpqlQuery.addQueryHint( hint );
		return this;
	}

	@Override
	public Iterator<X> iterate() {
		return jpqlQuery.iterate();
	}

	@Override
	public ScrollableResults scroll() {
		return jpqlQuery.scroll();
	}

	@Override
	public ScrollableResults scroll(ScrollMode scrollMode) {
		return jpqlQuery.scroll( scrollMode );
	}

	@Override
	public QueryImplementor setFetchSize(int fetchSize) {
		jpqlQuery.setFetchSize( fetchSize );
		return this;
	}

	@Override
	public boolean isReadOnly() {
		return jpqlQuery.isReadOnly();
	}*/

	@Override
	public LockModeType getLockMode() {
		return jpqlQuery.getLockMode();
	}

	@Override
	public TypedQuery<X> setLockMode(LockModeType lockModeType) {
		jpqlQuery.setLockMode( lockModeType );
		return this;
	}

	/*@Override
	public QueryImplementor setReadOnly(boolean readOnly) {
		jpqlQuery.setReadOnly( readOnly );
		return this;
	}

	@Override
	public Type determineProperBooleanType(int position, Object value, Type defaultType) {
		return jpqlQuery.determineProperBooleanType( position, value, defaultType );
	}

	@Override
	public Type determineProperBooleanType(String name, Object value, Type defaultType) {
		return jpqlQuery.determineProperBooleanType( name, value, defaultType );
	}

	@Override
	public String[] getReturnAliases() {
		return jpqlQuery.getReturnAliases();
	}*/

	@Override
	@SuppressWarnings({ "unchecked" })
	public Set<Parameter<?>> getParameters() {
//		//entityManager.checkOpen( false );
		return new HashSet( explicitParameterInfoMap.values() );
	}

	@Override
	public boolean isBound(Parameter<?> param) {
//		//entityManager.checkOpen( false );
		final ExplicitParameterInfo<?> parameterInfo = resolveParameterInfo( param );
		Parameter<?> jpqlParameter;
		if ( parameterInfo.isNamed() ) {
			jpqlParameter = jpqlQuery.getParameter( parameterInfo.getName() );
		}
		else {
			jpqlParameter = jpqlQuery.getParameter( parameterInfo.getPosition() );
		}
		return jpqlQuery.isBound( jpqlParameter );
	}

	@Override
	@SuppressWarnings({ "unchecked" })
	public <T> T getParameterValue(Parameter<T> param) {
//		//entityManager.checkOpen( false );
		final ExplicitParameterInfo<?> parameterInfo = resolveParameterInfo( param );
		if ( parameterInfo.isNamed() ) {
			return ( T ) jpqlQuery.getParameterValue( parameterInfo.getName() );
		}
		else {
			return ( T ) jpqlQuery.getParameterValue( parameterInfo.getPosition() );
		}
	}

	private <T> ExplicitParameterInfo<?> resolveParameterInfo(Parameter<T> param) {
		if ( ExplicitParameterInfo.class.isInstance( param ) ) {
			return (ExplicitParameterInfo<?>) param;
		}
		else if ( ParameterExpression.class.isInstance( param ) ) {
			return explicitParameterInfoMap.get( param );
		}
		else {
			for ( ExplicitParameterInfo<?> parameterInfo : explicitParameterInfoMap.values() ) {
				if ( param.getName() != null && param.getName().equals( parameterInfo.getName() ) ) {
					return parameterInfo;
				}
				else if ( param.getPosition() != null && param.getPosition().equals( parameterInfo.getPosition() ) ) {
					return parameterInfo;
				}
			}
		}
		throw new IllegalArgumentException( "Unable to locate parameter [" + param + "] in query" );
	}

	@Override
	public <T> TypedQuery<X> setParameter(Parameter<T> param, T t) {
//		//entityManager.checkOpen( false );
		final ExplicitParameterInfo<?> parameterInfo = resolveParameterInfo( param );
		if ( parameterInfo.isNamed() ) {
			jpqlQuery.setParameter( parameterInfo.getName(), t );
		}
		else {
			jpqlQuery.setParameter( parameterInfo.getPosition(), t );
		}
		return this;
	}

	@Override
	public TypedQuery<X> setParameter(Parameter<Calendar> param, Calendar calendar, TemporalType temporalType) {
//		//entityManager.checkOpen( false );
		final ExplicitParameterInfo<?> parameterInfo = resolveParameterInfo( param );
		if ( parameterInfo.isNamed() ) {
			jpqlQuery.setParameter( parameterInfo.getName(), calendar, temporalType );
		}
		else {
			jpqlQuery.setParameter( parameterInfo.getPosition(), calendar, temporalType );
		}
		return this;
	}

	@Override
	public TypedQuery<X> setParameter(Parameter<Date> param, Date date, TemporalType temporalType) {
//		//entityManager.checkOpen( false );
		final ExplicitParameterInfo<?> parameterInfo = resolveParameterInfo( param );
		if ( parameterInfo.isNamed() ) {
			jpqlQuery.setParameter( parameterInfo.getName(), date, temporalType );
		}
		else {
			jpqlQuery.setParameter( parameterInfo.getPosition(), date, temporalType );
		}
		return this;
	}

	@Override
	public <T> T unwrap(Class<T> cls) {
		return jpqlQuery.unwrap( cls );
	}

	@Override
	public Object getParameterValue(String name) {
//		//entityManager.checkOpen( false );
		locateParameterByName( name );
		return jpqlQuery.getParameterValue( name );
	}

	private ExplicitParameterInfo<?> locateParameterByName(String name) {
		for ( ExplicitParameterInfo<?> parameterInfo : explicitParameterInfoMap.values() ) {
			if ( parameterInfo.isNamed() && parameterInfo.getName().equals( name ) ) {
				return parameterInfo;
			}
		}
		throw new IllegalArgumentException( "Unable to locate parameter registered with that name [" + name + "]" );
	}

	private ExplicitParameterInfo<?> locateParameterByPosition(int position) {
		for ( ExplicitParameterInfo<?> parameterInfo : explicitParameterInfoMap.values() ) {
			if ( parameterInfo.getPosition() == position ) {
				return parameterInfo;
			}
		}
		throw new IllegalArgumentException( "Unable to locate parameter registered at position [" + position + "]" );
	}

	@Override
    public Parameter<?> getParameter(String name) {
//		//entityManager.checkOpen( false );
		return locateParameterByName( name );
	}

	@Override
	@SuppressWarnings({ "unchecked" })
	public <T> Parameter<T> getParameter(String name, Class<T> type) {
		//entityManager.checkOpen( false );
		Parameter parameter = locateParameterByName( name );
		if ( type.isAssignableFrom( parameter.getParameterType() ) ) {
			return parameter;
		}
		throw new IllegalArgumentException(
				"Named parameter [" + name + "] type is not assignanle to request type ["
						+ type.getName() + "]"
		);
	}

	@Override
	public TypedQuery<X> setParameter(String name, Object value) {
		//entityManager.checkOpen( true );
		ExplicitParameterInfo<?> parameterInfo = locateParameterByName( name );
		parameterInfo.validateBindValue( value );
		jpqlQuery.setParameter( name, value );
		return this;
	}

	@Override
	public TypedQuery<X> setParameter(String name, Calendar calendar, TemporalType temporalType) {
		//entityManager.checkOpen( true );
		ExplicitParameterInfo<?> parameterInfo = locateParameterByName( name );
		parameterInfo.validateCalendarBind();
		jpqlQuery.setParameter( name, calendar, temporalType );
		return this;
	}

	@Override
	public TypedQuery<X> setParameter(String name, Date date, TemporalType temporalType) {
		//entityManager.checkOpen( true );
		ExplicitParameterInfo<?> parameterInfo = locateParameterByName( name );
		parameterInfo.validateDateBind();
		jpqlQuery.setParameter( name, date, temporalType );
		return this;
	}

	/*@Override
	public TypedQuery<X> setEntity(String name, Object val) {
		//entityManager.checkOpen( false );
		ExplicitParameterInfo<?> parameterInfo = locateParameterByName( name );
		parameterInfo.validateBindValue( val );
		jpqlQuery.setEntity( name, val );
		return this;
	}

	@Override
	public QueryImplementor<X> setParameter(String name, Object val, Type type) {
		//entityManager.checkOpen( false );
		ExplicitParameterInfo<?> parameterInfo = locateParameterByName( name );
		parameterInfo.validateBindValue( val );
		jpqlQuery.setParameter( name, val, type );
		return this;
	}

	@Override
	public <T> QueryImplementor<X> setParameter(QueryParameter<T> parameter, T val) {
		//entityManager.checkOpen( false );
		final ExplicitParameterInfo<?> parameterInfo = resolveParameterInfo( parameter );
		parameterInfo.validateBindValue( val );
		if ( parameterInfo.isNamed() ) {
			jpqlQuery.setParameter( parameterInfo.getName(), val );
		}
		else {
			jpqlQuery.setParameter( parameterInfo.getPosition(), val );
		}
		return this;
	}

	@Override
	public <P> QueryImplementor<X> setParameter(
			QueryParameter<P> parameter, P val, TemporalType temporalType) {
		//entityManager.checkOpen( false );
		final ExplicitParameterInfo<?> parameterInfo = resolveParameterInfo( parameter );
		if ( parameterInfo.isNamed() ) {
			jpqlQuery.setParameter( parameterInfo.getName(), val, temporalType );
		}
		else {
			jpqlQuery.setParameter( parameterInfo.getPosition(), val, temporalType );
		}
		return this;
	}

	@Override
	public <P> QueryImplementor<X> setParameter(String name, P val, TemporalType temporalType) {
		//entityManager.checkOpen( false );
		locateParameterByName( name );
		jpqlQuery.setParameter( name, val, temporalType );
		return this;
	}

	@Override
	public <P> QueryImplementor<X> setParameterList(QueryParameter<P> parameter, Collection<P> values) {
		//entityManager.checkOpen( false );
		final ExplicitParameterInfo<?> parameterInfo = resolveParameterInfo( parameter );
		if ( parameterInfo.isNamed() ) {
			jpqlQuery.setParameter( parameterInfo.getName(), values );
		}
		else {
			jpqlQuery.setParameter( parameterInfo.getPosition(), values );
		}
		return this;
	}

	@Override
	public QueryImplementor<X> setParameterList(String name, Collection values) {
		//entityManager.checkOpen( false );
		locateParameterByName( name );
		jpqlQuery.setParameter( name, values );
		return this;
	}

	@Override
	public Query<X> setParameterList(int position, Collection values) {
		//entityManager.checkOpen( false );
		locateParameterByPosition( position );
		jpqlQuery.setParameter( position, values );
		return this;
	}

	@Override
	public QueryImplementor<X> setParameterList(String name, Collection values, Type type) {
		//entityManager.checkOpen( false );
		locateParameterByName( name );
		jpqlQuery.setParameter( name, values, type );
		return this;
	}

	@Override
	public Query<X> setParameterList(int position, Collection values, Type type) {
		//entityManager.checkOpen( false );
		locateParameterByPosition( position );
		jpqlQuery.setParameter( position, values, type );
		return this;
	}

	@Override
	public QueryImplementor<X> setParameterList(String name, Object[] values, Type type) {
		//entityManager.checkOpen( false );
		locateParameterByName( name );
		jpqlQuery.setParameter( name, values, type );
		return this;
	}

	@Override
	public Query<X> setParameterList(int position, Object[] values, Type type) {
		//entityManager.checkOpen( false );
		locateParameterByPosition( position );
		jpqlQuery.setParameter( position, values, type );
		return this;
	}

	@Override
	public QueryImplementor<X> setParameterList(String name, Object[] values) {
		//entityManager.checkOpen( false );
		locateParameterByName( name );
		jpqlQuery.setParameter( name, values );
		return this;
	}

	@Override
	public Query<X> setParameterList(int position, Object[] values) {
		//entityManager.checkOpen( false );
		locateParameterByPosition( position );
		jpqlQuery.setParameter( position, values );
		return this;
	}

	@Override
	public <P> QueryImplementor<X> setParameter(QueryParameter<P> parameter, P value, Type type) {
		//entityManager.checkOpen( false );
		final ExplicitParameterInfo<?> parameterInfo = resolveParameterInfo( parameter );
		if ( parameterInfo.isNamed() ) {
			jpqlQuery.setParameter( parameterInfo.getName(), value, type );
		}
		else {
			jpqlQuery.setParameter( parameterInfo.getPosition(), value, type );
		}
		return this;
	}

	@Override
	public QueryImplementor<X> setParameter(Parameter<Instant> param, Instant value, TemporalType temporalType){
		//entityManager.checkOpen( false );
		final ExplicitParameterInfo<?> parameterInfo = resolveParameterInfo( param );
		if ( parameterInfo.isNamed() ) {
			jpqlQuery.setParameter( parameterInfo.getName(), value, temporalType );
		}
		else {
			jpqlQuery.setParameter( parameterInfo.getPosition(), value, temporalType );
		}
		return this;
	}

	@Override
	public QueryImplementor<X> setParameter(Parameter<LocalDateTime> param, LocalDateTime value, TemporalType temporalType){
		//entityManager.checkOpen( false );
		final ExplicitParameterInfo<?> parameterInfo = resolveParameterInfo( param );
		if ( parameterInfo.isNamed() ) {
			jpqlQuery.setParameter( parameterInfo.getName(), value, temporalType );
		}
		else {
			jpqlQuery.setParameter( parameterInfo.getPosition(), value, temporalType );
		}
		return this;
	}

	@Override
	public QueryImplementor<X> setParameter(Parameter<ZonedDateTime> param, ZonedDateTime value, TemporalType temporalType){
		//entityManager.checkOpen( false );
		final ExplicitParameterInfo<?> parameterInfo = resolveParameterInfo( param );
		if ( parameterInfo.isNamed() ) {
			jpqlQuery.setParameter( parameterInfo.getName(), value, temporalType );
		}
		else {
			jpqlQuery.setParameter( parameterInfo.getPosition(), value, temporalType );
		}
		return this;
	}

	@Override
	public QueryImplementor<X> setParameter(Parameter<OffsetDateTime> param, OffsetDateTime value, TemporalType temporalType){
		//entityManager.checkOpen( false );
		final ExplicitParameterInfo<?> parameterInfo = resolveParameterInfo( param );
		if ( parameterInfo.isNamed() ) {
			jpqlQuery.setParameter( parameterInfo.getName(), value, temporalType );
		}
		else {
			jpqlQuery.setParameter( parameterInfo.getPosition(), value, temporalType );
		}
		return this;
	}

	@Override
	public QueryImplementor<X> setParameter(String name, Instant value, TemporalType temporalType){
		//entityManager.checkOpen( false );
		locateParameterByName( name );
		jpqlQuery.setParameter( name, value, temporalType );
		return this;
	}

	@Override
	public QueryImplementor<X> setParameter(String name, LocalDateTime value, TemporalType temporalType){
		//entityManager.checkOpen( false );
		locateParameterByName( name );
		jpqlQuery.setParameter( name, value, temporalType );
		return this;
	}

	@Override
	public QueryImplementor<X> setParameter(String name, ZonedDateTime value, TemporalType temporalType){
		//entityManager.checkOpen( false );
		locateParameterByName( name );
		jpqlQuery.setParameter( name, value, temporalType );
		return this;
	}

	@Override
	public QueryImplementor<X> setParameter(String name, OffsetDateTime value, TemporalType temporalType){
		//entityManager.checkOpen( false );
		locateParameterByName( name );
		jpqlQuery.setParameter( name, value, temporalType );
		return this;
	}

	@Override
	public QueryImplementor<X> setResultTransformer(ResultTransformer transformer) {
		jpqlQuery.setResultTransformer( transformer );
		return this;
	}

	@Override
	public QueryImplementor<X> setProperties(Object bean) {
		jpqlQuery.setProperties( bean );
		return this;
	}

	@Override
	public QueryImplementor setProperties(Map map) {
		jpqlQuery.setProperties( map );
		return this;
	}

	@Override
	public QueryProducerImplementor getProducer() {
		return jpqlQuery.getProducer();
	}

	@Override
	public void setOptionalId(Serializable id) {
		jpqlQuery.setOptionalId( id );
	}

	@Override
	public void setOptionalEntityName(String entityName) {
		jpqlQuery.setOptionalEntityName( entityName );
	}

	@Override
	public void setOptionalObject(Object optionalObject) {
		jpqlQuery.setOptionalObject( optionalObject );
	}

	@Override
	public QueryImplementor<X> setParameter(int position, LocalDateTime value, TemporalType temporalType) {
		//entityManager.checkOpen( false );
		locateParameterByPosition( position );
		jpqlQuery.setParameter( position, value, temporalType );
		return this;
	}

	@Override
	public QueryImplementor<X> setParameter(int position, Instant value, TemporalType temporalType) {
		//entityManager.checkOpen( false );
		locateParameterByPosition( position );
		jpqlQuery.setParameter( position, value, temporalType );
		return this;
	}

	@Override
	public QueryImplementor<X> setParameter(int position, ZonedDateTime value, TemporalType temporalType) {
		//entityManager.checkOpen( false );
		locateParameterByPosition( position );
		jpqlQuery.setParameter( position, value, temporalType );
		return this;
	}

	@Override
	public QueryImplementor<X> setParameter(int position, OffsetDateTime value, TemporalType temporalType) {
		//entityManager.checkOpen( false );
		locateParameterByPosition( position );
		jpqlQuery.setParameter( position, value, temporalType );
		return this;
	}

	@Override
	public QueryImplementor<X> setParameter(int position, Object val, Type type) {
		//entityManager.checkOpen( false );
		locateParameterByPosition( position );
		jpqlQuery.setParameter( position, val, type );
		return this;
	}

	@Override
	public QueryImplementor<X> setEntity(int position, Object val) {
		//entityManager.checkOpen( false );
		locateParameterByPosition( position );
		jpqlQuery.setParameter( position, val );
		return this;
	}

	@Override
	public <P> QueryImplementor<X> setParameter(int position, P val, TemporalType temporalType) {
		//entityManager.checkOpen( false );
		locateParameterByPosition( position );
		jpqlQuery.setParameter( position, val, temporalType );
		return this;
	}*/

	// unsupported stuff ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

	@Override
	public int executeUpdate() {
		throw new IllegalStateException( "Typed criteria queries do not support executeUpdate" );
	}

	@Override
	public TypedQuery<X> setParameter(int i, Object o) {
		throw new IllegalArgumentException( "Criteria queries do not support positioned parameters" );
	}

	@Override
	public TypedQuery<X> setParameter(int i, Calendar calendar, TemporalType temporalType) {
		throw new IllegalArgumentException( "Criteria queries do not support positioned parameters" );
	}

	@Override
	public TypedQuery<X> setParameter(int i, Date date, TemporalType temporalType) {
		throw new IllegalArgumentException( "Criteria queries do not support positioned parameters" );
	}

	@Override
	public Object getParameterValue(int position) {
		throw new IllegalArgumentException( "Criteria queries do not support positioned parameters" );
	}

	@Override
	public Parameter<?> getParameter(int position) {
		throw new IllegalArgumentException( "Criteria queries do not support positioned parameters" );
	}

	@Override
    public <T> Parameter<T> getParameter(int position, Class<T> type) {
		throw new IllegalArgumentException( "Criteria queries do not support positioned parameters" );
	}
}