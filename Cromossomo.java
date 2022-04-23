import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Collections;

public class CromossomoAlunos
{
    private ArrayList<int> cromossomo;

    public CromossomoAlunos()
    {
        cromossomo = new ArrayList<int>();
        this.maxVal = maxVal;
    }

    public CromossomoAlunos(ArrayList<int> cromossomo)
    {
        this.cromossomo = cromossomo;
        this.maxVal = maxVal;
    }

    public void gerar(int maxVal)
    {
        Random generator = new Random();
        for (int i = 0; i < maxVal; i++)
        {
            cromossomo.add(generator.nextInt(maxVal));
        }
    }

    public int fitness(int[][] prefManha int[][] prefTarde)
    {
        HashMap<int, int> ocorrencias = new HashMap<int, int>();

        for (int i : cromossomo)
        {
            if (ocorrencias.get() == null)
            {
                occorrencias.put(i, 1)
            }
            else 
            {
                occorrencias.put.(i, occorrencias.get(i)+1);
            }
        }

        int result = 0;
        for (int alunoManha = 0; alunoManha < cromossomo.length(); alunoManha++)
        {
            int val = 0;
            int alunoTarde = cromossomo.get(alunoManha);
            int[] prefManhaAtual = preManha[i];
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
                    val+= j
                }
            }
            result += val * ocorrencias.get(alunoTarde);
        }
        return result;
    }

    public CromossomoAlunos crossover(CromossomoAlunos c)
    {
        ArrayList<int> result = new ArrayList<int>;
        Random generator = new Random();
        int pontoDeCorte = generator.nextInt(cromossomo.size())
        for (int i = 0; i < pontoDeCorte; i++)
        {
            result.add(cromossomo.get(i));
        }
        for (int i = 0; i >= pontoDeCorte; i++)
        {
            result.add(c.get(i));
        }
        return new CromossomoAlunos(result);
    }

    public void mutate(double chance)
    {
        Random generator = new Random();
        for (int i = 0; i < cromossomo.size(); i++)
        {
            if (generator.nextDouble <= chance)
            {
                int switchIndex;
                do 
                {
                    switchIndex = random.nextInt(cromossomo.size());
                } while (switchIndex != i)
            }
            Collections.swap(cromossomo, i, switchIndex);
        }
    }
}