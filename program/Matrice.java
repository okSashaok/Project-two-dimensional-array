package program;

import java.util.Scanner;
import java.util.Random;

public class Matrice {
    private final Scanner scanner = new Scanner(System.in);
    private final byte OFFSET_ZERO = -128;
    private byte option = 0;
    private final byte OPTION_90 = 1;
    private final byte OPTION_180 = 2;
    private final byte OPTION_270 = 3;

    public void creationSquare(byte[][] matrice) {
        Random random = new Random();
        for (byte l0 = 0, l1; l0 < matrice.length; ++l0) {
            for (l1 = 0; l1 < matrice.length; ++l1) {
                matrice[l0][l1] = (byte) (OFFSET_ZERO + random.nextInt(256));
            }
        }
    }

    public boolean menu() {
        System.out.print("""


                Выберите градус разворота матрицы в право:
                1 – 90 градусов.
                2 – 180 градусов.
                3 – 270 градусов.
                >""");
        switch (scanner.nextLine()) {
            case "1": {
                option = 1;
            }
                return true;
            case "2": {
                option = 2;
            }
                return true;
            case "3": {
                option = 3;
            }
                return true;
            default: {
                System.out.print("Ошибка ввода!\n");
            }
                return false;
        }
    }

    public byte[][] turnSquareRight(final byte[][] matrice) {
        if (option == 0) {
            return null;
        }
        byte getMatrice[][] = new byte[matrice.length][matrice.length];
        final byte MAX = (byte) (matrice.length - 1);
        switch (option) {
            case OPTION_90: {
                for (byte l0 = 0, l1; l0 < matrice.length; ++l0) {// annular displacement
                    for (l1 = 0; l1 < matrice.length; ++l1) {
                        getMatrice[l1][MAX - l0] = matrice[l0][l1];
                    }
                }
            }
                break;
            case OPTION_180: {
                for (byte l0 = 0, l1; l0 < matrice.length; ++l0) {// annular displacement
                    for (l1 = 0; l1 < matrice.length; ++l1) {
                        getMatrice[MAX - l0][MAX - l1] = matrice[l0][l1];
                    }
                }
            }
                break;
            case OPTION_270: {
                for (byte l0 = 0, l1; l0 < matrice.length; ++l0) {// annular displacement
                    for (l1 = 0; l1 < matrice.length; ++l1) {
                        getMatrice[MAX - l1][l0] = matrice[l0][l1];
                    }
                }
            }
                break;
        }
        return getMatrice;
    }

    public void outputSquareMatrice(final byte[][] matrice) {
        for (byte l0 = 0, l1; l0 < matrice.length; ++l0) {
            System.out.print("\n");
            for (l1 = 0; l1 < matrice.length; ++l1) {
                System.out.printf("%4d", (matrice[l0][l1] - OFFSET_ZERO));
            }
        }
        System.out.print("\n");
    }
}