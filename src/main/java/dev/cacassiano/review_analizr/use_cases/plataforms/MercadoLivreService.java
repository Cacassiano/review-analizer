package dev.cacassiano.review_analizr.use_cases.plataforms;

import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Service;

import dev.cacassiano.review_analizr.adapters.services.PlataformService;
import dev.cacassiano.review_analizr.core.entities.analyze.Months;
import dev.cacassiano.review_analizr.core.entities.analyze.Review;

@Service("mercadolivre")
public class MercadoLivreService implements PlataformService {

    @Override
    public List<Review> getReviews(String url) {
        WebDriver driver = new ChromeDriver();
        List<Review> reviews = new ArrayList<>();
        try {
            driver.get(url);

            WebElement cookieButton = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector(".cookie-consent-banner-opt-out__container button")));
            cookieButton.click();
            WebElement showMoreButton = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.className("show-more-click")));
            showMoreButton.click();

            
            // Para pegar mais paginas de reviews
            // JavascriptExecutor js = (JavascriptExecutor) driver;
            // for (int i = 0; i < 5; i++) {
            //     TimeUnit.MILLISECONDS.sleep(20);
            //     js.executeScript("window.scrollTo(0,document.body.scrollHeight);");
            // }

            Document page = Jsoup.parse(driver.getPageSource());
            Elements comments  = page.getElementsByAttributeValue("data-testid", "comment-component");
            comments.forEach(e -> {
                Review review = createReview(e);
                reviews.add(review);
            });
            return reviews;
        } catch (RuntimeException ex) {
            throw new RuntimeException(ex.getMessage());
        } finally {
            driver.close();
            driver.quit();
        }
    }

    @Override
    public Review createReview(Element parent) {
        Review review = new Review();
        
        String strStar = parent.getElementsByClass("andes-visually-hidden").text().split(" ")[1];
        review.setStars(Float.parseFloat(strStar));

        Element date_html = parent.getElementsByClass("ui-review-capability-comments__comment__date").get(0);
        review.setIssued_at(getDate(date_html));

        Element numLikeHtml = parent.getElementsByClass("ui-review-capability-valorizations__button-like__text").get(1);
        review.setLikes(Integer.parseInt(numLikeHtml.text()));

        Element discription = parent.getElementsByAttributeValue("role", "presentation").get(0);
        review.setDiscription(discription.text());

        return review;
    }

    private LocalDate getDate(Element date_html) {
        String[] textDate = date_html.text().split(" ");
        int day = Integer.parseInt(textDate[0]);
        int month = Months.valueOf(textDate[1].replace(".", "").toUpperCase()).getValue();
        int year = Integer.parseInt(textDate[2]);

        return LocalDate.of(year, month, day);
    }
}