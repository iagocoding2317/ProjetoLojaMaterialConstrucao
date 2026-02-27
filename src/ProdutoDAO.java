import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class ProdutoDAO {

    // ─── TINTA ────────────────────────────────────────────────────────────────

    public void inserirTinta(Tinta tinta) {
        ConnectionPostgreSQL postgres = new ConnectionPostgreSQL();
        Connection conexao = null;
        PreparedStatement stmt = null;

        try {
            conexao = postgres.getConection();
            conexao.setAutoCommit(false);

            stmt = conexao.prepareStatement(
                "INSERT INTO Produto(nome, preco) VALUES(?,?) RETURNING id_produto"
            );
            stmt.setString(1, tinta.getNome());
            stmt.setFloat(2, tinta.getPreco());
            ResultSet rs = stmt.executeQuery();
            rs.next();
            int idGerado = rs.getInt(1);
            stmt.close();

            stmt = conexao.prepareStatement(
                "INSERT INTO Tinta(id_produto, cor, tipo_acabamento, volume_litros) VALUES(?,?,?,?)"
            );
            stmt.setInt(1, idGerado);
            stmt.setString(2, tinta.getCor());
            stmt.setString(3, tinta.getAcabamento());
            stmt.setFloat(4, tinta.getVolume());
            stmt.executeUpdate();

            conexao.commit();

        } catch (SQLException e) {
            try { if (conexao != null) conexao.rollback(); } catch (SQLException ex) { ex.printStackTrace(); }
            e.printStackTrace();
        } finally {
            postgres.close(stmt, conexao);
        }
    }

    // ─── TIJOLO ───────────────────────────────────────────────────────────────

    public void inserirTijolo(Tijolo tijolo) {
        ConnectionPostgreSQL postgres = new ConnectionPostgreSQL();
        Connection conexao = null;
        PreparedStatement stmt = null;

        try {
            conexao = postgres.getConection();
            conexao.setAutoCommit(false);

            stmt = conexao.prepareStatement(
                "INSERT INTO Produto(nome, preco) VALUES(?,?) RETURNING id_produto"
            );
            stmt.setString(1, tijolo.getNome());
            stmt.setFloat(2, tijolo.getPreco());
            ResultSet rs = stmt.executeQuery();
            rs.next();
            int idGerado = rs.getInt(1);
            stmt.close();

            stmt = conexao.prepareStatement(
                "INSERT INTO Tijolo(id_produto, quantidade_estoque, preco_custo) VALUES(?,?,?)"
            );
            stmt.setInt(1, idGerado);
            stmt.setInt(2, tijolo.getQuantidade());
            stmt.setFloat(3, tijolo.getPrecoCusto());
            stmt.executeUpdate();

            conexao.commit();

        } catch (SQLException e) {
            try { if (conexao != null) conexao.rollback(); } catch (SQLException ex) { ex.printStackTrace(); }
            e.printStackTrace();
        } finally {
            postgres.close(stmt, conexao);
        }
    }

    // ─── CIMENTO ──────────────────────────────────────────────────────────────

    public void inserirCimento(Cimento cimento) {
        ConnectionPostgreSQL postgres = new ConnectionPostgreSQL();
        Connection conexao = null;
        PreparedStatement stmt = null;

        try {
            conexao = postgres.getConection();
            conexao.setAutoCommit(false);

            stmt = conexao.prepareStatement(
                "INSERT INTO Produto(nome, preco) VALUES(?,?) RETURNING id_produto"
            );
            stmt.setString(1, cimento.getNome());
            stmt.setFloat(2, cimento.getPreco());
            ResultSet rs = stmt.executeQuery();
            rs.next();
            int idGerado = rs.getInt(1);
            stmt.close();

            stmt = conexao.prepareStatement(
                "INSERT INTO Cimento(id_produto, peso_kg, quantidade_estoque, marca) VALUES(?,?,?,?)"
            );
            stmt.setInt(1, idGerado);
            stmt.setInt(2, cimento.getKg());
            stmt.setInt(3, cimento.getQuantidade());
            stmt.setString(4, cimento.getMarcas());
            stmt.executeUpdate();

            conexao.commit();

        } catch (SQLException e) {
            try { if (conexao != null) conexao.rollback(); } catch (SQLException ex) { ex.printStackTrace(); }
            e.printStackTrace();
        } finally {
            postgres.close(stmt, conexao);
        }
    }

    // ─── LISTAGENS ────────────────────────────────────────────────────────────

    public List<String> listarTintas() {
        List<String> lista = new LinkedList<>();
        ConnectionPostgreSQL postgres = new ConnectionPostgreSQL();
        Connection conexao = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conexao = postgres.getConection();
            stmt = conexao.prepareStatement(
                "SELECT p.id_produto, p.nome, p.preco, t.cor, t.tipo_acabamento, t.volume_litros " +
                "FROM Produto p JOIN Tinta t ON p.id_produto = t.id_produto"
            );
            rs = stmt.executeQuery();
            while (rs.next()) {
                lista.add("Tinta: " + rs.getString("nome") +
                        " | Cor: " + rs.getString("cor") +
                        " | Acabamento: " + rs.getString("tipo_acabamento") +
                        " | Volume: " + rs.getFloat("volume_litros") + "L" +
                        " | Preço: R$" + rs.getFloat("preco"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            postgres.close(rs, stmt, conexao);
        }
        return lista;
    }

    public List<String> listarTijolos() {
        List<String> lista = new LinkedList<>();
        ConnectionPostgreSQL postgres = new ConnectionPostgreSQL();
        Connection conexao = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conexao = postgres.getConection();
            stmt = conexao.prepareStatement(
                "SELECT p.id_produto, p.nome, p.preco, tj.quantidade_estoque, tj.preco_custo " +
                "FROM Produto p JOIN Tijolo tj ON p.id_produto = tj.id_produto"
            );
            rs = stmt.executeQuery();
            while (rs.next()) {
                lista.add("Tijolo: " + rs.getString("nome") +
                        " | Qtd: " + rs.getInt("quantidade_estoque") +
                        " | Preço Custo: R$" + rs.getFloat("preco_custo") +
                        " | Preço Venda: R$" + rs.getFloat("preco"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            postgres.close(rs, stmt, conexao);
        }
        return lista;
    }

    public List<String> listarCimentos() {
        List<String> lista = new LinkedList<>();
        ConnectionPostgreSQL postgres = new ConnectionPostgreSQL();
        Connection conexao = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conexao = postgres.getConection();
            stmt = conexao.prepareStatement(
                "SELECT p.id_produto, p.nome, p.preco, c.peso_kg, c.quantidade_estoque, c.marca " +
                "FROM Produto p JOIN Cimento c ON p.id_produto = c.id_produto"
            );
            rs = stmt.executeQuery();
            while (rs.next()) {
                lista.add("Cimento: " + rs.getString("nome") +
                        " | Marca: " + rs.getString("marca") +
                        " | Peso: " + rs.getInt("peso_kg") + "kg" +
                        " | Qtd: " + rs.getInt("quantidade_estoque") +
                        " | Preço: R$" + rs.getFloat("preco"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            postgres.close(rs, stmt, conexao);
        }
        return lista;
    }

    public void deletarProduto(int idProduto) {
        ConnectionPostgreSQL postgres = new ConnectionPostgreSQL();
        Connection conexao = null;
        PreparedStatement stmt = null;

        try {
            conexao = postgres.getConection();
            conexao.setAutoCommit(false);

            for (String tabela : new String[]{"Tinta", "Tijolo", "Cimento"}) {
                stmt = conexao.prepareStatement("DELETE FROM " + tabela + " WHERE id_produto=?");
                stmt.setInt(1, idProduto);
                stmt.executeUpdate();
                stmt.close();
            }

            stmt = conexao.prepareStatement("DELETE FROM Produto WHERE id_produto=?");
            stmt.setInt(1, idProduto);
            stmt.executeUpdate();

            conexao.commit();

        } catch (SQLException e) {
            try { if (conexao != null) conexao.rollback(); } catch (SQLException ex) { ex.printStackTrace(); }
            e.printStackTrace();
        } finally {
            postgres.close(stmt, conexao);
        }
    }

    public Produto buscarProdutoPorId(int idProduto) {
        ConnectionPostgreSQL postgres = new ConnectionPostgreSQL();
        Connection conexao = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conexao = postgres.getConection();

            stmt = conexao.prepareStatement(
                "SELECT p.id_produto, p.nome, p.preco, t.cor, t.tipo_acabamento, t.volume_litros " +
                "FROM Produto p JOIN Tinta t ON p.id_produto = t.id_produto WHERE p.id_produto = ?"
            );
            stmt.setInt(1, idProduto);
            rs = stmt.executeQuery();
            if (rs.next()) {
                return new Tinta(
                    rs.getInt("id_produto"),
                    rs.getString("nome"),
                    rs.getFloat("preco"),
                    rs.getString("cor"),
                    rs.getString("tipo_acabamento"),
                    rs.getFloat("volume_litros")
                );
            }
            rs.close(); stmt.close();

            stmt = conexao.prepareStatement(
                "SELECT p.id_produto, p.nome, p.preco, tj.quantidade_estoque, tj.preco_custo " +
                "FROM Produto p JOIN Tijolo tj ON p.id_produto = tj.id_produto WHERE p.id_produto = ?"
            );
            stmt.setInt(1, idProduto);
            rs = stmt.executeQuery();
            if (rs.next()) {
                return new Tijolo(
                    rs.getInt("id_produto"),
                    rs.getString("nome"),
                    rs.getFloat("preco"),
                    rs.getInt("quantidade_estoque"),
                    rs.getFloat("preco_custo")
                );
            }
            rs.close(); stmt.close();

            stmt = conexao.prepareStatement(
                "SELECT p.id_produto, p.nome, p.preco, c.peso_kg, c.quantidade_estoque, c.marca " +
                "FROM Produto p JOIN Cimento c ON p.id_produto = c.id_produto WHERE p.id_produto = ?"
            );
            stmt.setInt(1, idProduto);
            rs = stmt.executeQuery();
            if (rs.next()) {
                return new Cimento(
                    rs.getInt("id_produto"),
                    rs.getString("nome"),
                    rs.getFloat("preco"),
                    rs.getInt("peso_kg"),
                    rs.getInt("quantidade_estoque"),
                    rs.getString("marca")
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