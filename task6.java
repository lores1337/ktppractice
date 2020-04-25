import java.util.Arrays;
import java.util.Vector;

public class task6 {
    public static void main(String[] args){

    }

    private static int bell(int n) {  // функция возвращает число Белла
        int[][] bell = new int[n+1][n+1];
        bell[0][0] = 1;

        for (int i=1; i<=n; i++)
        {
            bell[i][0] = bell[i-1][i-1];

            for (int j=1; j<=i; j++)
                bell[i][j] = bell[i-1][j-1] + bell[i][j-1];
        }

        return bell[n][0];
    }

    private static String translateWord(String s){ //функция,выполняющая перевод слова s английского языка на латинский язык
        if(s == "") return "";
        char first_c = Character.toLowerCase(s.charAt(0));
        if(first_c == 'a' || first_c == 'e' || first_c == 'o' || first_c == 'y' || first_c == 'u' || first_c == 'i'){
            s+="yay";
            return s;
        }
        else{
            String s2 = "";
            int i = 0;
            while(!(s.charAt(i) == 'a' || s.charAt(i) == 'e' || s.charAt(i) == 'o' ||
                    s.charAt(i) == 'y' || s.charAt(i) == 'u' || s.charAt(i) == 'i')){
                s2 += s.charAt(i);
                i++;
            }
            s2 += "ay";
            String s3 = "";
            for(int j = i;j<s.length();j++){
                s3 += s.charAt(j);
            }
            s3+= s2;
            return s3;
        }
    }
    private static String translateSentence(String str){ // функция,выполняющая перевод предложения (строки str)
        boolean t = false;                              // английского языка на латинский язык
        String str2="";
        if(str.charAt(str.length()-1) == '.') {
            t = true;
            for(int x=0;x<str.length()-1;x++)
                str2+=str.charAt(x);
        }
        else {
            for(int x=0;x<str.length();x++)
                str2+=str.charAt(x);
        }
        int probels = 0;
        for(int i = 0;i<str2.length();i++){
            if(str2.charAt(i) == ' ') probels++;
        }
        String[] p = new String[probels+1];
        Arrays.fill(p,"");
        int j = 0;
        for(int i=0;i<str2.length();i++){
            if(str2.charAt(i) == ' ') j++;
            else p[j] += str2.charAt(i);
        }
        String rez = "";
        for(int i=0;i<p.length;i++){
            if(i!=p.length-1) rez += translateWord(p[i]) + " ";
            else rez += translateWord(p[i]);
        }
        if(t) rez+=".";
        return rez;
    }

    private static double[] pColor(String c){  // функция возвращает массив чисел,которые были разделены запятыми
        int size = 0;                         // в строке s функции validColor(s)
        for(int i =0;i<c.length();i++)
            if(c.charAt(i) == ',') size ++;
        double[] m = new double[size+1]; size = 0;
        String d = "";
        for(int i=0;i<c.length();i++){
            if(c.charAt(i) == ',') {
                m[size] = Double.parseDouble(d);
                d = "";
                size ++;
            }
            else d+=c.charAt(i);
        }
        return m;
    }
    private static boolean validColor(String s){ // функция возвращает true,если строка s является допустимым форматом
        String type = "";                       // rgb или rgba принимающих значений
        String color = "";
        int i = 0;
        for(;i<s.length();i++){
            if(s.charAt(i) == '(') break;
            else type += s.charAt(i);
        } i++;
        if(type.equals("rgb")){
            int a = 0;
            for(;i<s.length()-1;i++){
                color += s.charAt(i);
            }
            for(int c=0;c<s.length();c++){
                if(s.charAt(c) == ',') a++;
            }
            if(a != 2) return false;
            double[] c = pColor(color);
            for(int v = 0;v<c.length;v++)
                if(c[v] < 0 || c[v] > 255) return false;
            return true;
        }
        else if(type.equals("rgba")){
            int a = 0;
            for(;i<s.length()-1;i++){
                color += s.charAt(i);
            }
            for(int c=0;c<s.length();c++){
                if(s.charAt(c) == ',') a++;
            }
            if(a != 3) return false;
            double[] c = pColor(color);
            for(int v = 0;v<c.length;v++){
                if(c[v] < 0 || c[v] > 255) return false;
            }
            return true;
        }
        return false;
    }

