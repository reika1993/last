package com.no10;

import org.hamcrest.Matcher;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static java.util.Optional.empty;
import static java.util.Optional.of;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.util.CollectionUtils.isEmpty;


@ExtendWith(MockitoExtension.class)
class CatServiceTest {
    private static final String NOT_FOUND_NAME = "Tama";
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
        List<Cat> actual = catService.findCat(null, null, null);
        assertThat(actual).isEqualTo(cats);
        verify(catMapper).findAll();
    }

    @Test
    public void 存在するねこの名前を検索した場合にねこの情報が取得されること() {
        // テスト用データのセットアップ
        List<Cat> cats = List.of(
                new Cat("Omochi", "female", 2));
        when(catMapper.findByName("Omochi")).thenReturn(cats);

        // テスト実行
        List<Cat> actual = catService.findCat("Omochi", null, null);

        // 結果の検証
        assertThat(actual).isEqualTo(cats);
    }

    @Test
    public void 存在しないねこの名前を検索した場合に404エラーが返されること() {
        String catNotFoundName = "Tama";
        String errorMessage = catNotFoundName + "という名前のねこは存在しません。";

        CatNotFoundException exception = assertThrows(CatNotFoundException.class, () -> {
            catService.findCat(catNotFoundName, null, null);
        }, "404 NOT_FOUND");

        assertThat(exception.getMessage()).isEqualTo(errorMessage);
        verify(catMapper).findByName(catNotFoundName);
    }

    @Test
    public void 存在するねこの性別を検索した場合にねこの情報が取得されること() {
        List<Cat> cats = List.of(new Cat("Omochi", "female", 2));
        when(catMapper.findBySex("female")).thenReturn(cats);
        List<Cat> actual = catService.findCat(null, "female", null);
        assertThat(actual).isEqualTo(cats);
        verify(catMapper).findBySex("female");
    }

    @Test
    public void 存在しないねこの性別を検索した場合に404エラーが返されること() {
        String catNotFoundSex = "famaleee";
        String errorMessage = "現在、性別が" + catNotFoundSex + "のねこはいません。";

        CatNotFoundException exception = assertThrows(CatNotFoundException.class, () -> {
            catService.findCat(null, catNotFoundSex, null);
        }, "404 NOT_FOUND");
        assertThat(exception.getMessage()).isEqualTo(errorMessage);
        verify(catMapper).findBySex(catNotFoundSex);
    }

    @Test
    public void 存在するねこの年齢を検索した場合にねこの情報が取得されること() {
        List<Cat> cats = List.of(new Cat("Omochi", "female", 2));
        when(catMapper.findByAge(2)).thenReturn(cats);
        List<Cat> actual = catService.findCat(null, null, 2);
        assertThat(actual).isEqualTo(cats);
        verify(catMapper).findByAge(2);
    }

    @Test
    public void 存在しないねこの年齢を検索した場合に404エラーが返されること() {
        Integer catNotFoundAge = 99;
        String errorMessage = "現在、" + catNotFoundAge + "才のねこはいません。";

        CatNotFoundException exception = assertThrows(CatNotFoundException.class, () -> {
            catService.findCat(null, null, catNotFoundAge);
        }, "404 NOT_FOUND");
        assertThat(exception.getMessage()).isEqualTo(errorMessage);
        verify(catMapper).findByAge(catNotFoundAge);

    }
}


