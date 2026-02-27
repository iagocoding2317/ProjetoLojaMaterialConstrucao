import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class CompraDAO {

    public void inserirCompra(int idCliente, int idProduto, int quantidade) {
        ConnectionPostgreSQL postgres = new ConnectionPostgreSQL();
        Connection conexao = null;
        PreparedStatement stmt = null;

        try {
            conexao = postgres.getConection();
            stmt = conexao.prepareStatement(
                "INSERT INTO Compra(id_cliente, id_produto, quantidade) VALUES(?,?,?)"
            );
            stmt.setInt(1, idCliente);
            stmt.setInt(2, idProduto);
            stmt.setInt(3, quantidade);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            postgres.close(stmt, conexao);
        }
    }

    public void inserirCompra(Compra compra) {
        inserirCompra(compra.getCliente().getID(),
                      compra.getProduto().getIdProduto(), 1);
    }

    public List<String> listarCompras() {
        List<String> lista = new LinkedList<>();
        ConnectionPostgreSQL postgres = new ConnectionPostgreSQL();
        Connection conexao = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conexao = postgres.getConection();
            stmt = conexao.prepareStatement(
                "SELECT p.nome AS nomeCliente, pr.nome AS nomeProduto, pr.preco " +
                "FROM Compra co " +
                "JOIN Cliente c ON co.id_cliente = c.id_cliente " +
                "JOIN Pessoa p ON c.cpf = p.cpf " +
                "JOIN Produto pr ON co.id_produto = pr.id_produto " +
                "ORDER BY p.nome"
            );
            rs = stmt.executeQuery();

            while (rs.next()) {
                lista.add("Cliente: " + rs.getString("nomeCliente") +
                        " | Produto: " + rs.getString("nomeProduto") +
                        " | Preço: R$" + rs.getFloat("preco"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            postgres.close(rs, stmt, conexao);
        }

        return lista;
    }

    public List<String> listarComprasPorCliente(int idCliente) {
        List<String> lista = new LinkedList<>();
        ConnectionPostgreSQL postgres = new ConnectionPostgreSQL();
        Connection conexao = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conexao = postgres.getConection();
            stmt = conexao.prepareStatement(
                "SELECT pr.nome AS nomeProduto, pr.preco " +
                "FROM Compra co " +
                "JOIN Produto pr ON co.id_produto = pr.id_produto " +
                "WHERE co.id_cliente = ?"
            );
            stmt.setInt(1, idCliente);
            rs = stmt.executeQuery();

            while (rs.next()) {
                lista.add("Produto: " + rs.getString("nomeProduto") +
                        " | Preço: R$" + rs.getFloat("preco"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            postgres.close(rs, stmt, conexao);
        }

        return lista;
    }

    public void deletarCompra(int idCliente, int idProduto) {
        ConnectionPostgreSQL postgres = new ConnectionPostgreSQL();
        Connection conexao = null;
        PreparedStatement stmt = null;

        try {
            conexao = postgres.getConection();
            stmt = conexao.prepareStatement(
                "DELETE FROM Compra WHERE id_cliente=? AND id_produto=?"
            );
            stmt.setInt(1, idCliente);
            stmt.setInt(2, idProduto);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            postgres.close(stmt, conexao);
        }
    }
}