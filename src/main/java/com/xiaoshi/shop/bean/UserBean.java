package com.xiaoshi.shop.bean;

import java.io.Serializable;

/**
 * @author xiaopang
 */

public class UserBean implements Serializable {
    private int id;
    private String account;
    private String password;
    private int age;
    private int sex;

    public UserBean() {
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public int getId() {
        return id;
    }

    public String getAccount() {
        return account;
    }

    public String getPassword() {
        return password;
    }

    public int getAge() {
        return age;
    }

    public int getSex() {
        return sex;
    }
}
