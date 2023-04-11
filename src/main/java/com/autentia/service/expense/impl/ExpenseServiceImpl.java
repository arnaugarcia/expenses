package com.autentia.service.expense.impl;

import com.autentia.domain.Expense;
import com.autentia.domain.User;
import com.autentia.repository.ExpenseRepository;
import com.autentia.service.expense.ExpenseService;
import com.autentia.service.expense.dto.ExpenseDTO;
import com.autentia.service.expense.dto.ExpenseSummaryDTO;
import com.autentia.service.expense.mapper.ExpenseMapper;
import com.autentia.service.expense.request.ExpenseRequest;
import com.autentia.service.group.GroupService;
import com.autentia.service.group.dto.GroupDTO;
import com.autentia.service.user.UserService;
import com.autentia.service.user.mapper.UserMapper;
import io.micronaut.transaction.annotation.ReadOnly;
import jakarta.inject.Singleton;

import javax.transaction.Transactional;
import java.util.List;
import java.util.function.Predicate;

import static java.util.Comparator.comparing;

@Singleton
public class ExpenseServiceImpl implements ExpenseService {

    private final ExpenseRepository expenseRepository;

    private final ExpenseMapper expenseMapper;

    private final UserMapper userMapper;

    private final GroupService groupService;

    private final UserService userService;

    public ExpenseServiceImpl(ExpenseRepository expenseRepository,
                              ExpenseMapper expenseMapper,
                              UserMapper userMapper,
                              GroupService groupService,
                              UserService userService) {
        this.expenseRepository = expenseRepository;
        this.expenseMapper = expenseMapper;
        this.userMapper = userMapper;
        this.groupService = groupService;
        this.userService = userService;
    }

    @Override
    @ReadOnly
    public List<ExpenseDTO> findExpensesByGroup(Long groupId) {
        return expenseRepository.findExpensesByGroupSortedByDateDesc(groupId).stream()
            .map(expenseMapper::toDto)
            .toList();
    }

    @Override
    @Transactional
    public ExpenseDTO createExpense(Long groupId, ExpenseRequest expenseRequest) {
        groupService.findById(groupId); // Check if group exists
        Expense expense = expenseMapper.toEntity(expenseRequest);
        User user = userService.findByLogin(expenseRequest.creatorLogin());

        expense.setUser(user);
        expenseRepository.save(expense);

        groupService.addExpense(groupId, expense);

        return expenseMapper.toDto(expense);
    }

    @Override
    public ExpenseSummaryDTO getExpenseSummaryByGroup(Long groupId) {
        GroupDTO group = groupService.findById(groupId);
        List<Expense> groupExpenses = findGroupExpensesSortedByAmount(groupId);

        double totalAmount = totalAmountOf(groupExpenses);

        List<User> users = userService.findByGroupId(groupId);

        final User userThatPaidTheMost = users.stream()
            .findFirst()
            .orElseThrow();

        final double amountPerUser = totalAmount / users.size();

        List<ExpenseSummaryDTO.ExpenseUser> expenseUsers = users.stream()
            .filter(byUserLogin(userThatPaidTheMost.getLogin())) // Removes the user that have paid the most
            .map(user -> sumAllUserExpensesAndSubtractTheAmount(groupExpenses, amountPerUser, user, userThatPaidTheMost))
            .toList();
        return new ExpenseSummaryDTO(group.name(), group.description(), expenseUsers);
    }

    private ExpenseSummaryDTO.ExpenseUser sumAllUserExpensesAndSubtractTheAmount(List<Expense> groupExpenses,
                                                                                        double amountPerUser,
                                                                                        User user,
                                                                                        User userThatPaidTheMost) {
        double userAmount = groupExpenses.stream()
            .filter(byLogin(user.getLogin()))
            .mapToDouble(Expense::getAmount)
            .sum();
        return new ExpenseSummaryDTO.ExpenseUser(userMapper.toDTO(user), (float) (amountPerUser - userAmount), userMapper.toDTO(userThatPaidTheMost));
    }

    private static Predicate<Expense> byLogin(String login) {
        return expense -> expense.getUser().getLogin().equals(login);
    }

    private static Predicate<User> byUserLogin(String login) {
        return user -> !user.getLogin().equals(login);
    }

    private List<Expense> findGroupExpensesSortedByAmount(Long groupId) {
        return expenseRepository.findExpensesByGroup(groupId)
            .stream()
            .sorted(comparing(Expense::getAmount).reversed())
            .toList();
    }

    private static double totalAmountOf(List<Expense> groupExpenses) {
        return groupExpenses.stream()
            .mapToDouble(Expense::getAmount)
            .sum();
    }
}
