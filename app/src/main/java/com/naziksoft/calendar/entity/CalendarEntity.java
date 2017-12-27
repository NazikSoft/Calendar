package com.naziksoft.calendar.entity;

import android.support.annotation.NonNull;

import java.util.Date;

/**
 * Created by nazar on 27.12.17.
 */

public class CalendarEntity implements Comparable<CalendarEntity> {
    private Date date;
    private String type;
    private String description;

    public CalendarEntity(Date date, String type, String description) {
        this.date = date;
        this.type = type;
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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

    @Override
    public String toString() {
        return "CalendarEntity{" +
                "date=" + date +
                ", type='" + type + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public int compareTo(@NonNull CalendarEntity o) {
        if (o.getDate().before(date)) {
            return 1;
        } else {
            return -1;
        }
    }
}
