package com.javalitterboy.ibk.entity;

import javax.persistence.*;

/**
 * @author 14183
 */
@Table(name = "User")
@Entity
public class User extends BaseEntity {
    private String name;
    @Column(unique = true)
    private String email;
    @Column(nullable = false,columnDefinition = "varchar(5) check( sex='男' or sex='女') default '男'")
    private String sex;
    @Column(nullable = false,columnDefinition = "int check(age<200 and age>1)")
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
