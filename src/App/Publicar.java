package App;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;

public class Publicar extends JFrame {
	private JTextField txtDescripcion;
	private JTextField txtDescripcion_1;
	private JTextField txtEstadofechacategorialocalizacion;
	private JTextField txtTituloPublicacion;
	private JTextField txtTitulo;
	private JLabel lblImage;
	private JTextField textField;
	private JTextField textField_1;

	public Publicar() {
		
		ImageIcon logo = new ImageIcon(new ImageIcon(this.getClass().getResource("/logo.png")).getImage()
				.getScaledInstance(170, 65, Image.SCALE_SMOOTH));
		ImageIcon casa = new ImageIcon(new ImageIcon(this.getClass().getResource("/casa.png")).getImage()
				.getScaledInstance(15, 15, Image.SCALE_SMOOTH));
		ImageIcon usuario = new ImageIcon(new ImageIcon(this.getClass().getResource("/usuario.png")).getImage()
				.getScaledInstance(15, 15, Image.SCALE_SMOOTH));
		ImageIcon libro = new ImageIcon(new ImageIcon(this.getClass().getResource("/libro-abierto.png")).getImage()
				.getScaledInstance(15, 15, Image.SCALE_SMOOTH));
		ImageIcon estrella = new ImageIcon(new ImageIcon(this.getClass().getResource("/estrella.png")).getImage()
				.getScaledInstance(15, 15, Image.SCALE_SMOOTH));
		ImageIcon pulgar = new ImageIcon(
				new ImageIcon(this.getClass().getResource("/pulgar-hacia-arriba-simbolo-de-la-mano.png")).getImage()
						.getScaledInstance(15, 15, Image.SCALE_SMOOTH));
		ImageIcon lupa = new ImageIcon(new ImageIcon(this.getClass().getResource("/lupa.png")).getImage()
				.getScaledInstance(15, 15, Image.SCALE_SMOOTH));
		ImageIcon mas = new ImageIcon(new ImageIcon(this.getClass().getResource("/mas.png")).getImage()
				.getScaledInstance(15, 15, Image.SCALE_SMOOTH));
		ImageIcon info = new ImageIcon(new ImageIcon(this.getClass().getResource("/informacion.png")).getImage()
				.getScaledInstance(30, 30, Image.SCALE_SMOOTH));
		
		getContentPane().setLocation(-260, -138);
		setBounds(100, 100, 1024, 760);
		getContentPane().setLayout(null);
		ImageIcon resizedLogo = new ImageIcon(new ImageIcon(this.getClass().getResource("/logo.png")).getImage()
				.getScaledInstance(153, 83, Image.SCALE_SMOOTH));
		
		JPanel panel = new JPanel();
		panel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		panel.setBackground(new Color(207, 226, 243));
		panel.setBounds(267, 34, 664, 586);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(62, 357, 532, 149);
		panel.add(scrollPane);
		
		txtDescripcion = new JTextField();
		scrollPane.setViewportView(txtDescripcion);
		txtDescripcion.setColumns(10);
		
		txtDescripcion_1 = new JTextField();
		txtDescripcion_1.setEditable(false);
		txtDescripcion_1.setText("Descripcion del problema");
		scrollPane.setColumnHeaderView(txtDescripcion_1);
		txtDescripcion_1.setColumns(10);
		
		txtEstadofechacategorialocalizacion = new JTextField();
		txtEstadofechacategorialocalizacion.setText("DD/MM/AAAA");
		txtEstadofechacategorialocalizacion.setBounds(127, 244, 141, 28);
		panel.add(txtEstadofechacategorialocalizacion);
		txtEstadofechacategorialocalizacion.setColumns(10);
		
		txtTituloPublicacion = new JTextField();
		txtTituloPublicacion.setText("Titulo publicacion");
		txtTituloPublicacion.setBounds(0, 0, 664, 19);
		panel.add(txtTituloPublicacion);
		txtTituloPublicacion.setColumns(10);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(62, 76, 532, 144);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		JButton btnUpload = new JButton("Subir Imagen");
		btnUpload.setBounds(184, 42, 150, 25);
		panel_2.add(btnUpload);
		
		btnUpload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				int result = fileChooser.showOpenDialog(null);
				if (result == JFileChooser.APPROVE_OPTION) {
					File selectedFile = fileChooser.getSelectedFile();
					ImageIcon icon = new ImageIcon(selectedFile.getAbsolutePath());
					Image img = icon.getImage().getScaledInstance(lblImage.getWidth(), lblImage.getHeight(), Image.SCALE_SMOOTH);
					lblImage.setIcon(new ImageIcon(img));
				}
			}
		});
		
		JButton btnNewButton_6 = new JButton("Publicar");
		btnNewButton_6.setIcon(mas);
		btnNewButton_6.setBounds(469, 517, 125, 34);
		panel.add(btnNewButton_6);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Categorias", "", "Derrumbamiento", "Bache ", "Arbol caido"}));
		comboBox.setToolTipText("");
		comboBox.setBounds(62, 517, 112, 28);
		panel.add(comboBox);
		
		txtTitulo = new JTextField();
		txtTitulo.setText("Titulo");
		txtTitulo.setBounds(62, 38, 532, 28);
		panel.add(txtTitulo);
		txtTitulo.setColumns(10);
		
		lblImage = new JLabel("");
		lblImage.setBackground(new Color(128, 128, 128));
		lblImage.setBounds(72, 76, 512, 117);
		panel.add(lblImage);
		
		textField = new JTextField();
		textField.setBounds(62, 319, 532, 28);
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Fecha");
		lblNewLabel_1.setBounds(95, 251, 45, 13);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Localizacion");
		lblNewLabel_2.setBounds(62, 302, 67, 13);
		panel.add(lblNewLabel_2);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(395, 244, 141, 28);
		panel.add(textField_1);
		
		JLabel lblNewLabel_4 = new JLabel("Codigo Postal");
		lblNewLabel_4.setBounds(325, 251, 75, 13);
		panel.add(lblNewLabel_4);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBackground(new Color(162, 196, 201));
		panel_1.setBounds(0, 0, 202, 721);
		getContentPane().add(panel_1);
		
		JButton btnNewButton = new JButton("Home");
		btnNewButton.setIcon(casa);
		btnNewButton.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton.setBounds(35, 156, 135, 40);
		panel_1.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Mi perfil");
		btnNewButton_1.setIcon(usuario);
		btnNewButton_1.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_1.setBounds(35, 229, 135, 40);
		panel_1.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Publicaciones");
		btnNewButton_2.setIcon(libro);
		btnNewButton_2.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_2.setBounds(35, 304, 135, 40);
		panel_1.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Favoritos");
		btnNewButton_3.setIcon(estrella);
		btnNewButton_3.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewButton_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_3.setBounds(35, 380, 135, 40);
		panel_1.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("Votados");
		btnNewButton_4.setIcon(pulgar);
		btnNewButton_4.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewButton_4.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_4.setBounds(35, 459, 135, 40);
		panel_1.add(btnNewButton_4);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(logo);
		lblNewLabel.setBounds(10, 40, 192, 82);
		panel_1.add(lblNewLabel);
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(info);
		lblNewLabel_3.setBounds(156, 670, 46, 40);
		panel_1.add(lblNewLabel_3);
		
		setVisible(true);
	}
}