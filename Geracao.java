import java.util.ArrayList;

public class Geracao
{
    //Parametros
    private int tamanhoPopulacao;
    private boolean elitismo;
    private double chanceMutacao;

    //
    ArrayList<CromossomoAlunos> populacao;

    public Geracao(int tamanhoPopulacao, boolean elitismo, double chanceMutacao)
    {
        this.tamanhoPopulacao = tamanhoPopulacao;
        this.elitismo = elitismo;
        this.chanceMutacao = chanceMutacao;
        this.populacao = new ArrayList<CromossomoAlunos>();
        generate();
    }

    public Geracao(Geracao anterior)
    {

    }

    private void generate()
    {
        for (int i = 0; i < tamanhoPopulacao; i++)
        {
            populacao.add(new );
        }
    }
}