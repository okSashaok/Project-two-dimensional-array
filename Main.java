import program.Matrice;

public class Main {
    private static final byte SIZE = 8;

    public static void main(String[] args) {
        Matrice matrice = new Matrice();
        byte[][] canvas = new byte[SIZE][SIZE];
        matrice.creationSquare(canvas);
        while (!matrice.menu()) ;
        byte[][] turnedCanvas = matrice.turnSquareRight(canvas);
        System.out.print("\nДана следующая матрица:\n");
        matrice.outputSquareMatrice(canvas);
        System.out.print("\nПовёрнутая матрица:\n");
        matrice.outputSquareMatrice(turnedCanvas);
        System.out.print("\n\n");
    }
}