public class Tinta extends Produto{
    private String cor;
    private String tipoAcabamento;
    private float volume;

    public Tinta(int idProduto, String nome, float preco, String cor, String tipoAcabamento, float volume){
        super(idProduto, nome, preco);
        this.cor = cor;
        this.tipoAcabamento = tipoAcabamento;
        this.volume = volume;
    }

    public String getCor(){
        return this.cor;
    }

    public String getAcabamento(){
        return this.tipoAcabamento;
    }

    public float getVolume(){
        return this.volume;
    }


     public void InformacoesdoProduto(){
        System.out.println("Tinta:" + getNome() + ", Cor: " + getCor() + ",Tipo de acabamento: " + getAcabamento() + ",Volume: " + getVolume() + "litros.");
    }

}
