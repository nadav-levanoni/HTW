/*
 * Name: Lucian Petriuc & Nadav Levanoni, Class Name: Trivia
Description: Part of trivia. Multiple choice question.
 */
public class Question{

    private String question;
    private String[]options;
    private String answer;
    private String optionsString;
    private boolean hasBeenAsked;

    public Question(String question, String[] options, String answer){

        this.question = question;
        this.options = options;
        this.answer = answer;
        this.optionsString = "";
        this.hasBeenAsked = false;

    }

    public String getQuestion(){
        return question;
    }

    public String[] getOptions(){
        return options;
    }

    public String getAnswer()                                                {
        return answer;
    }

    public String getOptionsString() {
        for(int i = 0; i < 4; i++)
            optionsString += (options[i] + "\n");
        return  optionsString;
    }

    public boolean getHasBeenAsked(){
        return hasBeenAsked;
    }

    public void askQuestion(){
        hasBeenAsked = true;
    }

    public String toString(){

        getOptionsString();

        return question + "\n" + optionsString + answer;

    }

}