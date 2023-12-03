/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.vVehiculo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Jhon
 */
public class fVehiculo {
    private Conexion mysql = new Conexion();
    private Connection cn = mysql.conectar();
    private String sSQL = "";
    public Integer totalRegistros;
    
    public DefaultTableModel mostrar (String buscar){
        DefaultTableModel modelo;
        String[] titulos = {"ID","Tipo","Placa","Marca","Modelo","Año","Color","Propietario","Estado"};
        String[] registro = new String[9];
        totalRegistros = 0;
        modelo = new DefaultTableModel(null, titulos);
        sSQL = "select * from vehiculo where placa like '%" + buscar + "%' order by placa";
        
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            
            while (rs.next()){
                registro[0] = rs.getString("idvehiculo");
                registro[1] = rs.getString("tipo");
                registro[2] = rs.getString("placa");
                registro[3] = rs.getString("marca");
                registro[4] = rs.getString("modelo");
                registro[5] = rs.getString("ano");
                registro[6] = rs.getString("color");
                registro[7] = rs.getString("propietario");
                registro[8] = rs.getString("estado");
                
                totalRegistros ++;
                modelo.addRow(registro);
            }
            
            return modelo;
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return null;
        }
    }
    
    public boolean insertar (vVehiculo dts){
        sSQL="insert into vehiculo (tipo,placa,marca,modelo,ano,color,propietario,estado)"+
                  " values (?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement pst = cn.prepareStatement(sSQL);
            pst.setString(1, dts.getTipo());
            pst.setString(2, dts.getPlaca());
            pst.setString(3, dts.getMarca());
            pst.setString(4, dts.getModelo());
            pst.setString(5, dts.getAno());
            pst.setString(6, dts.getColor());
            pst.setString(7, dts.getPropietario());
            pst.setString(8, dts.getEstado());
            
            int n = pst.executeUpdate();
            
            return n!=0;
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return false;
        }
    }
    public boolean editar (vVehiculo dts){
        sSQL="update vehiculo set tipo=?,placa=?,marca=?,modelo=?,ano=?,color=?,propietario=?,estado=?"+
                  " where idvehiculo=?";
        try {
            PreparedStatement pst = cn.prepareStatement(sSQL);
            pst.setString(1, dts.getTipo());
            pst.setString(2, dts.getPlaca());
            pst.setString(3, dts.getMarca());
            pst.setString(4, dts.getModelo());
            pst.setString(5, dts.getAno());
            pst.setString(6, dts.getColor());
            pst.setString(7, dts.getPropietario());
            pst.setString(8, dts.getEstado());
            pst.setInt(9, dts.getIdVehiculo());
            
            int n = pst.executeUpdate();
            
            return n!=0;
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return false;
        }
    }
    public boolean eliminar (vVehiculo dts){
        sSQL="delete from vehiculo where idvehiculo=?";
        try {
            PreparedStatement pst = cn.prepareStatement(sSQL);
            pst.setInt(1, dts.getIdVehiculo());
            
            int n = pst.executeUpdate();
            
            return n!=0;
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return false;
        }
    }
}
