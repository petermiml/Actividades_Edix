package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import vista.VentanaPrincipal;

public class ManejadorEventos implements ActionListener{

	VentanaPrincipal vp;
	
	public ManejadorEventos(VentanaPrincipal vp) {
		this.vp = vp;
	}
	
	public double suma(double num1, double num2) {
		return num1 + num2;
	}
	
	public double resta(double num1, double num2) {
		return num1 - num2;
	}
	
	public double multiplicacion(double num1, double num2) {
		return num1 * num2;
	}
	
	public double division(double num1, double num2) {
		return num1 / num2;
	}
	
	// FALTA ESTA FUNCION
	public String raiz3() {
		return "";
	}
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		double num1 = Double.valueOf(vp.getNum1().getText());
		double num2 = Double.valueOf(vp.getNum2().getText());
		
		if(e.getSource() == vp.getSumar()) {
			String resultado = String.valueOf(suma(num1,num2));
			vp.getResultado().setText(resultado);
			System.out.println(e.getSource());
			
		}else if(e.getSource() == vp.getRestar()){
			String resultado = String.valueOf(resta(num1,num2));
			vp.getResultado().setText(resultado); 
			
		}else if(e.getSource() == vp.getMultiplicar()) {
			String resultado = String.valueOf(multiplicacion(num1,num2));
			vp.getResultado().setText(resultado);
			
		}else if(e.getSource() == vp.getDividir()) {
			String resultado = String.valueOf(division(num1,num2));
			vp.getResultado().setText(resultado);
			
		}else if(e.getSource() == vp.getRaiz2()) {
			JOptionPane.showMessageDialog(null,"Funcionalidad no disponible", "Error", JOptionPane.INFORMATION_MESSAGE);	
		}
			
		
		
	}

}