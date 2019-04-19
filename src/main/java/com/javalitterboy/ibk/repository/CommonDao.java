package com.javalitterboy.ibk.repository;

import com.javalitterboy.ibk.entity.BaseEntity;
import com.javalitterboy.ibk.entity.User;

import java.util.List;
import java.util.Map;

/**
 * 一些公用操作的dao
 *
 * @author huxiansheng
 */
public interface CommonDao {
    /**
     * 根据ID查找entity
     *
     * @param clazz 指定类
     * @param id 唯一id
     * @return 结果对象
     */
    public <T extends BaseEntity> T get(Class<T> clazz, Long id);

    /**
     * 保存entity
     *
     * @param entity 需要保存的对象
     */
    public <T extends BaseEntity> void save(T entity);

    /**
     * 使用指定的用户保存entity
     *
     * @param entity 需要保存的对象
     * @param user 操作者
     */
    public <T extends BaseEntity> void save(T entity, User user);


    /**
     * 删除
     *
     * @param entity 被删除对象
     */
    public <T extends BaseEntity> void delete(T entity);

    /**
     * 执行hql
     *
     * @param hql hql语句
     * @param map 条件键值对
     */
    public void execute(String hql, Map<String, Object> map);

    /**
     * 根据hql查找
     *
     * @param hql hql语句
     * @param paramKey 条件key
     * @param paramValue 条件value
     * @return 返回查询对象数组
     */
    public List<? extends Object> findByHql(String hql, String paramKey, Object paramValue);
    /**
     * 根据条件查询
     *
     * @param hql hql语句
     * @param condition 约束键值对
     * @return 返回查询对象数组
     */
    public List<? extends BaseEntity> findByHql(String hql, Map<String, Object> condition);

    /**
     * @param clazz 指定对象类
     * @param whereSub where条件
     * @param condition 约束键值对
     * @return 返回查询对象数组
     */
    public List<? extends BaseEntity> findByHql(Class<?> clazz, String whereSub, Map<String, Object> condition);

    /**
     * @param clazz 指定对象类
     * @param whereSub hql语句
     * @param paramKey 条件key
     * @param paramValue 条件value
     * @return 返回查询对象数组
     */
    public List<? extends BaseEntity> findByHql(Class<?> clazz, String whereSub, String paramKey, Object paramValue);


    /**
     * 根据hql查找唯一的结果，如果为空或者多余一条结果，则抛出异常
     *
     * @param hql hql语句
     * @param condition 约束键值对
     * @return 返回查询对象
     */
    public BaseEntity findUniqueByHql(String hql, Map<String, Object> condition);

    /**
     * 根据hql查找唯一的结果，如果为空或者多余一条结果，则抛出异常
     *
     * @param clazz     所要查询类的class
     * @param whereSub  查询字句。例如 obj.username = :username
     * @param condition 参数
     * @return 返回查询对象
     */
    public <T extends BaseEntity> T findUniqueByHql(Class<T> clazz, String whereSub, Map<String, Object> condition);

    /**
     * 根据hql查找唯一的结果，如果为空或者多余一条结果，则抛出异常
     *
     * @param hql hql语句
     * @param paramKey 条件key
     * @param paramValue 条件value
     * @return 返回查询对象
     */
    public Object findUniqueByHql(String hql, String paramKey, Object paramValue);

    /**
     * 根据hql查找唯一的结果，如果为空或者多余一条结果，则抛出异常
     *
     * @param clazz      所要查询类的class
     * @param whereSub   查询字句。例如 obj.username = :username
     * @param paramKey   参数key
     * @param paramValue 参数值
     * @return 返回查询对象
     */
    public <T extends BaseEntity> T findUniqueByHql(Class<T> clazz, String whereSub, String paramKey,
                                                    Object paramValue);
}
