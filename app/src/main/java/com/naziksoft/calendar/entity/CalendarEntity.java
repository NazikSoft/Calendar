package com.naziksoft.calendar.entity;

import android.support.annotation.NonNull;

import java.util.Date;

/**
 * Created by nazar on 27.12.17.
 */

public class CalendarEntity implements Comparable<CalendarEntity> {
    private Date startDate;
    private Date endDate;
    private String type;
    private String description;

    public CalendarEntity(Date startDate, Date endDate, String type, String description) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.type = type;
        this.description = description;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "CalendarEntity{" +
                "startDate=" + startDate +
                ", endDate=" + endDate +
                ", type='" + type + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public int compareTo(@NonNull CalendarEntity o) {
        if (o.getStartDate().before(startDate)) {
            return 1;
        } else {
            return -1;
        }
    }
}
