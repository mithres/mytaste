package com.vc.core.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.red5.logging.Red5LoggerFactory;
import org.slf4j.Logger;
import org.springframework.orm.jpa.JpaCallback;
import org.springframework.orm.jpa.support.JpaDaoSupport;
import org.springframework.util.Assert;

/**
 * Generic Base DAO
 * 
 * @author ammen
 */
public class GenericDAO<T, PK extends Serializable> extends JpaDaoSupport {

    protected final Logger log = Red5LoggerFactory.getLogger(getClass(), "VideoConference");

    protected Class<T> entityClass;

    protected String className;

    @SuppressWarnings("unchecked")
    public GenericDAO() {
        this.entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        className = entityClass.getSimpleName();
    }

    public void create(T entity) {
        Assert.notNull(entity);
        if (logger.isDebugEnabled()) {
            logger.debug("saving {" + className + "} instance " + entity);
        }
        getJpaTemplate().persist(entity);
        if (logger.isDebugEnabled()) {
            logger.debug("save successful");
        }
    }

    public T update(T entity) {
        Assert.notNull(entity);
        if (logger.isDebugEnabled()) {
            logger.debug("updating {" + className + "} instance ");
        }
        T result = getJpaTemplate().merge(entity);
        if (logger.isDebugEnabled()) {
            logger.debug("update successful");
        }
        return result;
    }

    public void delete(T entity) {
        Assert.notNull(entity);
        logger.debug("deleting {" + className + "} instance ");
        getJpaTemplate().remove(entity);
        logger.debug("delete successful");
    }

    public void delete(final PK id) {
        Assert.notNull(id);
        if (logger.isDebugEnabled()) {
            logger.debug("deleting {" + className + "} instance ");
        }
        getJpaTemplate().remove(findById(id));
        if (logger.isDebugEnabled()) {
            logger.debug("delete successful");
        }
    }
    
    /**
     * @return Find entity by id if not found return null
     */
    public T findById(final PK id) {
        Assert.notNull(id);
        if (logger.isDebugEnabled()) {
            logger.debug("finding {" + entityClass + "} instance with id: {" + id + "}");
        }
        return getJpaTemplate().find(entityClass, id);
    }
    
    /**
     * Return a proxy object of the entity with the gaven id.
     * @param id
     * @return
     */
    public T findReferenceById(final PK id){
    	 Assert.notNull(id);
    	 if (logger.isDebugEnabled()) {
             logger.debug("Get the reference of {" + entityClass + "} with id: {" + id + "}");
         }
         return getJpaTemplate().getReference(entityClass, id);
    }
    
    /**
     * find object by object properties
     * 
     * @param propertyName
     *            property name
     * @param value
     *            property value
     * @param rowStartIdxAndCount
     * 
     */
    @SuppressWarnings("unchecked")
    public List<T> findByProperty(final String propertyName, final Object value, final Hints hints) {
        Assert.hasLength(propertyName);
        logger.info("finding {" + className + "} instance with property: {" + propertyName + "} , value: {" + value
                + "}");

        final String queryString = "select model from " + className + " model where model." + propertyName + "= ?1";
        return findPaged(queryString, hints, value);
    }

    public T findUniqueByProperty(final String propertyName, final Object value, final Hints hints) {
        return uniqueResult(findByProperty(propertyName, value, hints));
    }

    /**
     * Find all objects
     */
    @SuppressWarnings("unchecked")
    public List<T> findAll(final Hints hints) {
        logger.info("finding all {" + className + "} instances");
        final String queryString = "select model from " + className + " model";
        return findPaged(queryString, hints);
    }
    
    /**
     * use query string to find data
     * 
     * @param values
     * 
     */
    @SuppressWarnings("unchecked")
    public List find(final String queryString, final Hints hints, final Object... values) {

        Assert.hasLength(queryString);
        return getJpaTemplate().executeFind(new JpaCallback() {
            public Object doInJpa(EntityManager em) {
                Query query = em.createQuery(queryString);

                if (hints.getHints() != null) {
                    Hints.applyHintsTo(query, hints.getHints());
                }

                if (values != null) {
                    for (int i = 0; i < values.length; i++)
                        query.setParameter(i + 1, values[i]);
                }
                if (hints.getOffset() > 0)
                    query.setFirstResult(hints.getOffset());
                if (hints.getLength() > 0)
                    query.setMaxResults(hints.getLength());
                
                return query.getResultList();
            }
        });

    }

    @SuppressWarnings("unchecked")
    public List findByNamedParams(final String queryString, final Map<String, ?> params) {
        Assert.hasLength(queryString);
        Assert.notEmpty(params);
        return getJpaTemplate().findByNamedParams(queryString, params);
    }

    /**
     * Paged find method
     * 
     * @param hnts
     *            TODO
     * @param values
     */
    @SuppressWarnings("unchecked")
    public List<T> findPaged(final String queryString, final Hints hnts, final Object... values) {

        return getJpaTemplate().executeFind(new JpaCallback() {
            public Object doInJpa(EntityManager em) {
                Query query = em.createQuery(queryString);
                
                
                if (hnts.getHints() != null) {
                    Hints.applyHintsTo(query, hnts.getHints());
                }

                if (values != null) {
                    for (int i = 0; i < values.length; i++)
                        query.setParameter(i + 1, values[i]);
                }

                if (hnts.getOffset() > 0) {
                    query.setFirstResult(hnts.getOffset());
                }
                if (hnts.getLength() > 0) {
                    query.setMaxResults(hnts.getLength());
                }
                

                return query.getResultList();
            }
        });
    }

