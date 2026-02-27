public abstract class Pessoa{
    private String nome;
    private String CPF;
    private int idade;
    private String endereco;
    
    public Pessoa(String nome, String CPF, int idade, String endereco){
        this.nome = nome;
        this.CPF = CPF;
        this.idade = idade;
        this.endereco = endereco;
    }
    
    public abstract void Informacoes();

    public abstract int getID();


    public String getNome(){
        return nome;
    }

    public String getCPF(){
        return CPF;
    }

    public int getIdade(){
        return idade;
    }

    public String getEndereco(){
        return endereco;
    }
    
    
}
