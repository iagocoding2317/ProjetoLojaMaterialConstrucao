public class Compra {

    private Cliente cliente;
    private Produto produto;
    private Fornecedora fornecedora;

    public Compra(Cliente cliente, Produto produto, Fornecedora fornecedora) {
        this.cliente = cliente;
        this.produto = produto;
        this.fornecedora = fornecedora;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Produto getProduto() {
        return produto;
    }

    public Fornecedora getFornecedora() {
        return fornecedora;
    }

    public void mostrarCompra() {
        System.out.println("------------------------------");
        System.out.println("Compra: ");
        System.out.println("Cliente: " + cliente.getNome());
        System.out.println("Produto: " + produto.getNome());
        System.out.println("Fornecedora: " + (fornecedora != null ? fornecedora.getNome() : "N/A"));
        System.out.println("Preço: " + produto.getPreco() + " reais");
        System.out.println("------------------------------");
    }
}