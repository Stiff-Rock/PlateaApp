package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ConexionOracle {
	private String login = "SYSTEM";
	private String pwd = "0205";
	private String url = "jdbc:oracle:thin:@localhost:1521:XE";
	private Connection conexion;

	public ConexionOracle() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conexion = DriverManager.getConnection(url, login, pwd);
			System.out.println("-> Conexion con ORACLE establecida");
		} catch (ClassNotFoundException e) {
			System.out.println("Driver JDBC No encontrado");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Error al conectarse a la BD");
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("Error general de Conexion");
			e.printStackTrace();
		}
	}

	public String[] verificarUsuario(String usuario, String contraseña) {
		String[] usuarioEncontrado = new String[2];

		String query = "SELECT NICK, PWD FROM platea.usuario WHERE NICK = ? AND PWD = ?";
		try (PreparedStatement statement = conexion.prepareStatement(query)) {
			statement.setString(1, usuario);
			statement.setString(2, contraseña);
			ResultSet resultSet = statement.executeQuery();

			if (resultSet.next()) {
				usuarioEncontrado[0] = resultSet.getString("NICK");
				usuarioEncontrado[1] = resultSet.getString("PWD");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return usuarioEncontrado;
	}

	public boolean comprobarDisponibilidadNick(String nickname) {
		boolean isNickAvailable = true;

		String query = "SELECT COUNT(*) AS total FROM platea.usuario WHERE NICK = ?";
		try (PreparedStatement statement = conexion.prepareStatement(query)) {
			statement.setString(1, nickname);
			ResultSet resultSet = statement.executeQuery();

			if (resultSet.next()) {
				int totalUsuarios = resultSet.getInt("total");
				// Si el total de usuarios con ese nickname es mayor que 0 (1), ya está en uso.
				if (totalUsuarios > 0) {
					isNickAvailable = false;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return isNickAvailable;
	}

	public String[] obtenerPreguntasSeguridad() {
		ArrayList<String> preguntasList = new ArrayList<>();
		preguntasList.add("Elige una pregunta de seguridad");
		String query = "SELECT CUESTION FROM platea.pregunta";

		try (PreparedStatement statement = conexion.prepareStatement(query);
				ResultSet resultSet = statement.executeQuery()) {

			while (resultSet.next()) {
				String cuestion = resultSet.getString("CUESTION");
				preguntasList.add(cuestion);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		String[] preguntasObject = preguntasList.toArray(new String[preguntasList.size()]);
		return preguntasObject;
	}

	public void crearUsuario(String[] datosRegistro) {
		String query = "INSERT INTO platea.usuario (NICK, APELLIDO, NOMBRE, CP, PWD, ADMIN, PREGUNTA_CODIGO, RESPUESTA) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		try (PreparedStatement statement = conexion.prepareStatement(query)) {
			statement.setString(1, datosRegistro[0]);
			statement.setString(2, datosRegistro[1]);
			statement.setString(3, datosRegistro[2]);
			statement.setString(4, datosRegistro[3]);
			statement.setString(5, datosRegistro[4]);
			statement.setString(6, datosRegistro[6]);
			statement.setString(7, datosRegistro[7]);
			statement.setString(8, datosRegistro[8]);

			int rowsInserted = statement.executeUpdate();
			if (rowsInserted > 0) {
				System.out.println("El usuario ha sido creado correctamente.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

    public String[] cargarUsuario(String nick) {
        String[] datos = new String[9];
        
        String sql = "SELECT NICK, APELLIDO, NOMBRE, CP, PWD, ADMIN, FOTO, PREGUNTA_CODIGO, RESPUESTA FROM platea.usuario WHERE NICK = ?";
        
        try (PreparedStatement pstmt = conexion.prepareStatement(sql)) {
            pstmt.setString(1, nick);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    datos[0] = rs.getString("NICK");
                    datos[1] = rs.getString("APELLIDO");
                    datos[2] = rs.getString("NOMBRE");
                    datos[3] = rs.getString("CP");
                    datos[4] = rs.getString("PWD");
                    datos[5] = rs.getString("ADMIN");
                    datos[6] = rs.getBytes("FOTO") != null ? new String(rs.getBytes("FOTO")) : "";
                    datos[7] = rs.getString("PREGUNTA_CODIGO");
                    datos[8] = rs.getString("RESPUESTA");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return datos;
    }

	public String obtenerPreguntaUsuario(String nombreUsuario) {
		String pregunta = "";
		String query = "SELECT pregunta.CUESTION " + "FROM USUARIO "
				+ "INNER JOIN PREGUNTA ON USUARIO.PREGUNTA_CODIGO = PREGUNTA.CODIGO " + "WHERE USUARIO.NICK = ?";

		try (PreparedStatement statement = conexion.prepareStatement(query)) {
			statement.setString(1, nombreUsuario);

			try (ResultSet resultSet = statement.executeQuery()) {
				if (resultSet.next()) {
					pregunta = resultSet.getString("CUESTION");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return pregunta;
	}

	public void terminar() {
		try {
			conexion.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
