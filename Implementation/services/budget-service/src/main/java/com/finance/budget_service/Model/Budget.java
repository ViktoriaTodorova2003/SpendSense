package com.finance.budget_service.Model;
import jakarta.persistence.*;


@Entity
public class Budget implements java.io.Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String category;
    private Double amount;
    private String userId;

    public Budget() {}

    public Budget(String category, Double amount) {
        this.category = category;
        this.amount = amount;
    }

    public Long getId() {
        return id;
    }

    public String getCategory() {
        return category;
    }

    public Double getAmount() {
        return amount;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getUserId() {return userId; }
    public void setUserId(String userId) { this.userId = userId;}
}
