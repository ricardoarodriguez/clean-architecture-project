package co.com.bancolombia.exception;

import co.com.bancolombia.model.shared.exception.AppException;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

public class GlobalExceptionHandlerTest {

    @Test
    void handleAppExceptionTest() {
        // Given
        GlobalExceptionHandler exceptionHandler = new GlobalExceptionHandler();
        HttpServletRequest request = mock(HttpServletRequest.class);
        AppException appException = new AppException("Custom error message");

        // When
        ResponseEntity<String> responseEntity = exceptionHandler.handleAppException(request, appException);

        // Then
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals("Custom error message", responseEntity.getBody());
    }
}
