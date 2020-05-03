package com.example.restservices.RestServicesExample.beans;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;

@ApiModel(description = "User Information")
public class User {

    @Size(min = 2, message="Name should have at least 3 characters")
    @NotNull(message = "User name can't be null")
    @ApiModelProperty(notes = "Name length should be more than 1", required = true)
    private String name;
    private Integer id;

    @Past(message = "DOB should be less than the current date")
    @NotNull(message = "User DOB can't be null")
    @ApiModelProperty(notes = "DOB should be less than current date", required = true)
    private Date DOB;

    public User() {
    }

    public User(String name, Integer id, Date DOB) {
        this.name = name;
        this.id = id;
        this.DOB = DOB;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDOB() {
        return DOB;
    }

    public void setDOB(Date DOB) {
        this.DOB = DOB;
    }

    @Override
    public String toString() {
        return "User{" +
                "Name='" + name + '\'' +
                ", Id=" + id +
                ", DOB=" + DOB +
                '}';
    }
}
