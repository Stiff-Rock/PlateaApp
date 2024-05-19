package App;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PlateaLanzadera extends JFrame {

	public static void main(String[] args) {
		JFrame lanzadera = new PlateaLanzadera();
	}

	public PlateaLanzadera() {
		setBounds(100, 100, 1024, 760);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

		JLabel lblTitle = new JLabel("Lanzadera de Platea App");
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 45));
		lblTitle.setBounds(224, 65, 560, 61);
		getContentPane().add(lblTitle);

		JButton btnRegistrar = new JButton("Registrar");
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame window = new Registrar();
			}
		});
		btnRegistrar.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnRegistrar.setBounds(73, 214, 238, 38);
		getContentPane().add(btnRegistrar);

		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame window = new Login();
			}
		});
		btnLogin.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnLogin.setBounds(384, 214, 238, 38);
		getContentPane().add(btnLogin);

		JButton btnReestablecer = new JButton("Reestablecer");
		btnReestablecer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame window = new Reestablecer();
			}
		});
		btnReestablecer.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnReestablecer.setBounds(695, 214, 238, 38);
		getContentPane().add(btnReestablecer);
		
		JButton btnHome = new JButton("Home");
		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//JFrame window = new ();
			}
		});
		btnHome.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnHome.setBounds(73, 340, 238, 38);
		getContentPane().add(btnHome);

		JButton btnAdmin = new JButton("Admin");
		btnAdmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//JFrame window = new ();
			}
		});
		btnAdmin.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnAdmin.setBounds(384, 340, 238, 38);
		getContentPane().add(btnAdmin);
		setVisible(true);

		JButton btnFavoritos = new JButton("Favoritos");
		btnFavoritos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//JFrame window = new ();
			}
		});
		btnFavoritos.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnFavoritos.setBounds(695, 340, 238, 38);
		getContentPane().add(btnFavoritos);

		JButton btnVotados = new JButton("Votados");
		btnVotados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//JFrame window = new ();
			}
		});
		btnVotados.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnVotados.setBounds(73, 466, 238, 38);
		getContentPane().add(btnVotados);

		JButton btnMispublicaciones = new JButton("MisPublicaciones");
		btnMispublicaciones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//JFrame window = new ();
			}
		});
		btnMispublicaciones.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnMispublicaciones.setBounds(384, 466, 238, 38);
		getContentPane().add(btnMispublicaciones);

		JButton btnPublicacion = new JButton("Publicacion");
		btnPublicacion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame window = new Publicacion();
			}
		});
		btnPublicacion.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnPublicacion.setBounds(695, 466, 238, 38);
		getContentPane().add(btnPublicacion);

		JButton btnPublicar = new JButton("Publicar");
		btnPublicar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame window = new Publicar();
			}
		});
		btnPublicar.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnPublicar.setBounds(177, 592, 238, 38);
		getContentPane().add(btnPublicar);

		JButton btnPerfil = new JButton("Perfil");
		btnPerfil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame window = new Perfil();
			}
		});
		btnPerfil.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnPerfil.setBounds(592, 592, 238, 38);
		getContentPane().add(btnPerfil);

	}
}