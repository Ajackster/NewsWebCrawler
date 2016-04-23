import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * The NewsWebCrawler traverses major news websites and scrapes it for articles.
 * The articles content are stored in a text (.txt) file
 *
 * @author Andrew Jackson
 * @version 1.0
 * @since 2015-04-21
 */
public class NewsCrawlerWashingtonPost extends NewsCrawler {


    public String findArticle(Document document)
    {
        Element washingtonPostHeadline = document.select("div.headline").first();
        Elements washingtonPostLink = washingtonPostHeadline.select("a");
        return washingtonPostLink.attr("abs:href");
    }

}
