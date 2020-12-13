package View;
import edu.ubb.Controller;
import javafx.fxml.FXML;
import javafx.scene.control.*;


/**
 * Controller pentru QuestionsWindow
 */
public class QuestionsWindowController {

    private Controller mainApp;		// aplicatia principala
    @FXML private Label question;	// intrebarea de la grila respectiva
    @FXML private Label progress;   // labelul pentru progres
    @FXML private Label correctAnswers; // labelul cu nr de intrebari corecte
    @FXML private CheckBox checkBox1; // variantele de raspuns
    @FXML private CheckBox checkBox2;
    @FXML private CheckBox checkBox3;
    @FXML private Button Next;      // butonul next
    private CheckBox[] checkBoxList;		// lista pentru iterat prin checkboxuri


    public QuestionsWindowController() {
        question = new Label("");
        progress = new Label("0/26");
    }

}
