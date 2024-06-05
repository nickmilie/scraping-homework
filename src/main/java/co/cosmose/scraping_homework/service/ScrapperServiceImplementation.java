package co.cosmose.scraping_homework.service;

import co.cosmose.scraping_homework.content.PublisherContent;
import co.cosmose.scraping_homework.content.PublisherContentRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class ScrapperServiceImplementation implements ScrapperService{

    @Value("${web.rss.url}")
    String rssUrl;

    PublisherContentRepository contentRepository;

    public ScrapperServiceImplementation(PublisherContentRepository contentRepository) {
        this.contentRepository = contentRepository;
    }

    public void extractData() throws IOException {

        Document document = Jsoup.connect(rssUrl).get();

        Element mainElement = document.getElementsByTag("channel").first();

        Elements elements = mainElement.getElementsByTag("item");

        for(Element element : elements) {

            String title = element.getElementsByTag("title").first().text();

            Optional<PublisherContent> optionalPublisherContent = contentRepository.findByTitle(title);

            if (optionalPublisherContent.isEmpty()) {
                String link = element.getElementsByTag("link").first().text();
                String author = element.getElementsByTag("dc:creator").first().text();
                String htmlContent = element.getElementsByTag("description").first().text();
                String originalContent = element.getElementsByTag("guid ").first().text();
                String mainImageUrl = element.getElementsByTag("media:content ").first().attr("url");

                PublisherContent content = PublisherContent.builder()
                        .articleUrl(link)
                        .title(title)
                        .author(author)
                        .htmlContent(htmlContent)
                        .originalContent(originalContent)
                        .mainImageUrl(mainImageUrl)
                        .build();

                contentRepository.save(content);
            }
        }

    }

    public List<PublisherContent> getContent() {
        return contentRepository.findAll();
    }
}
