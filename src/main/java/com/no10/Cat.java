package com.no10;


import java.util.List;

public class Cat {
    private Integer id;

    private String name;

    private String sex;
    private Integer age;

    public Cat(String name, String sex, Integer age) {
        this.name = name;
        this.sex = sex;
        this.age = age;

    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSex() {
        return sex;
    }

    public Integer getAge() {
        return age;
    }

}
