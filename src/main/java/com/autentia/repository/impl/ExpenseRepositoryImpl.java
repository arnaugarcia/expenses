package com.autentia.repository.impl;

import com.autentia.domain.Expense;
import com.autentia.repository.ExpenseRepository;
import io.micronaut.transaction.annotation.ReadOnly;
import jakarta.inject.Singleton;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@Singleton
public class ExpenseRepositoryImpl implements ExpenseRepository {

    private final EntityManager entityManager;

    public ExpenseRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @ReadOnly
    public List<Expense> findExpensesByGroupSortedByDateDesc(Long groupId) {
        return entityManager.createQuery("select e from Expense e where e.group.id = :groupId order by e.date desc", Expense.class)
            .setParameter("groupId", groupId)
            .getResultList();
    }

    @Override
    @Transactional
    public Expense save(Expense expense) {
        entityManager.persist(expense);
        return expense;
    }
}
