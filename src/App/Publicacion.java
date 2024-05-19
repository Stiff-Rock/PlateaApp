package App;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Image;

import javax.swing.JScrollPane;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.SwingConstants;

public class Publicacion extends JFrame {
	private JTextField txtDescripcion;
	private JTextField txtDescripcion_1;
	private JTextField txtEstadofechacategorialocalizacion;
	private JTextField txtTituloPublicacion;

	public Publicacion() {
		
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
		panel.setBackground(new Color(207, 207, 243));
		panel.setBounds(267, 42, 664, 578);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(62, 357, 532, 149);
		panel.add(scrollPane);
		
		txtDescripcion = new JTextField();
		scrollPane.setViewportView(txtDescripcion);
		txtDescripcion.setColumns(10);
		
		txtDescripcion_1 = new JTextField();
		txtDescripcion_1.setText("Descripcion");
		scrollPane.setColumnHeaderView(txtDescripcion_1);
		txtDescripcion_1.setColumns(10);
		
		txtEstadofechacategorialocalizacion = new JTextField();
		txtEstadofechacategorialocalizacion.setText("Estado-Fecha-Categoria-Localizacion");
		txtEstadofechacategorialocalizacion.setBounds(62, 298, 532, 28);
		panel.add(txtEstadofechacategorialocalizacion);
		txtEstadofechacategorialocalizacion.setColumns(10);
		
		txtTituloPublicacion = new JTextField();
		txtTituloPublicacion.setText("Titulo publicacion");
		txtTituloPublicacion.setBounds(0, 0, 664, 28);
		panel.add(txtTituloPublicacion);
		txtTituloPublicacion.setColumns(10);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(72, 58, 532, 218);
		panel.add(panel_2);
		
		JButton btnNewButton_6 = new JButton("Publicar");
		btnNewButton_6.setIcon(mas);
		btnNewButton_6.setBounds(787, 646, 144, 35);
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
