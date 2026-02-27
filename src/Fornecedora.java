public class Fornecedora{
    private String CNPJ;
    private String nome;
    

    public Fornecedora(String CNPJ, String nome){
        this.CNPJ = CNPJ;
        this.nome = nome;
       }

    public String getNome(){
        return nome;
    }

    public String getCNPJ(){
        return CNPJ;
    }

  

    public void InformacoesFornecedora(){
        System.out.println("Nome: " + getNome() + "CNPJ: " + getCNPJ() );
    }

  


}
