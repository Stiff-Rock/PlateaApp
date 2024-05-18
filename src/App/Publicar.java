package App;

import java.awt.Color;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class Publicar extends JFrame {
	private JTextField txtDescripcion;
	private JTextField txtDescripcion_1;
	private JTextField txtEstadofechacategorialocalizacion;
	private JTextField txtTituloPublicacion;
	private JTextField txtTitulo;


	public static void main(String[] args) {
		PlateaLanzadera window = new PlateaLanzadera();
		window.setVisible(true);
	}

	public Publicar() {
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
		txtDescripcion_1.setText("Descripcion del problema");
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
		panel_2.setBounds(62, 129, 532, 144);
		panel.add(panel_2);
		
		JButton btnNewButton_6 = new JButton("Publicar");
		btnNewButton_6.setBounds(513, 516, 85, 21);
		panel.add(btnNewButton_6);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Categorias", "", "Derrumbamiento", "Bache ", "Arbol caido"}));
		comboBox.setToolTipText("");
		comboBox.setBounds(72, 516, 99, 21);
		panel.add(comboBox);
		
		txtTitulo = new JTextField();
		txtTitulo.setText("Titulo");
		txtTitulo.setBounds(62, 71, 532, 28);
		panel.add(txtTitulo);
		txtTitulo.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(162, 196, 201));
		panel_1.setBounds(0, 0, 185, 683);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.setBounds(42, 107, 100, 40);
		panel_1.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("New button");
		btnNewButton_1.setBounds(42, 173, 100, 40);
		panel_1.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("New button");
		btnNewButton_2.setBounds(42, 237, 100, 40);
		panel_1.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("New button");
		btnNewButton_3.setBounds(42, 308, 100, 40);
		panel_1.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("New button");
		btnNewButton_4.setBounds(42, 384, 100, 40);
		panel_1.add(btnNewButton_4);
		
		JButton btnNewButton_5 = new JButton("New button");
		btnNewButton_5.setBounds(42, 460, 100, 40);
		panel_1.add(btnNewButton_5);
	}
}
