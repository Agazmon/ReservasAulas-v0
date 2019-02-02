package org.iesalandalus.programacion.reservasaulas.modelo.dao;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Aula;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Permanencia;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Profesor;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Reserva;

public class Reservas {

	private static final int MAX_RESERVAS = 40;

	private Reserva[] coleccionReservas;
	private int numReservas;

	public Reservas() {
		coleccionReservas = new Reserva[MAX_RESERVAS];
		numReservas = 0;
	}

	public Reservas(Reservas reservas) {
		setReservas(reservas);
	}

	private void setReservas(Reservas reservas) {
		if (reservas == null) {
			throw new IllegalArgumentException("No se pueden copiar reservas nulas.");
		}
		coleccionReservas = copiaProfundaReservas(reservas.coleccionReservas);
		numReservas = reservas.numReservas;
	}

	private Reserva[] copiaProfundaReservas(Reserva[] reservas) {
		Reserva[] otrasReservas = new Reserva[reservas.length];
		for (int i = 0; i < reservas.length && reservas[i] != null; i++) {
			otrasReservas[i] = new Reserva(reservas[i]);
		}
		return otrasReservas;
	}

	public Reserva[] getReservas() {
		return copiaProfundaReservas(coleccionReservas);
	}

	public int getNumReservas() {
		return numReservas;
	}

	public void insertar(Reserva reserva) throws OperationNotSupportedException {
		if (reserva == null) {
			throw new IllegalArgumentException("No se puede realizar una reserva nula.");
		}
		int indice = buscarIndiceReserva(reserva);
		if (!indiceNoSuperaTamano(indice)) {
			coleccionReservas[indice] = new Reserva(reserva);
			numReservas++;
		} else {
			if (indiceNoSuperaCapacidad(indice)) {
				throw new OperationNotSupportedException("La reserva ya existe.");
			} else {
				throw new OperationNotSupportedException("No se aceptan más reservas.");
			}
		}
	}

	private int buscarIndiceReserva(Reserva reserva) {
		int indice = 0;
		boolean reservaEncontrada = false;
		while (indiceNoSuperaTamano(indice) && !reservaEncontrada) {
			if (coleccionReservas[indice].equals(reserva)) {
				reservaEncontrada = true;
			} else {
				indice++;
			}
		}
		return indice;
	}

	private boolean indiceNoSuperaTamano(int indice) {
		return indice < numReservas;
	}

	private boolean indiceNoSuperaCapacidad(int indice) {
		return indice < MAX_RESERVAS;
	}

	public Reserva buscar(Reserva reserva) {
		int indice = 0;
		indice = buscarIndiceReserva(reserva);
		if (indiceNoSuperaTamano(indice)) {
			return new Reserva(coleccionReservas[indice]);
		} else {
			return null;
		}
	}

	public void borrar(Reserva reserva) throws OperationNotSupportedException {
		if (reserva == null) {
			throw new IllegalArgumentException("No se puede anular una reserva nula.");
		}
		int indice = buscarIndiceReserva(reserva);
		if (indiceNoSuperaTamano(indice)) {
			desplazarUnaPosicionHaciaIzquierda(indice);
		} else {
			throw new OperationNotSupportedException("La reserva a anular no existe.");
		}
	}

	private void desplazarUnaPosicionHaciaIzquierda(int indice) {
		for (int i = indice; i < numReservas - 1; i++) {
			coleccionReservas[i] = coleccionReservas[i + 1];
		}
		coleccionReservas[numReservas] = null;
		numReservas--;
	}

	public String[] representar() {
		String[] representacion = new String[numReservas];
		for (int i = 0; indiceNoSuperaTamano(i); i++) {
			representacion[i] = coleccionReservas[i].toString();
		}
		return representacion;
	}

	public Reserva[] getReservasProfesor(Profesor profesor) {
		Reserva[] reservasProfesor = new Reserva[MAX_RESERVAS];
		int numReservasProfesor = 0;
		for (int i = 0; i < MAX_RESERVAS && indiceNoSuperaTamano(i); i++) {
			if (coleccionReservas[i].getProfesor().equals(profesor)) {
				reservasProfesor[numReservasProfesor] = new Reserva(coleccionReservas[i]);
				numReservasProfesor++;
			}
		}
		return copiaProfundaReservas(reservasProfesor);
	}

	public Reserva[] getReservasAula(Aula aula) {
		Reserva[] reservasAula = new Reserva[MAX_RESERVAS];
		int numReservasAula = 0;
		for (int i = 0; i < MAX_RESERVAS && indiceNoSuperaTamano(i); i++) {
			if (coleccionReservas[i].getAula().equals(aula)) {
				reservasAula[numReservasAula] = new Reserva(coleccionReservas[i]);
				numReservasAula++;
			}
		}
		return copiaProfundaReservas(reservasAula);
	}

	public Reserva[] getReservasPermanencia(Permanencia permanencia) {
		Reserva[] reservasPermanencia = new Reserva[MAX_RESERVAS];
		int numReservasPermanencia = 0;
		for (int i = 0; i < MAX_RESERVAS && indiceNoSuperaTamano(i); i++) {
			if (coleccionReservas[i].getPermanencia().equals(permanencia)) {
				reservasPermanencia[numReservasPermanencia] = new Reserva(coleccionReservas[i]);
				numReservasPermanencia++;
			}
		}
		return copiaProfundaReservas(reservasPermanencia);
	}

	public boolean consultarDisponibilidad(Aula aula, Permanencia permanencia) {
		if(aula==null) {
			throw new IllegalArgumentException("No se puede consultar la disponibilidad de un aula nula.");
		}
		if(permanencia==null) {
			throw new IllegalArgumentException("No se puede consultar la disponibilidad de una permanencia nula.");
		}
		
		Profesor profesorConsulta = new Profesor("Profesor","correo@correo.com");
		Reserva reservaConsulta = new Reserva(profesorConsulta, aula, permanencia);
		for(int i=0; i<numReservas&&indiceNoSuperaTamano(i);i++) {
			if(coleccionReservas[i].equals(reservaConsulta)) {
				return false;
			} else {
				i++;
			}
		}
		return true;
		}	
}