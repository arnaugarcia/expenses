package com.autentia.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@Setter
@Table(name = "payment")
public class Payment implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "amount")
    @NotBlank
    private Float amount;

    @Column(name = "date")
    @NotBlank
    private LocalDateTime date;

    @ManyToOne(optional = false)
    private Expense expense;

    @ManyToOne(optional = false)
    private User user;

    public Payment id(Long id) {
        this.setId(id);
        return this;
    }

    public Payment amount(Float amount) {
        this.setAmount(amount);
        return this;
    }

    public Payment date(LocalDateTime date) {
        this.setDate(date);
        return this;
    }

    public Payment expense(Expense expense) {
        this.setExpense(expense);
        return this;
    }

    public Payment user(User user) {
        this.setUser(user);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Payment)) {
            return false;
        }
        return id != null && id.equals(((Payment) o).id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Payment{" +
            "id=" + getId() +
            ", amount=" + getAmount() +
            ", date='" + getDate() + "'" +
            "}";
    }
}