    private static String stripUrlParams(String url){ //функция,удаляющая дублирующиеся параметры запроса в строке url
        String p = "";
        String r = "";
        for(int i=0;i<url.length();i++){
            if(url.charAt(i) == '?'){
                while(i < url.length()-1){
                    i++;
                    p += url.charAt(i);
                }
            }
            else r += url.charAt(i);
        }
        if(p.equals("")) return r;
        r+="?";
        int size = 1;
        for(int i=0;i<p.length();i++)
            if(p.charAt(i) == '&') size ++;
        String[] d = new String[size];
        Arrays.fill(d,"");
        int v = 0;
        for(int i=0;i<p.length();i++){
            if(p.charAt(i) == '&') v++;
            else d[v] += p.charAt(i);
        }
        for(int i=0;i<d.length;i++){
            for(int j=0;j<d.length-1;j++){
                if(i == j) continue;
                if(d[j].charAt(0) == d[i].charAt(0)){
                    d[j] = " ";
                }
            }
        }

        for(int z=0;z<d.length;z++){
            for(int j=0;j<d.length-1;j++){
                if( z == j) continue;
                if(d[z].charAt(0) < d[j].charAt(0)){
                    String g = d[z];
                    d[z] = d[j];
                    d[j] = g;
                }
            }
        }
        for(int i=0;i<d.length;i++){
            if(d[i] != " ") r+=d[i]+"&";
        }
        if(r.charAt(r.length()-1) == '&'){
            String rv = "";
            for(int i=0;i<r.length()-1;i++){
                rv += r.charAt(i);
            }
            return rv;
        }
        return r;
    }
    private static String stripUrlParams(String url,String[] rs){ // функция,удаляющая дублирующиеся параметры запроса в url
        String p = "";                                       // и параметры,указанные во втором аргументе (массиве строк rs)
        String r = "";
        for(int i=0;i<url.length();i++){
            if(url.charAt(i) == '?'){
                while(i < url.length()-1){
                    i++;
                    p += url.charAt(i);
                }
            }
            else r += url.charAt(i);
        }
        if(p.equals("")) return r;
        r+="?";
        int size = 1;
        for(int i=0;i<p.length();i++)
            if(p.charAt(i) == '&') size ++;
        String[] d = new String[size];
        Arrays.fill(d,"");
        int v = 0;
        for(int i=0;i<p.length();i++){
            if(p.charAt(i) == '&') v++;
            else d[v] += p.charAt(i);
        }
        for(int i=0;i<d.length;i++){
            for(int j=0;j<d.length-1;j++){
                if(i == j) continue;
                if(d[j].charAt(0) == d[i].charAt(0)){
                    d[j] = " ";
                }
            }
        }

        for(int z=0;z<d.length;z++){
            for(int j=0;j<d.length-1;j++){
                if( z == j) continue;
                if(d[z].charAt(0) < d[j].charAt(0)){
                    String g = d[z];
                    d[z] = d[j];
                    d[j] = g;
                }
            }
        }
        for(int i=0;i<d.length;i++){
            for(int j=0;j<rs.length;j++){
                if(d[i].charAt(0) == rs[j].charAt(0)){
                    d[i] = " ";
                }
            }
        }
        for(int i=0;i<d.length;i++){
            if(d[i] != " ") r+=d[i]+"&";
        }
        if(r.charAt(r.length()-1) == '&'){
            String rv = "";
            for(int i=0;i<r.length()-1;i++){
                rv += r.charAt(i);
            }
            return rv;
        }
        return r;
    }

