public class Cadastro {
    private Cliente[] clientes = new Cliente[100];
    private Funcionario[] funcionarios = new Funcionario[100];
    private Produto[] produtos = new Produto[100];
    private Fornecedora[] fornecedoras = new Fornecedora[100];
    private Compra[] compras = new Compra[500];

    private int qtdClientes = 0;
    private int qtdFuncionarios = 0;
    private int qtdProdutos = 0;
    private int qtdFornecedoras = 0;
    private int qtdCompras = 0;



    // CADASTROS
    
    public void cadastrarCliente(Cliente c){
        if (qtdClientes < clientes.length){
            clientes[qtdClientes++] = c;
        }
    }

    public void cadastrarFuncionario(Funcionario f){
        if (qtdFuncionarios < funcionarios.length){
            funcionarios[qtdFuncionarios++] = f;
        }
    }

    public void cadastrarProduto(Produto p){
        if (qtdProdutos < produtos.length){
            produtos[qtdProdutos++] = p;
        }
    }

    public void cadastrarFornecedoras(Fornecedora f){
        if (qtdFornecedoras < fornecedoras.length){
            fornecedoras[qtdFornecedoras++] = f;
        }
    }

    public void cadastrarCompra(Compra m){
        if (qtdCompras < compras.length){
            compras[qtdCompras++] = m;
        }
    }

    // LISTAGENS


    public void listarClientes(){
        System.out.println("--- CLIENTES ---");
        for (int i = 0; i < qtdClientes; i++){
            clientes[i].Informacoes();
        }
    }

    public void listarFuncionarios(){
        System.out.println("--- FUNCIONÁRIOS ---");
        for (int i = 0; i < qtdFuncionarios; i++){
            funcionarios[i].Informacoes();
        }
    }

    public void listarProdutos() {
        System.out.println("--- PRODUTOS ---");
        for (int i = 0; i < qtdProdutos; i++) {
            produtos[i].InformacoesdoProduto();
        }
    }

    public void listarFornecedoras() {
        System.out.println("--- FORNECEDORAS ---");
        for (int i = 0; i < qtdFornecedoras; i++) {
            fornecedoras[i].InformacoesFornecedora();
        }
    }

     public void listarCompras(){
        System.out.println("--- COMPRAS ---");
        for (int i = 0; i < qtdCompras; i++){
            compras[i].mostrarCompra();
        }
    }


}
