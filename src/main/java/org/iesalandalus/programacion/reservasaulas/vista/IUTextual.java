package org.iesalandalus.programacion.reservasaulas.vista;

import javax.naming.OperationNotSupportedException;
import org.iesalandalus.programacion.reservasaulas.modelo.ModeloReservasAulas;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Aula;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Permanencia;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Profesor;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Reserva;

public class IUTextual {
	private ModeloReservasAulas modelo;
	private static final String ERROR = "ERROR: ";
	private static final String NOMBRE_VALIDO = "Alex Gazquez";
	private static final String CORREO_VALIDO = "alexgazquezmonedero@gmail.com";

	public IUTextual() {
		modelo = new ModeloReservasAulas();
		Opcion.setVista(this);
	}

	public void comenzar() {
		int opcionEscogida;
		do {
			Consola.mostrarMenu();
			opcionEscogida = Consola.elegirOpcion();
			Opcion opcion = Opcion.getOpcionSegunOrdinal(opcionEscogida);
			opcion.ejecutar();
		} while (opcionEscogida != Opcion.SALIR.ordinal());
	}

	public void salir() {
		System.out.print("Has salido del programa Reservas Aulas.");
	}

	public void insertarAula() {
		Consola.mostrarCabecera("Insertar Aula");
		try {
			Aula aula = Consola.leerAula();
			modelo.insertarAula(aula);
			System.out.println("La Aula proporcionada ha sido añadida al sistema.");
			comenzar();
		} catch (OperationNotSupportedException | IllegalArgumentException e) {
			System.out.println(ERROR + e.getMessage());
			comenzar();
		}
	}

	public void borrarAula() {
		Consola.mostrarCabecera("Borrar Aula");
		try {
			Aula aula = Consola.leerAula();
			modelo.borrarAula(aula);
			System.out.println("La Aula ha sido borrada del sistema");
			comenzar();
		} catch (OperationNotSupportedException | IllegalArgumentException e) {
			System.out.println(ERROR + e.getMessage());
			comenzar();
		}
	}

	public void buscarAula() {
		Consola.mostrarCabecera("Buscar Aula");
		try {
			Aula aula = modelo.buscarAula(Consola.leerAula());
			if (aula != null) {
				System.out.println("El aula encontrada es: " + aula);
				comenzar();
			} else {
				System.out.println("Aula no encontrada en el sistema.");
				comenzar();
			}
		} catch (IllegalArgumentException e) {
			System.out.println(ERROR + e.getMessage());
			comenzar();
		}
	}

	public void listarAulas() {
		Consola.mostrarCabecera("Listar Aulas");
		try {
			String[] aulas = modelo.representarAulas();
			if (modelo.getNumAulas() > 0) {
				for (String aula : aulas) {
					System.out.println(aula);
					comenzar();
				}
			} else {
				System.out.println("No existen aulas para listar actualmente");
				comenzar();
			}
		} catch (IllegalArgumentException | UnsupportedOperationException e) {
			System.out.println(ERROR + e.getMessage());
			comenzar();
		}
	}

	public void insertarProfesor() {
		Consola.mostrarCabecera("Insertar Profesor/a");
		try {
			Profesor profesor = Consola.leerProfesor();
			modelo.insertarProfesor(profesor);
			System.out.println("Profesor/a proporcionado/a ha sido añadido/a al sistema.");
		} catch (OperationNotSupportedException | IllegalArgumentException e) {
			System.out.println(ERROR + e.getMessage());
			comenzar();
		}
	}

	public void borrarProfesor() {
		Consola.mostrarCabecera("Borrar Profesor/a");
		try {
			Profesor profesorBorrar = modelo.buscarProfesor(new Profesor(Consola.leerNombreProfesor(), CORREO_VALIDO));
			modelo.borrarProfesor(profesorBorrar);
			System.out.println("Profesor/a proporcionado/a ha sido borrado/a del sistema");				
		} catch (OperationNotSupportedException | IllegalArgumentException e) {
			System.out.println(ERROR + e.getMessage());
			comenzar();
		}
	}

	public void buscarProfesor() {
		Consola.mostrarCabecera("Buscar Profesor/a");
		Profesor profesorEncontrado = modelo.buscarProfesor(new Profesor(Consola.leerNombreProfesor(), CORREO_VALIDO));
		if (profesorEncontrado != null) {
			System.out.println(profesorEncontrado);
		}else {
			System.out.println("Profesor/a proporcionado/a no ha sido encontrado/a del sistema");
			comenzar();
		}
	
	}

	public void listarProfesores() {
		Consola.mostrarCabecera("Listar Profesores");
		String[] profesores = modelo.representarProfesores();
		if (modelo.getNumProfesores() > 0) {
			for (String profesor : profesores) {
				System.out.println(profesor);
			}
			comenzar();
		} else {
			System.out.println("No existen profesores para listar actualmente.");
		}
		comenzar();
	}

