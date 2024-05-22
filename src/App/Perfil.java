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
import javax.swing.border.BevelBorder;
import javax.swing.border.MatteBorder;

public class Perfil extends JFrame {
	private JTextField txtNombre;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JLabel lblImage;

	public static void main(String[] args) {
		Perfil window = new Perfil();
		window.setVisible(true);
	}

	public Perfil() {
		getContentPane().setLocation(-260, -138);
		setBounds(100, 100, 1024, 760);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		ImageIcon resizedLogo = new ImageIcon(new ImageIcon(this.getClass().getResource("/logo.png")).getImage()
				.getScaledInstance(153, 83, Image.SCALE_SMOOTH));

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(162, 196, 201));
		panel_1.setBounds(0, 0, 185, 733);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);

		JButton btnNewButton = new JButton("Home");
		btnNewButton.setBounds(42, 107, 100, 40);
		panel_1.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Mi perfil");
		btnNewButton_1.setBounds(42, 173, 100, 40);
		panel_1.add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("Publicaciones");
		btnNewButton_2.setBounds(42, 237, 100, 40);
		panel_1.add(btnNewButton_2);

		JButton btnNewButton_3 = new JButton("Favoritos");
		btnNewButton_3.setBounds(42, 308, 100, 40);
		panel_1.add(btnNewButton_3);

		JButton btnNewButton_4 = new JButton("Votados");
		btnNewButton_4.setBounds(42, 384, 100, 40);
		panel_1.add(btnNewButton_4);

		JButton btnNewButton_5 = new JButton("Notificaciones");
		btnNewButton_5.setBounds(42, 460, 100, 40);
		panel_1.add(btnNewButton_5);

		JPanel panel = new JPanel();
		panel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(263, 45, 664, 567);
		getContentPane().add(panel);
		panel.setLayout(null);

		JPanel panel_4 = new JPanel();
		panel_4.setBounds(36, 21, 100, 100);
		panel.add(panel_4);
		panel_4.setBackground(new Color(255, 255, 255));
		panel_4.setLayout(null);
<<<<<<< Updated upstream
		
				
				// JLabel para mostrar la imagen
				lblImage = new JLabel("");
				lblImage.setBackground(new Color(192, 192, 192));
				lblImage.setBounds(0, 0, 100, 100);
				panel_4.add(lblImage);
=======
>>>>>>> Stashed changes

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
		lblNewLabel_4.setBounds(165, 64, 145, 47);
		panel.add(lblNewLabel_4);

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		panel_2.setBackground(new Color(207, 226, 243));
		panel_2.setBounds(79, 178, 493, 366);
		panel.add(panel_2);
		panel_2.setLayout(null);

		JLabel lblNewLabel = new JLabel("Nombre y apellidos");
		lblNewLabel.setBounds(28, 33, 109, 13);
		panel_2.add(lblNewLabel);

		txtNombre = new JTextField();
		txtNombre.setEditable(false);
		txtNombre.setBounds(28, 46, 432, 19);
		panel_2.add(txtNombre);
		txtNombre.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Direccion:");
		lblNewLabel_1.setBounds(28, 91, 84, 13);
		panel_2.add(lblNewLabel_1);

		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setBounds(28, 104, 432, 19);
		panel_2.add(textField_1);
		textField_1.setColumns(10);

		textField_2 = new JTextField();
		textField_2.setEditable(false);
		textField_2.setColumns(10);
		textField_2.setBounds(28, 163, 432, 19);
		panel_2.add(textField_2);

<<<<<<< Updated upstream
		textField_3 = new JTextField();
		textField_3.setEditable(false);
		textField_3.setColumns(10);
		textField_3.setBounds(28, 220, 432, 19);
		panel_2.add(textField_3);
=======
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(208, 224, 227));
		panel_3.setBounds(0, 0, 664, 67);
		panel.add(panel_3);
		panel_3.setLayout(null);
		
				// JLabel para mostrar la imagen
				lblImage = new JLabel("");
				lblImage.setBounds(210, -59, 100, 100);
				panel_3.add(lblImage);
				lblImage.setBackground(new Color(192, 192, 192));
>>>>>>> Stashed changes

		JLabel lblNewLabel_2 = new JLabel("CP");
		lblNewLabel_2.setBounds(28, 150, 45, 13);
		panel_2.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Localidad");
		lblNewLabel_3.setBounds(28, 208, 45, 13);
		panel_2.add(lblNewLabel_3);
		
				JPanel panel_3 = new JPanel();
				panel_3.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
				panel_3.setBackground(new Color(208, 224, 227));
				panel_3.setBounds(0, 0, 664, 67);
				panel.add(panel_3);
				panel_3.setLayout(null);
		
//		JLabel lblsave= new JLabel("");
//		lblsave.setBounds(200, 257, 200, 40);
//		panel.add(lblsave);
//		lblsave.setIcon(resizedSave);
		
		JLabel lblLogo = new JLabel("");
		lblLogo.setBounds(10, 14, 153, 83);
		panel_1.add(lblLogo);

		lblLogo.setIcon(resizedLogo);
		
		JButton btnNewButton_6 = new JButton("Publicar");
		btnNewButton_6.setBounds(842, 652, 85, 21);
		getContentPane().add(btnNewButton_6);
	}
}
