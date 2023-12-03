/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Jhon
 */
public class Conexion {
    public String db = "baseparqueadero";
    public String url = "jdbc:mariadb://127.0.0.1/"+db;
    public String user = "root";
    public String pass = "";

    public Conexion() {
    }
    public Connection conectar(){
        Connection link = null;
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            link = DriverManager.getConnection(this.url, this.user, this.pass);
            
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showConfirmDialog(null, e);
        }
        return link;
    }
}
