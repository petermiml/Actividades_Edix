package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;

import controlador.ManejadorEventos;

import javax.swing.border.EtchedBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.UIManager;
import java.awt.Toolkit;
import javax.swing.JLabel;
import java.awt.Font;

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class VentanaContrase�a extends JDialog {

	private final JPanel cajaContenedora2 = new JPanel();
	private JTextField cajaDeTexto;
	private JButton botonOK, botonCancel;
	private JLabel etiquetaContrase�a, etiquetaImagen,etiqContrErro�ea;
	private boolean confirm=true;
	String contrase�a = "1234";	
	
	
	public String getContrase�a() {
		return contrase�a;
	}

	public boolean getConfirm() {
	return confirm;
	}

	/**
	 * Create the dialog.
	 */
	public VentanaContrase�a(VentanaPrincipal ventanaPrinvipal, boolean permiso) {
		super(ventanaPrinvipal,permiso);
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\user\\Downloads\\lock-alert(3).png"));
		setBackground(new Color(15, 176, 152));
		setSize( 482, 192);
		setLocationRelativeTo(null);
		setTitle("No access");		
		inicializarComponentes();		
	}

	private void inicializarComponentes() {
		
		
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(cajaContenedora2, BorderLayout.CENTER);
		cajaContenedora2.setForeground(new Color(0, 102, 51));
		cajaContenedora2.setBackground(new Color(0, 204, 102));
		cajaContenedora2.setBorder(null);
		cajaContenedora2.setLayout(null);
		
		
		
		etiqContrErro�ea = new JLabel("");
		etiqContrErro�ea.setIcon(null);
		etiqContrErro�ea.setForeground(new Color(255, 255, 255));
		etiqContrErro�ea.setFont(new Font("Century", Font.BOLD, 14));
		etiqContrErro�ea.setBounds(157, 74, 181, 28);
		cajaContenedora2.add(etiqContrErro�ea);
		
		etiquetaContrase�a = new JLabel("Contrase\u00F1a");
		etiquetaContrase�a.setIcon(null);
		etiquetaContrase�a.setForeground(new Color(255, 255, 255));
		etiquetaContrase�a.setFont(new Font("Century", Font.BOLD, 18));
		etiquetaContrase�a.setBounds(45, 38, 102, 42);
		cajaContenedora2.add(etiquetaContrase�a);
		
		etiquetaImagen = new JLabel("");
		etiquetaImagen.setIcon(new ImageIcon("Imagenes/key.png"));
		etiquetaImagen.setFont(new Font("Century", Font.PLAIN, 16));
		etiquetaImagen.setForeground(new Color(255, 255, 255));
		etiquetaImagen.setBounds(348, 48, 39, 24);
		cajaContenedora2.add(etiquetaImagen);
		
		
		botonOK = new JButton("OK");
		botonOK.setBackground(new Color(233, 233, 233));
		botonOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(getCajaDeTexto().equals(contrase�a)) {
			
					confirm = false;
					
					dispose();
				}else {	
					etiqContrErro�ea.setText("'Contrase�a inv�lida'");	
			}}
		});
		botonOK.setBounds(239, 106, 89, 23);
		cajaContenedora2.add(botonOK);
		
		
	    botonCancel = new JButton("Cancel");
		botonCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				dispose();
				
			}
		});
		botonCancel.setBackground(new Color(233, 233, 233));
		botonCancel.setBounds(348, 106, 89, 23);		
		cajaContenedora2.add(botonCancel);
		
		
		cajaDeTexto = new JTextField();
		cajaDeTexto.setBounds(157, 48, 181, 28);
		cajaDeTexto.setColumns(10);
		cajaContenedora2.add(cajaDeTexto);
	}

	public String getCajaDeTexto() {
		
		return cajaDeTexto.getText();
	}
}
