package LexcialAnalysis;

import java.util.Objects;
import java.util.Scanner;

public class Java_LexAnalysis
{
    private static StringBuffer prog = new StringBuffer();

    /**
     *  this method is to read the standard input
     */
    private static void read_prog()
    {
        Scanner sc = new Scanner(System.in);
        while(sc.hasNextLine())
        {
            prog.append(sc.nextLine());
        }
    }


    // add your method here!!

    static class Para{
        int count;  //lexical count
        int i;  //iteration

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Para para = (Para) o;
            return count == para.count && i == para.i;
        }

        @Override
        public int hashCode() {
            return Objects.hash(count, i);
        }

        public Para(int count, int i) {
            this.count = count;
            this.i = i;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public int getI() {
            return i;
        }

        public void setI(int i) {
            this.i = i;
        }
    }



    public static boolean isAlpha(char c){
        return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z');
    }

    public  static  boolean isNumber(char c){
        return c >= '0' && c <= '9';
    }

    /*
    Identifier/Keyword Automaton: a(a|n)*
     */
    public static Para addIdentifier_Keyword(Para currentPara, final char currentChar){

        int lexicalCount = currentPara.getCount();
        int i = currentPara.getI();

        int index = 0;  //index of lexical kind
        StringBuilder token = new StringBuilder();

        if(isAlpha(currentChar)){
            lexicalCount++;

            do{
                token.append(prog.charAt(i));  //identify alpha or number
                i++;
            }
            while(i < prog.length() && (isAlpha(prog.charAt(i)) || isNumber(prog.charAt(i))));
            i--;    //back to the last char of the identifier

            //set its index
            String result = token.toString();
            switch (result) {
                case "auto": {
                    index = 1;
                    break;
                }
                case "break": {
                    index = 2;
                    break;
                }
                case "case": {
                    index = 3;
                    break;
                }
                case "char": {
                    index = 4;
                    break;
                }
                case "const": {
                    index = 5;
                    break;
                }
                case "continue": {
                    index = 6;
                    break;
                }
                case "default": {
                    index = 7;
                    break;
                }
                case "do": {
                    index = 8;
                    break;
                }
                case "double": {
                    index = 9;
                    break;
                }
                case "else": {
                    index = 10;
                    break;
                }
                case "enum": {
                    index = 11;
                    break;
                }
                case "extern": {
                    index = 12;
                    break;
                }
                case "float": {
                    index = 13;
                    break;
                }
                case "for": {
                    index = 14;
                    break;
                }
                case "goto": {
                    index = 15;
                    break;
                }
                case "if": {
                    index = 16;
                    break;
                }
                case "int": {
                    index = 17;
                    break;
                }
                case "long": {
                    index = 18;
                    break;
                }
                case "register": {
                    index = 19;
                    break;
                }
                case "return": {
                    index = 20;
                    break;
                }
                case "short": {
                    index = 21;
                    break;
                }
                case "signed": {
                    index = 22;
                    break;
                }
                case "sizeof": {
                    index = 23;
                    break;
                }
                case "static": {
                    index = 24;
                    break;
                }
                case "struct": {
                    index = 25;
                    break;
                }
                case "switch": {
                    index = 26;
                    break;
                }
                case "typedef": {
                    index = 27;
                    break;
                }
                case "union": {
                    index = 28;
                    break;
                }
                case "unsigned": {
                    index = 29;
                    break;
                }
                case "void": {
                    index = 30;
                    break;
                }
                case "volatile": {
                    index = 31;
                    break;
                }
                case "while": {
                    index = 32;
                    break;
                }
                default: {
                    //is identifier
                    index = 81;
                    break;
                }
            }

            //clear token
            token.delete(0,token.length());

            //print result
            if (i == prog.length() - 1) {
                System.out.print(lexicalCount + ": " + "<" + result + "," + index + ">");
            }
            else    System.out.println(lexicalCount + ": " + "<" + result + "," + index + ">");
        }
        return new Para(lexicalCount, i);
    }

