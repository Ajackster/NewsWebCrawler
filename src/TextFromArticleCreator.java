
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * The NewsWebCrawler traverses major news websites and scrapes it for articles.
 * The articles content are stored in a text (.txt) file
 *
 * @author Andrew Jackson
 * @version 1.0
 * @since 2015-04-21
 */
public class TextFromArticleCreator
{

    public void writeToFile(Document articleDocument, String articleURL)
    {
        //Getting the date article is downloaded
        DateFinder dateArticleAccessed = new DateFinder();
        String date = dateArticleAccessed.getFileDate();

        //Getting the title of the article
        ArticleTitle articleTitle = new ArticleTitle(articleDocument);
        String title = articleTitle.getTitle();

        //Getting the body of the article
        ArticleBody articleBody = new ArticleBody(articleDocument);
        String body = articleBody.getArticleBody();

        //Getting the list of links in proper format from the body of the article
        ArticleLinks articleLinks= new ArticleLinks(articleDocument);
        ArrayList<String> links = articleLinks.getLinksList();

        //Stating filename in proper format
        String fileName = date + "-" + title + ".txt";

        //Write to file
        try(PrintWriter writer = new PrintWriter(fileName, "UTF-8"))
        {
            writer.println(articleURL);
            writer.println(body + "\n");
            for(String link: links)
            {
                writer.println(link + ",");
            }
            writer.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    //ArticleTitle Class
    private static class ArticleTitle
    {

        private String articleTitle;

        public ArticleTitle()
        {
            this.articleTitle = null;
        }

        public ArticleTitle(Document document)
        {
            this.articleTitle = document.title();
        }

        public void setTitle(Document document)
        {
            this.articleTitle = document.title();
        }

        public String getTitle()
        {
            return this.articleTitle;
        }
    }

    //ArticleBody Class
    private static class ArticleBody
    {
        private String articleBody;

        public ArticleBody()
        {
            this.articleBody = null;
        }

        public ArticleBody(Document document)
        {
            //Getting the Article
            Element articleBodyWithTags = document.select("[itemprop=articleBody]").first();

            //Removing all img tags in the article body
            articleBodyWithTags.removeAttr("img");

            this.articleBody = filterText(articleBodyWithTags);
        }

        public String getArticleBody()
        {
            return this.articleBody;
        }

        private String filterText(Element articleBodyWithTags) {
            //Getting rid of HTML tags
            this.articleBody = articleBodyWithTags.getElementsByTag("p")
                    .toString()
                    .replaceAll("<[^>]*>", "");

            //Getting rid of &nbsp; and &gt;
            this.articleBody = this.articleBody.replace("&nbsp;", "");
            this.articleBody = this.articleBody.replace("&gt;", "");
            return this.articleBody;
        }
    }

    //ArticleLinks Class
    private static class ArticleLinks
    {
        private ArrayList<String> linksList = new ArrayList<>();

        public ArticleLinks(Document document)
        {
            Element articleBodyWithTags = document.select("[itemprop=articleBody]").first();

            //Getting all the links in the article
            Elements unbracketedLinks = articleBodyWithTags.select("a[href]");

            for(Element link: unbracketedLinks)
            {
                //Adding all links to the ArrayList in proper link format
                String linkText = link.text();
                String href = link.attr("abs:href");
                this.linksList.add(linkText + "[" + href + "]");
            }
        }

        public ArrayList<String> getLinksList()
        {
            return linksList;
        }
    }
}
