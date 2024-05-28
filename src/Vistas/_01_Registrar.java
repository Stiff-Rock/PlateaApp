package Vistas;

import java.awt.Checkbox;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

import Controlador.Controlador;
import Modelo.Modelo;

//@Autor Yago Pernas
public class _01_Registrar extends JFrame implements Vista {
	private JPanel mainPanel;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtRespuesta;
	private JLabel lblWarning;
	private JLabel lblCaptcha;
	private Checkbox checkPolitica;
	private Checkbox checkMayor;
	private JComboBox cmbPregunta;
	private JTextField txtNickname;
	private JTextField txtCP;
	private Checkbox checkAdmin;
	private JPasswordField txtPwd1;
	private JPasswordField txtPwd2;
	private JTextField txtCaptcha;

	private String[] datosRegistro = new String[12];
	private Controlador controlador;

	public void setControlador(Controlador controlador) {
		this.controlador = controlador;
	}

	public void setCaptcha(String captcha) {
		lblCaptcha.setText(captcha);
	}

	public String getCaptcha() {
		String captcha = txtCaptcha.getText();
		return captcha;
	}

	public String[] getDatosRegistro() {
		datosRegistro[0] = txtNickname.getText();
		datosRegistro[1] = txtApellido.getText();
		datosRegistro[2] = txtNombre.getText();
		datosRegistro[3] = txtCP.getText();
		datosRegistro[4] = String.valueOf(txtPwd1.getPassword());
		datosRegistro[5] = String.valueOf(txtPwd2.getPassword());
		datosRegistro[6] = checkAdmin.getState() ? "S" : "N";
		datosRegistro[7] = String.valueOf(cmbPregunta.getSelectedIndex());
		datosRegistro[8] = txtRespuesta.getText();
		datosRegistro[9] = checkPolitica.getState() ? "S" : "N";
		datosRegistro[10] = checkMayor.getState() ? "S" : "N";
		datosRegistro[11] = txtCaptcha.getText();

		return datosRegistro;
	}

	public void mostrarWarning(String mensaje) {
		lblWarning.setText(mensaje);
	}

	public void cargarPreguntas(DefaultComboBoxModel preguntas) {
		cmbPregunta.setModel(preguntas);
	}

	public _01_Registrar() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Registro");

		// Panel principal que contendrá todos los componentes
		setBounds(480, 150, 1024, 760);
		mainPanel = new JPanel();
		setContentPane(mainPanel);
		mainPanel.setLayout(null);
		mainPanel.setForeground(new Color(162, 196, 201));

		ImageIcon resizedLogo = new ImageIcon(new ImageIcon(this.getClass().getResource("/img/logo.png")).getImage()
				.getScaledInstance(321, 113, Image.SCALE_SMOOTH));

		JPanel bottomPanel = new JPanel();
		bottomPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		bottomPanel.setBounds(260, 636, 488, 70);
		bottomPanel.setBackground(new Color(208, 224, 227));
		mainPanel.add(bottomPanel);
		bottomPanel.setLayout(null);

		JLabel lblBottom = new JLabel("¿Ya tienes cuenta? ");
		lblBottom.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblBottom.setBounds(72, 28, 158, 14);
		bottomPanel.add(lblBottom);

		JLabel lblBottom_1 = new JLabel("|");
		lblBottom_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblBottom_1.setBounds(240, 28, 7, 14);
		bottomPanel.add(lblBottom_1);

