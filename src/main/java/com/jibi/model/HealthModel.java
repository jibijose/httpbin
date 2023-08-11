package com.jibi.model;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;

/**
 * The type Health model.
 */
@Getter
@Setter
public class HealthModel {
  private String status;
  private Date date;

    /**
     * Instantiates a new Health model.
     */
    public HealthModel() {
    status = "up";
    date = new Date();
  }

    /**
     * Gets date.
     *
     * @return the date
     */
    public Date getDate() {
    return new Date(this.date.getTime());
  }

    /**
     * Sets date.
     *
     * @param date the date
     */
    public void setDate(Date date) {
    this.date = new Date(date.getTime());
  }
}
