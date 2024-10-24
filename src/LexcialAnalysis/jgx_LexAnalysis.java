package LexcialAnalysis;

import java.util.ArrayList;
import java.util.Scanner;

public class jgx_LexAnalysis
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

    static int index = 0;


    // add your method here!!
    public static boolean isNumber(char c){
        return c <= '9' && c >= '0';
    }

    public static boolean isAlpha(char c){
        return c <= 'z' && c >= 'a' || c <= 'Z' && c >= 'A';
    }

    /**
     *  you should add some code in this method to achieve this lab
     */
    private static void analysis()
    {
        read_prog();
        int count = 0;
        int index = 0;
        StringBuilder sb = new StringBuilder();
        loop:for (int i = 0; i < prog.length(); i++) {
        char charNow = prog.charAt(i);
        //判断标识符+保留字
        if(isAlpha(charNow)){
            count++;
            while(isAlpha(prog.charAt(i))||isNumber(prog.charAt(i))){
                sb.append(prog.charAt(i));
                i++;
            }
            i--;
            String result = sb.toString();
            //标号判断
            switch(result){
                case "auto" : {index = 1;break;}
                case "break" : {index = 2;break;}
                case "case" : {index = 3;break;}
                case "char" : {index = 4;break;}
                case "const" : {index = 5;break;}
                case "continue" : {index = 6;break;}
                case "default" : {index = 7;break;}
                case "do" : {index = 8;break;}
                case "double" : {index = 9;break;}
                case "else" : {index = 10;break;}
                case "enum" : {index = 11;break;}
                case "extern" : {index = 12;break;}
                case "float" : {index = 13;break;}
                case "for" : {index = 14;break;}
                case "goto" : {index = 15;break;}
                case "if" : {index = 16;break;}
                case "int" : {index = 17;break;}
                case "long" : {index = 18;break;}
                case "register" : {index = 19;break;}
                case "return" : {index = 20;break;}
                case "short" : {index = 21;break;}
                case "signed" : {index = 22;break;}
                case "sizeof" : {index = 23;break;}
                case "static" : {index = 24;break;}
                case "struct" : {index = 25;break;}
                case "switch" : {index = 26;break;}
                case "typedef" : {index = 27;break;}
                case "union" : {index = 28;break;}
                case "unsigned" : {index = 29;break;}
                case "void" : {index = 30;break;}
                case "volatile" : {index = 31;break;}
                case "while" : {index = 32;break;}
                default : {index = 81;break;}
            }
            //清空
            sb.delete(0,sb.length());
            System.out.println(count+": <"+result+","+index+">");
        }
        //如果是常数
        if(isNumber(charNow)){
            count++;
            while(isNumber(prog.charAt(i))){
                sb.append(prog.charAt(i));
                i++;
            }
            i--;
            String result = sb.toString();
            //常数标号设置
            index = 80;
            //清空
            sb.delete(0,sb.length());
            System.out.println(count+": <"+result+","+index+">");
        }

        //溢出检测
        char next = ' ';
        if((i+1)<= prog.length()-1){
            next = prog.charAt(i+1);
        }
        //界符和运算符
        String result;
        switch (charNow){
            case '-' : {
                if (next == '-') {index = 34;sb.append(charNow).append(next);i++;}
                else if (next == '=') {index = 35;sb.append(charNow).append(next);i++;}
                else if (next == '>') {index = 36;sb.append(charNow).append(next);i++;} else {index = 33;sb.append(charNow);}
                break;
            }
            case '!' :{
                if(next == '='){index = 38;sb.append(charNow).append(next);i++;}
                else {index = 37;sb.append(charNow);}
                break;
            }
            case '%' :{
                if(next == '='){index = 40;sb.append(charNow).append(next);i++;}
                else {index = 39;sb.append(charNow);}
                break;
            }
            case '&' :{
                if(next == '='){index = 43;sb.append(charNow).append(next);i++;}
                else if(next == '&'){index = 42;sb.append(charNow).append(next);i++;}
                else {index = 41;sb.append(charNow);}
                break;
            }
            case '(' :{
                index = 44;
                sb.append(charNow);
                break;
            }
            case ')' :{
                index = 45;
                sb.append(charNow);
                break;
            }
            case '*' :{
                if(next == '='){index = 47;sb.append(charNow).append(next);i++;}
                else {index = 46;sb.append(charNow);}
                break;
            }
            case ',' :{
                index = 48;
                sb.append(charNow);
                break;
            }
            case '.' :{
                index = 49;
                sb.append(charNow);
                break;
            }
            case '/' :{
                if(next == '='){index = 51;sb.append(charNow).append(next);i++;}
                else if(next == '/'){
                    //注释，一直检测到行尾
                    while((i!=prog.length()-1)&&prog.charAt(i)!='\t'){
                        sb.append(prog.charAt(i));
                        i++;
                    }

                    index = 79;
                } else if (next == '*') {
                    // 注释，一直检测到*/为止
                    while(true){
                        if(prog.charAt(i)=='*'&&prog.charAt(i+1)=='/'){
                            sb.append(prog.charAt(i)).append(prog.charAt(i+1));
                            i++;
                            break;
                        }
                        sb.append(prog.charAt(i));
                        i++;
                    }
                    index = 79;

                } else {index = 50;sb.append(charNow);}
                break;
            }
            case ':' :{
                index = 52;
                sb.append(charNow);
                break;
            }
            case ';' :{
                index = 53;
                sb.append(charNow);
                break;
            }
            case '?' :{
                index = 54;
                sb.append(charNow);
                break;
            }
            case '[' :{
                index = 55;
                sb.append(charNow);
                break;
            }
            case ']' :{
                index = 56;
                sb.append(charNow);
                break;
            }
            case '^' :{
                if(next == '='){index = 58;sb.append(charNow).append(next);i++;}
                else {index = 57;sb.append(charNow);}
                break;
            }
            case '{' :{
                index = 59;
                sb.append(charNow);
                break;
            }
            case '|' :{
                if(next == '|'){index = 61;sb.append(charNow).append(next);i++;}
                else if(next == '='){index = 62;sb.append(charNow).append(next);i++;}
                else {index = 60;sb.append(charNow);}
                break;
            }
            case '}' :{
                index = 63;
                sb.append(charNow);
                break;
            }
            case '~' :{
                index = 64;
                sb.append(charNow);
                break;
            }
            case '+' :{
                if(next == '+'){index = 66;sb.append(charNow).append(next);i++;}
                else if(next == '='){index = 67;sb.append(charNow).append(next);i++;}
                else {index = 65;sb.append(charNow);}
                break;
            }
            case '<' :{
                if(next == '<'){
                    if(prog.charAt(i+1)=='='){
                        sb.append(prog.charAt(i+1));
                        i++;
                        index = 70;
                    }
                    else{
                        index = 69;sb.append(charNow).append(next);i++;
                    }
                }
                else if(next == '='){index = 71;sb.append(charNow).append(next);i++;}
                else {index = 68;sb.append(charNow);}
                break;
            }
            case '=' :{
                if(next == '='){index = 73;sb.append(charNow).append(next);i++;}
                else {index = 72;sb.append(charNow);}
                break;
            }
            case '>' :{
                if(next == '>'){
                    if(prog.charAt(i+1)=='='){
                        sb.append(prog.charAt(i+1));
                        i++;
                        index = 77;
                    }
                    else{index = 76;sb.append(charNow).append(next);i++;}
                }
                else if(next == '='){index = 75;sb.append(charNow).append(next);i++;}
                else {index = 74;sb.append(charNow);}
                break;
            }
            case '"' :{
                //处理第一个“
                index = 78;
                sb.append(charNow);
                count++;
                result = sb.toString();
                sb.delete(0,sb.length());
                System.out.println(count+": <"+result+","+index+">");
                i++;

                //将“”内的内容视为标识符打印
                while(prog.charAt(i)!='"'){
                    sb.append(prog.charAt(i));
                    i++;
                }
                count++;
                index = 81;
                result = sb.toString();
                sb.delete(0,sb.length());
                System.out.println(count+": <"+result+","+index+">");

                //处理第二个"
                sb.append(prog.charAt(i));
                count++;
                index = 78;
                result = sb.toString();
                sb.delete(0,sb.length());
                System.out.println(count+": <"+result+","+index+">");
                continue loop;
            }
            default:{continue loop;}
        }
        count++;
        result = sb.toString();
        sb.delete(0,sb.length());
        System.out.println(count+": <"+result+","+index+">");
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

