package com.no10;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class CatMapperTest {

    @Autowired
    private CatMapper catMapper;

    @Test
    public void 全てのねこを取得できること() {
        List<Cat> cats = catMapper.findAll();
        assertEquals(4, cats.size(), "実際に取得した件数：" + cats.size());
    }

    public void 名前を指定してねこを取得できること() {
        List<Cat> cats = catMapper.findByName("Omochi");
        assertEquals(1, cats.size());
        assertEquals("Omochi", cats.get(0).getName());
    }

    public void 性別を指定してねこを取得できること() {
        List<Cat> cats = catMapper.findBySex("male");
        assertEquals(3, cats.size());
        assertEquals("male", cats.get(0).getSex());
    }

    public void 年齢を指定して猫を取得できること() {
        List<Cat> cats = catMapper.findByAge(3);
        assertEquals(1, cats.size());
        assertEquals(3, cats.get(0).getAge());
    }

    @Test
    public void ねこを登録できること() {
        Cat cat = new Cat("Tama", "female", 2);
        catMapper.insert(cat);

        List<Cat> cats = catMapper.findByName("Tama");
        assertEquals(1, cats.size());
        assertEquals("Tama", cats.get(0).getName());
        assertEquals("female", cats.get(0).getSex());
        assertEquals(2, cats.get(0).getAge());

        catMapper.delete("Tama");
    }

    @Test
    public void 性別を指定してねこの数を取得できること() {
        int count = catMapper.countBySex("male");
        assertEquals(3, count);
    }

    public void 年齢を指定してねこの数を取得できること() {
        int count = catMapper.countByAge(3);
        assertEquals(1, count);
    }

    @Test
    public void 指定したねこの更新ができること() {
        Cat cat = new Cat("Omochi", "male", 3);

        catMapper.update(cat);

        List<Cat> cats = catMapper.findByName("Omochi");

        assertEquals(1, cats.size());
        assertEquals("Omochi", cats.get(0).getName());
        assertEquals("male", cats.get(0).getSex());
        assertEquals(3, cats.get(0).getAge());

        Cat originalCat = new Cat("Omochi", "female", 2);
        catMapper.update(originalCat);

        cats = catMapper.findByName("Omochi");
        assertEquals(1, cats.size());
        assertEquals("Omochi", cats.get(0).getName());
        assertEquals("female", cats.get(0).getSex());
        assertEquals(2, cats.get(0).getAge());
    }

    @Test
    public void 指定した名前のねこを削除できること() {
        catMapper.delete("Katsuo");
        List<Cat> cats = catMapper.findByName("Katsuo");

        assertEquals(0, cats.size());

        Cat cat = new Cat("Katsuo", "male", 6);
        catMapper.insert(cat);
    }
}
