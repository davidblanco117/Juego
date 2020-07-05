package UI;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;


//import com.google.gson.Gson;

public class Servidor implements Runnable {

	
	ServerSocket server;
	private int puertoClienteAServidor = 9998; // El puerto por el que acceden los datos
	private int puertoServidorACliente = 9996;
	
	private JPanel contentPane;
	private DataInputStream entrada;
	private Socket socket; // Prepara un puente para conectarse a un serverSocket
	private int puertoParaConexionesActivas = 9994;
	private int puertoParaIngresoDeUsuarios = 9890;
	private int puertoParaValidacionDeUsuarios = 9894;
	private int puertoParaControlDeSalas = 9895;

	private ArrayList<String> listaDeSalas;
	private ArrayList<Integer> tiempoDeSockets;

	private JComboBox<String> cb;
	private JTextArea textArea;

	private boolean hilo1Work = false;

	private boolean hilo2Work = false;
	
	
	
	
	
	
	
	
	public static void main(String[]args) {
		
	}

	@Override
	public void run() {

		try {
			server = new ServerSocket(puertoClienteAServidor);
		} catch (IOException e1) {
			JOptionPane.showMessageDialog(null,
					"El punto de acceso se encuentra ocupado. Cierre todas las ventanas de esta aplicacion", "Error",
					JOptionPane.ERROR_MESSAGE);
			return;
		} // Intenta crear el puente del lado del servidor

	System.out.println("Esperando Conexion...\n");

		while (true) {
			// socket = new Socket();
			try {

				socket = server.accept(); // Espera a que alguien se quiera conectar y lo guarda

				entrada = new DataInputStream(socket.getInputStream());

			//	Gson gson = new Gson();
			//	String json = entrada.readUTF();
			//	Usuarios paquete = gson.fromJson(json, Usuarios.class);

				// ArrayList<PaqueteEnvio> users = listaDeSockets;

			//	ArrayList<Usuarios> users = new ArrayList<Usuarios>();
			//	users.addAll(listaDeSockets);

				Socket s;
			//	int cant = users.size();
				for (int i = 0; i < cant; i++) {
					Usuarios usuario = users.get(i);
					if ((!paquete.getNick().equals(usuario.getNick()) && paquete.getSala().equals(usuario.getSala()))
							|| (paquete.getNotificarA() != null && paquete.getNotificarA().equals(usuario.getNick()))) {

						try {
							s = new Socket(usuario.getIp(), puertoServidorACliente);
							DataOutputStream o = new DataOutputStream(s.getOutputStream());
							json = gson.toJson(paquete);
							o.writeUTF(json);

							s.close();
						} catch (IOException e1) {
						}
					}
				}

				textArea.append(paquete.getNick() + "---->" + paquete.getMensaje() + "--" + paquete.getIp() + "\n");

			} catch (IOException e) {
				e.printStackTrace();
			} finally {

				try {

					entrada.close();
					socket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	
		
	}
	
	
}
