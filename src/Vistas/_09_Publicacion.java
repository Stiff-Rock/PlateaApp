package Vistas;

import java.awt.Color;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
//@Autor: Hugo Osma

import Controlador.Controlador;
import Modelo.Modelo;
import Modelo.Usuario;

import javax.swing.JComboBox;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JToggleButton;

public class _09_Publicacion extends JFrame implements Vista {
	private int indice = 9;
	private JPanel mainPanel;
	private JTextField txtFecha;
	private JTextField txtTitulo;
	private JLabel lblImage;
	private JTextField txtDirecicion;
	private JTextField txtCp;
	private JPanel filtrosPanel;
	private JLabel lblLupa;
	private JLabel lblTitle;
	private JLabel lblDescripcion;
	private JTextField txtCategoria;

	private Usuario user;
	private Controlador controlador;
	private Modelo modelo;
	private NavPanel nav;

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
		nav.setIndiceActual(indice);
	}

	public _09_Publicacion() {
		// Panel principal que contendrá todos los componentes
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setTitle("Publicación");
		setBounds(480, 150, 1024, 760);
		mainPanel = new JPanel();
		setContentPane(mainPanel);
		mainPanel.setLayout(null);
		mainPanel.setForeground(new Color(162, 196, 201));

		// Agregar el panel de navegación
		nav = new NavPanel();
		mainPanel.add(nav);

		getContentPane().setForeground(new Color(162, 196, 201));
		setBounds(100, 100, 1024, 760);
		getContentPane().setLayout(null);

		JPanel contentPanel = new JPanel();
		contentPanel.setBackground(new Color(240, 240, 240));
		contentPanel.setBounds(212, 11, 786, 695);
		mainPanel.add(contentPanel);
		contentPanel.setLayout(null);

		JPanel publicarPanel = new JPanel();
		publicarPanel.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		publicarPanel.setBackground(new Color(207, 226, 243));
		publicarPanel.setBounds(61, 11, 664, 673);
		contentPanel.add(publicarPanel);
		publicarPanel.setLayout(null);

		txtFecha = new JTextField();
		txtFecha.setEditable(false);
		txtFecha.setText("DD/MM/AAAA");
		txtFecha.setBounds(60, 331, 141, 28);
		publicarPanel.add(txtFecha);
		txtFecha.setColumns(10);

		JPanel fotoPanel = new JPanel();
		fotoPanel.setBackground(new Color(255, 255, 255));
		fotoPanel.setBounds(225, 135, 213, 144);
		publicarPanel.add(fotoPanel);
		fotoPanel.setLayout(null);

		txtTitulo = new JTextField();
		txtTitulo.setEditable(false);
		txtTitulo.setText("Titulo");
		txtTitulo.setBounds(60, 76, 543, 28);
		publicarPanel.add(txtTitulo);
		txtTitulo.setColumns(10);

		lblImage = new JLabel("");
		lblImage.setBackground(new Color(128, 128, 128));
		lblImage.setBounds(72, 128, 512, 117);
		publicarPanel.add(lblImage);

		txtDirecicion = new JTextField();
		txtDirecicion.setEditable(false);
		txtDirecicion.setBounds(60, 391, 543, 28);
		publicarPanel.add(txtDirecicion);
		txtDirecicion.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Fecha");
		lblNewLabel_1.setBounds(60, 314, 45, 13);
		publicarPanel.add(lblNewLabel_1);

		JLabel lblDireccion = new JLabel("Localizacion");
		lblDireccion.setBounds(60, 374, 67, 13);
		publicarPanel.add(lblDireccion);

		txtCp = new JTextField();
		txtCp.setEditable(false);
		txtCp.setColumns(10);
		txtCp.setBounds(261, 331, 141, 28);
		publicarPanel.add(txtCp);

		JLabel lblCategoria = new JLabel("Categoria");
		lblCategoria.setBounds(462, 314, 75, 13);
		publicarPanel.add(lblCategoria);

		JLabel lblCp = new JLabel("Codigo Postal");
		lblCp.setBounds(261, 314, 69, 13);
		publicarPanel.add(lblCp);

		JTextArea txtDescripcion = new JTextArea();
		txtDescripcion.setEditable(false);
		txtDescripcion.setBounds(60, 461, 543, 117);
		publicarPanel.add(txtDescripcion);

		lblDescripcion = new JLabel("Descripción del problema:");
		lblDescripcion.setBounds(60, 437, 131, 13);
		publicarPanel.add(lblDescripcion);

		filtrosPanel = new JPanel();
		filtrosPanel.setBounds(0, 0, 664, 49);
		publicarPanel.add(filtrosPanel);
		filtrosPanel.setLayout(null);
		filtrosPanel.setForeground(Color.WHITE);
		filtrosPanel.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		filtrosPanel.setBackground(new Color(162, 196, 201));

		lblLupa = new JLabel("");
		lblLupa.setBounds(8, 17, 14, 14);
		filtrosPanel.add(lblLupa);

		lblTitle = new JLabel("Publicación");
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setBounds(170, 8, 324, 32);
		filtrosPanel.add(lblTitle);

		txtCategoria = new JTextField();
		txtCategoria.setEditable(false);
		txtCategoria.setColumns(10);
		txtCategoria.setBounds(462, 331, 141, 28);
		publicarPanel.add(txtCategoria);

		JToggleButton tglbtnVotar = new JToggleButton("Votar");
		tglbtnVotar.setBounds(482, 621, 121, 23);
		publicarPanel.add(tglbtnVotar);

		JToggleButton tglbtnFavoritos = new JToggleButton("Favoritos");
		tglbtnFavoritos.setBounds(329, 621, 121, 23);
		publicarPanel.add(tglbtnFavoritos);

		JLabel lblNewLabel_1_1 = new JLabel("Votos totales:  6.000");
		lblNewLabel_1_1.setBounds(60, 625, 200, 13);
		publicarPanel.add(lblNewLabel_1_1);
	}
}
