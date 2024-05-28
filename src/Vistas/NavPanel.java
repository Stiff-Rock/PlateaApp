package Vistas;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import Controlador.Controlador;
import Modelo.Usuario;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class NavPanel extends JPanel {
	private int indiceActual;
	private JButton btnInicio;
	private JButton btnPerfil;
	private JButton btnPublicaciones;
	private JButton btnFavoritos;
	private JButton btnVotados;
	private JButton btnGestionar;

	private Usuario user;
	private Controlador controlador;

	public void setControlador(Controlador controlador) {
		this.controlador = controlador;
	}
	
	public void setIndiceActual(int indice) {
		indiceActual = indice;
	}

	public void setUsuario(Usuario user) {
		this.user = user;
	       if (user.getEsAdmin().equals("S")) {
	            btnGestionar.setVisible(true);
	        } else {
	            btnGestionar.setVisible(false);
	        }
	}

	public NavPanel() {
		setLayout(null);
		setBackground(new Color(162, 196, 201));
		setBounds(0, 0, 202, 721);

		// Variables de los iconos e imágenes reescaladas para la interfaz
		ImageIcon logo = new ImageIcon(new ImageIcon(this.getClass().getResource("/img/logo.png")).getImage()
				.getScaledInstance(170, 65, Image.SCALE_SMOOTH));
		ImageIcon casa = new ImageIcon(new ImageIcon(this.getClass().getResource("/img/casa.png")).getImage()
				.getScaledInstance(15, 15, Image.SCALE_SMOOTH));
		ImageIcon usuario = new ImageIcon(new ImageIcon(this.getClass().getResource("/img/usuario.png")).getImage()
				.getScaledInstance(15, 15, Image.SCALE_SMOOTH));
		ImageIcon libro = new ImageIcon(new ImageIcon(this.getClass().getResource("/img/libro-abierto.png")).getImage()
				.getScaledInstance(15, 15, Image.SCALE_SMOOTH));
		ImageIcon estrella = new ImageIcon(new ImageIcon(this.getClass().getResource("/img/estrella.png")).getImage()
				.getScaledInstance(15, 15, Image.SCALE_SMOOTH));
		ImageIcon pulgar = new ImageIcon(
				new ImageIcon(this.getClass().getResource("/img/pulgar-hacia-arriba-simbolo-de-la-mano.png")).getImage()
						.getScaledInstance(15, 15, Image.SCALE_SMOOTH));
		ImageIcon gestionar = new ImageIcon(new ImageIcon(this.getClass().getResource("/img/llave.png")).getImage()
				.getScaledInstance(15, 15, Image.SCALE_SMOOTH));
		ImageIcon info = new ImageIcon(new ImageIcon(this.getClass().getResource("/img/informacion.png")).getImage()
				.getScaledInstance(30, 30, Image.SCALE_SMOOTH));

		// Label que contiene la imagen del logo
		JLabel lblLogo = new JLabel("");
		lblLogo.setBounds(16, 38, 170, 65);
		lblLogo.setIcon(logo);
		add(lblLogo);

		// Boton para ir al inicio
		btnInicio = new JButton("Inicio");
		btnInicio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.cambiarVentana(indiceActual, 3);
			}
		});
		btnInicio.setHorizontalAlignment(SwingConstants.LEFT);
		btnInicio.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnInicio.setBounds(33, 156, 135, 40);
		btnInicio.setIcon(casa);
		add(btnInicio);

		// Boton para ir a tu perfil
		btnPerfil = new JButton("Mi perfil");
		btnPerfil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.cambiarVentana(indiceActual, 4);
			}
		});
		btnPerfil.setHorizontalAlignment(SwingConstants.LEFT);
		btnPerfil.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnPerfil.setBounds(33, 229, 135, 40);
		btnPerfil.setIcon(usuario);
		add(btnPerfil);

		// Boton para ir a tus publicaciones
		btnPublicaciones = new JButton("Mis Publicaciones");
		btnPublicaciones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.cambiarVentana(indiceActual, 5);
			}
		});
		btnPublicaciones.setHorizontalAlignment(SwingConstants.LEFT);
		btnPublicaciones.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnPublicaciones.setBounds(33, 304, 135, 40);
		btnPublicaciones.setIcon(libro);
		add(btnPublicaciones);

		// Boton para ir a tus favoritos
		btnFavoritos = new JButton("Favoritos");
		btnFavoritos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.cambiarVentana(indiceActual, 6);
			}
		});
		btnFavoritos.setHorizontalAlignment(SwingConstants.LEFT);
		btnFavoritos.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnFavoritos.setBounds(33, 380, 135, 40);
		btnFavoritos.setIcon(estrella);
		add(btnFavoritos);

		// Boton para ir a tus votados
		btnVotados = new JButton("Votados");
		btnVotados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.cambiarVentana(indiceActual, 7);
			}
		});
		btnVotados.setHorizontalAlignment(SwingConstants.LEFT);
		btnVotados.setBounds(33, 459, 135, 40);
		btnVotados.setIcon(pulgar);
		add(btnVotados);

		// Boton para gestionar publicaciones (Debe desaparecer si no eres admin)
		btnGestionar = new JButton("Gestionar");
		btnGestionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.cambiarVentana(indiceActual, 8);
			}
		});
		btnGestionar.setHorizontalAlignment(SwingConstants.LEFT);
		btnGestionar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnGestionar.setBounds(33, 538, 135, 40);
		btnGestionar.setIcon(gestionar);
		add(btnGestionar);

		// Boton para mostrar informacion de contacto
		JLabel lblinfo = new JLabel("");
		lblinfo.setBounds(156, 670, 36, 40);
		lblinfo.setIcon(info);
		add(lblinfo);
	}
}
