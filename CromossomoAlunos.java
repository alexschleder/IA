import java.util.ArrayList;
import java.util.Random;
import java.util.Collections;
import java.lang.Comparable;
import java.math.*;

public class CromossomoAlunos implements Comparable<CromossomoAlunos>
{
    public ArrayList<Integer> cromossomo;
    private int maxVal;
    public int fitness = 0;

    public CromossomoAlunos(int maxVal)
    {
        cromossomo = new ArrayList<Integer>();
        this.maxVal = maxVal;
    }

    public CromossomoAlunos(ArrayList<Integer> cromossomo, int maxVal)
    {
        this.cromossomo = cromossomo;
        this.maxVal = maxVal;
    }

    public int getVal(int i)
    {
        return cromossomo.get(i);
    }

    public int getMaxVal()
    {
        return cromossomo.size();
    }

    public void gerar()
    {
        for (int i = 0; i < maxVal; i++)
        {
            cromossomo.add(i);
        }

        Collections.shuffle(cromossomo);
    }

    public int fitness(int[][] prefManha, int[][] prefTarde)
    {
        int result = 0;
        //System.out.println(cromossomo.size() + " " + maxVal + "\n");
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
            result += val;
        }
        fitness = result;
        return result;
    }

    public int getFitness()
    {
        return fitness;
    }

    public static void crossover(CromossomoAlunos p1, CromossomoAlunos p2, CromossomoAlunos c1, CromossomoAlunos c2)
    {
       ArrayList<Integer> cycle = new ArrayList<>();
       ArrayList<Integer>[] cycles = new ArrayList[p1.getMaxVal()];
       for (int i = 0; i < p1.getMaxVal(); i++)
       {
            cycle = new ArrayList<Integer>();
            int atual = p1.getVal(i);
            int next = p2.getVal(i);
            int comeco = atual;
            while (next != comeco)
            {
                cycle.add(atual);
                atual = next;
                //System.out.println(atual + " " + next);
                next = p2.getVal(p1.cromossomo.indexOf(atual));
            }
            cycle.add(atual);
            //System.out.println(cycle);
            cycles[i] = cycle;
       }

       int best = p1.maxVal + 1;
       int ideal = p1.maxVal/2;
       for (int i = 0; i < cycles.length; i++)
       {
            int current = Math.abs(cycles[i].size() - ideal);
            if (current < best)
            {
                best = current;
                cycle = cycles[i];
            }
       }
       
       ArrayList<Integer> result1 = new ArrayList<>();

       for (int i = 0; i < p1.getMaxVal(); i++)
       {
            if (cycle.contains(p1.getVal(i)))
            {
                result1.add(p1.getVal(i));
            }
            else
            {
                result1.add(p2.getVal(i));
            }
       }
       c1.cromossomo = result1;

       ArrayList<Integer> result2 = new ArrayList<>();

       for (int i = 0; i < p2.getMaxVal(); i++)
       {
            if (cycle.contains(p2.getVal(i)))
            {
                result2.add(p2.getVal(i));
            }
            else
            {
                result2.add(p1.getVal(i));
            }
       }

       c2.cromossomo = result2;

       for (int i = 0; i < p1.maxVal; i++)
       {
           if (Collections.frequency(c1.cromossomo, i) > 1)
           {
                System.out.println("---------------");
                System.out.println(cycle);
                System.out.println(p1.cromossomo);
                System.out.println(p2.cromossomo);
                System.out.println(c1.cromossomo);
                System.out.println("---------------");
           }
       }
       //System.out.println(c1.cromossomo.size() + " " + c1.maxVal + "\n");
       //System.out.println(c2.cromossomo.size() + " " + c2.maxVal + "\n");
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
                } while (switchIndex == i);
                Collections.swap(cromossomo, i, switchIndex);
            }
        }
    }

    @Override
    public int compareTo(CromossomoAlunos c)
    {
        if (this.fitness < c.fitness) return -1;
        else if (this.fitness == c.fitness) return 0;
        else return 1;
    }

    public String toString()
    {
        String result = "";
        result += "Cromossomo: " + cromossomo + "\n";
        result += "Fitness: " + fitness;
        return result;
    }
}