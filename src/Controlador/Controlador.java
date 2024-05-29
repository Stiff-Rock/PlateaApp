package Controlador;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import Modelo.Modelo;
import Modelo.Usuario;
import Vistas.Menus;
import Vistas.Vista;
import Vistas._00_Login;
import Vistas._01_Registrar;
import Vistas._02_Reestablecer;

public class Controlador {
	private Modelo modelo;
	private Vista[] vistas;

	public void setVista(Vista[] vistas) {
		this.vistas = vistas;
	}

	public void setModelo(Modelo modelo) {
		this.modelo = modelo;
	}
	
	public String getCaptcha() {
		return modelo.generateCaptcha();
	}
	
	public TableModel getTabla(String condicion) {
		return modelo.getTabla(condicion);
	}

	public DefaultComboBoxModel getPreguntas() {
		return modelo.obtenerPreguntasSeguridad();
	}

	public Usuario getUser() {
		return modelo.getUser();
	}
	
	public void cambiarVentana(int desde, int hasta) {
		((JFrame) vistas[desde]).setVisible(false);
		((JFrame) vistas[hasta]).setVisible(true);
		System.out.println("Movido a: " + hasta);
		
		//TODO revisar si esto se puede cambiar
		if (vistas[hasta] instanceof Menus) {
			((Menus) vistas[hasta]).setIndiceActual(hasta);
		}
	}

	public void login() {
		String usr = ((_00_Login) vistas[0]).getUsr();
		String pwd = ((_00_Login) vistas[0]).getPwd();
		String resultado = modelo.login(usr, pwd);
		switch (resultado) {
		case "Correcto":
			cambiarVentana(0, 3);
			break;
		case "Incorrecto":
			((_00_Login) vistas[0]).mostrarWarning();
			break;
		case "Cerrar":
			System.exit(0);
			break;
		}
	}

	public void singIn() {
		String datosRegistro[] = ((_01_Registrar) vistas[1]).getDatosRegistro();
		String resultado = modelo.singIn(datosRegistro);
		String mensaje = "Ha ocurrido un error inesperado.";

		if (resultado.equals("Correcto")) {
			cambiarVentana(1, 0);
		} else {
			int pantalla = 0;
			switch (resultado) {
			case "Contraseña":
				mensaje = "Las contraseñas no coinciden";
				pantalla = 1;
				break;
			case "Politica":
				mensaje = "No has aceptado la política de privacidad";
				pantalla = 2;
				break;
			case "Mayor":
				mensaje = "Menores de 14 años no están permitidos";
				pantalla = 2;
				break;
			case "Captcha":
				mensaje = "Captcha incorrecto";
				pantalla = 2;
				break;
			case "Nickname":
				mensaje = "Nickname no disponible";
				pantalla = 1;
				break;
			case "Pregunta":
				mensaje = "No has escogido una pregunta";
				pantalla = 2;
				break;
			case "Respuesta":
				mensaje = "No has respondido a la pregunta";
				pantalla = 2;
				break;
			case "Faltan":
				mensaje = "Es necesario rellenar todos los campos";
				break;
			}

			if (pantalla == 1) {
				((_01_Registrar) vistas[1]).mostrarWarning1(mensaje);
			} else if (pantalla == 2){
				((_01_Registrar) vistas[1]).mostrarWarning2(mensaje);
			} else {
				((_01_Registrar) vistas[1]).mostrarWarning1(mensaje);
				((_01_Registrar) vistas[1]).mostrarWarning2(mensaje);
			}
		}

	}

}
