package Controlador;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;

import Modelo.Modelo;
import Modelo.Usuario;
import Vistas.Menus;
import Vistas.Vista;
import Vistas._00_Login;
import Vistas._01_Registrar;
import Vistas._02_Reestablecer;

public class Controlador {
	private Usuario user;
	private Modelo modelo;
	private Vista[] vistas;

	public void setVista(Vista[] vistas) {
		this.vistas = vistas;
	}

	public void setModelo(Modelo modelo) {
		this.modelo = modelo;
	}

	public void setUsuario(Usuario user) {
		this.user = user;
	}

	public void cambiarVentana(int desde, int hasta) {
		((JFrame) vistas[desde]).setVisible(false);
		((JFrame) vistas[hasta]).setVisible(true);
		if (vistas[hasta] instanceof Menus) {
			((Menus) vistas[hasta]).setIndiceActual(hasta);
		}
	}

	public void cargarPreguntasRegistrar() {
		DefaultComboBoxModel preguntas = new DefaultComboBoxModel(modelo.obtenerPreguntasSeguridad());
		((_01_Registrar) vistas[1]).cargarPreguntas(preguntas);
		((_02_Reestablecer) vistas[2]).cargarPreguntas(preguntas);
	}

	public void login() {
		String usr = ((_00_Login) vistas[0]).getUsr();
		String pwd = ((_00_Login) vistas[0]).getPwd();
		String resultado = modelo.login(usr, pwd);
		user.mostrarUser();
		switch (resultado) {
		case "Correcto":
			for (int i = 3; i < vistas.length; i++) {
				((Menus) vistas[i]).cargarUsuario();
			}
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
			switch (resultado) {
			case "Contraseña":
				mensaje = "Las contraseñas no coinciden";
				break;
			case "Politica":
				mensaje = "No has aceptado la política de privacidad";
				break;
			case "Mayor":
				mensaje = "Menores de 14 años no están permitidos";
				break;
			case "Captcha":
				mensaje = "Captcha incorrecto";
				break;
			case "Nickname":
				mensaje = "Nickname no disponible";
				break;
			case "Pregunta":
				mensaje = "No has escogido una pregunta";
				break;
			case "Respuesta":
				mensaje = "No has respondido a la pregunta";
				break;
			case "Faltan":
				mensaje = "Es necesario rellenar todos los campos";
				break;
			}

			((_01_Registrar) vistas[1]).mostrarWarning(mensaje);
		}

	}

}
