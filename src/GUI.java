import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

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

        //Declaring an instance of Main in order to grab allActions() for the Button
        Main main = new Main();

        //On searchButton Click
        searchButton.setOnAction(e -> main.allActions(inputURLTextField));
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
}
