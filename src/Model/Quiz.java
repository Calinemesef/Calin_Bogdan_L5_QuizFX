package Model;

import java.util.List;

public class Quiz {
    private List<Grila> quiz;

    public void addGrila(List<String> list){
        quiz.add(new Grila(list));
    }
}