    private static int getMaxLenght(String[] m){   //функция возвращает макс. длину из всех элементов массива строк m
        int k = m[0].length();
        for(int i=1;i<m.length;i++)
            if(m[i].length() > k ) k = m[i].length();
        return k;
    }
    private static String[] getHashTags(String s){ // функция которая извлекает три самых длинных слова из заголовка
        int size = 0;                             // (строки s) и преобразует в хэштеги
        String[] a;
        for(int i=0;i<s.length();i++){
            if(s.charAt(i) == ' ') size++;
        }
        int m = 0;
        if(size == 1 || size == 0){
            a = new String[size+1];
            Arrays.fill(a,"#");
            for(int i=0;i<s.length();i++){
                if(s.charAt(i) == ' ') m++;
                else if(!(s.charAt(i) == ',')) a[m] += Character.toLowerCase(s.charAt(i));
            }
            if(size == 1){
                if(a[0].length() < a[1].length()){
                    String e = a[0];
                    a[0] = a[1];
                    a[1] = e;
                }
            }
            return a;
        }
        else {
            a = new String[size+1];
            Arrays.fill(a,"#");
            for(int i=0;i<s.length();i++){
                //if(s.charAt(i) == ' ' && m == 2) break;
                if(s.charAt(i) == ' ' ) m++;
                else if(!(s.charAt(i) == ',')) a[m] += Character.toLowerCase(s.charAt(i));
            }
            String rez = "";
            String[] b = new String[3];
            Arrays.fill(b,"");
            int x = 0;
            for(int j=0;j<a.length;j++){
            for(int i=0;i<a.length;i++){
                if(x == 3) break;
                if(a[i].length() == getMaxLenght(a)) {
                    b[x] += a[i];
                    a[i] = "";
                    x++;
                    break;
                }
            }}
            return b;
        }
    }

    private static Vector<Integer> ulam1(int MAX){ // функция,считает сколько всего чисел
        Vector<Integer> arr = new Vector<Integer>(); // для хранения номера Улама
        arr.add(1);
        arr.add(2);
        for (int i = 3; i < MAX; i++) { // генерация числа Улама (работаем с массивом начиная с 3 элемента)
            int count = 0;
            // пройти массив и проверить,
            // я могу быть представлен как сумма
            // два разных элемента массива
            for (int j = 0; j < arr.size() - 1; j++) {
                for (int k = j + 1; k < arr.size(); k++) {
                    if (arr.get(j) + arr.get(k) == i) {
                        count++;
                    }
                    if (count > 1)
                        break;
                }
                if (count > 1)
                    break;
            }
            // если число 2, значит можно его представить суммой двух разных членов последовательности
            if (count == 1) {
                arr.add(i);
            }
        }
        return arr;
    }
    private static int ulam(int n){ // функция возвращает n-е число в последовательности чисел Улама
        Vector<Integer> arr =  ulam1(8000);
        int [] ulam =new int[n];
        int k=0;
        // печатаем n-е число Улама
        for(int i=0;i<n;i++,k++,n--){
            ulam[k] =
                    (arr.get(n - 1));
            i=0;}
        return ulam[0];
    }

    private static boolean someS(String str){ // функция возвращает true если символы в строке str различны; false иначе
        for(int i=0;i<str.length();i++){
            for(int j=0;j<str.length();j++){
                if(i == j) continue;
                if(str.charAt(i) == str.charAt(j)) return false;
            }
        }
        return true;
    }
    private static String longestNonrepeatingSubstring(String str){ // функция возвращает самую длинную неповторяющуюся
        String rez = "";                                           // подстроку для строкового ввода
        String rez2 = "";
        for(int j=0;j<str.length();j++) {
            rez = "";
            for (int i = j; i < str.length(); i++) {
                if (i == 0) rez += str.charAt(i);
                else {
                    while (someS(rez)) {
                        rez += str.charAt(i);
                        if(i != str.length()-1) i++;
                        String r = rez;
                        r += str.charAt(i);
                        if (!someS(r)) break;
                    }
                    if(rez.length() > rez2.length()) rez2 = rez;
                    break;
                }
            }
        }
        return rez2;
    }

