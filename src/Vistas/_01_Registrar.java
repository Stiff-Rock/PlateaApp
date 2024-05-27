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
import javax.swing.border.MatteBorder;

import Controlador.Controlador;
import Modelo.Modelo;

/**
 * Vista para el registro de nuevos usuarios.
 * En esta vista sera donde los usuarios meteran su informacion para el registro
 * 
 * @autor Yago Pernas
 */
public class _01_Registrar extends JFrame implements Vista {
    private Controlador controlador;
    private String datosRegistro[] = new String[12];
    private JTextField txtNombre;
    private JTextField txtApellido;
    private JTextField txtRespuesta;
    private Modelo modelo;
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

    // Metodos para establecer el modelo y el controlador de la vista
    public void setModelo(Modelo modelo) {
        this.modelo = modelo;
    }

    public void setControlador(Controlador controlador) {
        this.controlador = controlador;
    }

    // Metodo para establecer el captcha
    public void setCaptcha(String captcha) {
        lblCaptcha.setText(captcha);
    }
    
    // Metodo para obtener el captcha ingresado por el usuario
    public String getCaptcha() {
        return txtCaptcha.getText();
    }
    
    // Metodo para obtener los datos de registro ingresados por el usuario
    public String[] getDatosRegistro() {
        datosRegistro[0] = txtNickname.getText();
        datosRegistro[1] = txtApellido.getText();
        datosRegistro[2] = txtNombre.getText();
        datosRegistro[3] = txtCP.getText();
        //@To Do Guardar hasheadas en el futuro
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

    // Metodo para actualizar la vista, y informacion de errores de registro por pantalla
    public void actualizar() {
        String resultado = modelo.getResultado();
        if (resultado.equals("Correcto")) {
            controlador.cambiarVentana(1, 0);
        } else {
            switch (resultado) {
                case "Contraseña":
                    lblWarning.setText("Las contraseñas no coinciden");
                    break;
                case "Politica":
                    lblWarning.setText("No has aceptado la política de privacidad");
                    break;
                case "Mayor":
                    lblWarning.setText("Menores de 14 años no están permitidos");
                    break;
                case "Captcha":
                    lblWarning.setText("Captcha incorrecto");
                    break;
                case "Pregunta":
                    lblWarning.setText("No has escogido una pregunta");
                    break;
                case "Respuesta":
                    lblWarning.setText("No has respondido a la pregunta");
                    break;
                case "Nickname":
                    lblWarning.setText("Nickname no disponible");
                    break;
                case "Faltan":
                    lblWarning.setText("Es necesario rellenar todos los campos");
                    break;
                default:
                    lblWarning.setText("Error desconocido");
            }
        }
    }

    /**
     * Constructor inicial
     * Inicializa los componentes y la disposicion de la ventana de registro.
     */
    public _01_Registrar() {
        getContentPane().setLocation(-260, -138);
        setBounds(100, 100, 1024, 760);
        getContentPane().setLayout(null);
        ImageIcon resizedLogo = new ImageIcon(new ImageIcon(this.getClass().getResource("/logo.png")).getImage()
                .getScaledInstance(321, 113, Image.SCALE_SMOOTH));

        // Panel inferior con enlace a iniciar sesión si ya tienes cuenta
        JPanel bottomPanel = new JPanel();
        bottomPanel.setBorder(new MatteBorder(1, 1, 1, 1, new Color(0, 0, 0)));
        bottomPanel.setBounds(260, 632, 488, 70);
        bottomPanel.setBackground(new Color(208, 224, 227));
        getContentPane().add(bottomPanel);
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
        registerPanel.setBorder(new MatteBorder(1, 1, 1, 1, new Color(0, 0, 0)));
        registerPanel.setBounds(260, 130, 488, 484);
        registerPanel.setBackground(new Color(207, 226, 243));
        getContentPane().add(registerPanel);
        registerPanel.setLayout(null);

        txtNombre = new JTextField();
        txtNombre.setFont(new Font("Tahoma", Font.PLAIN, 18));
        txtNombre.setBounds(34, 28, 192, 28);
        registerPanel.add(txtNombre);
        txtNombre.setColumns(10);

        txtApellido = new JTextField();
        txtApellido.setFont(new Font("Tahoma", Font.PLAIN, 18));
        txtApellido.setColumns(10);
        txtApellido.setBounds(260, 26, 192, 28);
        registerPanel.add(txtApellido);

        txtNickname = new JTextField();
        txtNickname.setFont(new Font("Tahoma", Font.PLAIN, 18));
        txtNickname.setColumns(10);
        txtNickname.setBounds(34, 84, 192, 28);
        registerPanel.add(txtNickname);

        txtCP = new JTextField();
        txtCP.setFont(new Font("Tahoma", Font.PLAIN, 18));
        txtCP.setColumns(10);
        txtCP.setBounds(260, 84, 192, 28);
        registerPanel.add(txtCP);

        txtRespuesta = new JTextField();
        txtRespuesta.setFont(new Font("Tahoma", Font.PLAIN, 18));
        txtRespuesta.setColumns(10);
        txtRespuesta.setBounds(34, 316, 418, 28);
        registerPanel.add(txtRespuesta);

        checkAdmin = new Checkbox("¿Eres admin?");
        checkAdmin.setFont(new Font("Dialog", Font.PLAIN, 14));
        checkAdmin.setBounds(34, 119, 107, 22);
        registerPanel.add(checkAdmin);

        cmbPregunta = new JComboBox();
        cmbPregunta.setModel(new DefaultComboBoxModel(new String[] { 
            "Elige una pregunta de seguridad:",
            "¿Cuál es el nombre del colegio de tu infancia?", 
            "¿Cuál es el nombre de tu primera mascota?",
            "¿Cuál es la ciudad en la que se conocieron tus padres?", 
            "¿Cuál fue tu primera videoconsola?",
            "¿Cuál es el año de nacimiento de tu abuelo paterno?" 
        }));
        cmbPregunta.setFont(new Font("Tahoma", Font.PLAIN, 18));
        cmbPregunta.setBounds(34, 260, 418, 28);
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
        checkPolitica.setBounds(34, 351, 222, 32);
        registerPanel.add(checkPolitica);

        checkMayor = new Checkbox("Soy mayor de 14 años");
        checkMayor.setFont(new Font("Dialog", Font.PLAIN, 10));
        checkMayor.setBounds(34, 390, 146, 22);
        registerPanel.add(checkMayor);

        // Panel para el captcha
        JPanel captchaPanel = new JPanel();
        captchaPanel.setBackground(new Color(255, 255, 255));
        captchaPanel.setBorder(new MatteBorder(1, 1, 1, 1, new Color(0, 0, 0)));
        captchaPanel.setBounds(275, 351, 176, 88);
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
        lblWarning.setBounds(34, 425, 222, 14);
        registerPanel.add(lblWarning);

        // Labels para los campos de entrada
        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(34, 7, 74, 14);
        registerPanel.add(lblNombre);

        JLabel lblApellido = new JLabel("Apellido:");
        lblApellido.setBounds(260, 8, 74, 14);
        registerPanel.add(lblApellido);

        JLabel lblUsr = new JLabel("Nickname:");
        lblUsr.setBounds(34, 63, 81, 14);
        registerPanel.add(lblUsr);

        JLabel lblCp = new JLabel("CP:");
        lblCp.setBounds(260, 66, 81, 14);
        registerPanel.add(lblCp);

        JLabel lblPwd = new JLabel("Contraseña");
        lblPwd.setBounds(34, 148, 81, 14);
        registerPanel.add(lblPwd);

        JLabel lblPwd_1 = new JLabel("Repetir contraseña:");
        lblPwd_1.setBounds(34, 204, 146, 14);
        registerPanel.add(lblPwd_1);

        JLabel lblRespuesta = new JLabel("Respuesta:");
        lblRespuesta.setBounds(34, 295, 81, 14);
        registerPanel.add(lblRespuesta);

        txtPwd1 = new JPasswordField();
        txtPwd1.setBounds(34, 169, 418, 28);
        registerPanel.add(txtPwd1);

        txtPwd2 = new JPasswordField();
        txtPwd2.setBounds(34, 225, 418, 28);
        registerPanel.add(txtPwd2);

        // Logo de la aplicación
        JLabel lblLogo = new JLabel("");
        lblLogo.setBounds(343, 10, 321, 113);
        getContentPane().add(lblLogo);
        lblLogo.setIcon(resizedLogo);
    }
}
