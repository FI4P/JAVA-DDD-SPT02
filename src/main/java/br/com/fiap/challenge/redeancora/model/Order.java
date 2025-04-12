package br.com.fiap.challenge.redeancora.model;

import java.time.LocalDate;

public class Order {

    private String id;
    private String mechanicId;
    private LocalDate date;
    private String status;

    public Order(String id, String mechanicId, LocalDate date, String status) {
        this.id = id;
        this.mechanicId = mechanicId;
        this.date = date;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public String getMechanicId() {
        return mechanicId;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
