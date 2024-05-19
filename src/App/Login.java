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

public class Login extends JFrame {
	private JTextField txtNickname;
	private JTextField txtContraseña1;

	public static void main(String[] args) {
		Login window = new Login();
		window.setVisible(true);
	}

	public Login() {
		getContentPane().setLocation(-2, 0);
		setBounds(100, 100, 1024, 760);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

		JPanel bottomPanel = new JPanel();
		bottomPanel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		bottomPanel.setBounds(259, 588, 488, 70);
		bottomPanel.setBackground(new Color(208, 224, 227));
		getContentPane().add(bottomPanel);
		bottomPanel.setLayout(null);

		JLabel lblBottom = new JLabel("¿No tienes cuenta?");
		lblBottom.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblBottom.setBounds(77, 28, 153, 14);
		bottomPanel.add(lblBottom);

		JLabel lblBottom_1 = new JLabel("|");
		lblBottom_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblBottom_1.setBounds(240, 28, 7, 14);
		bottomPanel.add(lblBottom_1);

		JLabel lblLink = new JLabel("Crear cuenta");
		lblLink.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblLink.setBounds(267, 28, 105, 14);
		bottomPanel.add(lblLink);

		JPanel registerPanel = new JPanel();
		registerPanel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		registerPanel.setBounds(259, 239, 488, 286);
		registerPanel.setBackground(new Color(207, 226, 243));
		getContentPane().add(registerPanel);
		registerPanel.setLayout(null);

		txtNickname = new JTextField();
		txtNickname.setText("Nickname");
		txtNickname.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtNickname.setColumns(10);
		txtNickname.setBounds(36, 105, 418, 28);
		registerPanel.add(txtNickname);

		txtContraseña1 = new JTextField();
		txtContraseña1.setText("Contraseña");
		txtContraseña1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtContraseña1.setColumns(10);
		txtContraseña1.setBounds(36, 166, 418, 28);
		registerPanel.add(txtContraseña1);

		JButton btnLogin = new JButton("Acceder");
		btnLogin.setBounds(36, 227, 124, 23);
		registerPanel.add(btnLogin);
		
		JLabel lblTitle = new JLabel("Iniciar sesión");
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 32));
		lblTitle.setBounds(151, 33, 185, 39);
		registerPanel.add(lblTitle);
		
		JLabel lblForgotPWD = new JLabel("Olvidé mi contraseña");
		lblForgotPWD.setBounds(330, 231, 124, 14);
		registerPanel.add(lblForgotPWD);
		
		JLabel lblLogo = new JLabel("");
		lblLogo.setBounds(343, 63, 321, 113);
		getContentPane().add(lblLogo);
		
		ImageIcon resizedLogo = new ImageIcon(new ImageIcon(this.getClass().getResource("/logo.png")).getImage()
				.getScaledInstance(321, 113, Image.SCALE_SMOOTH));
		lblLogo.setIcon(resizedLogo);
	}
}
