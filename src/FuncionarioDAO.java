import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class FuncionarioDAO {

    public void inserirFuncionarioCompleto(Funcionario funcionario) {
        ConnectionPostgreSQL postgres = new ConnectionPostgreSQL();
        Connection conexao = null;
        PreparedStatement stmt = null;

        try {
            conexao = postgres.getConection();
            conexao.setAutoCommit(false);

            stmt = conexao.prepareStatement(
                "INSERT INTO Pessoa(cpf, nome, idade, endereco) VALUES(?,?,?,?)"
            );
            stmt.setString(1, funcionario.getCPF());
            stmt.setString(2, funcionario.getNome());
            stmt.setInt(3, funcionario.getIdade());
            stmt.setString(4, funcionario.getEndereco());
            stmt.executeUpdate();
            stmt.close();

            stmt = conexao.prepareStatement(
                "INSERT INTO Funcionario(cpf, indice_fidelidade, salario) VALUES(?,?,?)"
            );
            stmt.setString(1, funcionario.getCPF());
            stmt.setInt(2, funcionario.getFidelidade());
            stmt.setDouble(3, funcionario.getSalario());
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

    public List<String> listarFuncionarios() {
        List<String> lista = new LinkedList<>();
        ConnectionPostgreSQL postgres = new ConnectionPostgreSQL();
        Connection conexao = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conexao = postgres.getConection();
            stmt = conexao.prepareStatement(
                "SELECT p.nome, p.cpf, f.id_funcionario, f.indice_fidelidade, f.salario " +
                "FROM Funcionario f JOIN Pessoa p ON f.cpf = p.cpf"
            );
            rs = stmt.executeQuery();

            while (rs.next()) {
                String funcionario = "Nome: " + rs.getString("nome") +
                        " | CPF: " + rs.getString("cpf") +
                        " | ID: " + rs.getInt("id_funcionario") +
                        " | Salário: R$" + rs.getDouble("salario") +
                        " | Fidelidade: " + rs.getInt("indice_fidelidade");
                lista.add(funcionario);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            postgres.close(rs, stmt, conexao);
        }

        return lista;
    }

    public void atualizarSalario(int idFuncionario, double novoSalario) {
        ConnectionPostgreSQL postgres = new ConnectionPostgreSQL();
        Connection conexao = null;
        PreparedStatement stmt = null;

        try {
            conexao = postgres.getConection();
            stmt = conexao.prepareStatement(
                "UPDATE Funcionario SET salario=? WHERE id_funcionario=?"
            );
            stmt.setDouble(1, novoSalario);
            stmt.setInt(2, idFuncionario);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            postgres.close(stmt, conexao);
        }
    }

    public void deletarFuncionario(int idFuncionario) {
        ConnectionPostgreSQL postgres = new ConnectionPostgreSQL();
        Connection conexao = null;
        PreparedStatement stmt = null;

        try {
            conexao = postgres.getConection();
            stmt = conexao.prepareStatement(
                "DELETE FROM Funcionario WHERE id_funcionario=?"
            );
            stmt.setInt(1, idFuncionario);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            postgres.close(stmt, conexao);
        }
    }
}