package Usuarios_controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static java.lang.Integer.parseInt;
import java.util.List;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.showMessageDialog;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import Usuarios_modelo.Usuarios;
import Usuarios_modelo.UsuariosDAO;

import Vista_usuarios.vistaLogin;
import java.sql.SQLException;


public class Controlar_usuarios implements ActionListener {
    
    Usuarios us = new Usuarios();
    UsuariosDAO usdao = new UsuariosDAO();
    vistaLogin vl = new vistaLogin();
    
    DefaultTableModel modelo = new DefaultTableModel();
    
    public Controlar_usuarios(vistaLogin vll)
    {
        this.vl = vll;
        this.vl.btnIngresar.addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        try{
            boolean muestro;
            if (ae.getSource() == vl.btnIngresar)
            {

                verificarLogin();
            }
        }
        catch(Exception e)
        {
            showMessageDialog(null,"no ingreso nada, intente nuevamente");
        }
    }
    public void verificarLogin()
    {
           
        try {
            String nombre = vl.txtNombre.getText();
            int contraseña = parseInt(vl.textoContraseña.getText());
            
            boolean r = usdao.login(nombre, contraseña);
            if( r== true)
            {
               showMessageDialog(null,"usuario correcto");
               
            }
            else
            {
                showMessageDialog(null,"usuario o contraseña incorrecta, intente nuevamente");
            }
        } catch (SQLException ex) {
            //showMessageDialog(null,"no ingreso nada, intente nuevamente");
        }
        
    }
    
}

    
    
