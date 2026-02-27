import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class FornecedoraDAO {

    public void inserirFornecedora(Fornecedora fornecedora) {
        ConnectionPostgreSQL postgres = new ConnectionPostgreSQL();
        Connection conexao = null;
        PreparedStatement stmt = null;

        try {
            conexao = postgres.getConection();
            stmt = conexao.prepareStatement(
                "INSERT INTO Fornecedora(cnpj, nome) VALUES(?,?)"
            );
            stmt.setString(1, fornecedora.getCNPJ());
            stmt.setString(2, fornecedora.getNome());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            postgres.close(stmt, conexao);
        }
    }

    public List<String> listarFornecedoras() {
        List<String> lista = new LinkedList<>();
        ConnectionPostgreSQL postgres = new ConnectionPostgreSQL();
        Connection conexao = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conexao = postgres.getConection();
            stmt = conexao.prepareStatement("SELECT * FROM Fornecedora");
            rs = stmt.executeQuery();

            while (rs.next()) {
                lista.add("Nome: " + rs.getString("nome") +
                        " | CNPJ: " + rs.getString("cnpj"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            postgres.close(rs, stmt, conexao);
        }

        return lista;
    }

    public void deletarFornecedora(String cnpj) {
        ConnectionPostgreSQL postgres = new ConnectionPostgreSQL();
        Connection conexao = null;
        PreparedStatement stmt = null;

        try {
            conexao = postgres.getConection();
            stmt = conexao.prepareStatement("DELETE FROM Fornecedora WHERE cnpj=?");
            stmt.setString(1, cnpj);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            postgres.close(stmt, conexao);
        }
    }
}

