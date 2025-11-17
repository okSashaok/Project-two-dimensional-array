import java.util.Scanner;
import java.util.Random;

public class Main {
    private final static Scanner scanner = new Scanner(System.in);
    private static final byte OFFSET_ZERO = -128;
    private static final byte SIZE = 8;
    private static final String OPTION_90 = "1";
    private static final String OPTION_180 = "2";
    private static final String OPTION_270 = "3";
    private static byte[][][] matrices = new byte[2][SIZE][SIZE];

    private static void conversion() {
        Random random = new Random();
        final byte[][] matrice1 = matrices[0];
        for (byte l0 = 0, l1; l0 < SIZE; ++l0) {
            for (l1 = 0; l1 < SIZE; ++l1) {
                matrice1[l0][l1] = (byte) (OFFSET_ZERO + random.nextInt(256));
            }
        }
    }

    private static void turnRight(final String SELECTED_OPTION) {
        final byte MAX = SIZE - 1;
        final byte[][] matrice1 = matrices[0];
        final byte[][] matrice2 = matrices[1];
        switch (SELECTED_OPTION) {
            case OPTION_90: {
                for (byte l0 = 0, l1; l0 < SIZE; ++l0) {// annular displacement
                    for (l1 = 0; l1 < SIZE; ++l1) {
                        matrice2[l1][MAX - l0] = matrice1[l0][l1];
                    }
                }
            }
                break;
            case OPTION_180: {
                for (byte l0 = 0, l1; l0 < SIZE; ++l0) {// annular displacement
                    for (l1 = 0; l1 < SIZE; ++l1) {
                        matrice2[MAX - l0][MAX - l1] = matrice1[l0][l1];
                    }
                }
            }
                break;
            case OPTION_270: {
                for (byte l0 = 0, l1; l0 < SIZE; ++l0) {// annular displacement
                    for (l1 = 0; l1 < SIZE; ++l1) {
                        matrice2[MAX - l1][l0] = matrice1[l0][l1];
                    }
                }
            }
                break;
        }
    }

    private static void outputMatrices(final String SELECTED_OPTION) {
        for (byte l0 = 0, l1, l2; l0 < matrices.length; ++l0) {
            final byte[][] matrice = matrices[l0];
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
                    System.out.printf("%4d", (matrice[l1][l2] - OFFSET_ZERO));
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
                turnRight(OPTION_90);
                outputMatrices(OPTION_90);
            }
                return 0;
            case OPTION_180: {
                turnRight(OPTION_180);
                outputMatrices(OPTION_180);
            }
                return 0;
            case OPTION_270: {
                turnRight(OPTION_270);
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