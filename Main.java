import java.util.Scanner;
import java.util.Random;

public class Main {
    private final static Scanner scanner = new Scanner(System.in);
    private static final byte OFFSET_ZERO = -128;
    private static final byte SIZE = 8;

    private static void conversion(byte array[][]) {
        for (byte l0 = 0, l1; l0 < SIZE; ++l0) {
            for (l1 = 0; l1 < SIZE; ++l1) {
                Random random = new Random();
                array[l0][l1] = (byte) (OFFSET_ZERO + random.nextInt(256));
            }
        }
    }

    private static void turn90DegreeRight(byte array[][]) {
        byte cache;
        final byte MAX = SIZE - 1;
        final byte MIDDLE = SIZE / 2;
        for (byte l0 = 0, l1;;) {// annular displacement
            for (l1 = l0;;) {
                cache = array[l0][l1];
                array[l0][l1] = array[MAX - l1][l0];
                array[MAX - l1][l0] = array[MAX - l0][MAX - l1];
                array[MAX - l0][MAX - l1] = array[l1][MAX - l0];
                array[l1][MAX - l0] = cache;
                if (MAX - l0 > ++l1) {
                    continue;
                }
                break;
            }
            if (MIDDLE > ++l0) {
                continue;
            }
            break;
        }
    }

    private static void turn180DegreeRight(byte array[][]) {
        byte cache;
        final byte MAX = SIZE - 1;
        final byte MIDDLE = SIZE / 2;
        for (byte l0 = 0, l1;;) {// annular displacement
            for (l1 = 0; l1 < SIZE; ++l1) {
                cache = array[l0][l1];
                array[l0][l1] = array[MAX - l0][MAX - l1];
                array[MAX - l0][MAX - l1] = cache;
            }
            if (MIDDLE > ++l0) {
                continue;
            }
            break;
        }
    }

    private static void turn270DegreeRight(byte array[][]) {
        byte cache;
        final byte MAX = SIZE - 1;
        final byte MIDDLE = SIZE / 2;
        for (byte l0 = 0, l1;;) {// annular displacement
            for (l1 = l0;;) {
                cache = array[l0][l1];
                array[l0][l1] = array[l1][MAX - l0];
                array[l1][MAX - l0] = array[MAX - l0][MAX - l1];
                array[MAX - l0][MAX - l1] = array[MAX - l1][l0];
                array[MAX - l1][l0] = cache;
                if (MAX - l0 > ++l1) {
                    continue;
                }
                break;
            }
            if (MIDDLE > ++l0) {
                continue;
            }
            break;
        }
    }

    private static void outputMatrices(byte array[][]) {
        for (byte l0 = 0, l1; l0 < SIZE; ++l0) {
            System.out.print("\n");
            for (l1 = 0; l1 < SIZE; ++l1) {
                if (array[l0][l1] < -118) {
                    System.out.print("   " + (array[l0][l1] - OFFSET_ZERO));
                } else if (array[l0][l1] < -28) {
                    System.out.print("  " + (array[l0][l1] - OFFSET_ZERO));
                } else {
                    System.out.print(" " + (array[l0][l1] - OFFSET_ZERO));
                }
            }
        }
    }

    private static byte menu(byte array[][]) {
        System.out.print("""


                Выберите градус разворота матрицы в право:
                1 – 90 градусов.
                2 – 180 градусов.
                3 – 270 градусов.
                >""");
        switch (scanner.nextLine()) {
            case "1": {
                turn90DegreeRight(array);
                System.out.print("\nРазворот матрицы на 90 градусов в право:\n");
                outputMatrices(array);
            }
                return 0;
            case "2": {
                turn180DegreeRight(array);
                System.out.print("\nРазворот матрицы на 180 градусов в право:\n");
                outputMatrices(array);
            }
                return 0;
            case "3": {
                turn270DegreeRight(array);
                System.out.print("\nРазворот матрицы на 270 градусов в право:\n");
                outputMatrices(array);
            }
                return 0;
            default: {
                System.out.print("Ошибка ввода!\n");
            }
                return 1;
        }
    }

    public static void main(String[] args) {
        byte[][] array = new byte[SIZE][SIZE];
        conversion(array);
        System.out.print("\nДана следующая матрица:\n");
        outputMatrices(array);
        while (menu(array) != 0)
            ;
        System.out.print("\n\n");
    }
}