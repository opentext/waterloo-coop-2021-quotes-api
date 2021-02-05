package com.opentext.waterloo.quotesapi.quote;

import org.json.JSONObject;
import org.junit.ClassRule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultHandler;
import org.springframework.test.web.servlet.request.RequestPostProcessor;
import org.springframework.web.context.WebApplicationContext;
import org.testcontainers.containers.PostgreSQLContainer;

import javax.print.attribute.standard.Media;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@ActiveProfiles("test")
@SpringBootTest
class QuoteControllerTest {
//
//    private static int POSTGRES_PORT = 5432;
//    @ClassRule
//    public static PostgreSQLContainer postgres = new PostgreSQLContainer<>("postgres")
//            .withDatabaseName("restart")
//            .withUsername("postgres")
//            .withPassword("password")
//            .withInitScript("sql/init_postgres.sql");
//
//    static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
//        public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
//            TestPropertyValues.of(
//                    "spring.data.postgres.host=" + postgres.getContainerIpAddress(),
//                    "spring.data.postgres.port=" + postgres.getMappedPort(POSTGRES_PORT),
//                    "spring.data.postgres.username=" + postgres.getUsername(),
//                    "spring.data.postgres.password=" + postgres.getPassword()
//            ).applyTo(configurableApplicationContext.getEnvironment());
//        }
//    }

    @Autowired
    private QuoteRepository quoteRepository;


    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    private static final String mockIP1 = "fake_ip_1";
    private static final String mockIp2 = "fake_ip_2";
    private static final Quote mockQuote = new Quote("fake", new Date(),0, 0 );

    @BeforeEach
    public void setup(){
        this.mockMvc = webAppContextSetup(wac).build();
    }

    @Test
    public void webAppContextTest(){
        assertTrue(wac.getServletContext() instanceof MockServletContext);
    }

    @Test
    public void incrementTest() throws Exception{
        String file = "src/test/resources/yes.json";
        String jsonLike = new String(Files.readAllBytes(Paths.get(file)));


        quoteRepository.save(mockQuote);
        mockMvc.perform(post("http://localhost:8080/api/v1/quotes/" +mockQuote.getId())
                .with(remoteAddr(mockIP1))
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON).content(jsonLike))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andDo(new ResultHandler(){
                    @Override
                    public void handle(MvcResult mvcResult) throws Exception{
                        assertEquals(mockQuote.getLikes(), 1);
                    }

                });
    }

    private static RequestPostProcessor remoteAddr(final String remoteAddr) { // it's nice to extract into a helper
        return new RequestPostProcessor() {
            @Override
            public MockHttpServletRequest postProcessRequest(MockHttpServletRequest request) {
                request.setRemoteAddr(remoteAddr);
                return request;
            }
        };
    }

}