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

	public TableModel getTabla(int pagina) {
		return modelo.getTabla(pagina);
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

		// TODO revisar si esto se puede cambiar
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

	public void siguienteSignIn() {
		String resultado = modelo.siguienteSingIn(((_01_Registrar) vistas[1]).getDatosRegistro());
		String mensaje = "";
		if (resultado.equals("Avanza")) {
			System.out.println("Registro avanzado");
			((_01_Registrar) vistas[1]).getRegisterPanel().setVisible(false);
			((_01_Registrar) vistas[1]).getRegisterPanel2().setVisible(true);
		} else {
			switch (resultado) {
			case "Nickname":
				mensaje = "Nickname no disponible";
				break;
			case "Contraseña":
				mensaje = "Las contraseñas no coinciden";
				break;
			case "Faltan":
				mensaje = "Es necesario rellenar todos los campos";
				break;
			}
			System.out.println("Error en el registro 1");
			((_01_Registrar) vistas[1]).mostrarWarning1(mensaje);
		}
	}

	public void singIn() {
		String resultado = modelo.singIn(((_01_Registrar) vistas[1]).getDatosRegistro());
		String mensaje = "Ha ocurrido un error inesperado.";

		System.out.println(mensaje);

		if (resultado.equals("Correcto")) {
			cambiarVentana(1, 0);
		} else {
			switch (resultado) {
			case "Politica":
				mensaje = "No has aceptado la política de privacidad";
				break;
			case "Mayor":
				mensaje = "Menores de 14 años no están permitidos";
				break;
			case "Captcha":
				mensaje = "Captcha incorrecto";
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
			System.out.println("Error en el registro 2");
			((_01_Registrar) vistas[1]).mostrarWarning2(mensaje);
		}
	}
}
