package br.com.fiap.challenge.redeancora.model;

import java.time.LocalDate;

public class Promotion {

    private String id;
    private String productId;
    private String description;
    private double discountPercent;
    private LocalDate startDate;
    private LocalDate endDate;

    public Promotion(String id, String productId, String description, double discountPercent, LocalDate startDate, LocalDate endDate) {
        this.id = id;
        this.productId = productId;
        this.description = description;
        this.discountPercent = discountPercent;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getProductId() {
        return productId;
    }

    public String getDescription() {
        return description;
    }

    public double getDiscountPercent() {
        return discountPercent;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }


}
