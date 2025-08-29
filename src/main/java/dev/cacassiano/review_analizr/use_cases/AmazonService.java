package dev.cacassiano.review_analizr.use_cases;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.stereotype.Service;

import dev.cacassiano.review_analizr.adapters.services.PlataformService;
import dev.cacassiano.review_analizr.core.entities.analyze.Comment;

@Service
public class AmazonService implements PlataformService {

    @Override
    public Map<String, List<Comment>> getComments(String url) {
        WebDriver driver = new ChromeDriver();
        driver.get(url);
        Document doc = Jsoup.parse(driver.getPageSource());
        Element ul = doc.getElementById("cm-cr-dp-review-list");
        List<Comment> comments = new ArrayList<>();
        ul.children().forEach(e -> {
            if(e.tagName().equals("li")){
                Comment comment = new Comment();
                Element div = e.child(0).child(0).child(0);
                String star = div.getElementsByClass("a-icon-alt").get(0).text().split(" ")[0];
                comment.setStars(star);
                System.out.println("estrela: "+star);

                String discription = div.getElementsByAttributeValue("data-hook", "review-collapsed").get(0).text();
                comment.setDiscription(discription);
                System.out.println(discription);

                comments.add(comment);
            }
        });
        driver.close();
        driver.quit();
        return comments.stream().collect(Collectors.groupingBy(Comment::getStars, Collectors.toList()));
    }
}
