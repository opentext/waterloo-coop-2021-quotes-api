package com.opentext.waterloo.quotesapi.quote;


import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RemoteFetchTest {



    @Mock
    RemoteFetch remoteFetch = Mockito.mock(RemoteFetch.class);
    JSONObject mockJSON = Mockito.mock(JSONObject.class);

    @Autowired
    CacheManager cacheManager;

    @Autowired
    WebApplicationContext wac;

    private MockMvc mockMvc;

    @Test
    public void connectTest() {
        try {
            doReturn(mockJSON).when(remoteFetch).connect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void clearCacheTest() throws Exception{
        mockMvc = webAppContextSetup(wac).build();
        mockMvc.perform(get("/quote"));
        Cache cache = cacheManager.getCache("quote");

        assertTrue(cache != null);
    }
}
//
