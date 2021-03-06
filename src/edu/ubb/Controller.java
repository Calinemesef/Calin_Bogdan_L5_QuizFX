package ubb;

import Model.FileRepo;
import Model.Quiz;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Controller extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;
    private Quiz quiz;
    private QuestionsWindowController view;
    private StartWindowController load;
    private int currentIndex;
    private int score=0;
    private int gresite=0;
    private Integer seconds;

    /**
     * metoda de start
     * @param primaryStage - scena principala
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception{
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Prufung");
        initRootLayout();
        showStartWindow();
    }

    /**
     * Functie pentru calcularea timpului
     *
     * @param primaryStage - scena pentru care sa cronometreze
     */
    private void doTime(Stage primaryStage) {
        Timeline time = new Timeline();
        KeyFrame frame = new KeyFrame(Duration.seconds(1), event -> {
            seconds--;
            if (seconds <= 0) {
                try {
                    showResult();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        time.setCycleCount(Timeline.INDEFINITE);
        time.getKeyFrames().add(frame);
        time.stop();
        time.play();
    }

    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Initializeaza root-ul cu o bara de meniu
     */
    private void initRootLayout() throws IOException {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Controller.class.getResource("Menu.fxml"));
            rootLayout = (BorderPane) loader.load();
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            MenuController viewController = loader.getController();
            viewController.setMainApp(this);
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
        seconds=1800;
        showQuizView();
    }

    /**
     * Metoda pt inceperea quizului
     */
    public void loadQuiz() throws Exception{
        seconds=1800;
        this.quiz = new Quiz();
        FileRepo.readFile(new File("src/Model/intrebari.txt"),quiz);
        quiz.createExam();
        showQuizView();
    }

    /**
     * Metoda pt initializarea controllerului dupa fisierul fxml
     */
    private void showQuizView() throws IOException {
        new Thread(() -> doTime(primaryStage)).start();
        doTime(primaryStage);
        gresite = 0;
        currentIndex = 0;
        if (quiz.getGameSize() > 0) {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(Controller.class.getResource("QuestionsWindow.fxml"));
                AnchorPane quizView = (AnchorPane) loader.load();
                rootLayout.setCenter(quizView);
                QuestionsWindowController viewController = loader.getController();
                viewController.setMainApp(this);
                view = viewController;
                showQuiz(currentIndex);
                view.setLabels(1,gresite);
        }
    }

    /**
     * Metoda pt afisarea unei grile
     * @param index - indexul grilei
     */
    private void showQuiz(int index) throws IOException {
        if( index == quiz.getGameSize())
            showResult();
        else {
            if (index <= quiz.getGameSize()) {
                view.setLabels(currentIndex + 1, gresite);
                view.showQuiz(quiz.getQuestion(index), quiz.getAllAnswers(index));
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setHeaderText("Indexul depaseste numarul de grile");
                alert.showAndWait();
            }
        }
    }

    /**
     * Metoda pt a trece la urmatoarea grila si pt a stabili punctajul
     */
    public void stepQuiz(ArrayList<String> pick) throws IOException {
        view.setLabels(currentIndex,gresite);
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

    /**
     * Metoda pentru inchiderea programului
     */
    public void close() {
        primaryStage.close();
    }
}
