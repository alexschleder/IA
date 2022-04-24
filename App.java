import java.util.*;
import java.io.*;

public class App {

    public static void main(String args[]) {
        //ArrayList<int[][]> teste = loadFile("pares10.txt");
        // for(int[][] mat : teste) {
        //     for(int[] linha : mat) {
        //         for(int num : linha) {
        //             System.out.print(num + " ");
        //         }
        //         System.out.println();
        //     }
        //     System.out.println();
        // }

        if(args.length < 5) {
            System.out.println("Usage: ");
            System.out.println("java App <arquivo> <numGeracoes> <tamPopulacao> <chanceMutacao> <s/n elitismo>");
            
            return;
        }

        ArrayList<int[][]> teste = loadFile(args[0]);
        int quantGeracoes = Integer.parseInt(args[1]);
        int tamPopulacao = Integer.parseInt(args[2]);
        double chanceMutacao = Double.parseDouble(args[3]);
        boolean elitismo = true;

        if(args[4].equals("s"))
            elitismo = false;

        int maxVal = teste.get(0).length;

        Geracao geracao = new Geracao(tamPopulacao, elitismo, chanceMutacao, maxVal, teste.get(0), teste.get(1));
        
        System.out.println("Geracao 0:\n" + geracao.getStats());

        for(int i=1; i<quantGeracoes; i++) {
            geracao = new Geracao(geracao, teste.get(0), teste.get(1));
            System.out.println("Geracao " + i + ":\n" + geracao.getStats());
        }
    }

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