package com.autentia.repository.impl;

import com.autentia.domain.Expense;
import com.autentia.repository.ExpenseRepository;
import jakarta.inject.Singleton;

import javax.persistence.EntityManager;
import java.util.List;

@Singleton
public class ExpenseRepositoryImpl implements ExpenseRepository {

    private final EntityManager entityManager;

    public ExpenseRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Expense> findExpensesByGroupSortedByDateDesc(Long groupId) {
        return entityManager.createQuery("select e from Expense e where e.group.id = :groupId order by e.date desc", Expense.class)
            .setParameter("groupId", groupId)
            .getResultList();
    }
}
