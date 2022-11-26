package vista;

import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import controlador.ManejadorEventos;

public class VentanaPrincipal extends JFrame{
	
	JButton sumar, restar, multiplicar, dividir, raiz2, raiz3;
	JLabel etiquetaNum1, etiquetaNum2, etiquetaResultado, resultado, logo;
	JTextField num1, num2;
	
	
	
	public JButton getSumar() {
		return sumar;
	}

	public JButton getRestar() {
		return restar;
	}

	public JButton getMultiplicar() {
		return multiplicar;
	}

	public JButton getDividir() {
		return dividir;
	}

	public JButton getRaiz2() {
		return raiz2;
	}

	public JButton getRaiz3() {
		return raiz3;
	}

	public JLabel getEtiquetaNum1() {
		return etiquetaNum1;
	}

	public JLabel getEtiquetaNum2() {
		return etiquetaNum2;
	}

	public JLabel getEtiquetaResultado() {
		return etiquetaResultado;
	}

	public JLabel getResultado() {
		return resultado;
	}

	public JTextField getNum1() {
		return num1;
	}

	public JTextField getNum2() {
		return num2;
	}

	
	
	public VentanaPrincipal() {
		setSize(380,490);		
		setLocationRelativeTo(null);	
		setDefaultCloseOperation(EXIT_ON_CLOSE);	
		setResizable(false);	
		setTitle("Calculadora");
		setLayout(null);			
		inicializarComponentes();
		
		//Cambiamos el icono de nuestra aplicacion por una imagen de una calculadora.
		setIconImage(Toolkit.getDefaultToolkit().getImage("Imagenes/calculator-variant.png"));
		
		setVisible(true);	
	}
	
	private void inicializarComponentes() {
		
		// ================ ETIQUETAS Y TEXTFIELD DE ARRIBA =================
		
		//Modificamos el color del fondo de la ventana principal y lo ponemos de color BLANCO.
		getContentPane().setBackground(Color.WHITE);
		
		//Añadimos la imagen de excel como logo de nuestra calculadora.
		Image img = new ImageIcon("Imagenes/microsoft-excel.png").getImage();
		logo = new JLabel(new ImageIcon(img.getScaledInstance(75, 75, Image.SCALE_SMOOTH)));
		logo.setBounds(140, 20, 75, 75);
		add(logo);
		
		
		//Como el logo nos ocupa, 75px de largo y está a 20px en el eje Y, las etiquetas tendrán que empezar por debajo. 75+20=95px mínimo en el eje y.
		
		etiquetaNum1 = new JLabel("Numero 1: "); 		
		etiquetaNum1.setBounds(90,110,80,30);		
		add(etiquetaNum1);
		
		num1 = new JTextField();
		num1.setBounds(180,110,80,30);
		add(num1);
		
		etiquetaNum2 = new JLabel("Numero 2: "); 		
		etiquetaNum2.setBounds(90,150,80,30);		
		add(etiquetaNum2);
		
		num2 = new JTextField();
		num2.setBounds(180,150,80,30);
		add(num2);
		
		// ===================================================================
		
		
		// ================== BOTONES =======================
		
		sumar = new JButton("Sumar");			
		sumar.setBounds(70, 220, 100, 40);
		sumar.setBackground(new Color(0,100,0));    //Color del relleno del botón en código rgb(0,100,0)(verde oscuro)
		sumar.setForeground(Color.WHITE);    //Color de las letras del botón.
		sumar.setBorder(null);    //Quitamos el borde del boton.
		sumar.addActionListener(new ManejadorEventos(this));
		add(sumar);	
		
		restar = new JButton("Restar");			
		restar.setBounds(175, 220, 100, 40);
		restar.setBackground(new Color(0,100,0)); 
		restar.setForeground(Color.WHITE); 
		restar.setBorder(null);
		restar.addActionListener(new ManejadorEventos(this));
		add(restar);
		
		multiplicar = new JButton("Multiplicar");			
		multiplicar.setBounds(70, 270, 100, 40);	
		multiplicar.setBackground(new Color(0,100,0)); 
		multiplicar.setForeground(Color.WHITE); 
		multiplicar.setBorder(null);
		multiplicar.addActionListener(new ManejadorEventos(this));
		add(multiplicar);
		
		dividir = new JButton("Dividir");			
		dividir.setBounds(175, 270, 100, 40);
		dividir.setBackground(new Color(0,100,0)); 
		dividir.setForeground(Color.WHITE); 
		dividir.setBorder(null);
		dividir.addActionListener(new ManejadorEventos(this));
		add(dividir);
		
		raiz2 = new JButton("Raiz 2");			
		raiz2.setBounds(70, 320, 100, 40);	
		raiz2.setBackground(new Color(0,100,0)); 
		raiz2.setForeground(Color.WHITE); 
		raiz2.setBorder(null);
		raiz2.addActionListener(new ManejadorEventos(this));
		add(raiz2);
		
		raiz3 = new JButton("Raiz 3");			
		raiz3.setBounds(175, 320, 100, 40);
		raiz3.setBackground(new Color(0,100,0)); 
		raiz3.setForeground(Color.WHITE); 
		raiz3.setBorder(null);
		raiz3.addActionListener(new ManejadorEventos(this));
		add(raiz3);
		
		// ====================================================
		
		// ================= ETIQUETAS DEL RESULTADO ======================
		
		etiquetaResultado = new JLabel("Resultado: "); 		
		etiquetaResultado.setBounds(90,380,80,30);		
		add(etiquetaResultado);
		
		resultado = new JLabel("");
		resultado.setBounds(158,380,180,30);
		add(resultado);
		
		// ===========================================================================
		
	}

}
