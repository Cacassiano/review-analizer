package dev.cacassiano.review_analizr.use_cases.plataforms;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.stereotype.Service;

import dev.cacassiano.review_analizr.adapters.services.PlataformService;
import dev.cacassiano.review_analizr.core.entities.analyze.Months;
import dev.cacassiano.review_analizr.core.entities.analyze.Review;

@Service("amazon")
public class AmazonService implements PlataformService {

    @Override
    public List<Review> getReviews(String url) {
        WebDriver driver = new ChromeDriver();
        List<Review> reviews = new ArrayList<>();
        try{
            driver.get(url);
            Document doc = Jsoup.parse(driver.getPageSource());
            Elements reviewsGroup = doc.getElementsByClass("a-section celwidget");
            reviewsGroup.remove(0);
            reviewsGroup.forEach(e -> {
                Review review = createReview(e);
                reviews.add(review);
            });
            return reviews;
        } catch(Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }finally {
            driver.close();
            driver.quit();
        }       
    }

    private LocalDate getDate(Element parent) {
        String[] text = parent.getElementsByAttributeValue("data-hook", "review-date")
            .get(0)
            .text()
            .split(" ");
        int year = Integer.parseInt(text[text.length-1]);
        int month = Months.valueOf(text[text.length-3].substring(0, 3).toUpperCase()).getValue();
        int day = Integer.parseInt(text[text.length-5]);

        return LocalDate.of(year, month, day);
    }

    @Override
    public Review createReview(Element parent) {
        Review review = new Review();

        String star = parent.getElementsByClass("a-icon-alt").get(0).text().split(" ")[0];
        review.setStars(Float.parseFloat(star.split(",")[0]));

        String discription = parent.getElementsByAttributeValue("data-hook", "review-collapsed").get(0).text();
        review.setDiscription(discription);
        
        Elements likes = parent.getElementsByAttributeValue("data-hook", "helpful-vote-statement");
        if(!likes.isEmpty()) {
            int like = Integer.parseInt(likes.get(0).text().split(" ")[0]);
            review.setLikes(like);
        }
        review.setIssued_at(getDate(parent));
        return review;
    }
}
