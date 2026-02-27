import java.util.Scanner;

public class MenuLoja {

    private Scanner sc = new Scanner(System.in);

    private ClienteDAO clienteDAO = new ClienteDAO();
    private FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
    private ProdutoDAO produtoDAO = new ProdutoDAO();
    private CompraDAO compraDAO = new CompraDAO();
    private FornecedoraDAO fornecedoraDAO = new FornecedoraDAO();

    public void iniciar() {

        int opcao;

        do {
            System.out.println("\n===== MENU LOJA =====");
            System.out.println("1  - Cadastrar Cliente");
            System.out.println("2  - Cadastrar Funcionario");
            System.out.println("3  - Cadastrar Fornecedora");
            System.out.println("4  - Cadastrar Tinta");
            System.out.println("5  - Cadastrar Tijolo");
            System.out.println("6  - Cadastrar Cimento");
            System.out.println("7  - Listar Clientes");
            System.out.println("8  - Listar Funcionarios");
            System.out.println("9  - Listar Produtos");
            System.out.println("10 - Realizar Compra");
            System.out.println("11 - Listar Compras");
            System.out.println("0  - Sair");
            System.out.print("Escolha: ");

            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1:  cadastrarCliente();    break;
                case 2:  cadastrarFuncionario(); break;
                case 3:  cadastrarFornecedora(); break;
                case 4:  cadastrarTinta();       break;
                case 5:  cadastrarTijolo();      break;
                case 6:  cadastrarCimento();     break;
                case 7:  clienteDAO.listarClientes().forEach(System.out::println); break;
                case 8:  funcionarioDAO.listarFuncionarios().forEach(System.out::println); break;
                case 9:
                    produtoDAO.listarTintas().forEach(System.out::println);
                    produtoDAO.listarTijolos().forEach(System.out::println);
                    produtoDAO.listarCimentos().forEach(System.out::println);
                    break;
                case 10: realizarCompra(); break;
                case 11: compraDAO.listarCompras().forEach(System.out::println); break;
                case 0:  System.out.println("Encerrando sistema..."); break;
                default: System.out.println("Opcao invalida!");
            }

        } while (opcao != 0);
    }

    private void cadastrarCliente() {
        System.out.print("Nome: ");
        String nome = sc.nextLine();
        System.out.print("CPF: ");
        String cpf = sc.nextLine();
        System.out.print("Idade: ");
        int idade = sc.nextInt(); sc.nextLine();
        System.out.print("Endereco: ");
        String endereco = sc.nextLine();
        System.out.print("Limite de Credito: ");
        float limite = sc.nextFloat(); sc.nextLine();

        clienteDAO.inserirClienteCompleto(new Cliente(0, nome, cpf, idade, endereco, limite));
        System.out.println("Cliente cadastrado com sucesso!");
    }

    private void cadastrarFuncionario() {
        System.out.print("Nome: ");
        String nome = sc.nextLine();
        System.out.print("CPF: ");
        String cpf = sc.nextLine();
        System.out.print("Idade: ");
        int idade = sc.nextInt(); sc.nextLine();
        System.out.print("Endereco: ");
        String endereco = sc.nextLine();
        System.out.print("Salario: ");
        double salario = sc.nextDouble(); sc.nextLine();
        System.out.print("Indice de Fidelidade: ");
        int fidelidade = sc.nextInt(); sc.nextLine();

        funcionarioDAO.inserirFuncionarioCompleto(new Funcionario(0, nome, cpf, idade, endereco, fidelidade, salario));
        System.out.println("Funcionario cadastrado com sucesso!");
    }

    private void cadastrarFornecedora() {
        System.out.print("CNPJ: ");
        String cnpj = sc.nextLine();
        System.out.print("Nome: ");
        String nome = sc.nextLine();

        fornecedoraDAO.inserirFornecedora(new Fornecedora(cnpj, nome));
        System.out.println("Fornecedora cadastrada!");
    }

    private void cadastrarTinta() {
        System.out.print("Nome: ");
        String nome = sc.nextLine();
        System.out.print("Preco: ");
        float preco = sc.nextFloat(); sc.nextLine();
        System.out.print("Cor: ");
        String cor = sc.nextLine();
        System.out.print("Tipo de Acabamento: ");
        String acabamento = sc.nextLine();
        System.out.print("Volume (litros): ");
        float volume = sc.nextFloat(); sc.nextLine();

        produtoDAO.inserirTinta(new Tinta(0, nome, preco, cor, acabamento, volume));
        System.out.println("Tinta cadastrada com sucesso!");
    }

    private void cadastrarTijolo() {
        System.out.print("Nome: ");
        String nome = sc.nextLine();
        System.out.print("Preco de Venda: ");
        float preco = sc.nextFloat(); sc.nextLine();
        System.out.print("Quantidade em Estoque: ");
        int quantidade = sc.nextInt(); sc.nextLine();
        System.out.print("Preco de Custo: ");
        float precoCusto = sc.nextFloat(); sc.nextLine();

        produtoDAO.inserirTijolo(new Tijolo(0, nome, preco, quantidade, precoCusto));
        System.out.println("Tijolo cadastrado com sucesso!");
    }

    private void cadastrarCimento() {
        System.out.print("Nome: ");
        String nome = sc.nextLine();
        System.out.print("Preco: ");
        float preco = sc.nextFloat(); sc.nextLine();
        System.out.print("Peso (kg): ");
        int kg = sc.nextInt(); sc.nextLine();
        System.out.print("Quantidade em Estoque: ");
        int quantidade = sc.nextInt(); sc.nextLine();
        System.out.print("Marca: ");
        String marca = sc.nextLine();

        produtoDAO.inserirCimento(new Cimento(0, nome, preco, kg, quantidade, marca));
        System.out.println("Cimento cadastrado com sucesso!");
    }

    private void realizarCompra() {
        System.out.print("ID Cliente: ");
        int idCliente = sc.nextInt();
        System.out.print("ID Produto: ");
        int idProduto = sc.nextInt();
        System.out.print("Quantidade: ");
        int quantidade = sc.nextInt(); sc.nextLine();

        Cliente cliente = clienteDAO.buscarPorId(idCliente);
        Produto produto = produtoDAO.buscarProdutoPorId(idProduto);

        if (cliente == null || produto == null) {
            System.out.println("Cliente ou Produto nao encontrado!");
            return;
        }

        compraDAO.inserirCompra(cliente.getID(), produto.getIdProduto(), quantidade);
        System.out.println("Compra realizada!");
    }
}