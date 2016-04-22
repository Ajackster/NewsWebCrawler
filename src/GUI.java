import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

/**
 * The NewsWebCrawler traverses major news websites and scrapes it for articles.
 * The articles content are stored in a text (.txt) file
 *
 * @author Andrew Jackson
 * @version 1.0
 * @since 2015-04-21
 */
public class GUI extends Application
{
    public void start(Stage primaryStage)
    {
        //Declaring label, textfield, and button for GUI
        Label inputURLLabel = new Label("Enter a URL");
        TextField inputURLTextField = new TextField();
        Button searchButton = new Button("Search");

        //Creating the GridView
        makeGrid(inputURLLabel, inputURLTextField, searchButton, primaryStage);

        //On searchButton Click
        searchButton.setOnAction((ActionEvent event) -> {

            //Getting the input URL from the user
            String inputURL = inputURLTextField.getText();

            //Creating document from the input URL
            Document document = getDocumentFromURL(inputURL);

            //This object is used to find the headline article from the given URL
            ArticleFinder articleFinder = new ArticleFinder();

            if(inputURL.contains("washingtonpost"))
            {
                //Find Washington Post headline article
                String washingtonPostLink = articleFinder.findWashingtonPostArticle(document);
                Document washingtonPostDocument = getDocumentFromURL(washingtonPostLink);

                write(washingtonPostLink, washingtonPostDocument);
            }
            else
            {
                //Finding headline articles from News Websites other than Washington Post
                String otherNewsLink = articleFinder.findOtherArticles(document);
                Document otherNewsDocument = getDocumentFromURL(otherNewsLink);

                write(otherNewsLink, otherNewsDocument);
            }
        });
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

    //This method makes the GridView
    private static void makeGrid(Label inputURLLabel, TextField inputURLTextField,
                                Button searchButton, Stage primaryStage)
    {

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.add(inputURLLabel, 0, 0);
        grid.add(inputURLTextField, 1, 0);
        grid.add(searchButton, 2, 0);

        Scene scene = new Scene(grid, 500, 500);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    //This method officially writes to the file
    public static void write(String newLink, Document newDocument) {
        TextFromArticleCreator otherTextFromArticleCreator = new TextFromArticleCreator();
        otherTextFromArticleCreator.writeToFile(newDocument, newLink);
    }
}
