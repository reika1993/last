package com.no10;

public class Cat {
    private Integer id;

    private String name;

    private String sex;
    private Integer age;


    public Cat(Integer id, String name, String sex, Integer age) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.age = age;

    }

    public Cat(String name, String sex, Integer age) {
        this.name = name;
        this.sex = sex;
        this.age = age;

    }

    public Cat(String sex, Integer age) {
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
