package co.cosmose.scraping_homework.service;


import co.cosmose.scraping_homework.content.PublisherContent;

import java.io.IOException;
import java.util.List;

public interface ScrapperService {

    public void extractData() throws IOException;

    public List<PublisherContent> getContent();
}
