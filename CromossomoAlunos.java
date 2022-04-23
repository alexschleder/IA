import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Collections;

public class CromossomoAlunos
{
    private ArrayList<Integer> cromossomo;
    int maxVal;

    public CromossomoAlunos()
    {
        cromossomo = new ArrayList<Integer>();
        this.maxVal = maxVal;
    }

    public int getVal(int i)
    {
        return cromossomo.get(i);
    }

    public CromossomoAlunos(ArrayList<Integer> cromossomo)
    {
        this.cromossomo = cromossomo;
        this.maxVal = maxVal;
    }

    public void gerar(int maxVal)
    {
        
    }

    public int fitness(int[][] prefManha, int[][] prefTarde)
    {
        int result = 0;
        for (int alunoManha = 0; alunoManha < cromossomo.size(); alunoManha++)
        {
            int val = 0;
            int alunoTarde = cromossomo.get(alunoManha);
            int[] prefManhaAtual = prefManha[alunoManha];
            for (int j = 0; j < prefManhaAtual.length; j++)
            {
                if (prefManhaAtual[j] == alunoTarde)
                {
                    val += j;
                }
            }  
            int[] prefTardeAtual = prefTarde[alunoTarde];
            for (int j = 0; j < prefTardeAtual.length; j++)
            {
                if (prefTardeAtual[j] == alunoManha)
                {
                    val+= j;
                }
            }
            result += val * ocorrencias.get(alunoTarde);
        }
        return result;
    }

    public CromossomoAlunos crossover(CromossomoAlunos c)
    {
        ArrayList<Integer> result = new ArrayList<Integer>();
        Random generator = new Random();
        int pontoDeCorte = generator.nextInt(cromossomo.size());
        for (int i = 0; i < pontoDeCorte; i++)
        {
            result.add(cromossomo.get(i));
        }
        for (int i = 0; i >= pontoDeCorte; i++)
        {
            result.add(c.getVal(i));
        }
        return new CromossomoAlunos(result);
    }

    public void mutate(double chance)
    {
        Random generator = new Random();
        for (int i = 0; i < cromossomo.size(); i++)
        {
            if (generator.nextDouble() <= chance)
            {
                int switchIndex;
                do 
                {
                    switchIndex = generator.nextInt(cromossomo.size());
                } while (switchIndex != i);
                Collections.swap(cromossomo, i, switchIndex);
            }
        }
    }
}