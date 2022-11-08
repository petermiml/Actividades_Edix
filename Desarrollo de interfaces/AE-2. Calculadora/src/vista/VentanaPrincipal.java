package vista;

import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import controlador.ManejadorEventos;

public class VentanaPrincipal extends JFrame{
	
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

	JButton sumar, restar, multiplicar, dividir, raiz2, raiz3;
	JLabel etiquetaNum1, etiquetaNum2, etiquetaResultado, resultado;
	JTextField num1, num2;
	
	public VentanaPrincipal() {
		setSize(380,460);		
		setLocationRelativeTo(null);	
		setDefaultCloseOperation(EXIT_ON_CLOSE);	
		setResizable(false);	
		setTitle("Calculadora");
		setLayout(null);			
		inicializarComponentes();
		setVisible(true);	
	}
	
	private void inicializarComponentes() {
		
		// ================ ETIQUETAS Y TEXTFIELD DE ARRIBA =================
		
		etiquetaNum1 = new JLabel("Numero 1: "); 		
		etiquetaNum1.setBounds(40,50,80,30);		
		add(etiquetaNum1);
		
		num1 = new JTextField();
		num1.setBounds(130,50,80,30);
		add(num1);
		
		etiquetaNum2 = new JLabel("Numero 2: "); 		
		etiquetaNum2.setBounds(40,100,80,30);		
		add(etiquetaNum2);
		
		num2 = new JTextField();
		num2.setBounds(130,100,80,30);
		add(num2);
		
		// ===================================================================
		
		
		// ================== BOTONES =======================
		
		sumar = new JButton("Sumar");			
		sumar.setBounds(40, 160, 95, 40);	
		sumar.addActionListener(new ManejadorEventos(this));
		add(sumar);	
		
		restar = new JButton("Restar");			
		restar.setBounds(145, 160, 95, 40);	
		restar.addActionListener(new ManejadorEventos(this));
		add(restar);
		
		multiplicar = new JButton("Multiplicar");			
		multiplicar.setBounds(40, 210, 95, 40);	
		multiplicar.addActionListener(new ManejadorEventos(this));
		add(multiplicar);
		
		dividir = new JButton("Dividir");			
		dividir.setBounds(145, 210, 95, 40);
		dividir.addActionListener(new ManejadorEventos(this));
		add(dividir);
		
		raiz2 = new JButton("Raiz 2");			
		raiz2.setBounds(40, 260, 95, 40);	
		raiz2.addActionListener(new ManejadorEventos(this));
		add(raiz2);
		
		raiz3 = new JButton("Raiz 3");			
		raiz3.setBounds(145, 260, 95, 40);	
		raiz3.addActionListener(new ManejadorEventos(this));
		add(raiz3);
		
		// ====================================================
		
		// ================= ETIQUETA Y TEXTFIELD DEL RESULTADO ======================
		
		etiquetaResultado = new JLabel("Resultado: "); 		
		etiquetaResultado.setBounds(40,330,80,30);		
		add(etiquetaResultado);
		
		resultado = new JLabel("");
		resultado.setBounds(130,330,80,30);
		add(resultado);
		
		// ===========================================================================
		
	}

}
