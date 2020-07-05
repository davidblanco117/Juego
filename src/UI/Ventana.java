package UI;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

import Juego.ManejadorEventosTurnos;
import Juego.Habilidades.Habilidad;
import Juego.Habilidades.HabilidadVacia;
import Juego.Personajes.Personaje;

import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import java.awt.Font;

public class Ventana extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private ImageIcon imagen;
	private Icon icono;
	private JButton btnsHabilidades[] = new JButton[6];
	private int contador1 = 0;
	private int contador2 = 0;
	private JTextPane textPane;
	private SimpleAttributeSet sas;
	private int indiceColor = 0;
	private Color[] colores;
	private JLabel lblValorSaludEnemigo;
	private JLabel lblValorSalud;
	private VentanaEleccionPersonaje jf;
	private JLabel lblValorPHabilidadEnem;
	private JLabel lblValorPHabilidad;
	private Personaje jugador1;
	private Personaje jugador2;
	private JLabel lblImagenPersonaje;
	private JLabel lblImagenRival;
	private JLabel lblValorDefensa;
	private JLabel lblValorDefensaEnemigo;
	private JLabel lblValorAtaque;
	private JLabel lblValorAtaqueEnemigo;
	private JLabel lblValorVelocidad;
	private JLabel lblValorVelocidadEnemigo;
	private JLabel lblValorClase;
	private JLabel lblValorClaseEnemigo;
	public static int turno = 1;
	private JFrame ventana = this;
	private ManejadorEventosTurnos mnt = new ManejadorEventosTurnos();

	/**
	 * Launch the application.
	 */
	/**
	 * Create the frame.
	 */
	public Ventana(VentanaEleccionPersonaje jf) {
		this.jf = jf;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 780, 590);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.setResizable(false);

		jugador1 = jf.getPersonaje1();
		jugador2 = jf.getPersonaje2();

		/*
		 * Bloque de pruebas:
		 */

		// jugador1.setPuntosHabilidad(10);

		jugador1.setPuntosHabilidad(10);
		jugador2.setPuntosHabilidad(10);

		/*
		*/

		imagen = new ImageIcon(getClass().getResource("/Imagenes/botonAtacar.jpg"));

		textPane = new JTextPane();
		textPane.setBounds(247, 0, 290, 360);
		textPane.setEditable(false);
		contentPane.add(textPane);
		textPane.setBackground(Color.BLACK);

		JScrollPane scrollPane = new JScrollPane(textPane);
		scrollPane.setBounds(247, 0, 290, 360);
		getContentPane().add(scrollPane);

		sas = new SimpleAttributeSet();
		StyleConstants.setBold(sas, true);
		StyleConstants.setItalic(sas, true);
		StyleConstants.setForeground(sas, Color.RED);
		StyleConstants.setFontSize(sas, 15);
		colores = new Color[2];
		colores[0] = Color.RED;
		colores[1] = Color.BLUE;

		JPanel panel = new JPanel();
		panel.setBounds(0, 357, 774, 204);
		contentPane.add(panel);
		panel.setLayout(null);
		JButton btnAtacar = new JButton();
		btnAtacar.setBounds(350, 41, 66, 52);
		panel.add(btnAtacar);
		btnAtacar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					jf.getPersonaje1().atacar(jf.getPersonaje2());
					StyleConstants.setForeground(sas, colores[indiceColor]);
					textPane.getStyledDocument().insertString(textPane.getStyledDocument().getLength(),
							jf.getPersonaje1().getDescripcionDeSituacion(), sas);
			
					indiceColor = (++indiceColor) % 2;
				} catch (BadLocationException e) {
					e.printStackTrace();
				}
				cambiarTurno();
			}
		});
		icono = new ImageIcon(
				imagen.getImage().getScaledInstance(btnAtacar.getWidth(), btnAtacar.getHeight(), Image.SCALE_DEFAULT));
		btnAtacar.setIcon(icono);

		JButton btnHabilidad1 = new JButton();
		btnHabilidad1.setBounds(304, 124, 46, 29);
		panel.add(btnHabilidad1);

		JButton btnHabilidad2 = new JButton();
		btnHabilidad2.setBounds(360, 124, 46, 29);
		panel.add(btnHabilidad2);

		JButton btnHabilidad3 = new JButton();
		btnHabilidad3.setBounds(416, 124, 46, 29);
		panel.add(btnHabilidad3);

		JButton btnHabilidad4 = new JButton();
		btnHabilidad4.setBounds(304, 164, 46, 29);
		panel.add(btnHabilidad4);

		JButton btnHabilidad5 = new JButton();
		btnHabilidad5.setBounds(360, 164, 46, 29);
		panel.add(btnHabilidad5);

		JButton btnHabilidad6 = new JButton();
		btnHabilidad6.setBounds(416, 164, 46, 29);
		panel.add(btnHabilidad6);

		btnsHabilidades[0] = btnHabilidad1;
		btnsHabilidades[1] = btnHabilidad2;
		btnsHabilidades[2] = btnHabilidad3;
		btnsHabilidades[3] = btnHabilidad4;
		btnsHabilidades[4] = btnHabilidad5;
		btnsHabilidades[5] = btnHabilidad6;

		JLabel lblHabilidades = new JLabel("Habilidades");
		lblHabilidades.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 14));
		lblHabilidades.setForeground(Color.WHITE);
		lblHabilidades.setHorizontalAlignment(SwingConstants.CENTER);
		lblHabilidades.setBounds(315, 100, 147, 21);
		panel.add(lblHabilidades);

		JLabel lblAcciones = new JLabel("Acciones");
		lblAcciones.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 14));
		lblAcciones.setForeground(Color.WHITE);
		lblAcciones.setHorizontalAlignment(SwingConstants.CENTER);
		lblAcciones.setBounds(281, 9, 210, 21);
		panel.add(lblAcciones);

		JLabel lblEstado = new JLabel("Estado");
		lblEstado.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 14));
		lblEstado.setForeground(Color.WHITE);
		lblEstado.setHorizontalAlignment(SwingConstants.CENTER);
		lblEstado.setBounds(38, 11, 154, 16);
		panel.add(lblEstado);

		imagen = new ImageIcon(getClass().getResource("/Imagenes/panelInferior3.png"));

		JLabel lblClase = new JLabel("Clase:");
		lblClase.setForeground(Color.WHITE);
		lblClase.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 14));
		lblClase.setBounds(10, 41, 58, 26);
		panel.add(lblClase);

		lblValorClase = new JLabel(jf.getPersonaje1().toString());
		lblValorClase.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 14));
		lblValorClase.setForeground(Color.WHITE);
		lblValorClase.setBounds(66, 47, 126, 14);
		panel.add(lblValorClase);

		JLabel lblSalud = new JLabel("Salud:");
		lblSalud.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 14));
		lblSalud.setForeground(Color.WHITE);
		lblSalud.setBounds(10, 66, 46, 14);
		panel.add(lblSalud);

		lblValorSalud = new JLabel(String.valueOf(jf.getPersonaje1().getSalud()));
		lblValorSalud.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 14));
		lblValorSalud.setForeground(Color.WHITE);
		lblValorSalud.setBounds(66, 66, 126, 14);
		panel.add(lblValorSalud);

		JLabel lblEstadoEnemigo = new JLabel("Estado Enemigo");
		lblEstadoEnemigo.setHorizontalAlignment(SwingConstants.CENTER);
		lblEstadoEnemigo.setForeground(Color.WHITE);
		lblEstadoEnemigo.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 14));
		lblEstadoEnemigo.setBounds(582, 11, 154, 16);
		panel.add(lblEstadoEnemigo);

		JLabel label = new JLabel("Clase:");
		label.setForeground(Color.WHITE);
		label.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 14));
		label.setBounds(548, 41, 58, 26);
		panel.add(label);

		JLabel label_1 = new JLabel("Salud:");
		label_1.setForeground(Color.WHITE);
		label_1.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 14));
		label_1.setBounds(548, 66, 46, 14);
		panel.add(label_1);

		lblValorClaseEnemigo = new JLabel(jf.getPersonaje2().toString());
		lblValorClaseEnemigo.setForeground(Color.WHITE);
		lblValorClaseEnemigo.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 14));
		lblValorClaseEnemigo.setBounds(598, 47, 126, 14);
		panel.add(lblValorClaseEnemigo);

		lblValorSaludEnemigo = new JLabel(String.valueOf(jf.getPersonaje2().getSalud()));
		lblValorSaludEnemigo.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 14));
		lblValorSaludEnemigo.setForeground(Color.WHITE);
		lblValorSaludEnemigo.setBounds(614, 66, 126, 14);
		panel.add(lblValorSaludEnemigo);

		JLabel lblPHabilidad = new JLabel("P.Habilidad:");
		lblPHabilidad.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 14));
		lblPHabilidad.setForeground(Color.WHITE);
		lblPHabilidad.setBounds(10, 83, 97, 21);
		panel.add(lblPHabilidad);

		lblValorPHabilidad = new JLabel(String.valueOf(jf.getPersonaje1().getPuntosHabilidad()));
		lblValorPHabilidad.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 14));
		lblValorPHabilidad.setForeground(Color.WHITE);
		lblValorPHabilidad.setBounds(104, 86, 126, 14);
		panel.add(lblValorPHabilidad);

		JLabel lblPHabilidadEnem = new JLabel("P.Habilidad:");
		lblPHabilidadEnem.setForeground(Color.WHITE);
		lblPHabilidadEnem.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 14));
		lblPHabilidadEnem.setBounds(548, 83, 97, 19);
		panel.add(lblPHabilidadEnem);

		lblValorPHabilidadEnem = new JLabel(String.valueOf(jf.getPersonaje2().getPuntosHabilidad()));
		lblValorPHabilidadEnem.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 14));
		lblValorPHabilidadEnem.setForeground(Color.WHITE);
		lblValorPHabilidadEnem.setBounds(648, 86, 126, 14);
		panel.add(lblValorPHabilidadEnem);

		JLabel lblDefensa = new JLabel("Defensa:");
		lblDefensa.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 14));
		lblDefensa.setForeground(Color.WHITE);
		lblDefensa.setBounds(10, 116, 97, 21);
		panel.add(lblDefensa);

		lblValorDefensa = new JLabel(String.valueOf(jugador1.getDefensa()));
		lblValorDefensa.setForeground(Color.WHITE);
		lblValorDefensa.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 14));
		lblValorDefensa.setBounds(77, 119, 66, 14);
		panel.add(lblValorDefensa);

		JLabel lblAtaque = new JLabel("Ataque:");
		lblAtaque.setForeground(Color.WHITE);
		lblAtaque.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 14));
		lblAtaque.setBounds(10, 135, 97, 21);
		panel.add(lblAtaque);

		lblValorAtaque = new JLabel(String.valueOf(jugador1.getAtaque()));
		lblValorAtaque.setForeground(Color.WHITE);
		lblValorAtaque.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 14));
		lblValorAtaque.setBounds(77, 138, 126, 14);
		panel.add(lblValorAtaque);

		JLabel lblAtaqueEnemigo = new JLabel("Ataque:");
		lblAtaqueEnemigo.setForeground(Color.WHITE);
		lblAtaqueEnemigo.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 14));
		lblAtaqueEnemigo.setBounds(549, 135, 97, 21);
		panel.add(lblAtaqueEnemigo);

		JLabel lblDefensaEnemigo = new JLabel("Defensa:");
		lblDefensaEnemigo.setForeground(Color.WHITE);
		lblDefensaEnemigo.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 14));
		lblDefensaEnemigo.setBounds(549, 116, 97, 21);
		panel.add(lblDefensaEnemigo);

		lblValorDefensaEnemigo = new JLabel(String.valueOf(jugador2.getDefensa()));
		lblValorDefensaEnemigo.setForeground(Color.WHITE);
		lblValorDefensaEnemigo.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 14));
		lblValorDefensaEnemigo.setBounds(615, 119, 136, 14);
		panel.add(lblValorDefensaEnemigo);

		lblValorAtaqueEnemigo = new JLabel(String.valueOf(jugador2.getAtaque()));
		lblValorAtaqueEnemigo.setForeground(Color.WHITE);
		lblValorAtaqueEnemigo.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 14));
		lblValorAtaqueEnemigo.setBounds(615, 138, 126, 14);
		panel.add(lblValorAtaqueEnemigo);

		JLabel lblVelocidad = new JLabel("Velocidad:");
		lblVelocidad.setForeground(Color.WHITE);
		lblVelocidad.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 14));
		lblVelocidad.setBounds(10, 156, 97, 21);
		panel.add(lblVelocidad);

		lblValorVelocidad = new JLabel(String.valueOf(jugador1.getVelocidad()));
		lblValorVelocidad.setForeground(Color.WHITE);
		lblValorVelocidad.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 14));
		lblValorVelocidad.setBounds(87, 159, 116, 14);
		panel.add(lblValorVelocidad);

		lblValorVelocidadEnemigo = new JLabel(String.valueOf(jugador2.getVelocidad()));
		lblValorVelocidadEnemigo.setForeground(Color.WHITE);
		lblValorVelocidadEnemigo.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 14));
		lblValorVelocidadEnemigo.setBounds(625, 159, 116, 14);
		panel.add(lblValorVelocidadEnemigo);

		JLabel lblVelocidadEnemigo = new JLabel("Velocidad:");
		lblVelocidadEnemigo.setForeground(Color.WHITE);
		lblVelocidadEnemigo.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 14));
		lblVelocidadEnemigo.setBounds(548, 156, 97, 21);
		panel.add(lblVelocidadEnemigo);

		JLabel lblPanelInferior = new JLabel("");
		lblPanelInferior.setBounds(0, 0, 774, 204);
		panel.add(lblPanelInferior);

		icono = new ImageIcon(imagen.getImage().getScaledInstance(lblPanelInferior.getWidth(),
				lblPanelInferior.getHeight(), Image.SCALE_DEFAULT));

		lblPanelInferior.setIcon(icono);

		lblImagenPersonaje = new JLabel("");
		lblImagenPersonaje.setBounds(0, 0, 249, 358);
		contentPane.add(lblImagenPersonaje);
		icono = new ImageIcon(jf.getPersonaje1().getImage().getImage().getScaledInstance(lblImagenPersonaje.getWidth(),
				lblImagenPersonaje.getHeight(), Image.SCALE_DEFAULT));
		lblImagenPersonaje.setIcon(icono);
		lblImagenPersonaje.setToolTipText(jugador1.getDescripcionPersonaje());

		lblImagenRival = new JLabel("");
		lblImagenRival.setBounds(536, 0, 238, 358);
		contentPane.add(lblImagenRival);
		icono = new ImageIcon(jf.getPersonaje2().getImage().getImage().getScaledInstance(lblImagenRival.getWidth(),
				lblImagenRival.getHeight(), Image.SCALE_DEFAULT));
		lblImagenRival.setIcon(icono);
		lblImagenRival.setToolTipText(jugador2.getDescripcionPersonaje());

		prepararBotones();
	}

	private void cambiarTurno() {

		if (!jugador1.isEstaVivo())
			jugador1.morir();
		if (!jugador2.isEstaVivo())
			jugador2.morir();

		jf.getPersonaje1().finalizarTurno();
		
		if (jf.getPersonaje1() == jugador2)
			turno++;

		jugador1.setDescripcionDeSituacion("");
		jugador2.setDescripcionDeSituacion("");
		mnt.manejar(jf.getPersonaje1());
		try {
			StyleConstants.setForeground(sas, Color.GREEN);
			textPane.getStyledDocument().insertString(textPane.getStyledDocument().getLength(),
					jf.getPersonaje2().getDescripcionDeSituacion(), sas);
			textPane.getStyledDocument().insertString(textPane.getStyledDocument().getLength(),
					jf.getPersonaje1().getDescripcionDeSituacion(), sas);

		} catch (BadLocationException e1) {
			e1.printStackTrace();
		}

		jugador1.setDescripcionDeSituacion("");
		jugador2.setDescripcionDeSituacion("");

		icono = new ImageIcon(jugador1.getImage().getImage().getScaledInstance(lblImagenPersonaje.getWidth(),
				lblImagenPersonaje.getHeight(), Image.SCALE_DEFAULT));
		lblImagenPersonaje.setIcon(icono);
		lblImagenPersonaje.setToolTipText(jugador1.getDescripcionPersonaje());

		icono = new ImageIcon(jugador2.getImage().getImage().getScaledInstance(lblImagenRival.getWidth(),
				lblImagenRival.getHeight(), Image.SCALE_DEFAULT));
		lblImagenRival.setIcon(icono);
		lblImagenRival.setToolTipText(jugador2.getDescripcionPersonaje());

		try {
			textPane.getStyledDocument().insertString(textPane.getStyledDocument().getLength(), "\n", sas);
		} catch (BadLocationException e) {
			e.printStackTrace();
		}

		jf.getPersonaje1().aumentarPuntosHabilidad();
		actualizarInfo();
		jf.cambiarJugadores();

		actualizarBotones();

	}

	private void actualizarInfo() {

		
		DecimalFormat formato=new DecimalFormat("#.##");
		
		lblValorClase.setText(jugador1.toString());
		lblValorClaseEnemigo.setText(jugador2.toString());

		actualizarLabel(lblValorSalud, Double.valueOf(formato.format(jugador1.getSalud()).replace(',','.')));
		actualizarLabel(lblValorSaludEnemigo, Double.valueOf(formato.format(jugador2.getSalud()).replace(',','.')));

		actualizarLabel(lblValorPHabilidad, jugador1.getPuntosHabilidad());
		actualizarLabel(lblValorPHabilidadEnem, jugador2.getPuntosHabilidad());

		actualizarLabel(lblValorDefensa, Double.valueOf(formato.format(jugador1.getDefensa()).replace(',','.')));
		actualizarLabel(lblValorDefensaEnemigo, Double.valueOf(formato.format(jugador2.getDefensa()).replace(',','.')));

		actualizarLabel(lblValorAtaque, Double.valueOf(formato.format(jugador1.getAtaque()).replace(',','.')));
		actualizarLabel(lblValorAtaqueEnemigo, Double.valueOf(formato.format(jugador2.getAtaque()).replace(',','.')));

		actualizarLabel(lblValorVelocidad, Double.valueOf(formato.format(jugador1.getVelocidad()).replace(',','.')));
		actualizarLabel(lblValorVelocidadEnemigo, Double.valueOf(formato.format(jugador2.getVelocidad()).replace(',','.')));

	}

	private void actualizarLabel(JLabel jl, double valorActual) {
		double valorAnterior = Double.valueOf(jl.getText());
		if (valorActual < valorAnterior)
			jl.setForeground(Color.RED);
		else if (valorActual > valorAnterior)
			jl.setForeground(Color.GREEN);
		else
			jl.setForeground(Color.WHITE);
		jl.setText(String.valueOf(valorActual));
	}

	private void actualizarBotones() {
		contador2 = 0;

		for (JButton jButton : btnsHabilidades) {
			jButton.setEnabled(false);
			jButton.setIcon(null);
		}

		for (Habilidad h : jf.getPersonaje1().getListaHabilidades()) {
			if (!h.toString().equals(new HabilidadVacia().toString())) {
				btnsHabilidades[contador2].setEnabled(true);
				Icon icon = new ImageIcon(
						h.getImage().getImage().getScaledInstance(btnsHabilidades[contador2].getWidth(),
								btnsHabilidades[contador2].getHeight(), Image.SCALE_DEFAULT));
				btnsHabilidades[contador2].setIcon(icon);
				btnsHabilidades[contador2].setToolTipText(h.getDescripcion());
				
			}
			contador2++;
		}

	}

	private void prepararBotones() {
		contador1 = 0;

		while (contador1 < btnsHabilidades.length) {

			actualizarBotones();
			btnsHabilidades[contador1].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					try {

						int i = 0;
						int resp = 0;
						for (JButton jButton : btnsHabilidades) {
							if (arg0.getSource() == jButton) {
								resp = i;
								break;
							}
							i++;
						}

						StyleConstants.setForeground(sas, colores[indiceColor]);
						Habilidad h = jf.getPersonaje1().getListaHabilidades().get(resp);
						boolean activaHabil = jf.getPersonaje1().activarHabilidad(resp, jf.getPersonaje2());
						if (activaHabil) {
							textPane.getStyledDocument().insertString(textPane.getStyledDocument().getLength(),
									jf.getPersonaje1().getDescripcionDeSituacion(), sas);
						
							cambiarTurno();
						} else
							textPane.getStyledDocument().insertString(textPane.getStyledDocument().getLength(),h.getDescripcionNoActivacion() + "\n",
									sas);
						indiceColor = (++indiceColor) % 2;

					} catch (BadLocationException e) {
						e.printStackTrace();
					}

				}
			});

			contador1++;

		}
	}

	public JFrame getVentana() {
		return ventana;
	}

	public void setVentana(JFrame ventana) {
		this.ventana = ventana;
	}
}
