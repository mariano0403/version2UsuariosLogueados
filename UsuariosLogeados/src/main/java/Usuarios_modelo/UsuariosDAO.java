package Usuarios_modelo;

import static java.lang.System.in;
import static java.lang.System.out;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.showMessageDialog;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class UsuariosDAO {
    Conexion conectando = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
    public List listar()
    {
        List<Usuarios>datos = new ArrayList<>();
        var sql = "select * from usuarios";
        try{
            con = conectando.conector();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next())
            {
                var us = new Usuarios();
                us.setLegajo(rs.getInt("legajo"));
                us.setNombre(rs.getString("nombre"));
                us.setContraseña(rs.getInt("contraseña"));
              
                
                datos.add(us);
                
            }
            out.println();
            
        }catch(Exception e)
        {
            System.out.println("no hay nada que consultar");
        }
        
        return datos;
        
    }
    
    
    
    
    public int agregar(Usuarios us){
        var sql = "INSERT INTO usuarios(nombre, contraseña) VALUES (?, ?)";
        try{
            con = conectando.conector();
            ps = con.prepareStatement(sql);
            ps.setString(1, us.getNombre());
            ps.setInt(2, us.getContraseña());
            ps.executeUpdate();
                    
        }catch(SQLException e)
        {
            showMessageDialog(null, "no se cargo nada");
        }
       
        return 1;
        
    }
    
    public int actualizar(Usuarios us)
    {
        var sql = "update usuarios set nombre=?, contraseña=? where legajo=?";
        try{
            con = conectando.conector();
            ps = con.prepareStatement(sql);
            ps.setString(1, us.getNombre());
            ps.setInt(2, us.getContraseña());
            ps.setInt(3, us.getLegajo());
            
            ps.executeUpdate();
                    
        }catch(SQLException e)
        {
            showMessageDialog(null, "no se actualizo nada");
        }
       
        return 1;
    }
    
    public void eliminarFila(int legajo)
    {
        var sql = "delete from usuarios where legajo="+legajo;
        try{
            con = conectando.conector();
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
            
        }catch(Exception e){
            
        }
        
    }
    
    public List buscarUsuario(int legajo)
    {
        
        List<Usuarios>datos = new ArrayList<>();
        var sql = "select * from usuarios where legajo='"+legajo+"'";
        try{
            con = conectando.conector();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while ( rs.next())
            {
                var p = new Usuarios();
                //p.setLegajo(rs.getInt("legajo"));
                p.setNombre(rs.getString("nombre"));
                p.setContraseña(rs.getInt("contraseña"));
                
                datos.add(p);
                
            }
            out.println();
            
        }catch(Exception e){}
        
        return datos;
    }
    public boolean login(String nombre ,int contraseña)throws SQLException {
     
        Conexion conexion = new Conexion();
        conexion.conector();
        var sql =  "SELECT * FROM usuarios WHERE nombre = '" + nombre + "' and contraseña = '" + contraseña + "'"; 
        con = conectando.conector();
        ps = con.prepareStatement(sql);
        rs = ps.executeQuery();
        
        if (rs.next()){
             //JOptionPane.showMessageDialog(null,"logeado correctamente");
             return true;
        }
        else
        {
             //JOptionPane.showMessageDialog(null,"logeado incorrectamente");
             return false;
        }
    
      
    }
    public static void main(String[]args) throws SQLException
    {
        var op = 0;
        String menu;
        var producto = new UsuariosDAO();
        Usuarios p = new Usuarios(); 
        Scanner entrada = new Scanner(in);
        Conexion c = new Conexion();
         
        do
        {
            out.println("*******menu*******");
            out.println("1- agregar usuario");
            out.println("2- mostrar usuario");
            out.println("3- buscar usuario");
            out.println("4- actualizar contraseña");
            out.println("5- eliminar usuario");
            out.println("6- verificar usuario");
            
            out.println("-1- salir");
            out.println("******************");
            System.out.print("elija opcion: ");
            op = entrada.nextInt();
            switch(op)
            {
            case 1:
                //int id = entrada.nextInt();
                System.out.println("ingrese legajo");
                var legajo = entrada.nextInt();
                System.out.println("ingrese nombre");
                var nombre = entrada.next();
                System.out.println("ingrese contraseña");
                var contraseña = entrada.nextInt();
                p = new Usuarios(legajo, nombre,contraseña);
                producto.agregar(p);
                break;
            case 2:
                var muestreo = producto.listar();
                out.println(muestreo);
                
                break;
            case 3:
                System.out.println("ingrese legajo");
                int l = entrada.nextInt();
                System.out.println(producto.buscarUsuario(l));
                
                //producto.listar();
                break;
                
            case 4:
                System.out.println("ingrese legajo");
                var q = entrada.nextInt();
                System.out.println("ingrese contraseña");
                var n = entrada.next();
                System.out.println("ingrese contraseña");
                var r = entrada.nextInt();
                p = new Usuarios(q, n, r);
                
                producto.actualizar(p);
                break;
            case 5:
                System.out.println("ingrese legajo");
                int lega = entrada.nextInt();
                producto.eliminarFila(lega);
            case 6:
                System.out.println("ingrese nombre");
                var s = entrada.next();
                System.out.println("ingrese contraseña");
                var t = entrada.nextInt();
                producto.login(s, t);
            
            case 7:
                //c.cerrarConexion();
            }
            
            
            }while(op!=-1);
                
    }
}
