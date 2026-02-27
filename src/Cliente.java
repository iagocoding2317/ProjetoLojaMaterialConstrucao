  public class Cliente extends Pessoa{
    private int idCliente;
    private float limiteCreditos;
    
     public Cliente(int idCliente, String nome, String CPF, int idade, String endereco, float limiteCreditos){
        super(nome, CPF, idade, endereco);  
         this.idCliente = idCliente;
         this.limiteCreditos = limiteCreditos;
    }   

    public int getID(){
        return idCliente;
    }

    public float getLimite(){
      return limiteCreditos;
    }

    public void Informacoes(){
      System.out.println("Nome: " + getNome() +  ", CPF: " + getCPF() + ", ID: " + getID() +", Limite: " + getLimite() + "reais");
    }
}

    
