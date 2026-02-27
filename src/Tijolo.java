public class Tijolo extends Produto {
    private int quantidade;
    private float precoCusto;

    public Tijolo(int idProduto, String nome, float preco, int quantidade, float precoCusto){
        super(idProduto, nome, preco);
        this.quantidade = quantidade;
        this.precoCusto = precoCusto;
    }

    public int getQuantidade(){
        return quantidade;
    }

    public float getPrecoCusto(){
        return precoCusto;
    }

   public void InformacoesdoProduto(){
    System.out.println("Tijolo: " + getNome() + "Preço: " + getPreco() + "reais, Quantidade do tijolo: " + getQuantidade() + "Preço de Custo: " + getPrecoCusto());
   }
}
