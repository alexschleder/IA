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
        Collections.sort(anterior.populacao);
        if (elitismo)
        {   
            populacao.add(anterior.populacao.get(0));
            i++;
        }
        while (i < tamanhoPopulacao)
        {
            CromossomoAlunos p1 = anterior.torneio(); 
            CromossomoAlunos p2 = p1;
            while (p1 == p2)
            {
                p2 = anterior.torneio();
            }
            CromossomoAlunos c1 = new CromossomoAlunos(p1.getMaxVal());
            CromossomoAlunos c2 = new CromossomoAlunos(p1.getMaxVal());
            CromossomoAlunos.crossover(p1, p2, c1, c2);
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
    }
    
    public CromossomoAlunos torneio()
    {
        Random r = new Random();
        int p1Index = r.nextInt(tamanhoPopulacao);
        int p2Index;
        do 
        {
            p2Index = r.nextInt(tamanhoPopulacao);
        } while (p1Index == p2Index);
        CromossomoAlunos p1 = populacao.get(p1Index); 
        CromossomoAlunos p2 = populacao.get(p1Index); 
        if (p1.compareTo(p2) == -1) return p1;
        else return p2;
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
        for (int i = 1; i < tamanhoPopulacao; i++)
        {
            result += populacao.get(i);
        }
        result += "Melhor cromossomo:\n" + populacao.get(0) + "\n" + populacao.get(0).pares(); 

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