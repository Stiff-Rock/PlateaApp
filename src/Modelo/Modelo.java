package Modelo;

import java.util.Random;

import Vistas.Vista;
import Vistas._01_Registrar;

public class Modelo {
	private Usuario user;
	private Vista[] vistas;
	private String resultado;
	private int fallos;
	private ConexionOracle conexion = new ConexionOracle();

	public void setVistas(Vista[] vistas) {
		this.vistas = vistas;
	}

	public void setUsuario(Usuario user) {
		this.user = user;
	}

	public String getResultado() {
		return resultado;
	}

	public String generateCaptcha() {
		String letras = "abcdefghijklmnopqrstuvwxyz";
		String numeros = "1234567890";
		StringBuilder captcha = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < 6; i++) {
			int option = random.nextInt(4);
			if (option == 0) {
				captcha.append(numeros.charAt(random.nextInt(numeros.length())));
			} else {
				int mayus = random.nextInt(2);
				if (mayus == 0) {
					captcha.append(letras.charAt(random.nextInt(letras.length())));
				} else {
					captcha.append(String.valueOf(letras.charAt(random.nextInt(letras.length()))).toUpperCase());
				}
			}
		}
		return String.valueOf(captcha);
	}

	public String singIn(String[] datosRegistro) {
		resultado = "";

		if (!conexion.comprobarDisponibilidadNick(datosRegistro[0])) {
			resultado = "Nickname";
		}

		if (!datosRegistro[4].equals(datosRegistro[5])) {
			resultado = "Contraseña";
		}

		if (datosRegistro[9].equals("N")) {
			resultado = "Politica";
		}

		if (datosRegistro[10].equals("N")) {
			resultado = "Mayor";
		}

		if (!datosRegistro[11].equals(((_01_Registrar) vistas[1]).getCaptcha())) {
			resultado = "Captcha";
		}

		for (int i = 0; i < datosRegistro.length; i++) {
			if ((datosRegistro[i].equals(null) || datosRegistro[i].equals("")) && (i != 7 && i != 8)) {
				resultado = "Faltan";
			}
		}

		if (datosRegistro[7].equals("0") || datosRegistro[7].equals("-1")) {
			resultado = "Pregunta";
		}

		if (datosRegistro[8].equals("") || datosRegistro[8].equals(null)) {
			resultado = "Respuesta";
		}

		if (resultado.equals("")) {
			resultado = "Correcto";

			// Generar codigo pregunta a partir del índice de comboBox
			for (int i = datosRegistro[7].length(); i < 3; i++) {
				datosRegistro[7] += "0" + datosRegistro[7];
			}
			datosRegistro[7] = "PRE" + datosRegistro[7];

			conexion.crearUsuario(datosRegistro);
		}
		return resultado;
	}

	public String login(String usr, String pwd) {
		resultado = "";
		String userData[] = conexion.verificarUsuario(usr, pwd);
		if ((userData[0] != null && userData[1] != null) && (userData[0].equals(usr) && userData[1].equals(pwd))) {
			user.cargarDatos(conexion.cargarUsuario(usr));
			resultado = "Correcto";
			fallos = 0;
		} else {
			fallos++;
			if (fallos == 3) {
				resultado = "Cerrar";
			} else {
				resultado = "Incorrecto";
			}
		}
		return resultado;
	}

	public Object[] obtenerPreguntasSeguridad() {
		return conexion.obtenerPreguntasSeguridad();
	}

	public String cargarPreguntaUsuario() {
		return conexion.obtenerPreguntaUsuario(user.getNickname());
	}
}
