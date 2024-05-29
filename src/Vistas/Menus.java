package Vistas;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import Controlador.Controlador;
import Modelo.Usuario;

public class Menus extends JFrame implements Vista {
	protected JButton btnInicio;
	protected JButton btnPerfil;
	protected JButton btnPublicaciones;
	protected JButton btnFavoritos;
	protected JButton btnVotados;
	protected JButton btnGestionar;
	protected JPanel mainPanel;
	protected JPanel navPanel;

	protected Controlador controlador;
	protected JPanel contentPanel;
	// Esta variable muestra en que ventana se encuentra la aplicación para
	// reflejarlo en los botones.
	private int indiceActual = 0;

	public void setControlador(Controlador controlador) {
		this.controlador = controlador;
	}

	public final void setIndiceActual(int indice) {
		this.indiceActual = indice;
	}

	public final int getIndiceActual() {
		return indiceActual;
	}

	public Menus() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setBounds(480, 150, 1024, 760);

		// Panel principal que contendrá todos los componentes
		mainPanel = new JPanel();
		setContentPane(mainPanel);
		mainPanel.setLayout(null);
		mainPanel.setForeground(new Color(162, 196, 201));

		// Agregar el panel de navegación al panel principal
		navPanel = new JPanel();
		navPanel.setLayout(null);
		navPanel.setBackground(new Color(162, 196, 201));
		navPanel.setBounds(0, 0, 202, 721);
		mainPanel.add(navPanel);

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
		navPanel.add(lblLogo);

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
		navPanel.add(btnInicio);

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
		navPanel.add(btnPerfil);

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
		navPanel.add(btnPublicaciones);

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
		navPanel.add(btnFavoritos);

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
		navPanel.add(btnVotados);

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
		navPanel.add(btnGestionar);

		// Boton para mostrar informacion de contacto
		JLabel lblinfo = new JLabel("");
		lblinfo.setBounds(156, 670, 36, 40);
		lblinfo.setIcon(info);
		navPanel.add(lblinfo);

		// Panel con el contenido principal de cada ventana
		contentPanel = new JPanel();
		contentPanel.setBackground(new Color(240, 240, 240));
		contentPanel.setBounds(212, 11, 786, 695);
		mainPanel.add(contentPanel);
		contentPanel.setLayout(null);
		
		addWindowListener(new WindowAdapter() {
			public void windowActivated(WindowEvent e) {
				if ((controlador.getUser()).getEsAdmin().equals("S")) {
					btnGestionar.setVisible(true);
				} else {
					btnGestionar.setVisible(false);
				}
			}
		});
	}
}
