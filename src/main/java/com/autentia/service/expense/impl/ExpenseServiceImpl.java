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
import com.autentia.service.user.UserService;
import com.autentia.service.user.mapper.UserMapper;
import io.micronaut.transaction.annotation.ReadOnly;
import jakarta.inject.Singleton;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import static java.time.ZonedDateTime.now;
import static java.util.Comparator.comparing;
import static java.util.List.of;

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
        Expense expense = new Expense()
            .name(expenseRequest.name())
            .description(expenseRequest.description())
            .amount(expenseRequest.amount())
            .date(now());
        User user = userService.findByLogin(expenseRequest.creatorLogin());

        expense.setUser(user);
        expenseRepository.save(expense);

        groupService.addExpense(groupId, expense);

        return expenseMapper.toDto(expense);
    }

    @Override
    public List<ExpenseSummaryDTO> getExpenseSummaryByGroup(Long groupId) {
        List<Expense> groupExpenses = findGroupExpensesSortedByAmount(groupId);

        double totalAmount = totalAmountOf(groupExpenses);

        List<User> users = userService.findByGroupId(groupId);

        final Optional<User> userThatPaidTheMost = users.stream()
            .findFirst();

        if  (userThatPaidTheMost.isEmpty()) {
            return of();
        }

        final double amountPerUser = totalAmount / users.size();

        return users.stream()
            .filter(byUserLogin(userThatPaidTheMost.get().getLogin())) // Removes the user that have paid the most
            .map(user -> sumAllUserExpensesAndSubtractTheAmount(groupExpenses, amountPerUser, user, userThatPaidTheMost.get()))
            .toList();
    }

    private ExpenseSummaryDTO sumAllUserExpensesAndSubtractTheAmount(List<Expense> groupExpenses,
                                                                                        double amountPerUser,
                                                                                        User user,
                                                                                        User userThatPaidTheMost) {
        double userAmount = groupExpenses.stream()
            .filter(byLogin(user.getLogin()))
            .mapToDouble(Expense::getAmount)
            .sum();
        return new ExpenseSummaryDTO(userMapper.toDTO(user), (float) (amountPerUser - userAmount), userMapper.toDTO(userThatPaidTheMost));
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
