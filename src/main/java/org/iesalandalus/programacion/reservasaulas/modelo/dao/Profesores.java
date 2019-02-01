package org.iesalandalus.programacion.reservasaulas.modelo.dao;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Profesor;

public class Profesores {

	private static final int MAX_PROFESORES = 40;

	private Profesor[] coleccionProfesores;
	private int numProfesores;

	public Profesores() {
		coleccionProfesores = new Profesor[MAX_PROFESORES];
		numProfesores = 0;
	}

	public Profesores(Profesores profesores) {
		setProfesores(profesores);
	}

	private void setProfesores(Profesores profesores) {
		if (profesores == null) {
			throw new IllegalArgumentException("No se pueden copiar profesores nulos.");
		}
		coleccionProfesores = copiaProfundaProfesores(profesores.coleccionProfesores);
		numProfesores = profesores.numProfesores;
	}

	private Profesor[] copiaProfundaProfesores(Profesor[] profesores) {
		Profesor[] otrosProfesores = new Profesor[profesores.length];
		for (int i = 0; i < profesores.length && profesores[i] != null; i++) {
			otrosProfesores[i] = new Profesor(profesores[i]);
		}
		return otrosProfesores;
	}

	public Profesor[] getProfesores() {
		return copiaProfundaProfesores(coleccionProfesores);
	}

	public int getNumProfesores() {
		return numProfesores;
	}

	public void insertar(Profesor profesor) throws OperationNotSupportedException {
		if (profesor == null) {
			throw new IllegalArgumentException("No se puede insertar un profesor nulo.");
		}
		int indice = buscarIndiceProfesor(profesor);
		if (!indiceNoSuperaTamano(indice)) {
			coleccionProfesores[indice] = new Profesor(profesor);
			numProfesores++;
		} else {
			if (indiceNoSuperaCapacidad(indice)) {
				throw new OperationNotSupportedException("El profesor ya existe.");
			} else {
				throw new OperationNotSupportedException("No se aceptan más profesores.");
			}		
		}
	}
	
	private int buscarIndiceProfesor(Profesor profesor) {
		int indice = 0;
		boolean profesorEncontrado = false;
		while (indiceNoSuperaTamano(indice) && !profesorEncontrado) {
			if (coleccionProfesores[indice].equals(profesor)) {
				profesorEncontrado = true;
			} else {
				indice++;
			}
		}
		return indice;
	}
	
	private boolean indiceNoSuperaTamano(int indice) {
		return indice < numProfesores;
	}
	
	private boolean indiceNoSuperaCapacidad(int indice) {
		return indice < MAX_PROFESORES;
	}
	
	public Profesor buscar(Profesor profesor) {
		int indice = 0;
		indice = buscarIndiceProfesor(profesor);
		if (indiceNoSuperaTamano(indice)) {
			return new Profesor(coleccionProfesores[indice]);
		} else {
			return null;
		}
	}
	
	public void borrar(Profesor profesor) throws OperationNotSupportedException {
		if (profesor == null) {
			throw new IllegalArgumentException("No se puede borrar un profesor nulo.");
		}
		int indice = buscarIndiceProfesor(profesor);
		if (indiceNoSuperaTamano(indice)) {
			desplazarUnaPosicionHaciaIzquierda(indice);
		}
		else {
			throw new OperationNotSupportedException("El profesor a borrar no existe.");
		}
	}

	private void desplazarUnaPosicionHaciaIzquierda(int indice) {
		for (int i = indice; i < numProfesores - 1; i++) {
			coleccionProfesores[i] = coleccionProfesores[i+1];
		}
		coleccionProfesores[numProfesores] = null;
		numProfesores--;
	}
	
	public String[] representar() {
		String[] representacion = new String[numProfesores];
		for (int i = 0; indiceNoSuperaTamano(i); i++) {
			representacion[i] = coleccionProfesores[i].toString();
		}
		return representacion;
	}
}