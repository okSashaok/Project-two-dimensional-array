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
        for (byte i = 0; i < matrice.length; i++) {
            for (byte j = 0; j < matrice.length; j++) {
                matrice[i][j] = (byte) (OFFSET_ZERO + random.nextInt(256));
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
                option = OPTION_90;
            }
            return true;
            case "2": {
                option = OPTION_180;
            }
            return true;
            case "3": {
                option = OPTION_270;
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
                for (byte i = 0; i < matrice.length; i++) {// annular displacement
                    for (byte j = 0; j < matrice.length; j++) {
                        getMatrice[j][MAX - i] = matrice[i][j];
                    }
                }
            }
            break;
            case OPTION_180: {
                for (byte i = 0; i < matrice.length; i++) {// annular displacement
                    for (byte j = 0; j < matrice.length; j++) {
                        getMatrice[MAX - i][MAX - j] = matrice[i][j];
                    }
                }
            }
            break;
            case OPTION_270: {
                for (byte i = 0; i < matrice.length; i++) {// annular displacement
                    for (byte j = 0; j < matrice.length; j++) {
                        getMatrice[MAX - j][i] = matrice[i][j];
                    }
                }
            }
            break;
        }
        return getMatrice;
    }

    public void outputSquareMatrice(final byte[][] matrice) {
        for (byte i = 0; i < matrice.length; i++) {
            System.out.print("\n");
            for (byte j = 0; j < matrice.length; j++) {
                System.out.printf("%4d", (matrice[i][j] - OFFSET_ZERO));
            }
        }
        System.out.print("\n");
    }
}