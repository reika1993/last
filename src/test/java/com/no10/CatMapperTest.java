package com.no10;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
public class CatMapperTest {

    @Autowired
    private CatMapper catMapper;

    @Test
    public void getAllCat() {
        List<Cat> cats = catMapper.findAll();
        assertEquals(4, cats.size(), "実際に取得した件数：" + cats.size());
    }

    @Test
    public void findCatByName() {
        List<Cat> cats = catMapper.findByName("Omochi");
        assertEquals(1, cats.size());
        assertEquals("Omochi", cats.get(0).getName());
    }

    @Test
    public void findCatBySex() {
        List<Cat> cats = catMapper.findBySex("male");
        assertEquals(3, cats.size());
        assertEquals("male", cats.get(0).getSex());
    }

    @Test
    public void findCatByAge() {
        List<Cat> cats = catMapper.findByAge(3);
        assertEquals(1, cats.size());
        assertEquals(3, cats.get(0).getAge());
    }

    @Test
    public void registerCat() {
        Cat cat = new Cat("Tama", "female", 2);
        catMapper.insert(cat);

        List<Cat> cats = catMapper.findByName("Tama");
        assertEquals(1, cats.size());
        assertEquals("Tama", cats.get(0).getName());
        assertEquals("female", cats.get(0).getSex());
        assertEquals(2, cats.get(0).getAge());

    }

    @Test
    public void countCatBySex() {
        int count = catMapper.countBySex("male");
        assertEquals(3, count);
    }

    @Test
    public void countCatByAge() {
        int count = catMapper.countByAge(3);
        assertEquals(1, count);
    }

    @Test
    public void updateCatByName() {
        Cat cat = new Cat("Omochi", "male", 3);

        catMapper.update(cat);

        List<Cat> cats = catMapper.findByName("Omochi");

        assertEquals(1, cats.size());
        assertEquals("Omochi", cats.get(0).getName());
        assertEquals("male", cats.get(0).getSex());
        assertEquals(3, cats.get(0).getAge());

    }

    @Test
    public void deleteCatByName() {
        catMapper.delete("Katsuo");
        List<Cat> cats = catMapper.findByName("Katsuo");

        assertEquals(0, cats.size());

    }
}
