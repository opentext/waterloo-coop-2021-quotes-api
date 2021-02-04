package com.opentext.waterloo.quotesapi.quote;

import com.opentext.waterloo.quotesapi.QuotesApiApplication;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultHandler;
import org.springframework.test.web.servlet.request.RequestPostProcessor;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@SpringBootTest
@WebAppConfiguration
class QuoteControllerTest {
    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    private static final String mockIP1 = "fake_ip_1";
    private static final String mockIp2 = "fake_ip_2";
    private static final Quote mockQuote = new Quote();

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
        mockMvc.perform(post("http://spring-qod-interested-gecko-nc.bp-paas.otxlab.net/api/v1/quotes" +mockQuote.getId())
                .with(remoteAddr(mockIP1))
                .accept(MediaType.APPLICATION_JSON))
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