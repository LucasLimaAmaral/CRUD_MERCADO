/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Lucas
 */

import java.sql.*;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class ProdutoDAO {
    

    public void create(Produtos p){
        Connection con = Conexao.getConexao();
        PreparedStatement stmt = null;
        
        try {
            stmt =  con.prepareStatement("INSERT INTO produto(nome, preco, qtd)VALUES(?, ?, ?)");
            
            stmt.setString(1, p.getNomeProduto());
            stmt.setInt(2, p.getQuantidadeProduto());
            stmt.setDouble(3, p.getValorProduto());
            
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Salvo com sucesso");
            
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro ao salvar");
        }finally{
            Conexao.fecharConexao(con, stmt);
        }
    }

    // Método para listar todos os produtos
    public List<Produtos> read() {
        Connection con = Conexao.getConexao();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        
        List<Produtos> produtos = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM produtos");
            rs = stmt.executeQuery();
            
            
            while(rs.next()){
                
                Produtos produto = new Produtos();
                
                produto.setQuantidadeProduto(rs.getInt("qnt"));
                produto.setNomeProduto(rs.getString("nome"));
                produto.setValorProduto(rs.getDouble("preco"));
                
                
                produtos.add(produto);
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            Conexao.fecharConexao(con, stmt, rs);
        }
        return produtos;
    }
    
    public void update(Produtos p){
        Connection con = Conexao.getConexao();
        PreparedStatement stmt = null;
        
        try {
            stmt =  con.prepareStatement("UPDATE produto SET nome = ?, preco = ?, qtd = ? WHERE id = ?");
            
            stmt.setString(1, p.getNomeProduto());
            stmt.setInt(2, p.getQuantidadeProduto());
            stmt.setDouble(3, p.getValorProduto());
            //stmt.setInt(4, p.getID);
            
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Atualizado com sucesso");
            
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro ao atualizar");
        }finally{
            Conexao.fecharConexao(con, stmt);
        }
    }
    
     public void delete(Produtos p){
        Connection con = Conexao.getConexao();
        PreparedStatement stmt = null;
        
        try {
            stmt =  con.prepareStatement("DELETE FROM produto WHERE id = ?");
            
   
            //stmt.setInt(4, p.getID);
            
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Excluido com sucesso");
            
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro ao excluir");
        }finally{
            Conexao.fecharConexao(con, stmt);
        }
    }

}
