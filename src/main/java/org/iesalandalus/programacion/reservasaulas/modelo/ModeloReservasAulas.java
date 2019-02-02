package org.iesalandalus.programacion.reservasaulas.modelo;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.reservasaulas.modelo.dao.*;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Aula;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Permanencia;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Profesor;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Reserva;

public class ModeloReservasAulas {
	private Profesores profesores;
	private Aulas aulas;
	private Reservas reservas;

	public ModeloReservasAulas() {
		profesores = new Profesores();
		aulas = new Aulas();
		reservas = new Reservas();
	}

	public Aula[] getAulas() {
		return aulas.getAulas();
	}

	public int getNumAulas() {
		return aulas.getNumAulas();
	}

	public String[] representarAulas() {
		return aulas.representar();
	}

	public Aula buscarAula(Aula aulaBuscar) {
		return aulas.buscar(aulaBuscar);
	}

	public void insertarAula(Aula aulaInsertar) throws OperationNotSupportedException {
		aulas.insertar(aulaInsertar);
	}

	public void borrarAula(Aula aulaBorrar) throws OperationNotSupportedException {
		aulas.borrar(aulaBorrar);
	}

	public Profesor[] getProfesores() {
		return profesores.getProfesores();
	}

	public int getNumProfesores() {
		return profesores.getNumProfesores();
	}

	public String[] representarProfesores() {
		return profesores.representar();
	}

	public Profesor buscarProfesor(Profesor profesorBuscar) {
		return profesores.buscar(profesorBuscar);
	}

	public void insertarProfesor(Profesor profesorInsertar) throws OperationNotSupportedException {
		profesores.insertar(profesorInsertar);
	}

	public void borrarProfesor(Profesor profesorBorrar) throws OperationNotSupportedException {
		profesores.borrar(profesorBorrar);
	}

	public Reserva[] getReservas() {
		return reservas.getReservas();
	}

	public int getNumReservas() {
		return reservas.getNumReservas();
	}

	public String[] representarReservas() {
		return reservas.representar();
	}

	public Reserva buscarReserva(Reserva reservaBuscar) {
		return reservas.buscar(reservaBuscar);
	}

	public void realizarReserva(Reserva reservaRealizar) throws OperationNotSupportedException {
		reservas.insertar(reservaRealizar);
	}

	public void anularReserva(Reserva reservaAnular) throws OperationNotSupportedException {
		reservas.borrar(reservaAnular);
	}

	public Reserva[] getReservasAula(Aula aulaReservas) {
		return reservas.getReservasAula(aulaReservas);
	}

	public Reserva[] getReservasProfesor(Profesor profesorReservas) {
		return reservas.getReservasProfesor(profesorReservas);
	}

	public Reserva[] getReservasPermanencia(Permanencia permanenciaReservas) {
		return reservas.getReservasPermanencia(permanenciaReservas);
	}

	public boolean consultarDisponibilidad(Aula aula, Permanencia permanencia) {
		return reservas.consultarDisponibilidad(aula, permanencia);
	}
}