    /*
    Constant Automaton: n n*
     */
    public static Para addConstant(Para currentPara, final char currentChar){

        int lexicalCount = currentPara.getCount();
        int i = currentPara.getI();

        int index = 0;
        StringBuilder token = new StringBuilder();

        if(isNumber(currentChar)){

            lexicalCount++;

            do{
                token.append(prog.charAt(i));
                i++;
            }
            while (isNumber(prog.charAt(i)));
            i--;    //back to the last char of the constant

            //set its index
            String result = token.toString();
            index = 80;

            //clear token
            token.delete(0,token.length());

            //print result
            if (i == prog.length() - 1) {
                System.out.print(lexicalCount + ": " + "<" + result + "," + index + ">");
            }
            else    System.out.println(lexicalCount + ": " + "<" + result + "," + index + ">");
        }

        return new Para(lexicalCount, i);
    }

    /*
    Boundary Automaton: ( ) , . : ; [ ] { } "  // / * * /
     */
    public static Para addBoundary(Para currentPara, final char currentChar){

        int lexicalCount = currentPara.getCount();
        int i = currentPara.getI();

        int index = 0;
        StringBuilder token = new StringBuilder();

        token.append(currentChar);

        switch (currentChar){
            case '(': {
                index = 44;
                break;
            }
            case ')': {
                index = 45;
                break;
            }
            case ',': {
                index = 48;
                break;
            }
            case '.': {
                index = 49;
                break;
            }
            case ':': {
                index = 52;
                break;
            }
            case ';': {
                index = 53;
                break;
            }
            case '[': {
                index = 55;
                break;
            }
            case ']': {
                index = 56;
                break;
            }
            case '{': {
                index = 59;
                break;
            }
            case '}': {
                index = 63;
                break;
            }
            case '"': {
                index = 78;
                break;
            }
            case '/': {
                char followpos = ' ';
                i++;    //let i point to followpos
                if(i <= prog.length()-1) followpos = prog.charAt(i);    //set next char with overflow considered

                //which kind of annotation
                switch (followpos){

                    //  "//" read to the end of the line
                    case '/': {
                        token.append(prog.charAt(i));
                        i++;   // i points to the first char of annotation

                        while(i < prog.length()){
                            if(prog.charAt(i) == '\t')  break;
                            token.append(prog.charAt(i));
                            i++;
                        }
                        index = 79;
                        break;
                    }

                    //  "/* */" read until */
                    case '*': {
                        while (true){
                            if(prog.charAt(i) == '*' && prog.charAt(i+1) == '/'){
                                token.append(prog.charAt(i)).append(prog.charAt(i+1));
                                i++;    //move to the last char of the annotation
                                break;
                            }
                            else{
                                token.append(prog.charAt(i));
                                i++;
                            }
                        }
                        index = 79;
                        break;
                    }
                    default: break;
                }
                break;
            }
            default: break;
        }

        if(index == 0)  return new Para(lexicalCount, i);

        lexicalCount++;

        String result = token.toString();

        //clear token
        token.delete(0,token.length());

        //print result
        if (i == prog.length() - 1) {
            System.out.print(lexicalCount + ": " + "<" + result + "," + index + ">");
        }
        else    System.out.println(lexicalCount + ": " + "<" + result + "," + index + ">");

        return new Para(lexicalCount, i);
    }

