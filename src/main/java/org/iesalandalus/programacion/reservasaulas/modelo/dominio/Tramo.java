package org.iesalandalus.programacion.reservasaulas.modelo.dominio;

public enum Tramo {
	MANANA, TARDE;
	private String cadenaAMostrar;
	private void Tramo(String tramoPasado){
		if(tramoPasado.equals(MANANA)) {
			cadenaAMostrar="Mañana";
		} else {
			cadenaAMostrar="Tarde";
		}
		
	}
	   
	   public String toString() {
	       return cadenaAMostrar;
	   }
	
}