package cn.sexycode.myjpa.query;

import cn.sexycode.myjpa.session.Session;

import javax.persistence.*;
import java.util.*;

/**
 * @author Steve Ebersole
 */
public class MybatisQueryImpl<R> implements TypedQuery<R> {
	private final String queryString;
	private Session session;
	private Class<R> resultClass;

	public MybatisQueryImpl(
			Session session,
			String queryString) {
		this(session, queryString, null);
	}

	public MybatisQueryImpl(Session session, String qlString, Class<R> resultClass) {
		this.queryString = qlString;
		this.resultClass = resultClass;
		this.session = session;
	}


	public String getQueryString() {
		return queryString;
	}

	@Override
	public List<R> getResultList() {
		return null;
	}

	@Override
	public R getSingleResult() {
		return null;
	}

	@Override
	public int executeUpdate() {
		return 0;
	}

	@Override
	public TypedQuery<R> setMaxResults(int maxResult) {
		return null;
	}

	@Override
	public int getMaxResults() {
		return 0;
	}

	@Override
	public TypedQuery<R> setFirstResult(int startPosition) {
		return null;
	}

	@Override
	public int getFirstResult() {
		return 0;
	}

	@Override
	public TypedQuery<R> setHint(String hintName, Object value) {
		return null;
	}

	@Override
	public Map<String, Object> getHints() {
		return null;
	}

	@Override
	public <T> TypedQuery<R> setParameter(Parameter<T> param, T value) {
		return null;
	}

	@Override
	public TypedQuery<R> setParameter(Parameter<Calendar> param, Calendar value, TemporalType temporalType) {
		return null;
	}

	@Override
	public TypedQuery<R> setParameter(Parameter<Date> param, Date value, TemporalType temporalType) {
		return null;
	}

	@Override
	public TypedQuery<R> setParameter(String name, Object value) {
		return null;
	}

	@Override
	public TypedQuery<R> setParameter(String name, Calendar value, TemporalType temporalType) {
		return null;
	}

	@Override
	public TypedQuery<R> setParameter(String name, Date value, TemporalType temporalType) {
		return null;
	}

	@Override
	public TypedQuery<R> setParameter(int position, Object value) {
		return null;
	}

	@Override
	public TypedQuery<R> setParameter(int position, Calendar value, TemporalType temporalType) {
		return null;
	}

	@Override
	public TypedQuery<R> setParameter(int position, Date value, TemporalType temporalType) {
		return null;
	}

	@Override
	public Set<Parameter<?>> getParameters() {
		return null;
	}

	@Override
	public Parameter<?> getParameter(String name) {
		return null;
	}

	@Override
	public <T> Parameter<T> getParameter(String name, Class<T> type) {
		return null;
	}

	@Override
	public Parameter<?> getParameter(int position) {
		return null;
	}

	@Override
	public <T> Parameter<T> getParameter(int position, Class<T> type) {
		return null;
	}

	@Override
	public boolean isBound(Parameter<?> param) {
		return false;
	}

	@Override
	public <T> T getParameterValue(Parameter<T> param) {
		return null;
	}

	@Override
	public Object getParameterValue(String name) {
		return null;
	}

	@Override
	public Object getParameterValue(int position) {
		return null;
	}

	@Override
	public TypedQuery<R> setFlushMode(FlushModeType flushMode) {
		return null;
	}

	@Override
	public FlushModeType getFlushMode() {
		return null;
	}

	@Override
	public TypedQuery<R> setLockMode(LockModeType lockMode) {
		return null;
	}

	@Override
	public LockModeType getLockMode() {
		return null;
	}

	@Override
	public <T> T unwrap(Class<T> cls) {
		return null;
	}
}