/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pacoteDAO;

import connection.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import models.Cliente;

/**
 *
 * @author guilh
 */
public class ClienteDAO {
    public static void create(Cliente cli){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try{
            java.sql.Date sqlDate = new java.sql.Date(cli.getNascimento().getTime());
            stmt = con.prepareStatement("INSERT INTO cliente (codigo,nome,telefone,nascimento) VALUES (?,?,?,?)");
            stmt.setInt(1,cli.getCodigo());
            stmt.setString(2,cli.getNome());
            stmt.setString(3,cli.getTelefone());
            stmt.setDate(4,sqlDate);            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null,"Cliente salvo com sucesso!");
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null,"erro ao salvar: "+ex);
        }finally{
            ConnectionFactory.closeConnection(con,stmt,null);
        }
    }

    public static void update(Cliente cli){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
    
        try{
            java.sql.Date sqlDate = new java.sql.Date(cli.getNascimento().getTime());
            stmt = con.prepareStatement("UPDATE cliente SET  nome = ?, telefone = ?, nascimento = ? WHERE codigo = ?");
            
            stmt.setString(1,cli.getNome());
            stmt.setString(2,cli.getTelefone());
            stmt.setDate(3,sqlDate);            
            stmt.setInt(4,cli.getCodigo());
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null,"Cliente atualizado com sucesso!");
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null,"erro ao atualizaer: "+ex);
        }finally{
            ConnectionFactory.closeConnection(con,stmt,null);
        }
    
    } 
    public static void delete (int codigo){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try{
            stmt = con.prepareStatement("DELETE FROM cliente WHERE codigo = ?");
            stmt.setInt(1,codigo);
          
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null,"Cliente excluido com sucesso!");
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null,"erro ao excluir: "+ex);
        }finally{
            ConnectionFactory.closeConnection(con,stmt,null);
        }
    }
    public static List<Cliente> read(int codigo){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Cliente> cliList = new ArrayList<Cliente>();
        
        try{
            stmt = con.prepareStatement("SELECT * FROM cliente WHERE codigo = ?");
            stmt.setInt(1,codigo);
            rs = stmt.executeQuery();
            
            while (rs.next()){
                Cliente cli = new Cliente();
                cli.setCodigo(rs.getInt("codigo"));
                cli.setNome(rs.getString("nome"));
                cli.setTelefone(rs.getString("telefone"));
                cli.setNascimento(rs.getDate("nascimento"));
                cliList.add(cli);
            
            }    
             JOptionPane.showMessageDialog(null,"consulta realizada!");   
            }catch(SQLException ex){
            JOptionPane.showMessageDialog(null,"erro ao excluir: "+ex);
        }finally{
            ConnectionFactory.closeConnection(con,stmt,rs);
        }
        return cliList;
                
    }

}







