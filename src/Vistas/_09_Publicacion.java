package Vistas;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
//@Autor: Hugo Osma

public class _09_Publicacion extends Menus {
	private JTextField txtFecha;
	private JTextField txtDireccion;
	private JTextField txtCp;
	private JTextField txtCategoria;
	private JTextArea txtDescripcion;
	private JLabel lblFoto;
	private JLabel lblNumVotos;

	public void setTxtFecha(String txtFecha) {
		this.txtFecha.setText(txtFecha);
	}

	public void setTxtDireccion(String txtDireccion) {
		this.txtDireccion.setText(txtDireccion);
	}

	public void setTxtCp(String txtCp) {
		this.txtCp.setText(txtCp);
	}

	public void setTxtCategoria(String txtCategoria) {
		this.txtCategoria.setText(txtCategoria);
	}

	public void setTxtDescripcion(String txtDescripcion) {
		this.txtDescripcion.setText(txtDescripcion);
	}

	public void setLblFoto(ImageIcon imagen) {
		lblFoto.setIcon(imagen);
	}

	public _09_Publicacion() {
		setTitle("Publicación");
		setContentPane(mainPanel);

		JPanel publicarPanel = new JPanel();
		publicarPanel.setLayout(null);
		publicarPanel.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		publicarPanel.setBackground(new Color(207, 226, 243));
		publicarPanel.setBounds(61, 27, 664, 640);
		contentPanel.add(publicarPanel);

		txtFecha = new JTextField();
		txtFecha.setEditable(false);
		txtFecha.setColumns(10);
		txtFecha.setBounds(60, 311, 141, 28);
		publicarPanel.add(txtFecha);

		JPanel fotoPanel = new JPanel();
		fotoPanel.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0)));
		fotoPanel.setLayout(null);
		fotoPanel.setBackground(Color.WHITE);
		fotoPanel.setBounds(167, 79, 331, 187);
		publicarPanel.add(fotoPanel);

		lblFoto = new JLabel("");
		lblFoto.setHorizontalAlignment(SwingConstants.CENTER);
		lblFoto.setBounds(0, 0, 329, 185);
		fotoPanel.add(lblFoto);

		JLabel lblImage = new JLabel("");
		lblImage.setBackground(Color.GRAY);
		lblImage.setBounds(72, 128, 512, 117);
		publicarPanel.add(lblImage);

		txtDireccion = new JTextField();
		txtDireccion.setEditable(false);
		txtDireccion.setColumns(10);
		txtDireccion.setBounds(60, 371, 543, 28);
		publicarPanel.add(txtDireccion);

		JLabel lblFecha = new JLabel("Fecha (DD/MM/AAAA):");
		lblFecha.setBounds(60, 294, 141, 13);
		publicarPanel.add(lblFecha);

		JLabel lblDireccion = new JLabel("Direccion:");
		lblDireccion.setBounds(60, 354, 151, 13);
		publicarPanel.add(lblDireccion);

		txtCp = new JTextField();
		txtCp.setEditable(false);
		txtCp.setColumns(10);
		txtCp.setBounds(261, 311, 141, 28);
		publicarPanel.add(txtCp);

		JLabel lblCategoria = new JLabel("Categoria:");
		lblCategoria.setBounds(462, 294, 141, 13);
		publicarPanel.add(lblCategoria);

		JLabel lblCp = new JLabel("Codigo Postal:");
		lblCp.setBounds(261, 294, 141, 13);
		publicarPanel.add(lblCp);

		JLabel lblDescripcion = new JLabel("Descripción del problema:");
		lblDescripcion.setBounds(60, 417, 131, 13);
		publicarPanel.add(lblDescripcion);

		JPanel filtrosPanel = new JPanel();
		filtrosPanel.setLayout(null);
		filtrosPanel.setForeground(Color.WHITE);
		filtrosPanel.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		filtrosPanel.setBackground(new Color(162, 196, 201));
		filtrosPanel.setBounds(0, 0, 664, 49);
		publicarPanel.add(filtrosPanel);

		JLabel lblLupa = new JLabel("");
		lblLupa.setBounds(8, 17, 14, 14);
		filtrosPanel.add(lblLupa);

		JLabel lblTitle = new JLabel("Publicacion");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblTitle.setBounds(170, 8, 324, 32);
		filtrosPanel.add(lblTitle);
		//TODO VOTOS TOTALES
		lblNumVotos = new JLabel("Votos totales: 6.000");
		lblNumVotos.setBounds(60, 598, 174, 14);
		publicarPanel.add(lblNumVotos);

		JToggleButton tglbtnVotar = new JToggleButton("Votar");
		tglbtnVotar.setBounds(514, 594, 89, 23);
		publicarPanel.add(tglbtnVotar);

		txtCategoria = new JTextField();
		txtCategoria.setEditable(false);
		txtCategoria.setColumns(10);
		txtCategoria.setBounds(462, 311, 141, 28);
		publicarPanel.add(txtCategoria);

		JToggleButton tglbtnFavorito = new JToggleButton("Favorito");
		tglbtnFavorito.setBounds(407, 594, 89, 23);
		publicarPanel.add(tglbtnFavorito);

		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		panel.setBackground(Color.WHITE);
		panel.setBounds(60, 441, 543, 114);
		publicarPanel.add(panel);

		txtDescripcion = new JTextArea();
		txtDescripcion.setRows(5);
		txtDescripcion.setLineWrap(true);
		txtDescripcion.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtDescripcion.setBounds(10, 11, 523, 92);
		panel.add(txtDescripcion);

		addWindowListener(new WindowAdapter() {
			public void windowActivated(WindowEvent e) {
				controlador.cargarPublicacion();
			}
		});
	}
}
