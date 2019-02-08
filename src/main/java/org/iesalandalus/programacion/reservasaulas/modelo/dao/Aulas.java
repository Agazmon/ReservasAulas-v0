package org.iesalandalus.programacion.reservasaulas.modelo.dao;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Aula;

public class Aulas {
	private static final int MAX_AULAS = 70;
	private int numAulas;
	private Aula[] coleccionAulas;

	public Aulas() {
		coleccionAulas = new Aula[MAX_AULAS];
		numAulas = 0;
	}

	public Aulas(Aulas aulas) {
		setAulas(aulas);
	}

	private void setAulas(Aulas aulas) {
		if (aulas == null) {
			throw new IllegalArgumentException("No se pueden copiar aulas nulas.");
		} else {
			this.coleccionAulas = copiaProfundaAulas(aulas.coleccionAulas);
			numAulas = aulas.numAulas;
		}
	}

	private Aula[] copiaProfundaAulas(Aula[] arrayAula) {
		Aula[] aulasCopia = new Aula[arrayAula.length];
		for (int i = 0; (i < arrayAula.length) && arrayAula[i] != null; i++) {
			aulasCopia[i] = new Aula(arrayAula[i]);
		}
		return aulasCopia;
	}

	public Aula[] getAulas() {
		return copiaProfundaAulas(coleccionAulas);
	}

	public int getNumAulas() {
		return this.numAulas;
	}

	public void insertar(Aula aula) throws OperationNotSupportedException {
		if (aula == null) {
			throw new IllegalArgumentException("No se puede insertar un aula nula.");
		} else {
			int indice = buscarIndiceAula(aula);
			if (!indiceNoSuperaTamano(indice)) {
				coleccionAulas[indice] = new Aula(aula);
				numAulas++;
			} else {
				if (!indiceNoSuperaCapacidad(indice)) {
					throw new OperationNotSupportedException("No se pueden almacenar mas aulas");
				} else {
					throw new OperationNotSupportedException("El aula ya existe.");
				}
			}

		}

	}

	private int buscarIndiceAula(Aula aula) {
		int indiceAula = 0;
		boolean comprobacion = false;
		while (indiceNoSuperaTamano(indiceAula) && !comprobacion) {
			if (coleccionAulas[indiceAula].equals(aula)) {
				comprobacion = true;
			} else {
				indiceAula++;
			}
		}
		return indiceAula;
	}

	private boolean indiceNoSuperaTamano(int indice) {
		return indice < numAulas;
	}

	private boolean indiceNoSuperaCapacidad(int indice) {
		return indice < MAX_AULAS;
	}

	public Aula buscar(Aula aula) {
		if (aula == null) {
			return null;
		}
		int indice = buscarIndiceAula(aula);
		if (indiceNoSuperaTamano(indice)) {
			return new Aula(coleccionAulas[indice]);
		} else {
			return null;
		}
	}

	public void borrar(Aula aula) throws OperationNotSupportedException {
		if (aula == null) {
			throw new IllegalArgumentException("No se puede borrar un aula nula.");
		}
		int indice = buscarIndiceAula(aula);
		if (indiceNoSuperaTamano(indice)) {
			coleccionAulas[indice] = null;
			desplazarUnaPosicionHaciaIzquierda(indice);
			numAulas--;
		} else {
			throw new OperationNotSupportedException("El aula a borrar no existe.");
		}
	}

	private void desplazarUnaPosicionHaciaIzquierda(int indice) {
		for (int i = indice; i < numAulas | !indiceNoSuperaCapacidad(i); i++) {
			if (indiceNoSuperaTamano(indice)) {
				if (coleccionAulas[i + 1] != null) {
					coleccionAulas[i] = coleccionAulas[i + 1];
					coleccionAulas[i + 1] = null;
				}
			}
		}

	}

	public String[] representar() {
		String[] arrayString;

		if (numAulas == 0) {
			throw new UnsupportedOperationException("No existen aulas");
		} else {
			arrayString = new String[numAulas];
			for (int i = 0; i < numAulas; i++) {
				arrayString[i] = this.coleccionAulas[i].toString();
			}
			return arrayString;
		}

	}

}
