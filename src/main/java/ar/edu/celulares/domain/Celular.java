package ar.edu.celulares.domain;

import org.uqbar.commons.model.Entity;
import org.uqbar.commons.model.annotations.Dependencies;
import org.uqbar.commons.model.annotations.Observable;
import org.uqbar.commons.model.annotations.Transactional;
import org.uqbar.commons.model.exceptions.UserException;

@SuppressWarnings("serial")
@Transactional
@Observable
public class Celular extends Entity {
	private static final int MAX_NUMERO = 10000000;

	private Integer numero;
	private String nombre;
	private ModeloCelular modeloCelular;
	private Boolean recibeResumenCuenta = false;

	public Celular() {
	}

	public Celular(String nombre, Integer numero) {
		this.nombre = nombre;
		this.numero = numero;
	}

	public Celular(String nombre, Integer numero, ModeloCelular modeloCelular, boolean recibeResumenCuenta) {
		this.nombre = nombre;
		this.numero = numero;
		this.modeloCelular = modeloCelular;
		this.recibeResumenCuenta = recibeResumenCuenta;
	}

	// ********************************************************
	// ** Validacion
	// ********************************************************

	/**
	 * Valida que el celular esté correctamente cargado
	 */
	public void validar() {
		if (!this.ingresoNumero()) {
			throw new UserException("Debe ingresar número");
		}
		if (this.numero.intValue() <= MAX_NUMERO) {
			throw new UserException("El número debe ser mayor a " + MAX_NUMERO);
		}
		if (!this.ingresoNombre()) {
			throw new UserException("Debe ingresar nombre");
		}
		if (this.modeloCelular == null) {
			throw new UserException("Debe ingresar un modelo de celular");
		}
	}

	public boolean ingresoNombre() {
		return this.nombre != null && !this.nombre.trim().equals("");
	}

	public boolean ingresoNumero() {
		return this.numero != null;
	}

	// ********************************************************
	// ** Getters y setters
	// ********************************************************

	public Integer getNumero() {
		return this.numero;
	}

	public void setNumero(Integer numero) {
		if (numero < MAX_NUMERO) {
			throw new UserException("El número de celular debe ser mayor a " + MAX_NUMERO);
		}
		this.numero = numero;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public ModeloCelular getModeloCelular() {
		return this.modeloCelular;
	}

	public void setModeloCelular(ModeloCelular modeloCelular) {
		this.modeloCelular = modeloCelular;
		this.setRecibeResumenCuenta(modeloCelular.getRequiereResumenCuenta());
	}

	public void setRecibeResumenCuenta(Boolean recibeResumenCuenta) {
		this.recibeResumenCuenta = recibeResumenCuenta;
	}

	public Boolean getRecibeResumenCuenta() {
		return this.recibeResumenCuenta;
	}

	@Dependencies("modeloCelular")
	public Boolean getHabilitaResumenCuenta() {
		return !this.modeloCelular.getRequiereResumenCuenta();
	}
	
	// ********************************************************
	// ** Misceláneos
	// ********************************************************

	@Override
	public String toString() {
		StringBuffer result = new StringBuffer();
		if (this.ingresoNombre()) {
			result.append(this.nombre);
		}
		else {
			result.append("Celular sin nombre");
		}
		if (this.modeloCelular != null) {
			result.append(" - " + this.modeloCelular);
		}
		else {
			result.append(" - sin modelo");
		}
		if (this.ingresoNumero()) {
			result.append(" - " + this.numero);
		}
		else {
			result.append(" - sin número");
		}
		if (this.recibeResumenCuenta) {
			result.append(" - recibe resumen de cuenta");
		}
		else {
			result.append(" - no recibe resumen de cuenta");
		}
		return result.toString();
	}

}
