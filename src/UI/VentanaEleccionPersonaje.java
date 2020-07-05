package UI;


import Juego.Personajes.Arquera;
import Juego.Personajes.Demonio;
import Juego.Personajes.Enano;
import Juego.Personajes.Guerrero;
import Juego.Personajes.Hechicera;
import Juego.Personajes.Ingeniero;
import Juego.Personajes.Personaje;
import Juego.Personajes.Pistolero;
import Juego.Personajes.Reptiliano;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.Color;


public class VentanaEleccionPersonaje extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JComboBox<Personaje> comboBox = new JComboBox<>();
	private JComboBox<Personaje> comboBox_1 = new JComboBox<>();
	
	private Personaje personaje1;
	private Personaje personaje2;
	private VentanaEleccionPersonaje jf=this;
	private ImageIcon imagen;
	private Icon icono;
	/**
	 * Launch the application.
	 */
/*	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaEleccionPersonaje frame = new VentanaEleccionPersonaje();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the frame.
	 */
	public VentanaEleccionPersonaje() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 340, 327);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JLabel lblEligeTuPersonaje = new JLabel("Elige el personaje J1");
		lblEligeTuPersonaje.setBounds(22, 11, 145, 33);
		contentPane.add(lblEligeTuPersonaje);
		this.setResizable(false);
		
		comboBox = new JComboBox<>();
		
		comboBox.addItem(new Arquera());
		comboBox.addItem(new Guerrero());
		comboBox.addItem(new Enano());
		comboBox.addItem(new Hechicera());
		comboBox.addItem(new Reptiliano());
		comboBox.addItem(new Pistolero());
		comboBox.addItem(new Demonio());
		comboBox.addItem(new Ingeniero());
		
		comboBox.setBounds(19, 43, 134, 20);
		contentPane.add(comboBox);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					
				personaje1= (Personaje) comboBox.getSelectedItem();
				personaje2= (Personaje) comboBox_1.getSelectedItem();
				
				Ventana v= new Ventana(jf);
				v.setLocationRelativeTo(null);
				v.setVisible(true);
				jf.setVisible(false);
				
			
			}
		});
		btnAceptar.setBounds(120, 261, 89, 23);
		contentPane.add(btnAceptar);
		
		JLabel lblEligeElPersonaje = new JLabel("Elige el personaje J2");
		lblEligeElPersonaje.setBounds(192, 11, 145, 33);
		contentPane.add(lblEligeElPersonaje);
		
		
		comboBox_1 = new JComboBox<>();
		comboBox_1.addItem(new Arquera());
		comboBox_1.addItem(new Guerrero());
		comboBox_1.addItem(new Enano());
		comboBox_1.addItem(new Hechicera());
		comboBox_1.addItem(new Reptiliano());
		comboBox_1.addItem(new Pistolero());
		comboBox_1.addItem(new Demonio());
		comboBox_1.addItem(new Ingeniero());
		comboBox_1.setBounds(177, 43, 134, 20);
		contentPane.add(comboBox_1);
		
		JLabel lblImagenJ1 = new JLabel("");
		lblImagenJ1.setBounds(19, 72, 134, 178);
	
		icono= new ImageIcon(((Personaje)comboBox.getSelectedItem()).getImage().getImage().getScaledInstance(lblImagenJ1.getWidth(),
				lblImagenJ1.getHeight(), Image.SCALE_DEFAULT));
		lblImagenJ1.setIcon(icono);
		lblImagenJ1.setToolTipText(((Personaje)comboBox.getSelectedItem()).getDescripcionPersonaje());
		contentPane.add(lblImagenJ1);
		
		
		
		JLabel lblImagenJ2 = new JLabel("");
		lblImagenJ2.setBounds(177, 72, 134, 178);
		
		icono= new ImageIcon(((Personaje)comboBox_1.getSelectedItem()).getImage().getImage().getScaledInstance(lblImagenJ2.getWidth(),
				lblImagenJ2.getHeight(), Image.SCALE_DEFAULT));
		lblImagenJ2.setIcon(icono);
		lblImagenJ2.setToolTipText(((Personaje)comboBox_1.getSelectedItem()).getDescripcionPersonaje());
		contentPane.add(lblImagenJ2);
		
		JLabel panelContJ1 = new JLabel();
		panelContJ1.setBounds(-2, 11, 169, 33);
		
		imagen = new ImageIcon(getClass().getResource("/Imagenes/contenedorTexto.png"));
		icono= new ImageIcon(imagen.getImage().getScaledInstance(panelContJ1.getWidth(),
				panelContJ1.getHeight(), Image.SCALE_DEFAULT));
		panelContJ1.setIcon(icono);
		contentPane.add(panelContJ1);
		
		JLabel panelContJ2 = new JLabel();
		panelContJ2.setBounds(168, 11, 169, 33);
		contentPane.add(panelContJ2);
		icono= new ImageIcon(imagen.getImage().getScaledInstance(panelContJ2.getWidth(),
				panelContJ2.getHeight(), Image.SCALE_DEFAULT));
		panelContJ2.setIcon(icono);
		contentPane.add(panelContJ2);
		
		
		
		
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				lblImagenJ1.setToolTipText(((Personaje)comboBox.getSelectedItem()).getDescripcionPersonaje());
				icono= new ImageIcon(((Personaje)comboBox.getSelectedItem()).getImage().getImage().getScaledInstance(lblImagenJ1.getWidth(),
						lblImagenJ1.getHeight(), Image.SCALE_DEFAULT));
				lblImagenJ1.setIcon(icono);
			}
		});
		comboBox_1.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				lblImagenJ2.setToolTipText(((Personaje)comboBox_1.getSelectedItem()).getDescripcionPersonaje());
				icono= new ImageIcon(((Personaje)comboBox_1.getSelectedItem()).getImage().getImage().getScaledInstance(lblImagenJ2.getWidth(),
						lblImagenJ2.getHeight(), Image.SCALE_DEFAULT));
				lblImagenJ2.setIcon(icono);
			}
		});
		
		
	}
	public Personaje getPersonaje1() {
		return this.personaje1;
	}
	public Personaje getPersonaje2() {
		return this.personaje2;
	}
	public void cambiarJugadores() {
		Personaje personajeAuxiliar=personaje1;
		personaje1=personaje2;
		personaje2=personajeAuxiliar;
	}
}
