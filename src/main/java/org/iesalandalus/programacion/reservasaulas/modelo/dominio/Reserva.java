package org.iesalandalus.programacion.reservasaulas.modelo.dominio;

public class Reserva {
	private Profesor profesorR;
	private Aula aulaR;
	private Permanencia permanenciaR;

	public Reserva(Profesor profesor, Aula aula, Permanencia permanencia) {
		setProfesor(profesor);
		setAula(aula);
		setPermanencia(permanencia);
	}

	public Reserva(Reserva reserva) {
		if (reserva == null) {
			throw new IllegalArgumentException("No se puede copiar una reserva nula.");
		}
		setProfesor(reserva.profesorR);
		setAula(reserva.aulaR);
		setPermanencia(reserva.permanenciaR);
	}

	private void setProfesor(Profesor profesor) {
		if (profesor == null) {
			throw new IllegalArgumentException("La reserva debe estar a nombre de un profesor.");
		} else {
			profesorR = new Profesor(profesor);
		}
	}

	public Profesor getProfesor() {
		return new Profesor(profesorR);
	}

	private void setAula(Aula aula) {
		if (aula == null) {
			throw new IllegalArgumentException("La reserva debe ser para un aula concreta.");
		} else {
			aulaR = new Aula(aula);
		}
	}

	public Aula getAula() {
		return new Aula(aulaR);
	}

	private void setPermanencia(Permanencia permanencia) {
		if (permanencia==null) {
			throw new IllegalArgumentException("La reserva se debe hacer para una permanencia concreta.");
		} else {
			permanenciaR = new Permanencia(permanencia);
		}
	}
	public Permanencia getPermanencia() {
		return new Permanencia(permanenciaR);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((aulaR == null) ? 0 : aulaR.hashCode());
		result = prime * result + ((permanenciaR == null) ? 0 : permanenciaR.hashCode());
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
		Reserva other = (Reserva) obj;
		if (aulaR == null) {
			if (other.aulaR != null)
				return false;
		} else if (!aulaR.equals(other.aulaR))
			return false;
		if (permanenciaR == null) {
			if (other.permanenciaR != null)
				return false;
		} else if (!permanenciaR.equals(other.permanenciaR))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "[profesor=" + profesorR + ", aula=" + aulaR + ", permanencia=" + permanenciaR + "]";
	}
	
}
