package org.iesalandalus.programacion.reservasaulas.vista;

import java.text.DateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Aula;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Profesor;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Tramo;
import org.iesalandalus.programacion.utilidades.Entrada;

public class Consola {
	private static final DateTimeFormatter FORMATO_DIA = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	private Consola() {
		// Constructor por defecto no hace nada.
	}

	public static void mostrarMenu() {

		for (Opcion opcion : Opcion.values()) {
			System.out.println(opcion.toString());
		}
	}

	public static void mostrarCabecera(String cabecera) {
		System.out.printf("%n%s%n", cabecera);
		String bordes = "%0" + cabecera.length() + "d%n";
		System.out.println(String.format(bordes, 0).replace("0", "_"));
	}

	public static int elegirOpcion() {
		int opcion;
		do {
			System.out.print("\nElige una opcion:");
			opcion = Entrada.entero();
		} while (!Opcion.esOrdinalValido(opcion));
		return opcion;
	}

	public static Aula leerAula() {
		System.out.print("Introduce el nombre del Aula: ");
		return new Aula(Entrada.cadena());
	}

	public static String leerNombreAula() {
		String nombreAula;
		do {
			System.out.print("Introduce el nombre del Aula: ");
			nombreAula = Entrada.cadena();
		} while (nombreAula.trim().equals(""));
		return nombreAula;
	}

	public static Profesor leerProfesor() {
		String nombre, correo, telefono = "";
		System.out.print("Introduce el nombre de el Profesor o Profesora: ");
		nombre = Entrada.cadena();
		System.out.print("Introduce el correo de el Profesor o Profesora: ");
		correo = Entrada.cadena();
		System.out.print("Introduce el telefono de el Profesor o Profesora (No obligatorio): ");
		telefono = Entrada.cadena();
		if (telefono.trim().equals("")) {
			return new Profesor(nombre, correo);
		} else {
			return new Profesor(nombre, correo, telefono);
		}
	}

	public static String leerNombreProfesor() {
		String nombre;
		do {
			System.out.print("Introduce el nombre de el Profesor o Profesora: ");
			nombre = Entrada.cadena();
		} while (nombre.trim().equals(""));
		return nombre;
	}

	public static Tramo leerTramo() {
		String seleccion;
			System.out.print("Introduce el tramo (Mañana o Tarde): ");
			seleccion = Entrada.cadena();
		if (seleccion.equals("Mañana")){
			return Tramo.MANANA;
		} else if (seleccion.equals("Tarde")){
			return Tramo.TARDE;
		} else {
			return null;
		}

	}

	public static LocalDate leerDia() {
		LocalDate diaLeido;
		int dia, mes, anio;
		do {
			System.out.print("Introduce el dia: ");
			dia = Entrada.entero();
			System.out.print("Introduce el mes: ");
			mes = Entrada.entero();
			System.out.print("Introduce el año: ");
			anio = Entrada.entero();
			;
		} while (LocalDate.of(anio, mes, dia).isBefore(LocalDate.now()));
		return diaLeido = LocalDate.of(anio, mes, dia);
	}
}
