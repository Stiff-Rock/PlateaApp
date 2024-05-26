package Modelo;

import java.util.Random;

import Controlador.Controlador;
import Vistas.Vista;
import Vistas._00_Login;
import Vistas._01_Registrar;

public class Modelo {
	private Vista[] vistas;
	private String resultado;
	private int fallos;
	private ConexionOracle datos = new ConexionOracle();

	public void setVistas(Vista[] vistas) {
		this.vistas = vistas;
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

	public void singIn(String[] datosRegistro) {
		resultado = "";

		if (!datos.comprobarDisponibilidadNick(datosRegistro[0])) {
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

		for (String string : datosRegistro) {
			if (string.equals(null) || string.equals("")) {
				resultado = "Faltan";
			}
		}

		if (datosRegistro[7].equals("0")) {
			resultado = "Pregunta";
		}

		if (datosRegistro[8].equals("") || datosRegistro[8].equals(null)) {
			resultado = "Respuesta";
		}
		
		if (resultado.equals("")) {
			resultado = "Correcto";
			datos.crearUsuario(datosRegistro);
		}

		((_01_Registrar) vistas[1]).actualizar();
	}

	public void login(String usr, String pwd) {
		resultado = "";
		String userData[] = datos.verificarUsuario(usr, pwd);
		if ((userData[0] != null && userData[1] != null) && (userData[0].equals(usr) && userData[1].equals(pwd))) {
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
		((_00_Login) vistas[0]).actualizar();
	}

}
