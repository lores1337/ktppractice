package com.company;

import javax.swing.plaf.synth.SynthStyle;
import java.util.Arrays;

public class task4 {
    public static void main(String[] args){
        
    }
    
    private static int bessi_probeli(String str){ // функция,используемая для подсчета пробелов во входной строке str
        int p = 0;
        for(int i=0;i<str.length();i++)
            if(str.charAt(i) == ' ') p++;
        return p;
    }
    private static String[] bessi(int N,int K,String str2){ // функция,выводящая массив строк с учетом условий
        String str=" " + str2;                              // N - количество слов,K - ограничение количества символов на 1 строке
        int size = 0;                                       // str2 - строка,которую нужно оформить
        for(int i=0;i<str.length();i++)
            if(str.charAt(i) == ' ') size++;
        String[] r = new String[size+1];
        String[] r2 = new String[size+1];
        Arrays.fill(r,"");
        Arrays.fill(r2,"");
        int c = 0;
        for(int i=0;i<str.length();i++){
            if(str.charAt(i) == ' ') c++;
            r[c]+=str.charAt(i);           // генерация массива строк,каждый элемент которого является отдельным словом
        } c = 0;
        for(int i=0;i<r2.length;i++){
            if(r2[c].length() - bessi_probeli(r2[c]) > K+1) c++; // если в строке(не учитывая пробелов) символов > (K+1)
            else if(r2[c].length() + r[i].length() - bessi_probeli(r2[c]) > K+1) c++;  // то записываем следующую строку
            r2[c] += r[i];
        }
        return r2;
    }

    private static String[] split(String str) {  // функция,группирующая строку в кластер скобок и возвращает массив строк
        int s = 0;
        int k = 0;
        int ino = 0;
        int inz = 0;
        for(int i=0;i<str.length();i++){
            if(str.charAt(i) == '(') ino++;
            else if(str.charAt(i) == ')') inz++;
            if(ino == inz && ino != 0) {
                k++;  // число элементов будующего массива строк
                ino = 0;
                inz = 0;
            }
        }
        String[] a = new String[k]; k = 0;
        Arrays.fill(a,"");
        for(int i=0;i<str.length();i++){
            if(str.charAt(i) == '(') ino++;
            else if(str.charAt(i) == ')') inz++;
            a[k]+=str.charAt(i);
            if(ino == inz && ino != 0) {  // если совпало количество закрытых и открытых скобок,создаем новый эл. массива
                k++;
                ino = 0;
                inz = 0;
            }
        }
         return a;
    }

    private static String toCamelCase(String str){  // функция преобразует входную строку в str по принципу c_vr -> cVr
        String str2="";
        for(int i=0;i<str.length();i++){
            if(str.charAt(i) == '_'){
                i++;
                str2+=Character.toUpperCase(str.charAt(i));
            }
            else str2+=str.charAt(i);
        }
        return str2;
    }
    private static String toSnakeCase(String str){ // функция преобразует входную строку str по принципу cVr->c_vr
        String str2="";
        for(int i=0;i<str.length();i++){
            if(str.charAt(i) >= 'A' && str.charAt(i) <= 'Z'){
                str2+='_';
                str2+=Character.toLowerCase(str.charAt(i));
            }
            else str2+=str.charAt(i);
        }
        return str2;
    }

                                                     // функция,вычисляющая сверхурочную работу и оплату,связанную с
    private static String overTime(double[] array){  // сверхурочной работой.Работа с 5-9 - обычный график
        String str = "$"; //после-сверхурочная.Массив с 4 значениями-Начало,конец,почасовая ставка,множитель сверхурочных
        double price = 0;
        for(double i = array[0];i < array[1]; i++){
            if(i >= 17) price+=array[2]*array[3];
            else price+=array[2];
        }
        if(array[0] < 17) {if(array[0] - ((int) array[0]) != 0) price-=(array[0] - ((int) array[0]))*array[2];}
        else {
            if(array[0] - ((int) array[0]) != 0) price-=(array[0] - ((int) array[0]))*array[2]*array[3];
        }
        price = Math.round(price * 100.0) / 100.0;  // округление до ближайшей сотой
        str+=Double.toString(price);
        return str;
    }

    private static String BMI(String a,String b){ // функция,возвращающая ИМТ и связанную с ним категорию
        double W = 0; final double poundsatkil = 0.453592; // фунт в кг
        double H = 0; final double inchesatmet = 0.0254;   // дюйм в м
        String ch1; String ch2;
        ch1 = ""; ch2 = "";
        int i = 0;
        while(a.charAt(i) != ' ') {
            ch1+=a.charAt(i);
            i++;
        }
        if(a.charAt(i+1) == 'p') W = Double.parseDouble(ch1) * poundsatkil;
        else if(a.charAt(i+1) == 'k') W = Double.parseDouble(ch1);
        i = 0;
        while(b.charAt(i) != ' ') {
            ch2+=b.charAt(i);
            i++;
        }
        if(b.charAt(i+1) == 'i') H = Double.parseDouble(ch2) * inchesatmet;
        else if(b.charAt(i+1) == 'm') H = Double.parseDouble(ch2);
        double R = W / (H*H);
        R = Math.round(R * 10.0) / 10.0;               // округление до ближайшей десятой
        String s = Double.toString(R); s+=" ";
        if(R < 18.5) s+="Underweight";
        else if(R > 25) s+="Overweight";
        else s+="Normal weight";
        return s;
    }

