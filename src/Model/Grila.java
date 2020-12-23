package Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Clasa care reprezinta o grila din Quiz
 */
public class Grila {
    private String question;    // intrebarea
    private Integer nr_correct; // numarul de raspunsuri corecte
    private ArrayList<String> correct; // lista cu raspunsuri corecte
    private ArrayList<String> allAnswers; // lista cu toate raspunsurile


    /**
     * Constructor pt initializarea unei grile
     * Prima linie din lista-intrebarea
     * A doua linie- nr. de rsp. corecte n
     * Urmatoarele n linii- raspunsurile corecte
     * Liniile ramase- raspunsurile gresite
     * @param list - lista de stringuri care contine intrebarea,nr. de rsp corecte, rsp. corecte si toate rsp.
     */
    public Grila(List<String> list){
        correct = new ArrayList<>();
        allAnswers= new ArrayList<>();
        question = list.get(0);
        nr_correct = Integer.valueOf(list.get(1));
        for(int i = 0; i<nr_correct; i++){
            correct.add(list.get(i+2));
        }

        for(int i =2; i < list.size();i++){
            allAnswers.add(list.get(i));
        }
    }

    /**
     * Getter pt intrebare
     */
    public String getQuestion() {
        return question;
    }

    /**
     * Getter pt raspunsul corect
     * @return - returneaza lista de raspunsuri corecte
     */
    public ArrayList<String> getCorrect() {
        return correct;
    }

    /**
     * Getter pt lista cu toate variantele de raspuns
     */
    public ArrayList<String> getAllAnswers() {
        return allAnswers;
    }

    @Override
    public String toString() {
        return "Grila{" +
                "question='" + question + '\'' +
                ", nr_correct=" + nr_correct +
                ", correct=" + correct +
                ", allAnswers=" + allAnswers +
                '}';
    }
}
