/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.fabrica;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author mathe
 */
public class ConnectionFactory {
    
    private String driver = "org.postgresql.Driver";
    private String database = "jdbc:postgresql://localhost:5432/atividade-dac-docker";
    private String user = "postgres";
    private String password = "123456";
    
    public Connection getConnection() throws ClassNotFoundException, SQLException{
        Class.forName(driver);
        return DriverManager.getConnection(database, user, password);
    }
}