    private static String oneDigit(int a){ // функция возвращает римское представление числа a из разряда единиц
        if(a == 1) return "I";
        else if(a==2) return "II";
        else if(a==3) return "III";
        else if(a==4) return "IV";
        else if(a==5) return "V";
        else if(a==6) return "VI";
        else if(a==7) return "VII";
        else if(a==8) return "VIII";
        else if(a==9) return "IX";
        else if(a==0) return "";
        return "Error";
    }
    private static String desDigit(int a){ // функция возвращает римское представление числа a из разряда десятков
        if(a == 1) return "X";
        else if(a==2) return "XX";
        else if(a==3) return "XXX";
        else if(a==4) return "XL";
        else if(a==5) return "L";
        else if(a==6) return "LX";
        else if(a==7) return "LXX";
        else if(a==8) return "LXXX";
        else if(a==9) return "XC";
        else if(a==0) return "";
        return "Error";
    }
    private static String soDigit(int a){ // функция возвращает римское представление числа a из разряда сотен
        if(a == 1) return "C";
        else if(a==2) return "CC";
        else if(a==3) return "CCC";
        else if(a==4) return "CD";
        else if(a==5) return "D";
        else if(a==6) return "DC";
        else if(a==7) return "DCC";
        else if(a==8) return "DCCC";
        else if(a==9) return "CM";
        else if(a==0) return "";
        return "Error";
    }
    private static String tDigit(int a){ // функция возвращает римское представление числа a из разряда тысяч
        if(a == 1) return "M";
        else if(a==2) return "MM";
        else if(a==3) return "MMM";
        return "Error";
    }
    private static String convertToRoman(int a){ // функция возвращает римское представление арабского числа a
        String rez = "";
        if(a<0 || a>3999) return "Error";
        String b = Integer.toString(a);
        if(b.length() == 1) rez = oneDigit(Character.getNumericValue(b.charAt(0)));
        else if(b.length() == 2){
            rez += desDigit(Character.getNumericValue(b.charAt(0)));
            rez += oneDigit(Character.getNumericValue(b.charAt(1)));
        }
        else if(b.length() == 3){
            rez += soDigit(Character.getNumericValue(b.charAt(0)));
            rez += desDigit(Character.getNumericValue(b.charAt(1)));
            rez += oneDigit(Character.getNumericValue(b.charAt(2)));
        }
        else if(b.length() == 4){
            rez += tDigit(Character.getNumericValue(b.charAt(0)));
            rez += soDigit(Character.getNumericValue(b.charAt(1)));
            rez += desDigit(Character.getNumericValue(b.charAt(2)));
            rez += oneDigit(Character.getNumericValue(b.charAt(3)));
        }
        return rez;
    }

    private static boolean formula(String str){ // функция возвращает true если формула,заданная строкой str является правильной;
        int size = 0;                          // false иначе
        for(int i=0;i<str.length();i++){
            if(str.charAt(i) == ' ') size++;
        }
        String a[] = new String[size+1];
        Arrays.fill(a,"");
        size = 0;
        for(int i=0;i<str.length();i++){
            if(str.charAt(i) == ' ') size++;
            else a[size] += str.charAt(i);
        }
        if(size > 4 || !(a[3].equals("="))) return false;
        try {
            int a1 = Integer.parseInt(a[0]);
            int a2 = Integer.parseInt(a[2]);
            int a3 = Integer.parseInt(a[4]);
            if (a[1].equals("+")) {
                return (a1 + a2) == a3;
            } else if (a[1].equals("-")) {
                return (a1 - a2) == a3;
            } else if (a[1].equals("*")) {
                return (a1 * a2) == a3;
            } else if (a[1].equals("/")) {
                return (a1 / a2) == a3;
            }
            else return false;
        } catch (Throwable e){
            return false;
        }
    }

    private static boolean palindromeDescendant(int num) { // функция возвращает true если число num является палиндромом
        if (num<1000) {                                   // или любой из его потомков вплоть до 2 цифры; false иначе
            if (num< 100) {
                int sum = num/10 + num%10;
                if (sum >= 10) {
                    return palindromeDescendant(sum);
                }
                return num/10 == num%10;
            } else {
                return num/100 == num%10;
            }
        }
        String s = Integer.toString(num);
        StringBuilder sb = new StringBuilder();
        for (int i=0; i< s.length(); i+=2) {
            int n1 = Integer.parseInt(s.substring(i, i+1));
            int n2 = Integer.parseInt(s.substring(i+1, i+2));
            sb.append(n1+n2);
        }
        return palindromeDescendant(Integer.parseInt(sb.toString()));
    }
}