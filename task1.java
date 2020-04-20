package com.company;

public class task1 {
    public static void main(String[] args){

    }
    private static int remainer(int a,int b){     // остаток деления a на б
        return a % b;
    }

    private static int triArea(int osn,int h){    // площадь треугольника по высоте
        return osn * h / 2;
    }

    private static int animals(int ch,int co,int pi){   // количество ног всех животных
        return (ch*2+co*4+pi*4);
    }

    private static boolean profitableGamble(double prob,int prize,int pay){  // возвращает prob*prize > pay
        return (prob*prize) > pay;
    }

    private static String operation(int a1,int a2,int a3){  // возвращает строкой что можно сделать с a2 и a3 чтобы получить a1
        if (a2+a3 == a1) return "added";
        else if (a2-a3 == a1) return "subtracted";
        else if (a2 / a3 == a1) return "divided";
        else if( a2*a3 == a1) return "multiplied";
        return "none";
    }

    private static int ctoa(char c){             // возвращает значение ASCII переданного символа c
        return (int)c;
    }

    private static int addUpTo(int a){           // возвращает сумму всех чисел до a и включая a
        int b = 0;
        for(int i=0;i<=a;i++)
            b+=i;
        return b;
    }

    private static int nextEdge(int x,int y){    //  находит максимальное значение третьего ребра треугольника
        return x+y-1;
    }

    private static int sumOfCubes(int[] array){    // возвращает сумму кубов элементов входного массива array
        int s = 0;
        for(int i=0;i<array.length;i++)
            s+=Math.pow((double)array[i],3);
        return s;
    }

    private static boolean abcmath(int a,int b,int c){ // добавляет a к себе b раз и проверяет,делится ли результат на c
        int a2 = a;
        for(int i=0;i<b;i++){
            a2+=a2;
        }
        System.out.println(a2);
        return ((a2 % c) == 0);
    }
}
