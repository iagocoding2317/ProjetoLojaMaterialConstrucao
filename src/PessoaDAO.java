import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class PessoaDAO{

    public void inserirPessoa(String cpf, String nome, int idade, String endereco) {
        ConnectionPostgreSQL postgres = new ConnectionPostgreSQL();
        Connection conexao = null;
        PreparedStatement stmt = null;

        try {
            conexao = postgres.getConection();
            stmt = conexao.prepareStatement(
                "INSERT INTO Pessoa(CPF, nome, idade, endereco) VALUES(?,?,?,?)"
            );
            stmt.setString(1, cpf);
            stmt.setString(2, nome);
            stmt.setInt(3, idade);
            stmt.setString(4, endereco);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            postgres.close(stmt, conexao);
        }
    }

    public List<String> listarPessoas() {
        List<String> lista = new LinkedList<>();
        ConnectionPostgreSQL postgres = new ConnectionPostgreSQL();
        Connection conexao = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conexao = postgres.getConection();
            stmt = conexao.prepareStatement("SELECT * FROM Pessoa");
            rs = stmt.executeQuery();

            while (rs.next()) {
                String pessoa = "Nome: " + rs.getString("nome") +
                        " | CPF: " + rs.getString("CPF") +
                        " | Idade: " + rs.getInt("idade") +
                        " | Endereço: " + rs.getString("endereco");
                lista.add(pessoa);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            postgres.close(rs, stmt, conexao);
        }

        return lista;
    }

    public void deletarPessoa(String cpf) {
        ConnectionPostgreSQL postgres = new ConnectionPostgreSQL();
        Connection conexao = null;
        PreparedStatement stmt = null;

        try {
            conexao = postgres.getConection();
            stmt = conexao.prepareStatement("DELETE FROM Pessoa WHERE CPF=?");
            stmt.setString(1, cpf);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            postgres.close(stmt, conexao);
        }
    }
}

