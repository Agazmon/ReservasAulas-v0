package org.iesalandalus.programacion.reservasaulas.modelo.dominio;

public class Reserva {
	private Profesor profesorReserva;
	private Aula aulaReserva;
	private Permanencia permanenciaReserva;

	public Reserva(Profesor profesor, Aula aula, Permanencia permanencia) {
		setProfesor(profesor);
		setAula(aula);
		setPermanencia(permanencia);
	}

	public Reserva(Reserva reserva) {
		if (reserva == null) {
			throw new IllegalArgumentException("No se puede copiar una reserva nula.");
		}
		setProfesor(reserva.profesorReserva);
		setAula(reserva.aulaReserva);
		setPermanencia(reserva.permanenciaReserva);
	}

	private void setProfesor(Profesor profesor) {
		if (profesor == null) {
			throw new IllegalArgumentException("La reserva debe estar a nombre de un profesor.");
		} else {
			profesorReserva = new Profesor(profesor);
		}
	}

	public Profesor getProfesor() {
		return new Profesor(profesorReserva);
	}

	private void setAula(Aula aula) {
		if (aula == null) {
			throw new IllegalArgumentException("La reserva debe ser para un aula concreta.");
		} else {
			aulaReserva = new Aula(aula);
		}
	}

	public Aula getAula() {
		return new Aula(aulaReserva);
	}

	private void setPermanencia(Permanencia permanencia) {
		if (permanencia==null) {
			throw new IllegalArgumentException("La reserva se debe hacer para una permanencia concreta.");
		} else {
			permanenciaReserva = new Permanencia(permanencia);
		}
	}
	public Permanencia getPermanencia() {
		return new Permanencia(permanenciaReserva);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((aulaReserva == null) ? 0 : aulaReserva.hashCode());
		result = prime * result + ((permanenciaReserva == null) ? 0 : permanenciaReserva.hashCode());
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
		if (aulaReserva == null) {
			if (other.aulaReserva != null)
				return false;
		} else if (!aulaReserva.equals(other.aulaReserva))
			return false;
		if (permanenciaReserva == null) {
			if (other.permanenciaReserva != null)
				return false;
		} else if (!permanenciaReserva.equals(other.permanenciaReserva))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "[profesor=" + profesorReserva + ", aula=" + aulaReserva + ", permanencia=" + permanenciaReserva + "]";
	}
	
}
