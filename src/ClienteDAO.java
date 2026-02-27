import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class ClienteDAO {

    public void inserirClienteCompleto(Cliente cliente) {
        ConnectionPostgreSQL postgres = new ConnectionPostgreSQL();
        Connection conexao = null;
        PreparedStatement stmt = null;

        try {
            conexao = postgres.getConection();
            conexao.setAutoCommit(false);

            stmt = conexao.prepareStatement(
                "INSERT INTO Pessoa(cpf, nome, idade, endereco) VALUES(?,?,?,?)"
            );
            stmt.setString(1, cliente.getCPF());
            stmt.setString(2, cliente.getNome());
            stmt.setInt(3, cliente.getIdade());
            stmt.setString(4, cliente.getEndereco());
            stmt.executeUpdate();
            stmt.close();

            stmt = conexao.prepareStatement(
                "INSERT INTO Cliente(cpf, limite_credito) VALUES(?,?)"
            );
            stmt.setString(1, cliente.getCPF());
            stmt.setFloat(2, cliente.getLimite());
            stmt.executeUpdate();

            conexao.commit();

        } catch (SQLException e) {
            try {
                if (conexao != null) conexao.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            postgres.close(stmt, conexao);
        }
    }

    public List<String> listarClientes() {
        List<String> lista = new LinkedList<>();
        ConnectionPostgreSQL postgres = new ConnectionPostgreSQL();
        Connection conexao = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conexao = postgres.getConection();
            stmt = conexao.prepareStatement(
                "SELECT p.nome, p.cpf, p.idade, p.endereco, c.id_cliente, c.limite_credito " +
                "FROM Cliente c JOIN Pessoa p ON c.cpf = p.cpf"
            );
            rs = stmt.executeQuery();

            while (rs.next()) {
                String cliente = "Nome: " + rs.getString("nome") +
                        " | CPF: " + rs.getString("cpf") +
                        " | ID: " + rs.getInt("id_cliente") +
                        " | Limite: R$" + rs.getFloat("limite_credito");
                lista.add(cliente);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            postgres.close(rs, stmt, conexao);
        }

        return lista;
    }

    public void atualizarLimiteCreditos(int idCliente, float novoLimite) {
        ConnectionPostgreSQL postgres = new ConnectionPostgreSQL();
        Connection conexao = null;
        PreparedStatement stmt = null;

        try {
            conexao = postgres.getConection();
            stmt = conexao.prepareStatement(
                "UPDATE Cliente SET limite_credito=? WHERE id_cliente=?"
            );
            stmt.setFloat(1, novoLimite);
            stmt.setInt(2, idCliente);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            postgres.close(stmt, conexao);
        }
    }

    public void deletarCliente(int idCliente) {
        ConnectionPostgreSQL postgres = new ConnectionPostgreSQL();
        Connection conexao = null;
        PreparedStatement stmt = null;

        try {
            conexao = postgres.getConection();
            stmt = conexao.prepareStatement(
                "DELETE FROM Cliente WHERE id_cliente=?"
            );
            stmt.setInt(1, idCliente);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            postgres.close(stmt, conexao);
        }
    }

    public Cliente buscarPorId(int idCliente) {
        ConnectionPostgreSQL postgres = new ConnectionPostgreSQL();
        Connection conexao = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conexao = postgres.getConection();
            stmt = conexao.prepareStatement(
                "SELECT p.nome, p.cpf, p.idade, p.endereco, c.id_cliente, c.limite_credito " +
                "FROM Cliente c JOIN Pessoa p ON c.cpf = p.cpf WHERE c.id_cliente = ?"
            );
            stmt.setInt(1, idCliente);
            rs = stmt.executeQuery();

            if (rs.next()) {
                return new Cliente(
                    rs.getInt("id_cliente"),
                    rs.getString("nome"),
                    rs.getString("cpf"),
                    rs.getInt("idade"),
                    rs.getString("endereco"),
                    rs.getFloat("limite_credito")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            postgres.close(rs, stmt, conexao);
        }

        return null;
    }
}