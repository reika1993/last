package com.no10;

import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CatExceptionHandlerTest {

    @Mock
    private HttpServletRequest request;

    @Test
    void catNotFoundExceptionReturnsNotFoundResponse() {
        CatExceptionHandler handler = new CatExceptionHandler();
        CatNotFoundException exception = new CatNotFoundException("Omochiという名前のねこはいません。");
        when(request.getRequestURI()).thenReturn("/cats/Omochi");

        ResponseEntity<?> response = handler.CatNotFoundException(exception, request);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(response.getBody()).isInstanceOf(Map.class);

        Map<?, ?> body = (Map<?, ?>) response.getBody();
        assertThat(body.get("status")).isEqualTo("404");
        assertThat(body.get("error")).isEqualTo("Not Found");
        assertThat(body.get("message")).isEqualTo("Omochiという名前のねこはいません。");
        assertThat(body.get("path")).isEqualTo("/cats/Omochi");
        assertThat(body.get("timestamp")).isNotNull();
    }
}