    /**
     * Paged find method
     * 
     * @param hnts
     *            TODO
     * @param values
     */
    @SuppressWarnings("unchecked")
    public List<T> findPagedNative(final String queryString, final Class<T> clazz, final Hints hnts, final Object... values) {

        return getJpaTemplate().executeFind(new JpaCallback() {
            public Object doInJpa(EntityManager em) {
                Query query = em.createNativeQuery(queryString, clazz);

                if (hnts.getHints() != null) {
                    Hints.applyHintsTo(query, hnts.getHints());
                }

                if (values != null) {
                    for (int i = 0; i < values.length; i++)
                        query.setParameter(i + 1, values[i]);
                }

                if (hnts.getOffset() > 0) {
                    query.setFirstResult(hnts.getOffset());
                }
                if (hnts.getLength() > 0) {
                    query.setMaxResults(hnts.getLength());
                }

                return query.getResultList();
            }
        });
    }
    /**
     * find all row number
     * 
     * @param countHQL
     * @param values
     * @return
     */
    public Long findRowCount(final String countHQL, final Object... values) {

        return (Long) getJpaTemplate().execute(new JpaCallback() {
            public Object doInJpa(EntityManager em) {

                Query query = em.createQuery(countHQL);

                if (values != null)
                    for (int i = 0; i < values.length; i++)
                        query.setParameter(i + 1, values[i]);
                /*
                 * Replaced query.getSingleResult() because query such as select
                 * count(*) from urlinfo group by url may return EMPTY result
                 * set
                 */

                List<Number> result = query.getResultList();
                if (result == null || result.size() == 0) {
                    return 0l;
                } else {
                    return result.get(0);
                }
            }
        });
    }
    
    
    


    @SuppressWarnings("unchecked")
    public Object findUnique(final String queryString, final Hints hnts, final Object... values) {
        Assert.hasLength(queryString);
        return uniqueResult(find(queryString, hnts, values));
    }

    /**
     * Get the class of the entity
     */
    public Class<T> getEntityClass() {
        return entityClass;
    }

    /**
     * Get the first object in the collection return null if collection is empty
     * throw exception if collection size > 1
     */
    private static <E> E uniqueResult(Collection<E> results) {
        if (results == null || results.isEmpty())
            return null;
        if (results.size() > 1)
            throw new IllegalArgumentException("the Collection size is larger than 1");
        return results.iterator().next();
    }


    public List nativeQuery(final String queryString, final Hints hnts) {
        List list = getJpaTemplate().executeFind(new JpaCallback() {
            @Override
            public Object doInJpa(EntityManager em) throws PersistenceException {
                Query query = em.createNativeQuery(queryString);
                if (hnts.getHints() != null) {
                    Hints.applyHintsTo(query, hnts.getHints());
                }
                if (hnts.getOffset() > 0) {
                    query.setFirstResult(hnts.getOffset());
                }
                if (hnts.getLength() > 0) {
                    query.setMaxResults(hnts.getLength());
                }
                
                em.flush();
                // query.setFlushMode(FlushModeType.COMMIT);
                return query.getResultList();
            }
        });
        return list;

    }

    public Object nativeUpdate(final String queryString) {
        return getJpaTemplate().execute(new JpaCallback() {
            @Override
            public Object doInJpa(EntityManager em) throws PersistenceException {
                Query query = em.createNativeQuery(queryString);
                // em.flush();
                // query.setFlushMode(FlushModeType.COMMIT);
                return query.executeUpdate();
            }
        });

    }

    public List<T> nativeQueryByType(final String queryString, final Hints hnts) {
    	return nativeQueryByType(queryString,hnts);
    }
    
    /**
     * Native Query SQL Support for Specified Class
     * 
     * @author：gaoyibo<br>
     * @time：Aug 1, 2008<br>
     * @param <T>
     * @param queryString
     * @return
     */
    @SuppressWarnings({ "unchecked"})
	public List<T> nativeQueryByType(final String queryString, final Hints hnts, final Object... params) {
        return getJpaTemplate().executeFind(new JpaCallback() {
            @Override
            public Object doInJpa(EntityManager em) throws PersistenceException {
                Query query = em.createNativeQuery(queryString, entityClass);
                
                int i = 1;
                for (Object param : params) {
                	query.setParameter(i++, param);
                }
                
                if (hnts.getHints() != null) {
                    Hints.applyHintsTo(query, hnts.getHints());
                }

                if (hnts.getOffset() > 0) {
                    query.setFirstResult(hnts.getOffset());
                }
                if (hnts.getLength() > 0) {
                    query.setMaxResults(hnts.getLength());
                }
                
                em.flush();
                // query.setFlushMode(FlushModeType.COMMIT);
                return query.getResultList();
            }
        });
    }

}
