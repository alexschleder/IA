import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Geracao
{
    //Parametros
    public int tamanhoPopulacao;
    public boolean elitismo;
    public double chanceMutacao;

    //
    public ArrayList<CromossomoAlunos> populacao;

    public Geracao(int tamanhoPopulacao, boolean elitismo, double chanceMutacao, int maxVal, int[][] prefManha, int[][] prefTarde)
    {
        this.tamanhoPopulacao = tamanhoPopulacao;
        this.elitismo = elitismo;
        this.chanceMutacao = chanceMutacao;
        this.populacao = new ArrayList<CromossomoAlunos>();
        generate(maxVal);
        for (int i = 0; i < tamanhoPopulacao; i++)
        {
            populacao.get(i).fitness(prefManha, prefTarde);
        }
        Collections.sort(populacao);
    }

    public Geracao(Geracao anterior, int[][] prefManha, int[][] prefTarde)
    {
        this.tamanhoPopulacao = anterior.tamanhoPopulacao;
        this.elitismo = anterior.elitismo;
        this.chanceMutacao = anterior.chanceMutacao;
        this.populacao = new ArrayList<CromossomoAlunos>();
        int i = 0;
        Random r = new Random();
        if (elitismo)
        {   
            populacao.add(anterior.populacao.get(i));
            i++;
        }
        while (i < tamanhoPopulacao)
        {
            int p1Index = r.nextInt(tamanhoPopulacao);
            int p2Index;
            do 
            {
                p2Index = r.nextInt(tamanhoPopulacao);
            } while (p1Index == p2Index);
            CromossomoAlunos pai = anterior.populacao.get(p1Index); 
            CromossomoAlunos mae = anterior.populacao.get(p2Index); 
            CromossomoAlunos c1 = new CromossomoAlunos(pai.getMaxVal());
            CromossomoAlunos c2 = new CromossomoAlunos(pai.getMaxVal());
            CromossomoAlunos.crossover(pai, mae, c1, c2);
            populacao.add(c1);
            c1.mutate(chanceMutacao);
            c2.mutate(chanceMutacao);
            c1.fitness(prefManha, prefTarde);
            c2.fitness(prefManha, prefTarde);
            i++;
            if (i < tamanhoPopulacao)
            {
                populacao.add(c2);
                i++;
            }
        }
        //populacao.get(i).fitness(prefManha, prefTarde);
    }
    
    public String getStats()
    {
        String result = "";
        Collections.sort(populacao);

        double fitnessMedia = 0;
        for (CromossomoAlunos c : populacao)
        {
            fitnessMedia += c.getFitness();
        }
        fitnessMedia /= tamanhoPopulacao;
        result += "Media da pontuacao de fitness: " + fitnessMedia + "\n";
        result += "Melhor cromossomo: " + populacao.get(0); 
        for (int i = 0; i < tamanhoPopulacao; i++)
        {
            //result += populacao.get(i);
        }

        return result;
    }

    private void generate(int maxVal)
    {
        for (int i = 0; i < tamanhoPopulacao; i++)
        {
            CromossomoAlunos c = new CromossomoAlunos(maxVal);
            c.gerar();
            populacao.add(c);
        }
    }
}