package Model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Quizul efectiv care contine mai multe grile
 */
public class Quiz {
    private List<Grila> quiz;   // lista de grile
    private int score;

    /**
     * Constructor pt initializarea listei de grile
     */
    public Quiz(){
        quiz = new ArrayList<Grila>();
    }

    /**
     * Metoda pt adaugarea unei grile in lista, din fisierul txt
     * @param list - lista de stringuri din fisierul txt
     */
    public void addGrila(List<String> list){
        quiz.add(new Grila(list));
    }

    /**
     * Getter pt intrebare- returneaza intrebarea de la indexul respectiv
     * @param index - indexul din lista de grile
     */
    public String getQuestion(int index){
        return quiz.get(index).getQuestion();
    }

    /**
     * Getter - returneaza lista de raspunsuri corecte de la indexul respectiv
     * @param index - indexul din lista
     */
    public ArrayList<String> getCorrect(int index){
        return quiz.get(index).getCorrect();
    }

    /**
     * Getter - returneaza lista cu toate variantele de raspuns
     * @param index - indexul din lista
     */
    public ArrayList<String> getAllAnswers(int index){
        return quiz.get(index).getAllAnswers();
    }

    /**
     * Getter - returneaza numarul de grile din quiz
     */
    public int getGameSize(){
        return quiz.size();
    }

    /**
     * Getter - returneaza scorul
     */
    public int getScore(){
        return score;
    }

    /**
     * Setter - modifica scorul
     * @param val - valoarea noua
     */
    public void setScore(int val){
        this.score = val;
    }
    
    @Override
    public String toString() {
        return "Quiz{" +
                "quiz=" + quiz +
                ", score=" + score +
                '}';
    }
}
