package com.autentia.domain;

import io.micronaut.serde.annotation.Serdeable;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Serdeable
@Table(name = "user")
public class User implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "surnames")
    private String surnames;

    @Column(name = "login")
    private String login;

    @OneToMany(mappedBy = "user")
    @ToString.Exclude
    private Set<Payment> payments = new HashSet<>();

    @OneToMany(mappedBy = "user")
    @ToString.Exclude
    private Set<Expense> expenses = new HashSet<>();

    @ManyToMany
    @JoinTable(
        name = "rel_user__group",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "group_id")
    )
    @ToString.Exclude
    private Set<Group> groups = new HashSet<>();

    public User id(Long id) {
        this.setId(id);
        return this;
    }

    public User name(String name) {
        this.setName(name);
        return this;
    }

    public User surnames(String surnames) {
        this.setSurnames(surnames);
        return this;
    }

    public User login(String login) {
        this.setLogin(login);
        return this;
    }

    public void setPayments(Set<Payment> payments) {
        if (this.payments != null) {
            this.payments.forEach(i -> i.setUser(null));
        }
        if (payments != null) {
            payments.forEach(i -> i.setUser(this));
        }
        this.payments = payments;
    }

    public User payments(Set<Payment> payments) {
        this.setPayments(payments);
        return this;
    }

    public User addPayment(Payment payment) {
        this.payments.add(payment);
        payment.setUser(this);
        return this;
    }

    public User removePayment(Payment payment) {
        this.payments.remove(payment);
        payment.setUser(null);
        return this;
    }

    public void setExpenses(Set<Expense> expenses) {
        if (this.expenses != null) {
            this.expenses.forEach(i -> i.setUser(null));
        }
        if (expenses != null) {
            expenses.forEach(i -> i.setUser(this));
        }
        this.expenses = expenses;
    }

    public User expenses(Set<Expense> expenses) {
        this.setExpenses(expenses);
        return this;
    }

    public User addExpense(Expense expense) {
        this.expenses.add(expense);
        expense.setUser(this);
        return this;
    }

    public User removeExpense(Expense expense) {
        this.expenses.remove(expense);
        expense.setUser(null);
        return this;
    }

    public void setGroups(Set<Group> groups) {
        if (this.groups != null) {
            this.groups.forEach(i -> i.getUsers().remove(this));
        }
        if (groups != null) {
            groups.forEach(i -> i.getUsers().add(this));
        }
        this.groups = groups;
    }

    public User groups(Set<Group> groups) {
        this.setGroups(groups);
        return this;
    }

    public User addGroup(Group group) {
        this.groups.add(group);
        group.getUsers().add(this);
        return this;
    }

    public User removeGroup(Group group) {
        this.groups.remove(group);
        group.getUsers().remove(this);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        User user = (User) o;
        return getId() != null && Objects.equals(getId(), user.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
