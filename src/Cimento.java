public class Cimento extends Produto{
    private int kg;
    private int quantidade;
    private String marca;

    public Cimento(int idProduto, String nome, float preco, int kg, int quantidade, String marca){
    super(idProduto, nome, preco);
    this.kg = kg;
    this.quantidade = quantidade;
    this.marca = marca;
    }

    public int getKg(){
        return kg;
    }

    public int getQuantidade(){
        return quantidade;
    }

    public String getMarcas(){
        return marca;
    }

    public void InformacoesdoProduto(){
        System.out.println("Cimento: " + getNome() + "Preço: " + getPreco() + "reais, Peso do cimento: " + getKg() + "Quantidade de cimento: " + getQuantidade() + "Marca do cimento: " + getMarcas());
    }
}
