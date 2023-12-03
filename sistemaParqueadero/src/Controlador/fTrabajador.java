/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.vTrabajador;
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
public class fTrabajador {

    private Conexion mysql = new Conexion();
    private Connection cn = mysql.conectar();
    private String sSQL = "";
    private String sSQL2 = "";
    public Integer totalRegistros;

    public DefaultTableModel mostrar(String buscar) {
        DefaultTableModel modelo;
        String[] titulos = {"ID", "Nombre", "1er Apellido", "2do Apellido", "Tipo ID", "Numero ID",
            "Dirección", "Teléfono", "Celular", "Email", "Sueldo", "Acceso", "Login", "Clave", "Estado"};
        String[] registro = new String[16];
        totalRegistros = 0;
        modelo = new DefaultTableModel(null, titulos);
        sSQL = "select p.idpersona,p.nombre,p.apellidop,p.apellidom,p.tipo_id,p.num_id,p.direccion,p.telefono,p.celular,p.email"
                + "t.sueldo,t.acceso,t.login,t.password,t.estado from persona p inner join trabajador t "
                + "on p.idpersona=t.trabajador where num_id like '%" + buscar + "%' order by num_id desc";

        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);

            while (rs.next()) {
                registro[0] = rs.getString("idpersona");
                registro[1] = rs.getString("nombre");
                registro[2] = rs.getString("apellidop");
                registro[3] = rs.getString("apellidom");
                registro[4] = rs.getString("tipo_id");
                registro[5] = rs.getString("num_id");
                registro[6] = rs.getString("direccion");
                registro[7] = rs.getString("telefono");
                registro[8] = rs.getString("celular");
                registro[9] = rs.getString("email");
                registro[10] = rs.getString("sueldo");
                registro[11] = rs.getString("acceso");
                registro[12] = rs.getString("login");
                registro[13] = rs.getString("password");
                registro[14] = rs.getString("estado");

                totalRegistros++;
                modelo.addRow(registro);
            }

            return modelo;
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return null;
        }
    }

    public boolean insertar(vTrabajador dts) {
        sSQL = "insert into persona (nombre,apellidop,apellidom,tipo_id,num_id,direccion,telefono,celular,email)"
                + " values (?,?,?,?,?,?,?,?,?)";
        sSQL2 = "insert into trabajador (idpersona,sueldo,acceso,login,password,estado,contrato)"
                + " values ((select idpersona from persona order by idpersona desc limit 1),?,?,?,?,?,?)";
        try {
            PreparedStatement pst = cn.prepareStatement(sSQL);
            PreparedStatement pst2 = cn.prepareStatement(sSQL2);
            pst.setString(1, dts.getNombre());
            pst.setString(2, dts.getApellidop());
            pst.setString(3, dts.getApellidom());
            pst.setString(4, dts.getTipoID());
            pst.setString(5, dts.getNumeroID());
            pst.setString(6, dts.getDireccion());
            pst.setInt(7, dts.getTelefono());
            pst.setInt(8, dts.getCelular());
            pst.setString(9, dts.getEmail());

            pst2.setDouble(1, dts.getSueldo());
            pst2.setString(2, dts.getAcceso());
            pst2.setString(3, dts.getLogin());
            pst2.setString(4, dts.getPassword());
            pst2.setString(5, dts.getEstado());
            pst2.setString(6, dts.getContrato());

            int n = pst.executeUpdate();

            if (n != 0) {
                int n2 = pst2.executeUpdate();

                if (n2 != 0) 
                    return true;
                else 
                    return false;
                
            }else
                return false;

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return false;
        }
    }

    public boolean editar(vTrabajador dts) {
        sSQL = "update persona set nombre=?,apellidop=?,apellidom=?,tipo_id=?,num_id=?,direccion=?,telefono=?,celular=?,email=?"
                + " where idpersona=?";
        sSQL2 = "update trabajador set sueldo=?,acceso=?,login=?,password=?,estado=?,contrato=?"
                + " where idpersona=?";
        try {
            PreparedStatement pst = cn.prepareStatement(sSQL);
            PreparedStatement pst2 = cn.prepareStatement(sSQL2);
            pst.setString(1, dts.getNombre());
            pst.setString(2, dts.getApellidop());
            pst.setString(3, dts.getApellidom());
            pst.setString(4, dts.getTipoID());
            pst.setString(5, dts.getNumeroID());
            pst.setString(6, dts.getDireccion());
            pst.setInt(7, dts.getTelefono());
            pst.setInt(8, dts.getCelular());
            pst.setString(9, dts.getEmail());
            pst.setInt(10, dts.getIdPersona());

            pst2.setDouble(1, dts.getSueldo());
            pst2.setString(2, dts.getAcceso());
            pst2.setString(3, dts.getLogin());
            pst2.setString(4, dts.getPassword());
            pst2.setString(5, dts.getEstado());
            pst2.setString(6, dts.getContrato());
            pst.setInt(7, dts.getIdPersona());

            int n = pst.executeUpdate();

            if (n != 0) {
                int n2 = pst2.executeUpdate();

                if (n2 != 0) 
                    return true;
                else 
                    return false;
                
            }else
                return false;

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return false;
        }
    }

    public boolean eliminar(vTrabajador dts) {
        sSQL = "delete from trabajador where idpersona=?";
        sSQL2 = "delete from persona where idpersona=?";
        try {
            PreparedStatement pst = cn.prepareStatement(sSQL);
            PreparedStatement pst2 = cn.prepareStatement(sSQL2);
            
            
            pst.setInt(1, dts.getIdPersona());

            pst.setInt(1, dts.getIdPersona());

            int n = pst.executeUpdate();

            if (n != 0) {
                int n2 = pst2.executeUpdate();

                if (n2 != 0) 
                    return true;
                else 
                    return false;
                
            }else
                return false;

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return false;
        }
    }

}
