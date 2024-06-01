package com.no10;

import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CatMapper {
    @Select("SELECT * FROM cats")
    List<Cat> findAll();

    @Select("SELECT * FROM cats WHERE name = #{name}")
    List<Cat> findByName(String name);

    @Select("SELECT * FROM cats WHERE sex = #{sex}")
    List<Cat> findBySex(String sex);

    @Select("SELECT * FROM cats WHERE age = #{age}")
    List<Cat> findByAge(Integer age);

    @Insert("INSERT INTO cats (name, sex,age) VALUES (#{name}, #{sex}, #{age})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(Cat cat);

    @Update("UPDATE cats SET sex=#{sex},age = #{age} WHERE name = #{name}")
    void update(Cat cat);


}
