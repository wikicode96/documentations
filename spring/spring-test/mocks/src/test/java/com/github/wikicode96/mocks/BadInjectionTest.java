package com.github.wikicode96.mocks;

import com.github.wikicode96.mocks.dependency.MyDependency;
import com.github.wikicode96.mocks.service.MyService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
@AutoConfigureMockMvc
public class BadInjectionTest {

    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    private MyService service; // Nunca se usará en el contexto de Spring

    @Mock
    private MyDependency dependencyMock;

    @Test
    void test_with_mock_fails() throws Exception {
        mockMvc.perform(get("/test"));

        // Falla, porque dependencyMock nunca fue inyectado realmente
        verify(dependencyMock, times(1)).doSomething();
    }
}
