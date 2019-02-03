package org.iesalandalus.programacion.reservasaulas;

import org.iesalandalus.programacion.reservasaulas.vista.Consola;
import org.iesalandalus.programacion.reservasaulas.vista.IUTextual;

public class MainApp {

	public static void main(String[] args) {
		System.out.println("Programa para la gestión de reservas de espacios del IES Al-Ándalus");
		Consola.mostrarCabecera("Realizado por Alejandro Gázquez Monedero");
		IUTextual vista = new IUTextual();
		vista.comenzar();
	}

}
