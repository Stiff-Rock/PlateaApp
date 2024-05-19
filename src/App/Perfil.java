package App;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

public class Perfil extends JFrame {
	private JTextField txtNombre;
	private JLabel lblImage;
	private JTextField textField;
	private JTextField textField_1;

	public Perfil() {
		
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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		ImageIcon resizedLogo = new ImageIcon(new ImageIcon(this.getClass().getResource("/logo.png")).getImage()
				.getScaledInstance(153, 83, Image.SCALE_SMOOTH));

		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(263, 45, 664, 567);
		getContentPane().add(panel);
		panel.setLayout(null);

		JPanel panel_4 = new JPanel();
		panel_4.setBounds(36, 21, 100, 100);
		panel.add(panel_4);
		panel_4.setBackground(new Color(255, 255, 255));
		panel_4.setLayout(null);
		
				
				// JLabel para mostrar la imagen
				lblImage = new JLabel("");
				lblImage.setBackground(new Color(192, 192, 192));
				lblImage.setBounds(0, 0, 100, 100);
				panel_4.add(lblImage);

		// Botón para subir archivos
		JButton btnUpload = new JButton("Subir Imagen");
		btnUpload.setBounds(0, 44, 100, 25);
		panel_4.add(btnUpload);

		btnUpload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				int result = fileChooser.showOpenDialog(null);
				if (result == JFileChooser.APPROVE_OPTION) {
					File selectedFile = fileChooser.getSelectedFile();
					ImageIcon icon = new ImageIcon(selectedFile.getAbsolutePath());
					Image img = icon.getImage().getScaledInstance(lblImage.getWidth(), lblImage.getHeight(),
							Image.SCALE_SMOOTH);
					lblImage.setIcon(new ImageIcon(img));
				}
			}
		});

		JLabel lblNewLabel_4 = new JLabel("Nickname");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_4.setBounds(147, 66, 268, 52);
		panel.add(lblNewLabel_4);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(207, 207, 243));
		panel_2.setBounds(79, 178, 493, 280);
		panel.add(panel_2);
		panel_2.setLayout(null);

		JLabel lblNewLabel = new JLabel("Nombre y apellidos");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(28, 27, 160, 19);
		panel_2.add(lblNewLabel);

		txtNombre = new JTextField();
		txtNombre.setEditable(false);
		txtNombre.setBounds(28, 46, 432, 29);
		panel_2.add(txtNombre);
		txtNombre.setColumns(10);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setColumns(10);
		textField.setBounds(28, 120, 432, 29);
		panel_2.add(textField);
		
		JLabel lblCdigoPostal = new JLabel("Código Postal");
		lblCdigoPostal.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCdigoPostal.setBounds(28, 100, 160, 19);
		panel_2.add(lblCdigoPostal);
		
		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setColumns(10);
		textField_1.setBounds(28, 196, 432, 29);
		panel_2.add(textField_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Localidad");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_1.setBounds(28, 175, 160, 19);
		panel_2.add(lblNewLabel_1_1);
		
				JPanel panel_3 = new JPanel();
				panel_3.setBackground(new Color(208, 224, 227));
				panel_3.setBounds(0, 0, 664, 67);
				panel.add(panel_3);
				panel_3.setLayout(null);
		
		JButton btnNewButton_6 = new JButton("Publicar");
		btnNewButton_6.setIcon(mas);
		btnNewButton_6.setBounds(794, 640, 133, 33);
		getContentPane().add(btnNewButton_6);
		
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
		
		JLabel lblNewLabel_5 = new JLabel("");
		lblNewLabel_5.setIcon(logo);
		lblNewLabel_5.setBounds(10, 40, 192, 82);
		panel_1.add(lblNewLabel_5);
		
		JLabel lblNewLabel_3_1 = new JLabel("");
		lblNewLabel_3_1.setIcon(info);
		lblNewLabel_3_1.setBounds(156, 670, 46, 40);
		panel_1.add(lblNewLabel_3_1);
		
		setVisible(true);
	}
}
