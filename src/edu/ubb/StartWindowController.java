package ubb;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import ubb.Controller;

/**
 * Controller pentru scena de start si de sfarsit
 */
public class StartWindowController {

    private Controller mainApp;		// aplicatia principala
    @FXML private Label title;	// GFX-elements...
    @FXML private Label result;
    @FXML private Button CloseApp;
    @FXML private Button Restart;
    @FXML private Button StartApp;

    /**
     * Constructorul
     */
    public StartWindowController() {
        title = new Label();
        result = new Label();
    }

    @FXML private void initialize() {}

    /**
     * Metoda pentru setarea aplicatiei principale
     */
    public void setMainApp(Controller mainApp) {
        this.mainApp = mainApp;
    }

    /**
     * Metode pentru butoanele de start,restart si close
     */
    @FXML private void StartApp() throws Exception {mainApp.loadQuiz();}
    @FXML private void Restart() throws Exception	{ mainApp.restartQuiz(); }
    @FXML private void CloseApp() throws Exception	{ mainApp.close(); }

    /**
     * Metoda pentru afisearea rezultatului
     * @param score - scorul final
     */
    public void showResult(int score) {
        Restart.setVisible(true);
        title.setText("Ergebnis:");
        StartApp.setVisible(false);
        if( score < 22){
            result.setText("Prufung nicht bestanden mit "+ score + " Punkten");
            Restart.setVisible(true);
        }
        else
        {
            result.setText("Prufung bestanden mit " + score + " Punkten");
        }
    }

}
