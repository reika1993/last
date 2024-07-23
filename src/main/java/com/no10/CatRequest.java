package com.no10;


public class CatRequest {
    private String name;
    private String sex;
    private Integer age;


    public CatRequest(String name, String sex, Integer age) {
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


}


