package Controlador;

import javax.swing.JFrame;

import Modelo.Modelo;
import Vistas.Vista;
import Vistas._00_Login;
import Vistas._01_Registrar;


public class Controlador {
    private Modelo modelo;
    private Vista[] vistas;

    /**
     * @param vistas el array de vistas a establecer
     */
    public void setVista(Vista[] vistas) {
        this.vistas = vistas;
    }

    /**
     * @param modelo el modelo a establecer
     */
    public void setModelo(Modelo modelo) {
        this.modelo = modelo;
    }

    /**
     * Cambia entre diferentes vistas.
     * @param desde el índice de la vista desde la que cambiar
     * @param hasta el índice de la vista a la que cambiar
     */
    public void cambiarVentana(int desde, int hasta) {
        ((JFrame) vistas[desde]).setVisible(false);
        ((JFrame) vistas[hasta]).setVisible(true);
    }
    
    /**
     * Maneja el proceso de inicio de sesión.
     * Obtiene el nombre de usuario y la contraseña desde la vista de inicio de sesión 
     * y llama al método de inicio de sesión del modelo.
     */
    public void login() {
        String usr = ((_00_Login) vistas[0]).getUsr();
        String pwd = ((_00_Login) vistas[0]).getPwd();
        modelo.login(usr, pwd);
    }
    
    /**
     * Maneja el proceso de registro.
     */
    public void singIn() {
        String datosRegistro[] = ((_01_Registrar) vistas[1]).getDatosRegistro();
        modelo.singIn(datosRegistro);
    }
}
