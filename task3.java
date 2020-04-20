package com.company;

public class task3 {
    public static void main(String[] args){

    }

    private static int solutions(int a,int b,int c){ // функция возвращает число решений(корней) квадратного уравнения
        int D = b*b-4*a*c;                           // a,b,c - соответствующие коэффициенты, D - дискриминант
        if(D < 0) return 0;
        else if(D > 0) return 2;
        return 1;
    }

    private static int findZip(String a){  // функция возвращает позицию второго вхождения подстроки "zip" в
        int v1 = a.indexOf("zip");         // строку a, -1 в противном случае
        String a2="";
        for(int i=0;i<a.length();i++){
            if(i!=v1) a2+=a.charAt(i);
            else a2+='_';
        }
        return a2.indexOf("zip");
    }

    private static boolean checkPerfect(int x){ // функция возвращает true, если входное целое число x - совершенное,false иначе
        int y = 0;                // совершенное число - число,которое можно записать как сумму множителей,не включая само число
        for(int i=1;i<x;i++){
            if(x % i == 0) y+=i;
        }
        return y == x;
    }

    private static String flipEndChars(String str){                   // функция возвращает строку с заменой первого и последнего символов
        if(str.length() < 2) return "Incompatible.";          // строки str с условиями: 1)если длина строки меньше 2,вернуть Incompatible.
        if(str.charAt(0) == str.charAt(str.length()-1)) return "Two's a pair."; // 2)если 1 и последний символы равны,вернуть Two's a pair.
        String str2="";str2+=str.charAt(str.length()-1);
        for(int i=1;i<str.length()-1;i++){
            str2+=str.charAt(i);
        }
        str2+=str.charAt(0);
        return str2;
    }

    private static boolean isValidHexCode(String str){      // функция определяет,является ли входная строка str допустимым
        if(str.charAt(0) == '#' && str.length() != 7) return false; //16-м кодом(начинаться с # и иметь длину 6 символов) и
        for(int i=1;i<str.length();i++){    // каждый символ должен быть цифрой 0-9 или буквой A-F (независимо от регистра)
            if(Character.isDigit(str.charAt(i))) continue;
            if(!(Character.toLowerCase(str.charAt(i)) >= 'a' && Character.toLowerCase(str.charAt(i)) <= 'f')) return false;
        }
        return true;
    }

    private static boolean same(int[] array1,int[] array2){ // функция возвращает true если массивы array1 и array2
        boolean z1,z2;                                      // имеют одиннаковое число уникальных элементов,false иначе
        z1 = z2 = false;
        for (int i1:array1)
            if(i1 == 0){
                z1 = true;
                break;
            }
        for (int i2:array2)
            if(i2 == 0){
                z2 = true;
                break;
            }
        for(int i=0;i<array1.length;i++){
            for(int j=0;j<array1.length;j++){
                if(i == j) break;
                if(array1[i] == array1[j]){
                    array1[j] = 0;
                }
            }
        }
        for(int i=0;i<array2.length;i++){
            for(int j=0;j<array2.length;j++){
                if(i == j) break;
                if(array2[i] == array2[j]){
                    array2[j] = 0;
                }
            }
        }
        int s1 = 0;
        int s2 = 0;
        for(int a:array1) {
            if(a != 0) s1++;
        }
        for(int b:array2){
            if(b !=0) s2++;
        }
        if(z1) s1++;
        if(z2) s2++;
        return s1==s2;
    }

    private static boolean isKaprekar(int n){ // функция,возвращающая true если входное целое число n - число Капрекара
        int n2 = n*n;  //число Капрекара-число,которое после возведения в квадрат и разбиения на две лекс. части= сумме 2 полученных чисел.
        String s = Integer.toString(n2);  // false иначе
        String str1,str2;
        str1 = str2 = "";
        int ser = s.length()/2;
        if(n2 < 10){
            return (n2 == n);
        }
        for(int i=0;i<ser;i++)
            str1+=s.charAt(i);
        for(int i=ser;i<s.length();i++)
            str2+=s.charAt(i);
        int sum = Integer.parseInt(str1) + Integer.parseInt(str2);
        return (n==sum);
    }

    private static String longestZero(String str){ // функция возвращает самую длинную последовательность 0 во входной строке str
        String s = ""; int l = 0;
        for(int i=0;i<str.length();i++){
            if(str.charAt(i) == '0'){
                int l2 = 0;
                while(str.charAt(i) == '0'){
                    if(i == str.length()-1) break;
                    i++;
                    l2++;
                }
                if(l2 > l) l = l2;
            }
        }
        for(int z=0;z<l;z++)
            s+='0';
        return s;
    }

    private static boolean pros(int a){  // функция,возвращающая true если входное число a - простое число, false иначе
        for(int i=2;i<a;i++)
            if(a % i == 0) return false;
        return true;
    }
    private static int nextPrime(int a){ // функция возвращает следующее простое число после входного целого числа a
        if(pros(a)) return a;            // если само входное число a простое,возвращает его
        while(!pros(a)){
            a++;
        }
        return a;
    }

    private static boolean rightTriangle(int x,int y,int z){ // функция,определяющая являются ли входные числа x,y,z
        if(z > y && z > x && x < y) return true;             // допустимыми ребрами прямоугольного треугольника
        if(x > y && x > z && y > z) return true;
        return false;
    }
}
