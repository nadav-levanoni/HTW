/*
Name: Lucian Petriuc & Nadav Levanoi, Class Name: Trivia
Description: Asks Trivia Questions and keeps track of the answers by the user
*/
import java.io.IOException;
import java.util.*;

import java.util.*;
import java.io.*;

public class Trivia {

    private static final int numTotalQuestions = 100; //number of questions in question bank

    private Question[] questions;
    private Scanner input;

    public Trivia() throws IOException{
    	String path = "";
        input = new Scanner(new File(path + "C:\\Users\\nadav\\OneDrive\\Desktop\\htw19_p4_fazeclans\\Hunt the Wumpus\\input\\triviaQuestions.txt"));
        questions = readFile();

    }

    private Question[] readFile(){

        Question[] readingQuestions =  new Question[numTotalQuestions];
        String question;
        String[][] options = new String[numTotalQuestions][4];
        String answer;

        for(int i = 0; i < numTotalQuestions; i++){

            question = input.nextLine();
            for(int j = 0; j < 4; j++)
                options[i][j] = input.nextLine();
            answer = input.nextLine();
            readingQuestions[i]= new Question(question, options[i], answer);

        }
        return readingQuestions;
    }

    public ArrayList<Question> getQuestion(int numQuestions){
        ArrayList<Question> questionHolder = new ArrayList<Question>();
        int num = -1;
        for(int i = 0; i < numQuestions; i++){
            num = (int)(Math.random()*numTotalQuestions);
            if(questions[i].getHasBeenAsked()){
                i--;
            }
            else{
                questionHolder.add(questions[num]);
                questions[i].askQuestion();
            }
        }
        return questionHolder;
    }

}

