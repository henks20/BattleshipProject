package pl.shipproject;

public class Main {

    public static void main(String[] args) {
        System.out.print("  ");
        for (int i = 0; i < 10 ; i++) {
            System.out.print((char)('A' + i));
        }
        System.out.print('\n');
        for (int i = 1; i <= 10 ; i++) {
            if(i < 10) {
                System.out.print(' ');
            }
            System.out.println(i);
            for (int j = 0; j < 10; j++) {
                if (Math.random() < 0.2){
                    System.out.print('O');
                } else {
                System.out.print(' ');
                }
            }
            System.out.print('\n');
        }

        double x = 10;
        double value = f(x);
        printValue(value);
        value = f(x *2);
        printValue(value);
    }

    static double f(double x) {
        return x * x * 3;
    }

    static void printValue(double value) {
        System.out.println("f(x)=" + value);
    }
}
