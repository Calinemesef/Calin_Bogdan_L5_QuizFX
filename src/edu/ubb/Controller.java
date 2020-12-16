package ubb;

import Model.Quiz;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class Controller extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;
    private Quiz quiz;
    private QuestionsWindowController view;  //////////////////////////////////
    private StartWindowController load;     ///////////////////////////////////
    private int currentIndex;
    private int score=0;
    private int gresite=0;
    private MenuController menu;


    @Override
    public void start(Stage primaryStage) throws Exception{
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Quiz");
        initRootLayout();
        showStartWindow();
    }


    public static void main(String[] args) {
        launch(args);
    }

    private void initRootLayout() throws IOException {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Controller.class.getResource("Menu.fxml"));
            rootLayout = (BorderPane) loader.load();
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            MenuController viewController = loader.getController();
            viewController.setMainApp(this);
            menu = viewController;
            primaryStage.show();

    }

    /**
     * Metoda pt initializarea paginii de start
     */
    private void showStartWindow() throws IOException {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Controller.class.getResource("StartWindow.fxml"));
            AnchorPane loadView = (AnchorPane) loader.load();
            rootLayout.setCenter(loadView);
            StartWindowController startController = loader.getController();
            startController.setMainApp(this);
            load = startController;
    }

    /**
     * Metoda pt optiunea de restart
     */
    public void restartQuiz() throws IOException {
        //quiz.startGame();
        showQuizView();
    }

    /**
     * Metoda pt inceperea quizului
     */
    public void loadQuiz() throws Exception{
        this.quiz = new Quiz();
        showQuizView();
    }

    /**
     * Metoda pt initializarea controllerului dupa fisierul fxml
     */
    private void showQuizView() throws IOException {
        currentIndex = 0;
        if (quiz.getGameSize() > 0) {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(Controller.class.getResource("ubb/QuestionsWindow.fxml"));
                AnchorPane quizView = (AnchorPane) loader.load();
                rootLayout.setCenter(quizView);
                QuestionsWindowController viewController = loader.getController();
                viewController.setMainApp(this);
                view = viewController;
                showQuiz(currentIndex);
        }
    }

    /**
     * Metoda pt afisarea unei grile
     * @param index - indexul grilei
     */
    private void showQuiz(int index) {
        if (index < quiz.getGameSize())
            view.showQuiz(quiz.getQuestion(index), quiz.getAllAnswers(index));
        else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Indexul depaseste numarul de grile");
            alert.showAndWait();
        }
    }

    /**
     * Metoda pt a trece la urmatoarea grila si pt a stabili punctajul
     */
    public void stepQuiz(ArrayList<String> pick) throws IOException {
        if (pick.equals(quiz.getCorrect(currentIndex)))
            score +=1;
        else
            gresite += 1;
        currentIndex = currentIndex + 1;
        if(gresite >4)
            showResult();
        else
            showQuiz(currentIndex);
    }

    /**
     * Metoda pt afisarea rezultatului final
     */
    private void showResult() throws IOException {
        showStartWindow();
        load.showResult(score);
    }

    public void close() {
        primaryStage.close();
    }
}
