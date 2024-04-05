package ru.javaguru.hibernate.dao;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ru.javaguru.hibernate.entity.BaseEntity;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class BaseRepository<K extends Serializable, E extends BaseEntity<K>>
        implements Repository<K, E> {

    private final Class<E> eClass;
    @Getter
    private final EntityManager entityManager;

    @Override
    public E save(E entity) {
        entityManager.persist(entity);
        return entity;
    }

    @Override
    public void delete(K id) {
        entityManager.remove(entityManager.find(eClass, id));
        entityManager.flush();
    }

    @Override
    public void update(E entity) {
        entityManager.merge(entity);
    }

    @Override
    public Optional<E> findById(K id) {
        return Optional.ofNullable(entityManager.find(eClass, id));
    }

    @Override
    public List<E> findAll() {
        var criteria = entityManager.getCriteriaBuilder().createQuery(eClass);
        criteria.from(eClass);
        return entityManager.createQuery(criteria).getResultList();
    }
}