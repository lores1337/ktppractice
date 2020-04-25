public class task2 {
    public static void main(String[] args){

    }

    private static String repeat(String s,int k){  // функция повторяет каждый символ в строке s k раз
        String s2 = "";
        for(int i=0;i<s.length();i++){
            for(int j=0;j<k;j++){
                s2+=s.charAt(i);
            }
        }
        return s2;
    }

    private static int differenceMaxMin(int[] array){  // функция находит максимум и минимум входного целочисленного
        int max,min;                                   // массива array и находит разность между ними
        max = min = array[0];
        for(int i=0;i<array.length;i++){
            if(array[i] > max) max = array[i];
            else if(array[i] < min) min = array[i];
        }
        return max - min;
    }

    private static boolean isAvgWhole(int[] array){  // функция находит сумму всех элементов входного целочисленного
        double s = 0;                                // массива array и делит полученную сумму на количество элементов;
        for(int a:array)                             // возвращает true если среднее значение целое,false иначе
            s+=a;
        double av = s / array.length;
        return(av == (int)av);
    }

    private static int[] cumulativeSum(int[] array){ // функция берет массив array и возвращает массив, в котором
        int[] m = new int[array.length];             // каждое число является суммой самого себя и всех
        for(int i=0;i<array.length;i++){             // предыдущих чисел в массиве
            int s = 0;
            for(int z=0;z<i;z++){
                s+=array[z];
            }
            m[i] = array[i] + s;
        }
        for(int x=0;x<m.length;x++)
            System.out.print(m[x] + " ");
        return m;
    }

    private static int getDecimalPlaces(String c){// функция возвращает число десятичных знаков которое имеет число в виде строки c
        int a = 0;
        for(int i=0;i<c.length();i++)
            if(c.charAt(i) == '.') {
                a+=(c.length()-i-1);
                break;
            }
        return a;
    }

    private static int Fibonacci(int x){  // функция возращает число Фибоначчи по указанному входному члену последовательности x
        if(x == 1) return 1;
        if(x == 2) return 2;
        int a1=1;
        int a2=2;
        int i = 2; // член последовательности
        while(1>0){
            i++;
            int a3 = a1+a2;
            a1 = a2;
            a2 = a3;
            if(x == i) return a3;
        }
    }

    private static boolean isValid(String s){  // функция,определяющая является ли строка s почтовым индексом
        if(s.length()>5) return false;         // (должна содержать только цифры и не должна содержать пробелы,длина < 6)
        for(int i=0;i<s.length();i++){
            if(s.charAt(i) == ' ') return false;
            if(!Character.isDigit(s.charAt(i))) return false;
        }
        return true;
    }

    private static boolean isStrangePair (String a1,String a2){ // функция,возвращающая true если a1 и a2 - странная пара
        if(a1.equals("") && a2.equals("")) return true;         // возвращает false иначе
        if(a1.charAt(0) != a2.charAt(a2.length()-1)) return false; // странная пара - если 1 буква 1 строки = последняя буква 2 строки
        if(a1.charAt(a1.length()-1) != a2.charAt(0)) return false; // и последняя буква 1 строки = 1 буква 2 строки
        return true;
    }

    private static boolean isPrefix(String word,String prefix){ // функция возвращает true если строка prefix является
        for(int i=0;i<prefix.length()-2;i++){                   // началом строки word ( не считая тире), false иначе
            if(word.charAt(i) != prefix.charAt(i)) return false;
        }
        return true;
    }
    private static boolean isSuffix(String word,String suffix){ // функция возвращает true если строка word оканчивается
        for(int i=0;i<suffix.length()-2;i++){                   // строкой suffix, false иначе
            if(word.charAt(word.length()-1-i) != suffix.charAt(suffix.length()-1-i)) return false;
        }
        return true;
    }

    private static int boxSeq(int shag){ // функция  принимает число shag в качестве аргумента и
        if(shag == 0) return 0;          // возвращает количество полей на этом шаге (shag) последовательности
        else if(shag == 1) return 3;     // Шаг 0 0; Шаг 1 +3; Шаг 2 -1; Далее повторять шаги 1 и 2
        else if(shag == 2) return 2;
        else if(shag % 2 == 0) return shag;
        else return shag+2;
    }
}
