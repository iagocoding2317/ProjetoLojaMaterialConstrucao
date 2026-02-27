public class Funcionario extends Pessoa{
    private int idFuncionario;
    private int indiceFidelidade;
    private double salario;

     public Funcionario(int idFuncionario, String nome, String CPF, int idade, String endereco, int indiceFidelidade, double salario){
        super(nome, CPF, idade, endereco);
        this.idFuncionario = idFuncionario;
        this.indiceFidelidade = indiceFidelidade;
        this.salario = salario;

    }   

    public int getID(){
        return idFuncionario;
    }

    public double getSalario(){
        return salario;
    }

    public int getFidelidade(){
        return indiceFidelidade;
    }
    

    public void Informacoes(){
        System.out.println("Nome: " + getNome() +  ", CPF: " + getCPF() + ", Código: " + getID() + ", Salário: " + getSalario() + ", Índice de Fidelidade: " + getFidelidade());
    }

    
}