    /*
    Operator Automaton
     */
    public static Para addOperator(Para currentPara, final char currentChar){

        int lexicalCount = currentPara.getCount();
        int i = currentPara.getI();

        int index = 0;
        StringBuilder token = new StringBuilder();

        token.append(currentChar);

        char followpos = ' ';
        if(i+1 <= prog.length()-1) followpos = prog.charAt(i+1);    //set next char with overflow considered

        switch (currentChar){
            case '-': {
                switch (followpos){
                    case '-': { //--
                        index = 34;
                        token.append(followpos);
                        i++;
                        break;
                    }
                    case '=': { //-=
                        index = 35;
                        token.append(followpos);
                        i++;
                        break;
                    }
                    case '>': { //->
                        index = 36;
                        token.append(followpos);
                        i++;
                        break;
                    }
                    default: {  //-
                        index = 33;
                        break;
                    }
                }
                break;
            }
            case '!': {
                if(followpos == '='){
                    index = 38;
                    token.append(followpos);
                    i++;
                }
                else index = 37;
                break;
            }
            case '%': {
                if(followpos == '='){
                    index = 40;
                    token.append(followpos);
                    i++;
                }
                else if(followpos == 'd' || followpos == 's' || followpos == 'f' || followpos == 'c') {
                    index = 81;
                    token.append(followpos);
                    i++;
                }
                else index = 39;

                break;
            }
            case '&': {
                if(followpos == '&'){
                    index = 42;
                    token.append(followpos);
                    i++;
                }
                else if(followpos == '='){
                    index = 43;
                    token.append(followpos);
                    i++;
                }
                else index = 41;
                break;
            }
            case '*': {
                if(followpos == '='){
                    index = 47;
                    token.append(followpos);
                    i++;
                }
                else index = 46;
                break;
            }
            case '/': {
                if(followpos == '='){
                    index = 51;
                    token.append(followpos);
                    i++;
                }
                else index = 50;
                break;
            }
            case '?': {
                index = 54;
            }
            case '^': {
                if(followpos == '='){
                    index = 58;
                    token.append(followpos);
                    i++;
                }
                else index = 57;
                break;
            }
            case '|': {
                switch (followpos){
                    case '=': { //|=
                        index = 62;
                        token.append(followpos);
                        i++;
                        break;
                    }
                    case '|': { //||
                        index = 61;
                        token.append(followpos);
                        i++;
                        break;
                    }
                    default: {  //|
                        index = 60;
                        break;
                    }
                }
                break;
            }
            case '~': {
                index = 64;
            }
            case '+': {
                switch (followpos){
                    case '=': { //+=
                        index = 67;
                        token.append(followpos);
                        i++;
                        break;
                    }
                    case '+': { //++
                        index = 66;
                        token.append(followpos);
                        i++;
                        break;
                    }
                    default: {  //+
                        index = 65;
                        break;
                    }
                }
                break;
            }
            case '<': {
                switch (followpos){
                    case '<': {
                        if(prog.charAt(i+2) == '='){    //<<=
                            index = 70;
                            token.append(followpos).append(prog.charAt(i+2));
                            i = i+2;
                        }
                        else{
                            index = 69;    //<<
                            token.append(followpos);
                            i++;
                        }
                        break;
                    }
                    case '=': { //<=
                        index = 71;
                        token.append(followpos);
                        i++;
                        break;
                    }
                    default: {  //<
                        index = 68;
                        break;
                    }
                }
                break;
            }
            case '=': {
                if(followpos == '='){   //==
                    index = 73;
                    token.append(followpos);
                    i++;
                }
                else index = 72;
                break;
            }
            case '>': {
                switch (followpos){
                    case '>': {
                        if(prog.charAt(i+2) == '='){    //>>=
                            index = 77;
                            token.append(followpos).append(prog.charAt(i+2));
                            i = i+2;
                        }
                        else{
                            index = 76;    //>>
                            token.append(followpos);
                            i++;
                        }
                        break;
                    }
                    case '=': { //>=
                        index = 75;
                        token.append(followpos);
                        i++;
                        break;
                    }
                    default: {  //>
                        index = 74;
                        break;
                    }
                }
                break;
            }
        }

        lexicalCount++;

        String result = token.toString();

        //clear token
        token.delete(0,token.length());

        //print result
        if (i == prog.length() - 1) {
            System.out.print(lexicalCount + ": " + "<" + result + "," + index + ">");
        }
        else    System.out.println(lexicalCount + ": " + "<" + result + "," + index + ">");

        return new Para(lexicalCount, i);
    }




    /**
     *  you should add some code in this method to achieve this lab
     */
    private static void analysis()
    {
        read_prog();

        Para para = new Para(0, 0);

        for(;para.getI() < prog.length();para.setI(para.getI()+1)){

            char current = prog.charAt(para.getI());

            if (current == ' ' || current == '\t' || current == '\n') {
                continue;
            }

            if(isAlpha(current))    para = addIdentifier_Keyword(para, current);
            else if(isNumber(current))  para = addConstant(para, current);
            else {
                Para old_para = para;
                para = addBoundary(para, current);
                if(para.equals(old_para))    para = addOperator(para, current);  //if not boundary, then operator; else, next iteration.
            }
        }
    }

    /**
     * this is the main method
     * @param args
     */
    public static void main(String[] args) {
        analysis();
    }
}