	public void realizarReserva() {
		Consola.mostrarCabecera("Realizar Reserva");
		try {
			Reserva reserva;
			Profesor profesor = new Profesor(
					modelo.buscarProfesor(new Profesor(Consola.leerNombreProfesor(), CORREO_VALIDO)));
			reserva = leerReserva(profesor);
			if (reserva != null) {
				modelo.realizarReserva(reserva);
				System.out.println("La reserva ha sido realizada y añadida al sistema.");
				System.out.println("Los datos de la reserva son" + reserva.toString());
				comenzar();
			} else {
				System.out.println("Ya hay una reserva realizada en este tramo");
				comenzar();
			}
		} catch (OperationNotSupportedException | IllegalArgumentException e) {
			System.out.println(ERROR + e.getMessage());
			comenzar();
		}
	}

	private Reserva leerReserva(Profesor profesorReserva) {
		Reserva reservaLeida;
		Permanencia permanencia;
		Aula aula = modelo.buscarAula(new Aula(Consola.leerNombreAula()));
		permanencia = new Permanencia(Consola.leerDia(), Consola.leerTramo());
		if (modelo.consultarDisponibilidad(aula, permanencia)) {
			reservaLeida = new Reserva(profesorReserva, aula, permanencia);
		} else {
			return null;
		}
		return reservaLeida;
	}

	public void anularReserva() {
		Consola.mostrarCabecera("Anula Reserva");
		try {
			Profesor profesor = new Profesor(
					modelo.buscarProfesor(new Profesor(Consola.leerNombreProfesor(), CORREO_VALIDO)));
			Aula aula = modelo.buscarAula(new Aula(Consola.leerNombreAula()));
			Permanencia permanencia = new Permanencia(Consola.leerDia(), Consola.leerTramo());
			Reserva reservaAnular = new Reserva(profesor, aula, permanencia);
			modelo.anularReserva(reservaAnular);
			comenzar();
		} catch (OperationNotSupportedException | IllegalArgumentException e) {
			System.out.println(ERROR + e.getMessage());
			comenzar();
		}
	}

	public void listarReservas() {
		Consola.mostrarCabecera("Listar Reservas");
		String[] reservas = modelo.representarReservas();
		if (modelo.getNumReservas() > 0) {
			for (String reserva : reservas) {
				System.out.println(reserva);
			}
			comenzar();
		} else {
			System.out.println("No existen reservas para listar actualmente");
			comenzar();
		}
	}

	public void listarReservasAula() {
		Consola.mostrarCabecera("Listar Reservas por Aula");
		Aula reservasAula = new Aula(Consola.leerAula());
		Reserva[] reservaAula = modelo.getReservasAula(reservasAula);
		if (modelo.getNumReservas() > 0) {
			if (reservaAula[0] != null) {
				for (Reserva reserva : reservaAula) {
					try {
						System.out.println(reserva.toString());
					} catch (Exception e) {
						comenzar();
					}
				}
				comenzar();
			} else {
				System.out.println("No existen reservas para la aula");
				comenzar();
			}
		} else {
			System.out.println("No existen reservas para listar actualmente");
			comenzar();
		}

	}

	public void listarReservasProfesor() {
		Consola.mostrarCabecera("Listar Reservas por Profesor");
		Profesor profesor = new Profesor(
				modelo.buscarProfesor(new Profesor(Consola.leerNombreProfesor(), CORREO_VALIDO)));
		Reserva[] reservaProfesor = modelo.getReservasProfesor(profesor);
		if (modelo.getNumReservas() > 0) {
			if (reservaProfesor[0] != null) {
				for (Reserva reserva: reservaProfesor) {
					try {
						System.out.println(reserva.toString());
					} catch (NullPointerException e) {
						comenzar();
					}
				}
				comenzar();
			} else {
				System.out.println("No existen reservas para el profesor");
				comenzar();
			}
		} else {
			System.out.println("No existen reservas para listar actualmente");
			comenzar();
		}
	}

	public void listarReservasPermanencia() {
		Consola.mostrarCabecera("Listar Reservas por Permanencia");
		Permanencia permanencia = new Permanencia(Consola.leerDia(), Consola.leerTramo());
		Reserva[] reservaPermanencia = modelo.getReservasPermanencia(permanencia);
		if (modelo.getNumReservas() > 0) {
			if (reservaPermanencia[0] != null) {
				for (Reserva reserva : reservaPermanencia) {
					try {
						System.out.println(reserva.toString());
					}catch (Exception e) {
						comenzar();
					}
				}
				comenzar();
			} else {
				System.out.println("No existen reservas para la permanencia.");
				comenzar();
			}
		} else {
			System.out.println("No existen reservas para listar actualmente");
			comenzar();
		}
	}

	public void consultarDisponibilidad() {
		if (modelo.consultarDisponibilidad(Consola.leerAula(),
				new Permanencia(Consola.leerDia(), Consola.leerTramo()))) {
			System.out.println("El Aula esta disponible en el tramo solicitado.");
			comenzar();
		} else {
			System.out.println("El Aula no esta disponible en el tramo solicitado.");
			comenzar();
		}
	}
}
