package co.cosmose.scraping_homework.content;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class PublisherContentRepositoryTest {

    PublisherContentRepository contentRepository;

    @Autowired
    public PublisherContentRepositoryTest(PublisherContentRepository contentRepository) {
        this.contentRepository = contentRepository;
    }

    @BeforeEach
    void clean(){
        contentRepository.deleteAll();
    }

    @Test
    void should_proceed_if_html_is_long(){
        String characters = " ".repeat(256);

        PublisherContent content = PublisherContent.builder()
                .title("Test")
                .htmlContent(characters)
                .build();

        contentRepository.save(content);

        Optional<PublisherContent> result = contentRepository.findByTitle("Test");

        assertThat(result.isPresent()).isTrue();
    }

}
