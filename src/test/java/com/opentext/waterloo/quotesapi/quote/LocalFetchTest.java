package com.opentext.waterloo.quotesapi.quote;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@AutoConfigureMockMvc
public class LocalFetchTest {

    @Autowired
    private LocalFetch localFetch;

    @Test
    public void connectTest() {
        assertThat(localFetch).isNotNull();
    }
}
