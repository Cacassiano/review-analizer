package dev.cacassiano.review_analizr.use_cases.plataforms;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.stereotype.Service;

import dev.cacassiano.review_analizr.adapters.services.PlataformService;
import dev.cacassiano.review_analizr.core.entities.analyze.Comment;

@Service("amazon")
public class AmazonService implements PlataformService {

    @Override
    public Map<Integer, List<Comment>> getComments(String url) {
        WebDriver driver = new ChromeDriver();
        List<Comment> comments = new ArrayList<>();
        try{
            driver.get(url);
            Document doc = Jsoup.parse(driver.getPageSource());
            Elements reviews = doc.getElementsByClass("a-section celwidget");
            reviews.remove(0);
            reviews.forEach(e -> {
                Comment comment = new Comment();
                String star = e.getElementsByClass("a-icon-alt").get(0).text().split(" ")[0];
                comment.setStars(Integer.parseInt(star.split(",")[0]));

                String discription = e.getElementsByAttributeValue("data-hook", "review-collapsed").get(0).text();
                comment.setDiscription(discription);
                
                Elements likes = e.getElementsByAttributeValue("data-hook", "helpful-vote-statement");
                if(!likes.isEmpty()) {
                    int like = Integer.parseInt(likes.get(0).text().charAt(0)+"");
                    comment.setLikes(like);
                }
                comments.add(comment);
            });
            return comments.stream().collect(Collectors.groupingBy(Comment::getStars, Collectors.toList()));
        } catch(Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }finally {
            driver.close();
            driver.quit();
        }       
    }
}
