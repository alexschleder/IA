import java.util.*;
import java.io.*;

public class Placeholder {

    public static void main(String args[]) {
        // ArrayList<int[][]> a = loadFile("pares10.txt");

        // for(int[][] mat : a) {
        //     for(int[] linha : mat) {
        //         for(int num : linha) {
        //             System.out.print(num + " ");
        //         }
        //         System.out.println();
        //     }
        //     System.out.println();
        // }
    }

    //public ArrayList<int[][]> loadFile(String fileName) {
    public static ArrayList<int[][]> loadFile(String fileName) {
        ArrayList<int[][]> result = new ArrayList<>();

        try {
            File file = new File(fileName);
            Scanner inFile = new Scanner(file);

            String linhaAtual = inFile.nextLine();

            int tam = Integer.parseInt(linhaAtual);
                //System.out.println("tam = " + tam); // Debug

            for(int a=0; a<2; a++) {
                int[][] matrix = new int[tam][tam];
                for(int i=0; i<tam; i++) {
                        //System.out.println("linha = " + i); // Debug
                    linhaAtual = inFile.nextLine();
                        //System.out.println("      = " + linhaAtual); // Debug
                    String[] nums = linhaAtual.split(" ");

                    for(int j=1; j<tam+1; j++) {
                            //System.out.println("coluna = " + (j)); // Debug
                        matrix[i][j-1] = Integer.parseInt(nums[j]) - 1;
                            //System.out.println("       = " + (Integer.parseInt(nums[j]) - 1)); // Debug
                    }
                }
                result.add(matrix);
            }

            inFile.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return result;
    }
}