import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Analyzer {

    List<Token> output;

    public Analyzer(){
        output=new ArrayList<>();
    }

    public void analyze(String input){
        String preToken;
        int ptr = 0;
        while(input.charAt(ptr)!='$'){
            preToken="";
            if (isLetter(input.charAt(ptr))){//判断为标识符或者保留字
                preToken+=input.charAt(ptr);
                ptr++;

                while (isLetter(input.charAt(ptr))||isDigit(input.charAt(ptr))){
                    preToken+=input.charAt(ptr);
                    ptr++;
                }

                if (isReservedWord(preToken)){
                    output.add(new Token(1,preToken));
                }else {
                    output.add(new Token(2,preToken));
                }
            }else if(isDigit(input.charAt(ptr))){//判断为数字

                //读取最左前缀数字
                while (isDigit(input.charAt(ptr))) {
                    preToken+=input.charAt(ptr);
                    ptr++;
                }

                if (input.charAt(ptr)=='.'){//判断为小数
                    preToken+=".";
                    ptr++;

                    if (!isDigit(input.charAt(ptr))){
                        output.add(new Token(0,preToken));
                    }else {
                        while (isDigit(input.charAt(ptr))) {
                            preToken+=input.charAt(ptr);
                            ptr++;
                        }
                        output.add(new Token(4,preToken));
                    }
                }else {//判断为整数
                    output.add(new Token(3,preToken));
                }
            }else {
                preToken+=input.charAt(ptr);
                if (input.charAt(ptr)==','){
                    ptr++;
                    output.add(new Token(6,preToken));
                }else if (input.charAt(ptr)=='.'){
                    ptr++;
                    output.add(new Token(5,preToken));
                }else if (input.charAt(ptr)==':'){
                    ptr++;
                    output.add(new Token(6,preToken));
                }else if (input.charAt(ptr)==';'){
                    ptr++;
                    output.add(new Token(6,preToken));
                }else if (input.charAt(ptr)=='('){
                    ptr++;
                    output.add(new Token(5,preToken));
                }else if (input.charAt(ptr)==')'){
                    ptr++;
                    output.add(new Token(5,preToken));
                }else if (input.charAt(ptr)=='['){
                    ptr++;
                    output.add(new Token(5,preToken));
                }else if (input.charAt(ptr)==']'){
                    ptr++;
                    output.add(new Token(5,preToken));
                }else if (input.charAt(ptr)=='{'){
                    ptr++;
                    output.add(new Token(5,preToken));
                }else if (input.charAt(ptr)=='}'){
                    ptr++;
                    output.add(new Token(5,preToken));
                }else if (input.charAt(ptr)=='+'){
                    ptr++;

                    if (input.charAt(ptr)=='='){
                        preToken+='=';
                        ptr++;
                        output.add(new Token(5,preToken));
                    }else if (input.charAt(ptr)=='+'){
                        preToken+='+';
                        ptr++;
                        output.add(new Token(5,preToken));
                    }else {
                        output.add(new Token(5,preToken));
                    }
                }else if (input.charAt(ptr)=='-'){
                    ptr++;

                    if (input.charAt(ptr)=='='){
                        preToken+='=';
                        ptr++;
                        output.add(new Token(5,preToken));
                    }else if (input.charAt(ptr)=='-'){
                        preToken+='-';
                        ptr++;
                        output.add(new Token(5,preToken));
                    }else if (isDigit(input.charAt(ptr))){
                        preToken+=input.charAt(ptr);
                        ptr++;

                        while (isDigit(input.charAt(ptr))){
                            preToken+=input.charAt(ptr);
                            ptr++;
                        }

                        if (input.charAt(ptr)=='.'){
                            preToken+='.';
                            ptr++;

                            if (!isDigit(input.charAt(ptr))){
                                output.add(new Token(0,preToken));
                            }else {
                                while (isDigit(input.charAt(ptr))) {
                                    preToken+=input.charAt(ptr);
                                    ptr++;
                                }
                                output.add(new Token(4,preToken));
                            }
                        }else {//判断为整数
                            output.add(new Token(3,preToken));
                        }
                    }else {
                        output.add(new Token(5,preToken));
                    }
                }else if (input.charAt(ptr)=='*'){
                    ptr++;

                    if(input.charAt(ptr)=='='){
                        preToken+='=';
                        ptr++;
                        output.add(new Token(5,preToken));
                    }else {
                        output.add(new Token(5,preToken));
                    }
                }else if(input.charAt(ptr)=='/'){
                    ptr++;

                    if (input.charAt(ptr)=='='){
                        preToken+='=';
                        ptr++;
                        output.add(new Token(5,preToken));
                    }else if (input.charAt(ptr)=='/'){// //型注释
                        preToken+='/';
                        ptr++;
                        while (input.charAt(ptr)!='\n'){
                            preToken+=input.charAt(ptr);
                            ptr++;
                        }
                        output.add(new Token(7,preToken));
                    }else if (input.charAt(ptr)=='*'){// /*型注释
                        preToken+='*';
                        ptr++;
                        while (true){
                            if (input.charAt(ptr)=='\n'){
                                ptr++;
                            }else if (input.charAt(ptr)=='*'){
                                preToken+='*';
                                ptr++;
                                if (input.charAt(ptr)=='/'){
                                    preToken+='/';
                                    ptr++;
                                    output.add(new Token(7,preToken));
                                    break;
                                }else {
                                    preToken+=input.charAt(ptr);
                                    ptr++;
                                }
                            }else {
                                preToken+=input.charAt(ptr);
                                ptr++;
                            }
                        }
                    }else {
                        output.add(new Token(5,preToken));
                    }
                }else if (input.charAt(ptr)=='='){
                    ptr++;

                    if (input.charAt(ptr)=='='){
                        preToken+='=';
                        ptr++;
                        output.add(new Token(5,preToken));
                    }else {
                        output.add(new Token(5,preToken));
                    }
                }else if (input.charAt(ptr)=='&'){
                    ptr++;

                    if (input.charAt(ptr)=='&'){
                        preToken+='&';
                        ptr++;
                        output.add(new Token(5,preToken));
                    }else {
                        output.add(new Token(5,preToken));
                    }
                }else if (input.charAt(ptr)=='|'){
                    ptr++;

                    if (input.charAt(ptr)=='|'){
                        preToken+='|';
                        ptr++;
                        output.add(new Token(5,preToken));
                    }else {
                        output.add(new Token(5,preToken));
                    }
                }else if (input.charAt(ptr)=='!'){
                    ptr++;

                    if (input.charAt(ptr)=='='){
                        preToken+='=';
                        ptr++;
                        output.add(new Token(5,preToken));
                    }else {
                        output.add(new Token(5,preToken));
                    }
                }else if (input.charAt(ptr)=='^'){
                    ptr++;

                    output.add(new Token(5,preToken));
                }else if (input.charAt(ptr)=='>'){
                    ptr++;

                    if (input.charAt(ptr)=='='){
                        preToken+='=';
                        ptr++;
                        output.add(new Token(5,preToken));
                    }else {
                        output.add(new Token(5,preToken));
                    }
                }else if (input.charAt(ptr)=='<'){
                    ptr++;

                    if (input.charAt(ptr)=='='){
                        preToken+='=';
                        ptr++;
                        output.add(new Token(5,preToken));
                    }else {
                        output.add(new Token(5,preToken));
                    }
                }else if (input.charAt(ptr)=='\"'){
                    ptr++;

                    while(true){
                        if (input.charAt(ptr)!= '\n') {
                            preToken+=input.charAt(ptr);
                            if (input.charAt(ptr)== '\"') {
                                ptr++;
                                output.add(new Token(8,preToken));
                                break;
                            } else {
                                ptr++;
                            }
                        } else {
                            ptr++;
                            output.add(new Token(0,preToken));
                            break;
                        }
                    }
                }else{
                    ptr++;
                }
            }
        }

    }


    private boolean isLetter(char c){
        return Character.isLetter(c);
    }

    private boolean isDigit(char c){
        return Character.isDigit(c);
    }

    private boolean isReservedWord(String word){
        return Arrays.asList("void", "class", "public",
                "private", "protected", "for", "if", "else",
                "while", "do", "int", "double", "char", "boolean",
                "String", "new", "try", "catch", "static",
                "return", "this", "main", "switch", "case",
                "break", "continue").contains(word);
    }

    public List<Token> getOutput() {
        return output;
    }
}
