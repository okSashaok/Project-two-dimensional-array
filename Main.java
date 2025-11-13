public class Main{
    private static final byte OFFSET_ZERO = 127
    , SIZE = 8;
    private static final short[][] SAMPLE = {
        { 114, 112, 148,  83, 204,  22, 125,  31 }
        ,{ 192, 231, 245, 128,  63, 246, 139,  17 }
        ,{  61, 128, 224, 45,  91,  57, 239,  34 }
        ,{ 219, 237, 167, 191, 236, 146, 144, 117 }
        ,{  35, 199, 102, 124, 208, 195,  21, 147 }
        ,{  52, 229, 126,  32,  24, 145,  19,  39 }
        ,{ 107,  43, 190,  43,  47, 172,  18,  19 }
        ,{  62, 221,   6, 208, 241, 198, 187,  85 }
    };
    private static byte[][] array = new byte[SIZE][SIZE];
    private static void conversion(){
        for(byte l0 = 0, l1; l0 < SIZE; ++l0){
            for(l1 = 0; l1 < SIZE; ++l1){
                array[l0][l1] = (byte)(SAMPLE[l0][l1] - OFFSET_ZERO);
            }
        }
    }
    private static void outputMatrices(){
        for(byte l0 = 0, l1; l0 < SIZE; ++l0){
            System.out.print("\n");
            for(l1 = 0; l1 < SIZE; ++l1){
                if(array[l0][l1] < -117){
                    System.out.print("   " + (array[l0][l1] + OFFSET_ZERO));
                }
                else if(array[l0][l1] < -27){
                    System.out.print("  " + (array[l0][l1] + OFFSET_ZERO));
                }
                else{
                    System.out.print(" " + (array[l0][l1] + OFFSET_ZERO));
                }
            }
        }
    }
    private static void turn90DegreeRight(){
        byte cache;
        final byte MAX = SIZE - 1
        , MIDDLE = SIZE / 2;
        for(byte l0 = 0, l1; l0 < SIZE;){//annular displacement
            for(l1 = l0; l1 < SIZE;){
                cache = array[l0][l1];
                array[l0][l1] = array[MAX - l1][l0];
                array[MAX - l1][l0] = array[MAX - l0][MAX - l1];
                array[MAX - l0][MAX - l1] = array[l1][MAX - l0];
                array[l1][MAX - l0] = cache;
                if(MAX - l0 > ++l1){ continue; }
                break;
            }
            if(MIDDLE > ++l0){ continue; }
            break;
        }

    }
    public static void main(String[] args){
        conversion();
        System.out.print("\nДана следующая матрица:\n");
        outputMatrices();
        turn90DegreeRight();
        System.out.print("\n\nРазворот матрицы на 90 градусов в право:\n");
        outputMatrices();
        System.out.print("\n\n");
    }
}