		JLabel lblLink = new JLabel("Iniciar Sesión");
		lblLink.setCursor(new Cursor(Cursor.HAND_CURSOR));
		lblLink.setForeground(new Color(0, 0, 255));
		lblLink.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				controlador.cambiarVentana(1, 0);
			}
		});
		lblLink.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblLink.setBounds(267, 28, 137, 14);
		bottomPanel.add(lblLink);

		JPanel registerPanel = new JPanel();
		registerPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		registerPanel.setBounds(260, 134, 488, 484);
		registerPanel.setBackground(new Color(207, 226, 243));
		mainPanel.add(registerPanel);
		registerPanel.setLayout(null);

		txtNombre = new JTextField();
		txtNombre.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtNombre.setBounds(34, 47, 192, 28);
		registerPanel.add(txtNombre);
		txtNombre.setColumns(10);

		txtApellido = new JTextField();
		txtApellido.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtApellido.setColumns(10);
		txtApellido.setBounds(260, 45, 192, 28);
		registerPanel.add(txtApellido);

		txtNickname = new JTextField();
		txtNickname.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtNickname.setColumns(10);
		txtNickname.setBounds(34, 103, 192, 28);
		registerPanel.add(txtNickname);

		txtCP = new JTextField();
		txtCP.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtCP.setColumns(10);
		txtCP.setBounds(260, 103, 192, 28);
		registerPanel.add(txtCP);

		txtRespuesta = new JTextField();
		txtRespuesta.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtRespuesta.setColumns(10);
		txtRespuesta.setBounds(34, 333, 418, 28);
		registerPanel.add(txtRespuesta);

		checkAdmin = new Checkbox("¿Eres admin?");
		checkAdmin.setFont(new Font("Dialog", Font.PLAIN, 12));
		checkAdmin.setBounds(34, 138, 107, 22);
		registerPanel.add(checkAdmin);

		// TODO INSERTAR POR BASE DE DATOS
		cmbPregunta = new JComboBox();
		cmbPregunta.setFont(new Font("Tahoma", Font.PLAIN, 18));
		cmbPregunta.setBounds(34, 281, 418, 28);
		registerPanel.add(cmbPregunta);

		JButton btnRegister = new JButton("Crear Cuenta");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.singIn();
			}
		});
		btnRegister.setBounds(34, 450, 124, 23);
		registerPanel.add(btnRegister);

		checkPolitica = new Checkbox("He leído y acepto la política de privacidad.");
		checkPolitica.setFont(new Font("Dialog", Font.PLAIN, 10));
		checkPolitica.setBounds(34, 370, 222, 32);
		registerPanel.add(checkPolitica);

		checkMayor = new Checkbox("Soy mayor de 14 años");
		checkMayor.setFont(new Font("Dialog", Font.PLAIN, 10));
		checkMayor.setBounds(34, 409, 146, 22);
		registerPanel.add(checkMayor);

		JPanel captchaPanel = new JPanel();
		captchaPanel.setBackground(new Color(255, 255, 255));
		captchaPanel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		captchaPanel.setBounds(275, 370, 176, 88);
		captchaPanel.setBackground(new Color(162, 196, 201));
		registerPanel.add(captchaPanel);

		captchaPanel.setLayout(null);

		JLabel lblCaptchaTitle = new JLabel("Completa el Captcha:");
		lblCaptchaTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblCaptchaTitle.setBounds(13, 6, 149, 14);
		captchaPanel.add(lblCaptchaTitle);

		txtCaptcha = new JTextField();
		txtCaptcha.setBounds(44, 57, 86, 20);
		captchaPanel.add(txtCaptcha);
		txtCaptcha.setColumns(10);

		lblCaptcha = new JLabel("");
		lblCaptcha.setHorizontalAlignment(SwingConstants.CENTER);
		lblCaptcha.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCaptcha.setBounds(23, 32, 129, 14);
		captchaPanel.add(lblCaptcha);

		lblWarning = new JLabel("");
		lblWarning.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblWarning.setForeground(new Color(255, 0, 0));
		lblWarning.setBounds(34, 428, 222, 22);
		registerPanel.add(lblWarning);

		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(34, 26, 74, 14);
		registerPanel.add(lblNombre);

		JLabel lblApellido = new JLabel("Apellido:");
		lblApellido.setBounds(260, 27, 74, 14);
		registerPanel.add(lblApellido);

		JLabel lblUsr = new JLabel("Nickname:");
		lblUsr.setBounds(34, 82, 81, 14);
		registerPanel.add(lblUsr);

		JLabel lblCp = new JLabel("CP:");
		lblCp.setBounds(260, 85, 81, 14);
		registerPanel.add(lblCp);

		JLabel lblPwd = new JLabel("Contraseña");
		lblPwd.setBounds(34, 165, 81, 14);
		registerPanel.add(lblPwd);

		JLabel lblPwd_1 = new JLabel("Repetir contraseña:");
		lblPwd_1.setBounds(34, 219, 146, 14);
		registerPanel.add(lblPwd_1);

		JLabel lblRespuesta = new JLabel("Respuesta:");
		lblRespuesta.setBounds(34, 314, 81, 14);
		registerPanel.add(lblRespuesta);

		txtPwd1 = new JPasswordField();
		txtPwd1.setBounds(34, 181, 418, 28);
		registerPanel.add(txtPwd1);

		txtPwd2 = new JPasswordField();
		txtPwd2.setBounds(34, 236, 418, 28);
		registerPanel.add(txtPwd2);

		JLabel lblLogo = new JLabel("");
		lblLogo.setBounds(343, 11, 321, 113);
		mainPanel.add(lblLogo);

		lblLogo.setIcon(resizedLogo);
	}
}
