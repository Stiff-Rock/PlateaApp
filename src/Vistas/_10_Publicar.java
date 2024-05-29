package Vistas;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
//@Autor: Hugo Osma

public class _10_Publicar extends Menus {
	private JTextField txtFecha;
	private JLabel lblImage;
	private JTextField txtDirecicion;
	private JTextField txtCp;
	private JPanel filtrosPanel;
	private JLabel lblLupa;
	private JLabel lblTitle;
	private JLabel lblDescripcion;
	private JButton btnSubirFoto;
	private JButton btnPubllicar;
	private JLabel lblWarning;
	private JComboBox comboBoxCategoria;

	public _10_Publicar() {
		setTitle("Publicar");
		setContentPane(mainPanel);

		JPanel publicarPanel = new JPanel();
		publicarPanel.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		publicarPanel.setBackground(new Color(207, 226, 243));
		publicarPanel.setBounds(61, 27, 664, 640);
		contentPanel.add(publicarPanel);
		publicarPanel.setLayout(null);

		txtFecha = new JTextField();
		txtFecha.setBounds(60, 311, 141, 28);
		publicarPanel.add(txtFecha);
		txtFecha.setColumns(10);

		JPanel fotoPanel = new JPanel();
		fotoPanel.setBackground(new Color(255, 255, 255));
		fotoPanel.setBounds(60, 79, 524, 185);
		publicarPanel.add(fotoPanel);
		fotoPanel.setLayout(null);

		btnSubirFoto = new JButton("Subir Foto");
		btnSubirFoto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnSubirFoto.setBounds(205, 81, 114, 23);
		fotoPanel.add(btnSubirFoto);

		lblImage = new JLabel("");
		lblImage.setBackground(new Color(128, 128, 128));
		lblImage.setBounds(72, 128, 512, 117);
		publicarPanel.add(lblImage);

		txtDirecicion = new JTextField();
		txtDirecicion.setBounds(60, 371, 543, 28);
		publicarPanel.add(txtDirecicion);
		txtDirecicion.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Fecha (DD/MM/AAAA):");
		lblNewLabel_1.setBounds(60, 294, 141, 13);
		publicarPanel.add(lblNewLabel_1);

		JLabel lblDireccion = new JLabel("Direccion:");
		lblDireccion.setBounds(60, 354, 151, 13);
		publicarPanel.add(lblDireccion);

		txtCp = new JTextField();
		txtCp.setColumns(10);
		txtCp.setBounds(261, 311, 141, 28);
		publicarPanel.add(txtCp);

		JLabel lblCategoria = new JLabel("Categoria:");
		lblCategoria.setBounds(462, 294, 141, 13);
		publicarPanel.add(lblCategoria);

		JLabel lblCp = new JLabel("Codigo Postal:");
		lblCp.setBounds(261, 294, 141, 13);
		publicarPanel.add(lblCp);

		JTextArea txtDescripcion = new JTextArea();
		txtDescripcion.setBounds(60, 441, 543, 117);
		publicarPanel.add(txtDescripcion);

		lblDescripcion = new JLabel("Descripción del problema:");
		lblDescripcion.setBounds(60, 417, 131, 13);
		publicarPanel.add(lblDescripcion);

		comboBoxCategoria = new JComboBox();
		comboBoxCategoria.setBounds(462, 311, 141, 28);
		publicarPanel.add(comboBoxCategoria);

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

		lblTitle = new JLabel("Crear publlicación");
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setBounds(170, 8, 324, 32);
		filtrosPanel.add(lblTitle);
		
		btnPubllicar = new JButton("Publicar");
		btnPubllicar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.crearPublicacion();
			}
		});
		btnPubllicar.setBounds(514, 594, 89, 23);
		publicarPanel.add(btnPubllicar);
		
		lblWarning = new JLabel("");
		lblWarning.setForeground(new Color(255, 0, 0));
		lblWarning.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblWarning.setBounds(60, 598, 378, 14);
		publicarPanel.add(lblWarning);
	}
	
	public void mostrarWarning(String mensaje) {
		lblWarning.setText(mensaje);
	}

//	public ImageIcon getFoto() {
//		return null;
//	}

	public String getFecha() {
		return txtFecha.getText();
	}

	public String getCp() {
		return txtCp.getText();
	}

	public String getCategoria() {
		return String.valueOf(comboBoxCategoria.getSelectedIndex());
	}

	public String getDireccion() {
		return txtDirecicion.getText();
	}

	public String getDescripcion() {
		return txtDirecicion.getText();
	}
}
