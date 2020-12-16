package ubb;
import javafx.event.ActionEvent;
import ubb.Controller;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


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

    /**
     * Metoda pt initializarea controllerului dupa fisierul fxml
     */
    @FXML
    private void initialize() {
        checkBoxList = new CheckBox[3];
        checkBox1.setSelected(false);
        checkBox2.setSelected(false);
        checkBox3.setSelected(false);

        checkBoxList[0] = checkBox1;
        checkBoxList[1] = checkBox2;
        checkBoxList[2] = checkBox3;
    }

    /**
     * Metoda pt setarea scenei principale
     */
    public void setMainApp(Controller mainApp) {
        this.mainApp = mainApp;
    }

//    /**
//     * Metoda pt checkbox bifat
//     * @param pressed - checkboxul respectiv
//     */
//    private void checkboxPressed(CheckBox pressed){
//        if (pressed.isVisible()){
//            pressed.setSelected(true);
//        }
//    }

    @FXML private void Next() throws IOException { getNextQuiz();			}

    /**
     * Metoda pt a trimite raspunsurile alese controllerului, sub forma de lista de stringuri
     */
    private void getNextQuiz() throws IOException {
        ArrayList<String> picked = new ArrayList<String>();
        if(checkBox1.isSelected())
            picked.add(checkBox1.getText());
        if(checkBox2.isSelected())
            picked.add(checkBox2.getText());
        if(checkBox3.isSelected())
            picked.add(checkBox3.getText());
        mainApp.stepQuiz(picked);
    }

    /**
     * Metoda pt afisarea unei grile

     */
    public void showQuiz(String question, ArrayList<String> allAnswers){
        this.question.setText(question);
        this.question.setVisible(true);
        for (int i = 0; i < 3; i++){
            checkBoxList[i].setText(allAnswers.get(i));
            checkBoxList[i].setSelected(false);
            checkBoxList[i].setVisible(true);
        }
    }


    public void checkBoxSelected(ActionEvent event) {
        if (event.getSource() instanceof CheckBox) {
            CheckBox chk = (CheckBox) event.getSource();
            if ("checkBox1".equals(chk.getText())) {
                checkBox1.setSelected(!checkBox1.isSelected());
            } else if ("checkBox2".equals(chk.getText())) {
                checkBox2.setSelected(!checkBox2.isSelected());
            } else if ("checkBox3".equals(chk.getText())) {
                checkBox3.setSelected(!checkBox3.isSelected());
            }
        }
    }
}
