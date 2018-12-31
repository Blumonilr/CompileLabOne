import java.util.List;

public class Main {

    public static void main(String args[]){
        String input="";
        input+="public boolean a;";
        input+="\n";
        input+="a=true;";
        input+="\n";
        input+="int b=5;";
        input+="\n";
        input+="double c=1.2";
        input+="\n";
        input+="/* This is note*/";
        input+="\n";
        input+="b++;";
        input+="\n";
        input+="String s=\"string\";";
        input+="\n";
        input+="c=(b+1)*2.5;";
        input+="\n";
        input+="return c;";
        input+="\n";

        Analyzer analyzer=new Analyzer();
        input+="$";
        analyzer.analyze(input);
        List<Token> result=analyzer.getOutput();
        for (Token token:result){
            System.out.println(token.toString());
        }
    }
}
