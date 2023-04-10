package com.autentia.domain;

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

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name = "expenses_group") // group is a reserved keyword in mysql
public class Group implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Lob
    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "group")
    @ToString.Exclude
    private Set<Expense> expenses = new HashSet<>();

    @ManyToMany(mappedBy = "groups")
    @ToString.Exclude
    private Set<User> users = new HashSet<>();

    public Long id() {
        return id;
    }

    public Group id(Long id) {
        this.setId(id);
        return this;
    }


    public Group name(String name) {
        this.setName(name);
        return this;
    }

    public Group description(String description) {
        this.setDescription(description);
        return this;
    }

    public void setExpenses(Set<Expense> expenses) {
        if (this.expenses != null) {
            this.expenses.forEach(i -> i.setGroup(null));
        }
        if (expenses != null) {
            expenses.forEach(i -> i.setGroup(this));
        }
        this.expenses = expenses;
    }

    public Group expenses(Set<Expense> expenses) {
        this.setExpenses(expenses);
        return this;
    }

    public Group addExpense(Expense expense) {
        this.expenses.add(expense);
        expense.setGroup(this);
        return this;
    }

    public Group removeExpense(Expense expense) {
        this.expenses.remove(expense);
        expense.setGroup(null);
        return this;
    }

    public void setUsers(Set<User> users) {
        if (this.users != null) {
            this.users.forEach(i -> i.removeGroup(this));
        }
        if (users != null) {
            users.forEach(i -> i.addGroup(this));
        }
        this.users = users;
    }

    public Group users(Set<User> users) {
        this.setUsers(users);
        return this;
    }

    public Group addUser(User user) {
        this.users.add(user);
        user.getGroups().add(this);
        return this;
    }

    public Group removeUser(User user) {
        this.users.remove(user);
        user.getGroups().remove(this);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Group group = (Group) o;
        return getId() != null && Objects.equals(getId(), group.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
