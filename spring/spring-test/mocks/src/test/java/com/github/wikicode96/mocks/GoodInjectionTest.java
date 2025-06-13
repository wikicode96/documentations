package com.github.wikicode96.mocks;

import com.github.wikicode96.mocks.dependency.MyDependency;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
@AutoConfigureMockMvc
public class GoodInjectionTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MyDependency dependencyMock;

    @Test
    void test_with_mockbean_works() throws Exception {
        mockMvc.perform(get("/test"));

        // Pasa el test, porque Spring inyecta el mock
        verify(dependencyMock, times(1)).doSomething();
    }
}
