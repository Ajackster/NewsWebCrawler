import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

/**
 * The NewsWebCrawler traverses major news websites and scrapes it for articles.
 * The articles content are stored in a text (.txt) file
 *
 * @author Andrew Jackson
 * @version 1.0
 * @since 2015-04-21
 */
public class NewsCrawlerLATimes extends NewsCrawler {

    public String findArticle(Document document) {
        Element LATimesHeadline = document.select("a.trb_outfit_primaryItem_article_title_a").first();
        return LATimesHeadline.attr("abs:href");
    }
}
