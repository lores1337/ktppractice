package com.company;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class task5 {
    public static void main(String[] args) {
        
    }
    private static int[] encrypt(String str){  // функция отправляет закодированное сообщение по входной строке str
        int[] array = new int[str.length()];  // первая буква закодированного сообщения - символьный код первой буквы str
        array[0] = str.charAt(0);            // следующие буквы - разность между следующей буквой и предыдущей,пр. в char
        for(int i=1;i<str.length();i++){
             array[i] = (str.charAt(i) - str.charAt(i-1));
        }
        return array;
    }
    private static String decrypt(int[] array){// функция принимает закодированное сообщение по элементам вх. массива array
        String str = "";                 // первая буква декодированного сообщения - символьный код первого элемента array
        str += (char) array[0];         // следующие буквы - сумма предыдущей буквы закодированного сообщения и
        for(int i=1;i<array.length;i++){// следующего элемента массива array,пребразованная в char
            int a = str.charAt(i-1);
            str += (char) (a + array[i]);
        }
        return str;
    }

    private static boolean canMove(String name,String dpos,String npos){ // функция принимает имя шахматной фигуры(name),
        if( dpos.equals(npos)) return false; //исходную позицию(dpos),конечную позицию(npos) и возвращает true,если может
        if(Character.getNumericValue(dpos.charAt(1)) > 9 || Character.getNumericValue(npos.charAt(1)) > 9 || // false иначе
                Character.getNumericValue(dpos.charAt(1)) < 1 || Character.getNumericValue(npos.charAt(1)) < 1) return false;
        if(name.equals("Rook")) {
            return (dpos.charAt(0) == npos.charAt(0) || dpos.charAt(1) == dpos.charAt(1));
        }
        else if(name.equals("Bishop")){
            int a = (int) npos.charAt(0) - (int) dpos.charAt(0);
            return (Character.getNumericValue(npos.charAt(1)) + a == Character.getNumericValue(dpos.charAt(1)));
        }
        else if(name.equals("King")){
            int a = Math.abs((int) npos.charAt(0) - (int) dpos.charAt(0));
            int b = Math.abs(Character.getNumericValue(dpos.charAt(1)) - Character.getNumericValue(npos.charAt(1)));
            return ((a == 0 || a == 1) && (b == 0 || b == 1));
        }
        else if(name.equals("Pawn")){
            int a = Character.getNumericValue(npos.charAt(1)) - Character.getNumericValue(dpos.charAt(1));
            return (dpos.charAt(0) == npos.charAt(0)  && a == 1);
        }
        else if(name.equals("Horse")){
            int a = Math.abs((int) npos.charAt(0) - (int) dpos.charAt(0));
            if(a == 0 || a > 2) return false;
            else if(a == 1){
                return (Math.abs(Character.getNumericValue(npos.charAt(1)) - Character.getNumericValue(dpos.charAt(1))) == 2 );
            }
            else if(a == 2){
                return (Math.abs(Character.getNumericValue(npos.charAt(1)) - Character.getNumericValue(dpos.charAt(1))) == 1 );
            }
        }
        else if(name.equals("Queen")){
            if(dpos.charAt(0) == npos.charAt(0) || dpos.charAt(1) == npos.charAt(1)) return true;
            return(Math.abs((int) npos.charAt(0) - (int) dpos.charAt(0)) ==
                    Math.abs(Character.getNumericValue(npos.charAt(1)) - Character.getNumericValue(dpos.charAt(1))));
        }
        return false;
    }

    private static int kolvoOneSimbol(String a,char c){  // функция для подсчета количества символов c в строке a
        int kolvo = 0;
        for(int i=0;i<a.length();i++){
            if(a.charAt(i) == c) kolvo++;
        }
        return kolvo;
    }
    private static boolean canComplete(String a,String b){ // функция возвращает true,если входная строка a может быть завершена
        if(a.charAt(0) != b.charAt(0)) return false;      // (привести к виду до строки b). При этом никакие буквы нельзя удалить
        int k = 0;                                       // и нельзя изменять порядок букв во входной строке a; false иначе
        for(char c = 'a';c <= 'z';c++){
            if(kolvoOneSimbol(a,c) > kolvoOneSimbol(b,c)) return false;
        }
        return true;
    }

    private static int sumDigProd(int ... v){  // функция принимает переменное число аргументов,складывает их вместе
        int k = 0;                             // и возвращает произведение цифр до тех пор,пока ответ не станет < 10
        for(int i=0;i<v.length;i++)
            k+=v[i];
        int u = 1;
        String s = Integer.toString(k);
        for(int j=0;j<s.length();j++)
            u*=Character.getNumericValue(s.charAt(j));
        if(u>9) return sumDigProd(u);
        else return u;
    }

    private static boolean sameGl(String a,String g){ // функция возвращает true, если строка a содержит те же гласные
        final String gl = "aeiouy";                  // что и в строке g
        String gd = "";
        for(int j=0;j<gl.length();j++) {
            for (int i = 0; i < a.length(); i++) {
                if(gl.charAt(j) == a.charAt(i)) {
                    gd+=gl.charAt(j);
                    break;
                }
            }
        }
        return g.equals(gd);
    }
    private static String[] sameVowelGroup(String[] ua){ // функция возвращает массив строк,в который входят все слова
        int k = 0;                                      // из массива ua,которые имеют все те же гласные что и 1 слово в ua
        final String gl = "aeiouy";
        String gd = "";
        for(int j=0;j<gl.length();j++) {
            for (int i = 0; i < ua[0].length(); i++) {
                if(gl.charAt(j) == ua[0].charAt(i)) {
                    gd+=gl.charAt(j);
                    break;
                }
            }
        }
        for(int i=1;i<ua.length;i++){
            if(sameGl(ua[i],gd)) k++;
        } String[] b = new String[k+1];
        b[0] = ua[0];
        k = 1;
        for(int i=1;i<ua.length;i++){
            if(sameGl(ua[i],gd)) {
                b[k] = ua[i];
                k++;
            }
        }
        return b;
    }

    private static boolean validateCard(long a) { // функция возвращает true,если число a является действительным номером
        String s = Long.toString(a);             // кредитной карты; false иначе
        String s2 = "";
        if(s.length() < 14 || s.length() > 19) return false;  // длина > 14 и < 19
        for(int i=0;i<s.length()-1;i++){
            s2 += s.charAt(i);    // step 1 - удаляем последнюю цифру
        }
        int step1 = Character.getNumericValue(s.charAt(s.length()-1));
        StringBuffer s3 = new StringBuffer(s2);
        s3.reverse();      // step 2 - переворачиваем число
        String s4 = "";
        for(int i=0;i<s3.length();i++){   // step 3 - удвоим все цифры,стоящие в нечетных позициях
            if( i % 2 == 0){
                int c = Character.getNumericValue(s3.charAt(i)) * 2;
                if(c < 10)
                    s4 += Integer.toString(c);
                else if( c == 10)
                    s4 += Integer.toString(1);
                else {
                    String u = Integer.toString(c);
                    int d = Character.getNumericValue(u.charAt(0)) + Character.getNumericValue(u.charAt(1));
                    s4 += Integer.toString(d);
                }
            }
            else s4 += s3.charAt(i);
        }
        int u = 0;
        for(int i=0;i<s4.length();i++)
            u+= Character.getNumericValue(s4.charAt(i));     // step 4 - складываем все цифры
        String step4 = Integer.toString(u);
        return (10 - Character.getNumericValue(step4.charAt(step4.length()-1)) == step1);   // step 5
    }

    private static String numToEng(int a){  //функция возвращает строковое представление числа a(0-999) на английском языке
        if(!(a >= 0 && a <= 999)) return "Error";
        String s = "";
        String[] onechifr = {"zero","one","two","three","four","five","six","seven","eight","nine"};
        String[] tn = {"ten","eleven","twelve","thirteen","fourteen","fifteen","sixteen","seventeen","eighteen","nineteen","twenty"};
        String[] twochifr = {"","","twenty","thirty","forty","fifty","sixty","seventy","eighty","ninety"};
        String[] threechift = {"","one hundred","two hundred","three hundred","four hundred","five hundred",
                "six hundreds","seven hundred","eight hundred","nine hundred"};
        if(a < 10) return onechifr[a];
        else if(a>= 10 && a <= 20) return tn[a-10];
        else if(a>20 && a<100) {
            String b = Integer.toString(a); // число в строку
            int fi = Character.getNumericValue(b.charAt(0)); // десятки
            int tw = Character.getNumericValue(b.charAt(1)); // единицы
            s += twochifr[fi] + " " + onechifr[tw];
        }
        else if(a % 100 == 0){
            String b = Integer.toString(a); // число в строку
            int fi = Character.getNumericValue(b.charAt(0)); // сотни
            s += threechift[fi];
        }
        else {
            String b = Integer.toString(a); // число в строку
            int fi = Character.getNumericValue(b.charAt(0)); // сотни
            int tw = Character.getNumericValue(b.charAt(1)); // десятки
            int th = Character.getNumericValue(b.charAt(2)); // единицы
            s += threechift[fi] + " " + twochifr[tw] + " " + onechifr[th];
        }
        return s;
    }

    private static String getSha256Hash(String s) throws NoSuchAlgorithmException{ //функция возвращает хеш SHA-256 для строки s
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[]hashInBytes = md.digest(s.getBytes(StandardCharsets.UTF_8));
        StringBuilder sb = new StringBuilder();
        for (byte b : hashInBytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    private static String correctTitle(String s){// функция возвращает строку s с правильным регистром
        String s2="";            // (слова and the of in должны быть строчными),а остальные начинаться с большой буквы
        int k = 1;
        for(int i=0;i<s.length()-1;i++) {
            s2 += Character.toLowerCase(s.charAt(i));
            if(s.charAt(i) == ' ') k++;
        }
        String[] z = new String[k];
        Arrays.fill(z,"");
        int j = 0;
        for(int i=0;i<s2.length();i++){
            if(s2.charAt(i) == ' ') j++;
            else z[j]+=s2.charAt(i);
        }
        for(int i=0;i<z.length;i++){
            if(!(z[i].equals("and") || z[i].equals("the") || z[i].equals("of") || z[i].equals("in"))){
                String w = "";
                for(int v=0;v<z[i].length();v++){
                    w+=z[i].charAt(v);
                }
                z[i] = "";
                for(int v=0;v<w.length();v++){
                    if(v == 0) z[i] += Character.toUpperCase(w.charAt(v));
                    else z[i] += w.charAt(v);
                }
            }
        }
        s2="";
        for(int i=0;i<z.length;i++){
            if(i != (z.length-1)) s2 += z[i] + " ";
            else s2 += z[i];
        }
        s2+=".";
        return s2;
    }

    private static int sixSer(int b){  // функция,находящая длину серединной линии не учитывая пробелов по входному числу b
        if (b < 19 && b != 7) return -1;
        if (b == 7) return 3;
        int ser = 5;
        while (1 > 0){
            int v = ser + (ser-1)*2 + (ser-2)*2;
            if(v == b) return ser;
            if( v > b) return -1;
            ser+=2;
        }
    }
    private static String hexLattice(int a){ // функция возвращает строку,представляющую шестиугольник и "Invalid!" иначе
        if( a == 1) return " o ";
        String str = "";
        int ser = sixSer(a); // нахождение количества кружков в серединной линии
        if( ser == -1) return "Invalid!";
        str+="  ";
        for(int i=0;i<(ser-2)*2;i++){
            if(i % 2 == 0) str+= " ";
            else str+= "o";
        } str+="  \n ";
        for(int i=0;i<(ser-1)*2;i++){
            if( i % 2 == 0) str+= " ";
            else str+= "o";
        } str+= " \n";
        for(int i=0;i<ser*2;i++){
            if( i % 2 == 0) str+=" ";
            else str+= "o";
        } str+= "\n ";
        for(int i=0;i<(ser-1)*2;i++){
            if( i % 2 == 0) str+= " ";
            else str+= "o";
        } str+= " \n  ";
        for(int i=0;i<(ser-2)*2;i++){
            if(i % 2 == 0) str+= " ";
            else str+= "o";
        } str+="  \n ";
        return str;
    }
}