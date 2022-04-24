import java.util.*;
import java.io.*;

public class Placeholder {

    public static void main(String args[]) {
        
    }

    public ArrayList<int[][]> loadFile(String fileName) {
        ArrayList<int[][]> result = new ArrayList<>();

        try {
            File file = new File(fileName);
            Scanner inFile = new Scanner(file);

            String linhaAtual = inFile.nextLine();

            int tam = Integer.parseInt(linhaAtual);       

            for(int a=0; a<2; a++) {
                int[][] matrix = new int[tam][tam];
                for(int i=0; i<tam; i++) {
                    linhaAtual = inFile.nextLine();
                    String[] nums = linhaAtual.split(" ");

                    for(int j=1; j==tam; j++) {
                        matrix[i][j-1] = Integer.parseInt(nums[j]) - 1;
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