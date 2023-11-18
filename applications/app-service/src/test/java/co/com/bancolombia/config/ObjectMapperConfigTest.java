package co.com.bancolombia.config;

import org.junit.jupiter.api.Test;
import org.reactivecommons.utils.ObjectMapper;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ObjectMapperConfigTest {

    @Test
    void testObjectMapperBeanCreation() {
        // Given
        ObjectMapperConfig objectMapperConfig = new ObjectMapperConfig();

        // When
        ObjectMapper objectMapper = objectMapperConfig.objectMapper();

        // Then
        assertNotNull(objectMapper);
    }


}
