package App;

import javax.swing.JFrame;

public class PlateaLanzadera extends JFrame {

	public static void main(String[] args) {
		PlateaLanzadera window = new PlateaLanzadera();
		window.setVisible(true);
	}

	public PlateaLanzadera() {
		setBounds(100, 100, 1280, 720);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
	}

}