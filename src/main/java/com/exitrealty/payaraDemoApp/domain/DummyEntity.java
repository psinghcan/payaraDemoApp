package com.exitrealty.payaraDemoApp.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity(name="Dummy")
@Table(name="\"DUMMY\"")
public class DummyEntity extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Size(max = 50)
    @Column(length = 50, name="\"name\"")
    private String name;

    @Size(max = 50)
    @Column(length = 50, name="\"description\"")
    private String description;

    @Size(max = 50)
    @Column(length = 50, name="\"comment\"")
    private String comment;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getComment() {
        return this.comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

}
