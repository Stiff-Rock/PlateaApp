package Controlador;

import javax.swing.JFrame;

import Modelo.Modelo;
import Vistas.Vista;
import Vistas._00_Login;
import Vistas._01_Registrar;

public class Controlador {
	private Modelo modelo;
	private Vista[] vistas;

	public void setVista(Vista[] vistas) {
		this.vistas = vistas;
	}

	public void setModelo(Modelo modelo) {
		this.modelo = modelo;
	}

	public void cambiarVentana(int desde, int hasta) {
		((JFrame) vistas[desde]).setVisible(false);
		((JFrame) vistas[hasta]).setVisible(true);
	}
	
	public void login() {
		String usr = ((_00_Login) vistas[0]).getUsr();
		String pwd = ((_00_Login) vistas[0]).getPwd();
		modelo.login(usr, pwd);
	}
	
	public void singIn() {
		String datosRegistro[] = ((_01_Registrar) vistas[1]).getDatosRegistro();
		modelo.singIn(datosRegistro);
	}

}
