package com.autentia.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name = "expense")
public class Expense implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Lob
    @Column(name = "description")
    private String description;

    @Column(name = "ammount")
    private Float amount;

    @Column(name = "date")
    private ZonedDateTime date;

    @OneToMany(mappedBy = "expense")
    @ToString.Exclude
    private Set<Payment> payments = new HashSet<>();

    @ManyToOne
    private Group group;

    @ManyToOne
    private User user;

    public Expense id(Long id) {
        this.setId(id);
        return this;
    }

    public Expense name(String name) {
        this.setName(name);
        return this;
    }

    public Expense description(String description) {
        this.setDescription(description);
        return this;
    }

    public Expense amount(Float amount) {
        this.setAmount(amount);
        return this;
    }

    public Expense date(ZonedDateTime date) {
        this.setDate(date);
        return this;
    }

    public void setPayments(Set<Payment> payments) {
        if (this.payments != null) {
            this.payments.forEach(i -> i.setExpense(null));
        }
        if (payments != null) {
            payments.forEach(i -> i.setExpense(this));
        }
        this.payments = payments;
    }

    public Expense payments(Set<Payment> payments) {
        this.setPayments(payments);
        return this;
    }

    public Expense addPayment(Payment payment) {
        this.payments.add(payment);
        payment.setExpense(this);
        return this;
    }

    public Expense removePayment(Payment payment) {
        this.payments.remove(payment);
        payment.setExpense(null);
        return this;
    }

    public Expense group(Group group) {
        this.setGroup(group);
        return this;
    }

    public Expense user(User user) {
        this.setUser(user);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Expense expense = (Expense) o;
        return getId() != null && Objects.equals(getId(), expense.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
