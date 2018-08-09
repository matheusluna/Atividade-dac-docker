/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.daos;

import br.edu.ifpb.fabrica.ConnectionFactory;
import br.edu.ifpb.interfaces.DaoContatoInterface;
import br.ifpb.edu.entidades.Contato;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mathe
 */
public class DaoContato implements DaoContatoInterface{

    @Override
    public boolean create(Contato contato) {
        Contato c = read(contato.getEmail());
        if(c == null){
            boolean resultado = false;
            try {
                Connection connection = new ConnectionFactory().getConnection();
                String sql = "insert into contato values(?,?,?,?)";
                PreparedStatement stmt = connection.prepareStatement(sql);
                stmt.setString(1, contato.getNome());
                stmt.setString(2, contato.getEmail());
                stmt.setString(3, contato.getTelefone());
                stmt.setDate(4, Date.valueOf(contato.getDataNascimento()));
                resultado = !stmt.execute();
                stmt.close();
                connection.close();
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(DaoContato.class.getName()).log(Level.SEVERE, null, ex);
            }
            return resultado;
        }else{
            return false;
        }
    }

    @Override
    public Contato read(String email) {
        Contato contato = null;
        try {
            Connection connection = new ConnectionFactory().getConnection();
            String sql = "select * from contato where email = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                String nome = rs.getString("nome");
                String telefone = rs.getString("telefone");
                LocalDate dataNascimento = rs.getDate("datanascimento").toLocalDate();
                contato = new Contato(nome, email, telefone, dataNascimento);
            }
            stmt.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DaoContato.class.getName()).log(Level.SEVERE, null, ex);
        }
        return contato;
    }

    @Override
    public boolean update(Contato contato) {
        boolean resultado = false;
        try {
            Connection connection = new ConnectionFactory().getConnection();
            String sql = "update contato set nome = ?, telefone = ?, datanascimento = ? where email = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, contato.getNome());
            stmt.setString(2, contato.getEmail());
            stmt.setDate(3, Date.valueOf(contato.getDataNascimento()));
            stmt.setString(4, contato.getEmail());
            resultado = !stmt.execute();
            stmt.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DaoContato.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultado;
    }

    @Override
    public boolean delete(Contato contato) {
        boolean resultado = false;
        try {
            Connection connection = new ConnectionFactory().getConnection();
            String sql = "delete from contato where email = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, contato.getEmail());
            resultado = !stmt.execute();
            stmt.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DaoContato.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultado;
    }

    @Override
    public List<Contato> list() {
        List<Contato> lista = new ArrayList<>();
        try {
            Connection connection = new ConnectionFactory().getConnection();
            String sql = "select * from contato";
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                String nome = rs.getString("nome");
                String email = rs.getString("email");
                String telefone = rs.getString("telefone");
                LocalDate dataNascimento = rs.getDate("datanascimento").toLocalDate();
                Contato contato = new Contato(nome, email, telefone, dataNascimento);
                lista.add(contato);
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DaoContato.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    @Override
    public List<Contato> list(String nome) {
        List<Contato> lista = new ArrayList<>();
        try {
            Connection connection = new ConnectionFactory().getConnection();
            String sql = "select * from contato where nome ilike ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, "%"+nome+"%");
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                String nomeContato = rs.getString("nome");
                String email = rs.getString("email");
                String telefone = rs.getString("telefone");
                LocalDate dataNascimento = rs.getDate("datanascimento").toLocalDate();
                Contato contato = new Contato(nomeContato, email, telefone, dataNascimento);
                lista.add(contato);
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DaoContato.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    @Override
    public List<Contato> listInitial(String letra) {
        List<Contato> lista = new ArrayList<>();
        try {
            Connection connection = new ConnectionFactory().getConnection();
            String sql = "select * from contato where nome ilike ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, letra+"%");
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                String nomeContato = rs.getString("nome");
                String email = rs.getString("email");
                String telefone = rs.getString("telefone");
                LocalDate dataNascimento = rs.getDate("datanascimento").toLocalDate();
                Contato contato = new Contato(nomeContato, email, telefone, dataNascimento);
                lista.add(contato);
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DaoContato.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }
    
}
