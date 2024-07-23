package com.no10;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

class CatRequest {
    @NotBlank(message = "名前は必須項目です。")
    private String name;
    @NotBlank(message = "性別は必須項目です。")
    private String sex;
    @NotNull(message = "年齢は必須項目です。")
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
