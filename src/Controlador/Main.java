package Controlador;

import Modelo.Modelo;
import Modelo.Usuario;
import Vistas.Menus;
import Vistas.NavPanel;
import Vistas.Vista;
import Vistas._00_Login;
import Vistas._01_Registrar;
import Vistas._02_Reestablecer;
import Vistas._03_Home;
import Vistas._04_MiPerfil;
import Vistas._05_MisPublicaciones;
import Vistas._06_MisFavoritos;
import Vistas._07_Votados;
import Vistas._08_Administrador;
import Vistas._09_Publicacion;
import Vistas._10_Publicar;

//@Autor: Yago Pernas
public class Main {
	public static void main(String[] args) {
		Modelo modelo = new Modelo();
		Controlador controlador = new Controlador();
		NavPanel nav = new NavPanel();
		Usuario user = new Usuario();
		Menus menu = new Menus();
		Vista[] vistas = new Vista[11];

		menu.setModelo(modelo);
		menu.setControlador(controlador);
		menu.setUsuario(user);
		
		vistas[0] = new _00_Login();
		vistas[1] = new _01_Registrar();
		vistas[2] = new _02_Reestablecer();
		vistas[3] = new _03_Home();
		vistas[4] = new _04_MiPerfil();
		vistas[5] = new _05_MisPublicaciones();
		vistas[6] = new _06_MisFavoritos();
		vistas[7] = new _07_Votados();
		vistas[8] = new _08_Administrador();
		vistas[9] = new _09_Publicacion();
		vistas[10] = new _10_Publicar();

		modelo.setVistas(vistas);
		modelo.setUsuario(user);
		
		controlador.setVista(vistas);
		controlador.setModelo(modelo);
		controlador.setUsuario(user);
		
		// Genera el captcha para esta sesión
		((_01_Registrar) vistas[1]).setCaptcha(modelo.generateCaptcha());
		
		// Carga las preguntas de seguridad
		controlador.cargarPreguntasRegistrar();

		controlador.cambiarVentana(0, 0);
	}
}
