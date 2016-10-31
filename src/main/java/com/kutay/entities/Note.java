package com.kutay.entities;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Kutay on 30.10.2016.
 */
public class Note {
    private BigDecimal id;
    private String note;
    private Date createDate;

    public Note() {
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
