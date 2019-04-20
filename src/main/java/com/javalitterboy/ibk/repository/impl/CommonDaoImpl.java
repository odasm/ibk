package com.javalitterboy.ibk.repository.impl;


import com.javalitterboy.ibk.entity.BaseEntity;
import com.javalitterboy.ibk.entity.User;
import com.javalitterboy.ibk.exception.ServiceException;
import com.javalitterboy.ibk.repository.CommonDao;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * @author 14183
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
@Repository
public class CommonDaoImpl implements CommonDao {

	private final EntityManager entityManager;

	public CommonDaoImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public BaseEntity get(Class clazz, Long id) {
		return (BaseEntity) entityManager.find(clazz, id);
	}

	@Override
	public void save(BaseEntity entity) {
//		Subject subject = SecurityUtils.getSubject();
//		User user = (User) subject.getSession().getAttribute(Constants.CURRENT_USER_IN_SESSION);
//		save(entity, user);
	}


	@Override
	public <T extends BaseEntity> void save(T entity, User user) {
		Date date = new Date();
		if (entity.isNew()) {
			entity.setCreateTime(date);
			entity.setCreateUserId(user.getId());
			entity.setCreateUserName(user.getName());
		}
		entity.setLastUpdateTime(date);
		entity.setLastUpdateUserId(user.getId());
		entity.setLastUpdateUserName(user.getName());
		entityManager.merge(entity);
	}

	@Override
	public <T extends BaseEntity> void delete(T entity) {
		entityManager.remove(entity);
	}

	@Override
	public void execute(String hql, Map<String, Object> map) {
		Query query = entityManager.createQuery(hql);
		if (null != map) {
			for (Entry<String, Object> entry : map.entrySet()) {
				query.setParameter(entry.getKey(), entry.getValue());
			}
		}
		query.executeUpdate();
	}

	@Override
	public List<? extends BaseEntity> findByHql(String hql, String paramKey, Object paramValue) {
		Query query = entityManager.createQuery(hql);
		if(null != paramKey){
			query.setParameter(paramKey, paramValue);
		}
		return query.getResultList();
	}


	@Override
	public List<? extends BaseEntity> findByHql(String hql, Map<String, Object> condition) {
		Query query = entityManager.createQuery(hql);
		for (Entry<String, Object> entry : condition.entrySet()) {
			query.setParameter(entry.getKey(), entry.getValue());
		}
		return query.getResultList();
	}

	@Override
	public List<? extends BaseEntity> findByHql(Class<?> clazz, String whereSub, Map<String, Object> condition) {
		String hql = "FROM " + clazz.getSimpleName() + " obj WHERE " + whereSub;
		return findByHql(hql, condition);
	}

	@Override
	public List<? extends BaseEntity> findByHql(Class<?> clazz, String whereSub, String paramKey, Object paramValue) {
		String hql = "FROM " + clazz.getSimpleName() + " obj WHERE " + whereSub;
		return findByHql(hql, paramKey,paramValue);
	}

	@Override
	public BaseEntity findUniqueByHql(String hql, String paramKey, Object paramValue) {
		List<BaseEntity> entitys = (List<BaseEntity>) findByHql(hql, paramKey, paramValue);
		if (entitys.size() > 1) {
			throw new ServiceException("查询结果不唯一");
		}
		if (entitys.size() == 0) {
			return null;
		}
		return entitys.get(0);
	}

	@Override
	public <T extends BaseEntity> T findUniqueByHql(Class<T> clazz, String whereSub, String paramKey, Object paramValue) {
		String hql = "FROM " + clazz.getSimpleName() + " obj WHERE " + whereSub;
		return (T) findUniqueByHql(hql, paramKey,paramValue);
	}

	@Override
	public BaseEntity findUniqueByHql(String hql, Map<String, Object> condition) {
		List<? extends BaseEntity> entitys = findByHql(hql, condition);
		if (entitys.size() > 1) {
			throw new ServiceException("查询结果不唯一");
		}
		if (entitys.size() == 0) {
			return null;
		}
		return entitys.get(0);
	}

	@Override
	public <T extends BaseEntity> T findUniqueByHql(Class<T> clazz, String whereSub, Map<String, Object> condition) {
		String hql = "FROM " + clazz.getSimpleName() + " obj WHERE " + whereSub;
		return (T) findUniqueByHql(hql, condition);
	}
}
