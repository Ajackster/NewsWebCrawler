import javafx.scene.control.TextField;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * The NewsWebCrawler traverses major news websites and scrapes it for articles.
 * The articles content are stored in a text (.txt) file
 *
 * @author Andrew Jackson
 * @version 1.0
 * @since 2015-04-21
 */
public class NewsCrawler
{

    public static void main(String[] args)
    {
        javafx.application.Application.launch(GUI.class);
    }

    public void Scrape(TextField inputURLTextField) {

        //Getting the input URL from the user
        String inputURL = inputURLTextField.getText();

        //Creating document from the input URL
        Document document = getDocumentFromURL(inputURL);

        if(inputURL.contains("washingtonpost"))
        {
            //This object is used to find the headline article from the given URL
            NewsCrawlerWashingtonPost newsCrawlerWashingtonPost = new NewsCrawlerWashingtonPost();

            //Find Washington Post headline article
            String washingtonPostLink = newsCrawlerWashingtonPost.findArticle(document);
            Document washingtonPostDocument = getDocumentFromURL(washingtonPostLink);

            write(washingtonPostLink, washingtonPostDocument);
        }
        else
        {
            //This object is used to find the headline article from the given URL
            NewsCrawler newsCrawler = new NewsCrawler();

            //Finding headline articles from News Websites other than Washington Post
            String otherNewsLink = newsCrawler.findArticle(document);
            Document otherNewsDocument = getDocumentFromURL(otherNewsLink);

            write(otherNewsLink, otherNewsDocument);
        }
    }

    public String findArticle(Document document)
    {
        Elements allLinks = document.select("a");
        for (Element link : allLinks)
        {

            String potentialLinkToArticle = link.attr("abs:href");

            //Getting date
            DateFinder dateFinder = new DateFinder();
            String day = dateFinder.getDay();
            String yesterday = dateFinder.getYesterday();
            String month = dateFinder.getMonth();
            String year = dateFinder.getYear();

            //Finding the link of headline article
            if (potentialLinkToArticle.contains(year) && potentialLinkToArticle.contains(month) &&
                    potentialLinkToArticle.contains(day) || potentialLinkToArticle.contains(year) &&
                    potentialLinkToArticle.contains(month) && potentialLinkToArticle.contains(yesterday))
            {
                return potentialLinkToArticle;
            }
        }
        return null;
    }

    //This method utiliszes the TextFromArticleCreator to write to the file
    private static void write(String newLink, Document newDocument) {
        TextFromArticleCreator otherTextFromArticleCreator = new TextFromArticleCreator();
        otherTextFromArticleCreator.writeToFile(newDocument, newLink);
    }


    //This method creates the document from the input URL
    private static Document getDocumentFromURL(String URL)
    {
        try
        {
            return Jsoup.connect(URL).get();
        }
        catch(IOException e)
        {
            throw new RuntimeException(e);
        }
    }
}