    private static int bugger(int a){// функция,возвращающая кол-во раз,которое нужно умножать цифры a,пока не станет 1 цифры
        if(a < 10) return 0;
        String n = Integer.toString(a);
        int b=1; int h = 0;
        do {
            for(int i=0;i<n.length();i++){
                b*=Character.getNumericValue(n.charAt(i));
            }
            n = Integer.toString(b);
            h++;
            b = 1;
        } while(Integer.parseInt(n) >= 10);
        return h;
    }

    private static String toStarShorthand(String str){ // функция преобразует строку str в звездную стенографию по признаку
        if(str.equals("")) return "";                  // если символ повторяется n раз,преобразовать его в символ *n
        String s = "";
        int n = 1;
        for(int i=0;i<str.length()-1;i++){
            if(str.charAt(i) == str.charAt(i+1)){
                int j = i;
                while(str.charAt(i) == str.charAt(i+1)){
                    if(i == str.length()-2) break;
                    i++;
                    n++;
                }
                s+=str.charAt(j);
                s+="*";
                s+=Integer.toString(n);
                n = 2;
            }
            else s+=str.charAt(i);
        }
        return s;
    }

    private static boolean doesRhyme(String a,String b){ // функция,возвращает true,если последнее слова строки a и b
        String si1 = "";                                 // содержит одни и те же гласные ; false иначе
        String si2 = "";
        for(int i=0;i<a.length();i++){
            if((a.charAt(i) >= 'a' && a.charAt(i) <= 'z') || (a.charAt(i) >= 'A' && a.charAt(i) <= 'Z') || (a.charAt(i) == ' '))
                si1+=Character.toLowerCase(a.charAt(i));}
        for(int i=0;i<b.length();i++){
            if((b.charAt(i) >= 'a' && b.charAt(i) <= 'z') || (b.charAt(i) >= 'A' && b.charAt(i) <= 'Z') || (b.charAt(i) == ' '))
                si2+=Character.toLowerCase(b.charAt(i));}
        int k1 = 0; for(int i = 0;i<si1.length();i++){
            if(si1.charAt(i) == ' ') k1 = i; // нахождение индекса последнего пробела (перед последним словом в строке a)
        }
        int k2 = 0; for(int i=0;i<si2.length();i++){
            if(si2.charAt(i) == ' ') k2 = i; // нахождение индекса последнего пробела (перед последним словом в строке b)
        }
        String word1 = "";
        String word2 = "";

        for(int j=k1;j<si1.length();j++){
            char c = si1.charAt(j);
            if(c =='a' || c == 'e' || c == 'y' || c == 'u' || c == 'i' || c == 'o') word1+=si1.charAt(j);}// гласные посл.слова
        for(int j=k2;j<si2.length();j++){
            char c = si2.charAt(j);
            if(c =='a' || c == 'e' || c == 'y' || c == 'u' || c == 'i' || c == 'o') word2+=si2.charAt(j);}// гласные посл.слова
        int x = 0;
        for(int i=0;i<word1.length();i++){
            for(int j=0;j<word2.length();j++){
                if(word1.charAt(i) == word2.charAt(j)) {x++;break;}
            }
        }
        return x == word1.length();
    }

    private static boolean trouble(long a,long b){ // функция возвращает true,если число,повторяющееся 3 раза в a
        String a1 = Long.toString(a); int N=0;     // повторяется 2 раза в b ; false иначе
        String a2 = Long.toString(b); int j = 0;
        for(int i=0;i<a1.length()-1;i++){
            if(a1.charAt(i) == a1.charAt(i+1)){
                while(a1.charAt(i) == a1.charAt(i+1)){
                    if(i == a1.length() - 2) break;
                    else  i++;
                    j++;
                }
                if(j == 2) N = Character.getNumericValue(a1.charAt(i-2));
            }
        }
        int l = 0;
        for(int i=0;i<a2.length();i++){
            if(Character.getNumericValue(a2.charAt(i)) == N){
                while(Character.getNumericValue(a2.charAt(i)) == N){
                    l++;
                    if(i == a2.length()-1) break;
                    else i++;
                }
                if(l == 2) return true;
            }
        }
        return false;
    }

    private static int countUniqOne(String str){ // функция для подсчета индивидуальных символов во входной строке str
        char[] a = str.toCharArray();
        for(int i=0;i<a.length;i++){
            for(int j=0;j<a.length;j++){
                if(i == j) continue;
                if(a[i] == a[j]) a[j] = '_';
            }
        }
        int k = 0;
        for(char z:a)
            if(z != '_') k++;
        return k;
    }
    private static int countUniqueBooks(String str,char a){ // функция,возвращает общее количество уникальных символов
        int U = 0;                             // во входной строке str, между парами подстрок,разделенных символом a
        String s="";
        for(int i=0;i<str.length();i++){
            if(str.charAt(i) == a){
                i++;
                while(str.charAt(i) != a){
                    if(i == str.length()-1) break;
                    s+=str.charAt(i);
                    i++;
                }
                U+=countUniqOne(s);
                s="";
            }
        }
        return U;
    }
}
