package Model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileRepo {

    /** Citirea din fisierul txt
     * Fisierul meu are forma:
     *
     * Intrebarea 1
     * Numar raspunsuri corecte ( de ex 2)
     * Primul rsp corect
     * Al doilea rsp corect
     * Rsp gresit
     *
     * Intrebarea 2
     * Numar raspunsuri corecte ( de ex 1)
     * Primul rsp corect
     * Rsp gresit
     * Rsp gresit
     *
     * @param f - fisierul
     * @param quiz - Quizul(lista de grile) la care adaug grilele pe baza tuplurilor din fisier
     *               Un "tuplu" are - (intrebare,nr. rsp corecte, rsp. corecte, rsp gresite)
     * In "string" citesc linie cu linie si adaug in "lista"
     * Cand "lista" are tuplul complet, o adaug sub forma de Grila in Quiz/lista de grile
     * @throws FileNotFoundException
     */
    private static void readFile(File f, Quiz quiz) throws FileNotFoundException {

        FileInputStream file = new FileInputStream(f);
        Scanner read = new Scanner(file);

        String string;
        List<String> lista = new ArrayList<>();

        while (read.hasNext()) {
            string = read.nextLine();
            if ((string.isEmpty()) && (!lista.isEmpty())) {  // am terminat de citit un tuplu complet(intrebare,nr. rsp,rsp. corecte,toate rsp.)
                quiz.addGrila(lista);    // adaug grila in Quizz/lista de grile
                lista.clear();   // golesc lista ca sa o pot refolosi pt urmatorul tuplu
            }
            else if ((!string.isEmpty()) && (string.charAt(0) != '/')) {    // citesc liniile dintr-un tuplu
                lista.add(string);
            }
        }
        quiz.addGrila(lista);
        read.close();

    }
}
