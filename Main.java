import java.util.Scanner;
import java.util.Random;

public class Main {
    private final static Scanner scanner = new Scanner(System.in);
    private static final byte OFFSET_ZERO = -128;
    private static final byte SIZE = 8;
    private static final byte MATRICE1 = 0;
    private static final byte MATRICE2 = 1;
    private static final String OPTION_90 = "1";
    private static final String OPTION_180 = "2";
    private static final String OPTION_270 = "3";
    private static byte[][][] matrices = new byte[2][SIZE][SIZE];

    private static void conversion() {
        for (byte l0 = 0, l1; l0 < SIZE; ++l0) {
            for (l1 = 0; l1 < SIZE; ++l1) {
                Random random = new Random();
                matrices[0][l0][l1] = (byte) (OFFSET_ZERO + random.nextInt(256));
            }
        }
    }

    private static void turn90DegreeRight() {
        final byte MAX = SIZE - 1;
        final byte MIDDLE = SIZE / 2;
        for (byte l0 = 0, l1;;) {// annular displacement
            for (l1 = l0;;) {
                matrices[MATRICE2][l1][MAX - l0] = matrices[MATRICE1][l0][l1];
                matrices[MATRICE2][l0][l1] = matrices[MATRICE1][MAX - l1][l0];
                matrices[MATRICE2][MAX - l1][l0] = matrices[MATRICE1][MAX - l0][MAX - l1];
                matrices[MATRICE2][MAX - l0][MAX - l1] = matrices[MATRICE1][l1][MAX - l0];
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

    private static void turn180DegreeRight() {
        final byte MAX = SIZE - 1;
        final byte MIDDLE = SIZE / 2;
        for (byte l0 = 0, l1;;) {// annular displacement
            for (l1 = 0; l1 < SIZE; ++l1) {
                matrices[MATRICE2][MAX - l0][MAX - l1] = matrices[MATRICE1][l0][l1];
                matrices[MATRICE2][l0][l1] = matrices[MATRICE1][MAX - l0][MAX - l1];
            }
            if (MIDDLE > ++l0) {
                continue;
            }
            break;
        }
    }

    private static void turn270DegreeRight() {
        final byte MAX = SIZE - 1;
        final byte MIDDLE = SIZE / 2;
        for (byte l0 = 0, l1;;) {// annular displacement
            for (l1 = l0;;) {
                matrices[MATRICE2][MAX - l1][l0] = matrices[MATRICE1][l0][l1];
                matrices[MATRICE2][l0][l1] = matrices[MATRICE1][l1][MAX - l0];
                matrices[MATRICE2][l1][MAX - l0] = matrices[MATRICE1][MAX - l0][MAX - l1];
                matrices[MATRICE2][MAX - l0][MAX - l1] = matrices[MATRICE1][MAX - l1][l0];
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

    private static void outputMatrices(final String SELECTED_OPTION) {
        final byte UNSIGNED_CHAR_10 = -118;
        final byte UNSIGNED_CHAR_100 = -28;
        for (byte l0 = 0, l1, l2; l0 < matrices.length; ++l0) {
            if (l0 == 0) {
                System.out.print("\nДана следующая матрица:\n");
            } else {
                switch (SELECTED_OPTION) {
                    case OPTION_90: {
                        System.out.print("\n\nРазворот матрицы на 90 градусов в право:\n");
                    }
                        break;
                    case OPTION_180: {
                        System.out.print("\n\nРазворот матрицы на 180 градусов в право:\n");
                    }
                        break;
                    case OPTION_270: {
                        System.out.print("\n\nРазворот матрицы на 270 градусов в право:\n");
                    }
                        break;
                }
            }
            for (l1 = 0; l1 < SIZE; ++l1) {
                System.out.print("\n");
                for (l2 = 0; l2 < SIZE; ++l2) {
                    if (matrices[l0][l1][l2] < UNSIGNED_CHAR_10) {
                        System.out.print("   " + (matrices[l0][l1][l2] - OFFSET_ZERO));
                    } else if (matrices[l0][l1][l2] < UNSIGNED_CHAR_100) {
                        System.out.print("  " + (matrices[l0][l1][l2] - OFFSET_ZERO));
                    } else {
                        System.out.print(" " + (matrices[l0][l1][l2] - OFFSET_ZERO));
                    }
                }
            }
        }
    }

    private static byte menu() {
        System.out.print("""


                Выберите градус разворота матрицы в право:
                1 – 90 градусов.
                2 – 180 градусов.
                3 – 270 градусов.
                >""");
        switch (scanner.nextLine()) {
            case OPTION_90: {
                turn90DegreeRight();
                outputMatrices(OPTION_90);
            }
                return 0;
            case OPTION_180: {
                turn180DegreeRight();
                outputMatrices(OPTION_180);
            }
                return 0;
            case OPTION_270: {
                turn270DegreeRight();
                outputMatrices(OPTION_270);
            }
                return 0;
            default: {
                System.out.print("Ошибка ввода!\n");
            }
                return 1;
        }
    }

    public static void main(String[] args) {
        conversion();
        while (menu() != 0)
            ;
        System.out.print("\n\n");
    }
}