package App;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class PublicacionSi extends JFrame {
	private JTextField txtDescripcion;
	private JTextField txtDescripcion_1;
	private JTextField txtEstadofechacategorialocalizacion;
	private JTextField txtTituloPublicacion;


	public static void main(String[] args) {
		PlateaLanzadera window = new PlateaLanzadera();
		window.setVisible(true);
	}

	public PublicacionSi() {
		setBounds(100, 100, 1280, 720);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(207, 207, 243));
		panel.setBounds(313, 59, 664, 547);
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
		txtTituloPublicacion.setBounds(0, 0, 664, 19);
		panel.add(txtTituloPublicacion);
		txtTituloPublicacion.setColumns(10);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(62, 55, 532, 218);
		panel.add(panel_2);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(162, 196, 201));
		panel_1.setBounds(0, 0, 185, 683);
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
	}
}
