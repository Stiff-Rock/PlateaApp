package App;

import java.awt.Color;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Checkbox;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;

public class Registrar extends JFrame {
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtNickname;
	private JTextField txtContraseña1;
	private JTextField txtContraseña2;
	private JTextField txtCP;
	private JTextField txtRespuesta;
	private JTextField textField;
	private JTextField txtCodigoDeAdministrador;

	public Registrar() {
		getContentPane().setLocation(-260, -138);
		setBounds(100, 100, 1024, 760);
		getContentPane().setLayout(null);
		ImageIcon resizedLogo = new ImageIcon(new ImageIcon(this.getClass().getResource("/logo.png")).getImage()
				.getScaledInstance(321, 113, Image.SCALE_SMOOTH));

		JPanel bottomPanel = new JPanel();
		bottomPanel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		bottomPanel.setBounds(260, 632, 488, 70);
		bottomPanel.setBackground(new Color(208, 224, 227));
		getContentPane().add(bottomPanel);
		bottomPanel.setLayout(null);

		JLabel lblBottom = new JLabel("¿Ya tienes cuenta? ");
		lblBottom.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblBottom.setBounds(77, 28, 153, 14);
		bottomPanel.add(lblBottom);

		JLabel lblBottom_1 = new JLabel("|");
		lblBottom_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblBottom_1.setBounds(240, 28, 7, 14);
		bottomPanel.add(lblBottom_1);

		JLabel lblLink = new JLabel("Iniciar Sesión");
		lblLink.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblLink.setBounds(267, 28, 105, 14);
		bottomPanel.add(lblLink);

		JPanel registerPanel = new JPanel();
		registerPanel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		registerPanel.setBounds(260, 149, 488, 465);
		registerPanel.setBackground(new Color(207, 226, 243));
		getContentPane().add(registerPanel);
		registerPanel.setLayout(null);

		txtNombre = new JTextField();
		txtNombre.setText("Nombre");
		txtNombre.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtNombre.setBounds(34, 18, 192, 28);
		registerPanel.add(txtNombre);
		txtNombre.setColumns(10);

		txtApellido = new JTextField();
		txtApellido.setText("Apellido");
		txtApellido.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtApellido.setColumns(10);
		txtApellido.setBounds(260, 18, 192, 28);
		registerPanel.add(txtApellido);

		txtNickname = new JTextField();
		txtNickname.setText("Nickname");
		txtNickname.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtNickname.setColumns(10);
		txtNickname.setBounds(34, 64, 192, 28);
		registerPanel.add(txtNickname);

		txtCP = new JTextField();
		txtCP.setText("Códgo postal");
		txtCP.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtCP.setColumns(10);
		txtCP.setBounds(260, 64, 192, 28);
		registerPanel.add(txtCP);

		txtContraseña1 = new JTextField();
		txtContraseña1.setText("Contraseña");
		txtContraseña1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtContraseña1.setColumns(10);
		txtContraseña1.setBounds(34, 156, 418, 28);
		registerPanel.add(txtContraseña1);

		txtContraseña2 = new JTextField();
		txtContraseña2.setText("Respuesta");
		txtContraseña2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtContraseña2.setColumns(10);
		txtContraseña2.setBounds(34, 294, 418, 28);
		registerPanel.add(txtContraseña2);

		txtRespuesta = new JTextField();
		txtRespuesta.setText("Repetir contraseña");
		txtRespuesta.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtRespuesta.setColumns(10);
		txtRespuesta.setBounds(34, 202, 418, 28);
		registerPanel.add(txtRespuesta);

		Checkbox checkAdmin = new Checkbox("¿Eres admin?");
		checkAdmin.setFont(new Font("Dialog", Font.PLAIN, 14));
		checkAdmin.setBounds(34, 113, 107, 22);
		registerPanel.add(checkAdmin);

		JComboBox cmbPregunta = new JComboBox();
		cmbPregunta.setModel(new DefaultComboBoxModel(new String[] {"Elige una pregunta de seguridad:", "¿Cuál es el nombre de tu primera mascota? ", "¿Cuál es el nombre de tu escuela primaria? ", "¿En qué ciudad naciste? ", "¿Cuál es el nombre de tu mejor amigo de la infancia? ", "¿Cuál es el segundo nombre de tu padre? ", "¿Cuál fue tu primer trabajo?"}));
		cmbPregunta.setFont(new Font("Tahoma", Font.PLAIN, 18));
		cmbPregunta.setBounds(34, 248, 418, 28);
		registerPanel.add(cmbPregunta);

		JButton btnRegister = new JButton("Crear Cuenta");
		btnRegister.setBounds(34, 424, 124, 23);
		registerPanel.add(btnRegister);

		Checkbox checkPolitica = new Checkbox("He leído y acepto la política de privacidad.");
		checkPolitica.setFont(new Font("Dialog", Font.PLAIN, 10));
		checkPolitica.setBounds(34, 330, 222, 32);
		registerPanel.add(checkPolitica);

		Checkbox checkMayor = new Checkbox("Soy mayor de 14 años");
		checkMayor.setFont(new Font("Dialog", Font.PLAIN, 10));
		checkMayor.setBounds(34, 368, 124, 22);
		registerPanel.add(checkMayor);

		JPanel captchaPanel = new JPanel();
		captchaPanel.setBackground(new Color(255, 255, 255));
		captchaPanel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		captchaPanel.setBounds(276, 340, 176, 99);
		captchaPanel.setBackground(new Color(162, 196, 201));
		registerPanel.add(captchaPanel);

		ImageIcon resizedCaptcha = new ImageIcon(new ImageIcon(this.getClass().getResource("/textoCaptcha.png")).getImage()
				.getScaledInstance(141, 41, Image.SCALE_SMOOTH));
		
		JLabel lblCaptchaTitle = new JLabel("Completa el sigueinte Captcha:");
		captchaPanel.add(lblCaptchaTitle);
		
		JLabel lblCaptcha = new JLabel("");
		captchaPanel.add(lblCaptcha);
		lblCaptcha.setIcon(resizedCaptcha);
		
		textField = new JTextField();
		captchaPanel.add(textField);
		textField.setColumns(10);
		
		txtCodigoDeAdministrador = new JTextField();
		txtCodigoDeAdministrador.setText("Código de Admin");
		txtCodigoDeAdministrador.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtCodigoDeAdministrador.setColumns(10);
		txtCodigoDeAdministrador.setBounds(260, 110, 192, 28);
		registerPanel.add(txtCodigoDeAdministrador);
		
		JLabel lblLogo = new JLabel("");
		lblLogo.setBounds(343, 18, 321, 113);
		getContentPane().add(lblLogo);

		lblLogo.setIcon(resizedLogo);
		
		setVisible(true);
	}
}
