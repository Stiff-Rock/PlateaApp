package Controlador;

import java.awt.Color;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.table.TableModel;

import Modelo.Modelo;
import Modelo.Usuario;
import Vistas.Menus;
import Vistas.Vista;
import Vistas._00_Login;
import Vistas._01_Registrar;
import Vistas._04_MiPerfil;
import Vistas._10_Publicar;

public class Controlador {
	private Modelo modelo;
	private Usuario user;
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
			case "Admin":
				mensaje = "Código de admin incorrecto";
				break;
			case "Faltan":
				mensaje = "Es necesario rellenar todos los campos";
				break;
			}
			System.out.println("Error en el registro 2");
			((_01_Registrar) vistas[1]).mostrarWarning2(mensaje);
		}
	}

	public void setDatosUsuario() {
		((_04_MiPerfil) vistas[4]).setNombre(user.getNombre());
		((_04_MiPerfil) vistas[4]).setApellido(user.getApellido());
		((_04_MiPerfil) vistas[4]).setCp(user.getCp());
		((_04_MiPerfil) vistas[4]).setNickname(user.getNickname());
//		((_04_MiPerfil) vistas[4]).setImage(user.getFoto());

		String tipo = "";
		if (user.getEsAdmin().equals("S")) {
			tipo = "Admin";
		} else {
			tipo = "Usuario";
		}
		((_04_MiPerfil) vistas[4]).setTipo(tipo);
	}

	public void actualizarDatosUsuario() {
		String datos[] = new String[4];

		datos[0] = ((_04_MiPerfil) vistas[4]).getNombre();
		datos[1] = ((_04_MiPerfil) vistas[4]).getApellido();
		datos[2] = ((_04_MiPerfil) vistas[4]).getCp();
//		datos[3] = ((_04_MiPerfil) vistas[4]).getImage();
		String resultado = modelo.actualizarDatosUsuario(datos);

		Color color;
		String mensaje = "";
		if (resultado.equals("Correcto")) {
			mensaje = "Los cambios se han aplicado";
			color = new Color(0, 128, 0);
			user.setNombre(datos[0]);
			user.setApellido(datos[1]);
			user.setCp(datos[2]);
//			user.getFoto(datos[3]);
			modelo.updateUsuario();
			setDatosUsuario();
		} else {
			color = new Color(255, 0, 0);
			if (resultado.equals("Nombre")) {
				mensaje = "El nombre solo puede contener letras";
			}

			if (resultado.equals("Apellido")) {
				mensaje = "El apellido solo puede contener letras";
			}

			if (resultado.equals("Cp")) {
				mensaje = "El codigo postal solo puede contener números";
			}
		}

		((_04_MiPerfil) vistas[4]).mostrarWarning(mensaje, color);
	}

	public void crearPublicacion() {
		String[] datosPublicacion = new String[6];
//		datosPublicacion[0] = ((_10_Publicar) vistas[10]).getFoto();
		datosPublicacion[1] = ((_10_Publicar) vistas[10]).getFecha();
		datosPublicacion[2] = ((_10_Publicar) vistas[10]).getCp();
		datosPublicacion[3] = ((_10_Publicar) vistas[10]).getCategoria();
		datosPublicacion[4] = ((_10_Publicar) vistas[10]).getDireccion();
		datosPublicacion[5] = ((_10_Publicar) vistas[10]).getDescripcion();
		
		String resultado = modelo.comprobarInfoPublicacion(datosPublicacion);
		String mensaje = "";
		if (resultado.equals("Correcto")) {
			// TODO que se cargue la publicación recien creada
			cambiarVentana(10, 9);
		} else {
			switch (resultado) {
			case "Fecha":
				mensaje = "La fecha no es válida o no coincide con el formato";
				break;
			case "Cp":
				mensaje = "El código postal solo puede tener números";
				break;
			case "Categoria":
				mensaje = "No se ha seleccionado una categoría";
				break;
			case "Descripcion":
				// TODO contador de caracteres
				mensaje = "La descripción debe ser de al menos 120 caracteres";
				break;
			case "Faltan":
				mensaje = "Todos los campos son obligatorios";
				break;
			}
			((_10_Publicar) vistas[10]).mostrarWarning(mensaje);
		}
	}

}
