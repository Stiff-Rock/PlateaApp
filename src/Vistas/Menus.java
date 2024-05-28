package Vistas;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Controlador.Controlador;
import Modelo.Modelo;
import Modelo.Usuario;

public abstract class Menus extends JFrame implements Vista {
	protected NavPanel nav;
	protected JPanel mainPanel;
	
	protected Usuario user;
	protected Controlador controlador;
	protected Modelo modelo;

	public void setModelo(Modelo modelo) {
		this.modelo = modelo;
	}

	public void setControlador(Controlador controlador) {
		this.controlador = controlador;
	}

	public void setUsuario(Usuario user) {
		this.user = user;
	}

	public void configurarNav() {
		nav.setControlador(controlador);
	}

	public void setUserNav() {
		nav.setUsuario(user);
	}

	public Menus() {
		// Panel principal que contendrá todos los componentes
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setBounds(480, 150, 1024, 760);
		mainPanel = new JPanel();
		setContentPane(mainPanel);
		mainPanel.setLayout(null);
		mainPanel.setForeground(new Color(162, 196, 201));
	}
}
