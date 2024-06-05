package co.cosmose.scraping_homework.controller;

import co.cosmose.scraping_homework.service.ScrapperService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
@AutoConfigureMockMvc
public class RSSControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    ScrapperService scrapperService;

    @Test
    void check_if_extract_has_run() throws Exception {

        this.mockMvc.perform(get("/rss/scrape"));

        verify(scrapperService, times(1)).extractData();
    }

    @Test
    void check_if_get_has_run() throws Exception {

        this.mockMvc.perform(get("/rss/content"));

        verify(scrapperService, times(1)).getContent();
    }
}
