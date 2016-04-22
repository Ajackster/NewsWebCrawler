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
public class ArticleFinder {

    public String findWashingtonPostArticle(Document document)
    {
        Element washingtonPostHeadline = document.select("div.headline").first();
        Elements washingtonPostLink = washingtonPostHeadline.select("a");
        return washingtonPostLink.attr("abs:href");
    }

    public String findOtherArticles(Document document)
    {
        Elements allLinks = document.select("a");
        for (Element link : allLinks)
        {

            String potentialLinkToArticle = link.attr("abs:href");

            //***Getting dates
            DateFinder dateFinder = new DateFinder();
            String day = dateFinder.getDay();
            String yesterday = dateFinder.getYesterday();
            String month = dateFinder.getMonth();
            String year = dateFinder.getYear();

            if (potentialLinkToArticle.contains(year) && potentialLinkToArticle.contains(month) &&
                    potentialLinkToArticle.contains(day) || potentialLinkToArticle.contains(year) &&
                    potentialLinkToArticle.contains(month) && potentialLinkToArticle.contains(yesterday))
            {
                return potentialLinkToArticle;
            }
        }
        return null;
    }
}