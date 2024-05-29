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
	private JTextField txtTitulo;
	private JLabel lblImage;
	private JTextField txtDirecicion;
	private JTextField txtCp;
	private JPanel filtrosPanel;
	private JLabel lblLupa;
	private JLabel lblTitle;
	private JLabel lblDescripcion;
	private JButton btnSubirFoto;

	public _10_Publicar() {
		setTitle("Publicar");
		setContentPane(mainPanel);

		JPanel publicarPanel = new JPanel();
		publicarPanel.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		publicarPanel.setBackground(new Color(207, 226, 243));
		publicarPanel.setBounds(61, 54, 664, 586);
		contentPanel.add(publicarPanel);
		publicarPanel.setLayout(null);

		txtFecha = new JTextField();
		txtFecha.setText("DD/MM/AAAA");
		txtFecha.setBounds(60, 311, 141, 28);
		publicarPanel.add(txtFecha);
		txtFecha.setColumns(10);

		JPanel fotoPanel = new JPanel();
		fotoPanel.setBackground(new Color(255, 255, 255));
		fotoPanel.setBounds(225, 126, 213, 144);
		publicarPanel.add(fotoPanel);
		fotoPanel.setLayout(null);

		btnSubirFoto = new JButton("Subir Foto");
		btnSubirFoto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnSubirFoto.setBounds(49, 60, 114, 23);
		fotoPanel.add(btnSubirFoto);

		txtTitulo = new JTextField();
		txtTitulo.setText("Titulo");
		txtTitulo.setBounds(60, 76, 543, 28);
		publicarPanel.add(txtTitulo);
		txtTitulo.setColumns(10);

		lblImage = new JLabel("");
		lblImage.setBackground(new Color(128, 128, 128));
		lblImage.setBounds(72, 128, 512, 117);
		publicarPanel.add(lblImage);

		txtDirecicion = new JTextField();
		txtDirecicion.setBounds(60, 371, 543, 28);
		publicarPanel.add(txtDirecicion);
		txtDirecicion.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Fecha");
		lblNewLabel_1.setBounds(60, 294, 45, 13);
		publicarPanel.add(lblNewLabel_1);

		JLabel lblDireccion = new JLabel("Localizacion");
		lblDireccion.setBounds(60, 354, 101, 13);
		publicarPanel.add(lblDireccion);

		txtCp = new JTextField();
		txtCp.setColumns(10);
		txtCp.setBounds(261, 311, 141, 28);
		publicarPanel.add(txtCp);

		JLabel lblCategoria = new JLabel("Categoria");
		lblCategoria.setBounds(462, 294, 75, 13);
		publicarPanel.add(lblCategoria);

		JLabel lblCp = new JLabel("Codigo Postal");
		lblCp.setBounds(261, 294, 69, 13);
		publicarPanel.add(lblCp);

		JTextArea txtDescripcion = new JTextArea();
		txtDescripcion.setBounds(60, 441, 543, 117);
		publicarPanel.add(txtDescripcion);

		lblDescripcion = new JLabel("Descripción del problema:");
		lblDescripcion.setBounds(60, 417, 131, 13);
		publicarPanel.add(lblDescripcion);

		JComboBox comboBoxCategoria = new JComboBox();
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
	}
}
