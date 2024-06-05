package co.cosmose.scraping_homework.controller;

import co.cosmose.scraping_homework.content.PublisherContent;
import co.cosmose.scraping_homework.service.ScrapperService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(path = "/rss")
public class RSSController {

    ScrapperService scrapperService;

    public RSSController(ScrapperService scrapperService) {
        this.scrapperService = scrapperService;
    }

    @GetMapping("/scrape")
    public void extractContent() throws IOException {
        scrapperService.extractData();
    }

    @GetMapping("/content")
    public List<PublisherContent> showContent()  {
        return scrapperService.getContent();
    }
}
