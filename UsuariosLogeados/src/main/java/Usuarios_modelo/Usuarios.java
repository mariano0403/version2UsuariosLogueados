package Usuarios_modelo;


public class Usuarios {
    private int legajo;
    private String nombre;
    private int contraseña;

    /**
     * @return the legajo
     */
    public int getLegajo() {
        return legajo;
    }

    /**
     * @param legajo the legajo to set
     */
    public void setLegajo(int legajo) {
        this.legajo = legajo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    
    
    /**
     * @return the contraseña
     */
    public int getContraseña() {
        return contraseña;
    }

    /**
     * @param contraseña the contraseña to set
     */
    public void setContraseña(int contraseña) {
        this.contraseña = contraseña;
    }

    public Usuarios(int legajo, String nombre, int contraseña) {
        this.legajo = legajo;
        this.nombre = nombre;
        this.contraseña = contraseña;
    }

    @Override
    public String toString() {
        return "Usuarios{" + "legajo=" + legajo + ", nombre=" + nombre + ", contraseña=" + contraseña + '}';
    }

    

    public Usuarios() {
    }
    
    
}
