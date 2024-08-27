package com.no10;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CatServiceTest {
    @Mock
    private CatMapper catMapper;

    @InjectMocks
    private CatService catService;

    @Test
    public void 全てのねこの情報が取得できること() {
        List<Cat> cats = List.of(
                new Cat("Omochi", "female", 2),
                new Cat("Coa", "male", 3),
                new Cat("Gonchi", "male", 5));
        when(catMapper.findAll()).thenReturn(cats);
        List<Cat> actual = catService.findAll();
        assertThat(actual).isEqualTo(cats);
        verify(catMapper).findAll();
    }

    @Test
    public void 存在するねこの名前を検索した場合にねこの情報が取得されること() throws CatNotFoundException {
        // テスト用データのセットアップ
        List<Cat> cats = List.of(
                new Cat("Omochi", "female", 2));
        when(catMapper.findByName("Omochi")).thenReturn(cats);

        // テスト実行
        List<Cat> actual = catService.findCat("Omochi", null, null);

        // 結果の検証
        assertThat(actual).isEqualTo(cats);
        verify(catMapper).findByName("Omochi");
        assertThrows(CatNotFoundException.class, () -> {
            catService.findCat("unknown", null, null);
        });
    }

    @Test
    public void 存在するねこの性別を検索した場合にねこの情報が取得されること() throws CatNotFoundException {
        List<Cat> cats = List.of(new Cat("Omochi", "female", 2));
        when(catMapper.findBySex("female")).thenReturn(cats);
        List<Cat> actual = catService.findCat(null, "female", null);
        assertThat(actual).isEqualTo(cats);
        verify(catMapper).findBySex("female");
        assertThrows(CatNotFoundException.class, () -> {
            catService.findCat(null, "unknown", null);
        });
    }

    @Test
    public void 存在するねこの年齢を検索した場合にねこの情報が取得されること() throws CatNotFoundException {
        List<Cat> cats = List.of(new Cat("Omochi", "female", 2));
        when(catMapper.findByAge(2)).thenReturn(cats);
        List<Cat> actual = catService.findCat(null, null, 2);
        assertThat(actual).isEqualTo(cats);
        verify(catMapper).findByAge(2);
        assertThrows(CatNotFoundException.class, () -> {
            catService.findCat(null, null, anyInt());
        });
    }


}
