package com.no10;

import java.util.Objects;

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

    public String getName() {
        return name;
    }

    public String getSex() {
        return sex;
    }

    public Integer getAge() {
        return age;
    }


    //オブジェクトの内容を比較して等価性を判断

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cat cat = (Cat) o;
        return age == cat.age &&
                Objects.equals(name, cat.name) &&
                Objects.equals(sex, cat.sex);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, sex, age);
    }
}
