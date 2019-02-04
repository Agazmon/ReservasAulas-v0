package org.iesalandalus.programacion.reservasaulas.modelo.dominio;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.google.common.base.Throwables;

public class Permanencia {
	private LocalDate dia;
	private static final DateTimeFormatter FORMATO_DIA = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	private Tramo tramoPermanencia;

	public Permanencia(LocalDate fechaPasada, Tramo tramoPasado) {
		setDia(fechaPasada);
		setTramo(tramoPasado);
	}

	public Permanencia(Permanencia permanenciaPasada) {
		if (permanenciaPasada == null) {
			throw new IllegalArgumentException("No se puede copiar una permanencia nula.");
		}
		setDia(permanenciaPasada.dia);
		setTramo(permanenciaPasada.tramoPermanencia);

	}

	public LocalDate getDia() {
		return dia;
	}

	private void setDia(LocalDate diaPasado) {
		if (diaPasado == null) {
			throw new IllegalArgumentException("El día de una permanencia no puede ser nulo.");
		} else if (diaPasado.equals("")) {
			throw new IllegalArgumentException("No se puede usar una fecha vacia");
		} else {
			dia = diaPasado;
		}
	}

	public Tramo getTramo() {
		return tramoPermanencia;
	}

	private void setTramo(Tramo tramoPasado) {
		if (tramoPasado == null) {
			throw new IllegalArgumentException("El tramo de una permanencia no puede ser nulo.");
		} else if (tramoPasado.equals(Tramo.MANANA)) {
			this.tramoPermanencia = Tramo.MANANA;
		} else if (tramoPasado.equals(Tramo.TARDE)) {
			this.tramoPermanencia = Tramo.TARDE;
		} else {
			throw new IllegalArgumentException("El tramo pasado no es valido");
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dia == null) ? 0 : dia.hashCode());
		result = prime * result + ((tramoPermanencia == null) ? 0 : tramoPermanencia.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Permanencia other = (Permanencia) obj;
		if (dia == null) {
			if (other.dia != null)
				return false;
		} else if (!dia.equals(other.dia))
			return false;
		if (tramoPermanencia != other.tramoPermanencia)
			return false;
		return true;
	}

	@Override
	public String toString() {
		// dia.format(FORMATO_DIA);
		return "[dia=" + dia.format(FORMATO_DIA) + ", tramo=" + tramoPermanencia + "]";
	}

}
