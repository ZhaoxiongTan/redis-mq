package com.ai.research.model;

import java.io.Serializable;

/**
 * TODO 添加注释
 */
public class User implements Serializable {

    private static final long serialVersionUID = -2726648859461720413L;
    
    private int id;
    
    private String name;
    
    private String sex;
    
    private String address;
    
    private String phone;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    public static final class UserBuilder {
        private int id;
        private String name;
        private String sex;
        private String address;
        private String phone;

        private UserBuilder() {
        }

        public static UserBuilder create() {
            return new UserBuilder();
        }

        public UserBuilder withId(int id) {
            this.id = id;
            return this;
        }

        public UserBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public UserBuilder withSex(String sex) {
            this.sex = sex;
            return this;
        }

        public UserBuilder withAddress(String address) {
            this.address = address;
            return this;
        }

        public UserBuilder withPhone(String phone) {
            this.phone = phone;
            return this;
        }

        public User build() {
            User user = new User();
            user.setId(id);
            user.setName(name);
            user.setSex(sex);
            user.setAddress(address);
            user.setPhone(phone);
            return user;
        }
    }
}
