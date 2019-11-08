package com.jibi.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class HealthModel {
    private String status;
    private Date date;

    public HealthModel() {
        status = "up";
        date = new Date();
    }

    public Date getDate() {
        return new Date(this.date.getTime());
    }

    public void setDate(Date date) {
        this.date = new Date(date.getTime());
    }
}
