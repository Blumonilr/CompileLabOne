public class Token {

    private int type;//0为无法识别token，1为保留字，2为标识符，3为整数，4为小数，5为操作符，6为标点，7为注释，8为字符串
    private String content;

    public Token(int type, String content) {
        this.type = type;
        this.content = content;
    }


    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String toString(){
        String re="Token: ";
        re+=content;
        re+="  ";
        switch (type){
            case 0:
                re+="Unknown Token";
                break;
            case 1:
                re+="Reserved Word";
                break;
            case 2:
                re+="Identifier";
                break;
            case 3:
                re+="Integer";
                break;
            case 4:
                re+="Double";
                break;
            case 5:
                re+="Operator";
                break;
            case 6:
                re+="Punctuation";
                break;
            case 7:
                re+="Note";
                break;
            case 8:
                re+="String";
                break;
        }

        return re;
    }
}
