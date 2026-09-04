package com.no10;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CatController.class)
class CatControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CatService catService;

    @Test
    void 全てのねこの情報を取得できること() throws Exception {
        List<Cat> cats = List.of(
                new Cat("Omochi", "female", 2),
                new Cat("Coa", "male", 3)
        );

        when(catService.findCat(null, null, null)).thenReturn(cats);

        mockMvc.perform(get("/cats"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Omochi"))
                .andExpect(jsonPath("$[0].sex").value("female"))
                .andExpect(jsonPath("$[0].age").value(2))
                .andExpect(jsonPath("$[1].name").value("Coa"))
                .andExpect(jsonPath("$[1].sex").value("male"))
                .andExpect(jsonPath("$[1].age").value(3));
    }

    @Test
    void 名前でねこを検索できること() throws Exception {
        List<Cat> cats = List.of(
                new Cat("Omochi", "female", 2)
        );

        when(catService.findCat("Omochi", null, null)).thenReturn(cats);

        mockMvc.perform(get("/cats")
                        .param("name", "Omochi"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Omochi"))
                .andExpect(jsonPath("$[0].sex").value("female"))
                .andExpect(jsonPath("$[0].age").value(2));
    }

    @Test
    void 性別でねこを検索できること() throws Exception {
        List<Cat> cats = List.of(
                new Cat("Omochi", "female", 2)
        );

        when(catService.findCat(null, "female", null)).thenReturn(cats);

        mockMvc.perform(get("/cats")
                        .param("sex", "female"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Omochi"))
                .andExpect(jsonPath("$[0].sex").value("female"))
                .andExpect(jsonPath("$[0].age").value(2));
    }

    @Test
    void 年齢でねこを検索できること() throws Exception {
        List<Cat> cats = List.of(
                new Cat("Omochi", "female", 2)
        );

        when(catService.findCat(null, null, 2)).thenReturn(cats);

        mockMvc.perform(get("/cats")
                        .param("age", "2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Omochi"))
                .andExpect(jsonPath("$[0].sex").value("female"))
                .andExpect(jsonPath("$[0].age").value(2));
    }

    @Test
    void 存在しない名前で検索するとエラーになること() throws Exception {
        when(catService.findCat("Tama", null, null))
                .thenThrow(new CatNotFoundException("Tamaという名前のねこは存在しません。"));

        mockMvc.perform(get("/cats")
                        .param("name", "Tama"))
                .andExpect(status().isNotFound());
    }
}
