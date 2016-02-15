package ar.edu.celulares.repo;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections15.Predicate;
import org.uqbar.commons.model.CollectionBasedRepo;
import org.uqbar.commons.model.UserException;
import org.uqbar.commons.utils.Observable;

import ar.edu.celulares.domain.Celular;
import ar.edu.celulares.domain.ModeloCelular;

/**
 * 
 * @author npasserini
 */
@Observable
public class RepositorioCelulares extends CollectionBasedRepo<Celular>{
	private static RepositorioCelulares instance = new RepositorioCelulares();

	public static RepositorioCelulares repositorioCelulares() {
		return instance;
	}
	
	public RepositorioCelulares() {
		this.init();
	}

	public void init(){
		this.create("Laura Iturbe", 88022202, getModelo("NOKIA LUMIA 625"), false);
		this.create("Julieta Passerini", 45636453, getModelo("NOKIA ASHA 501"), false);
		this.create("Debora Fortini", 45610892, getModelo("NOKIA ASHA 501"), true);
		this.create("Chiara Dodino", 68026976, getModelo("NOKIA ASHA 501"), false);
		this.create("Melina Dodino", 40989911, getModelo("LG OPTIMUS L3 II"), true);
	}
	
	public ModeloCelular getModelo(String modeloDescripcion) {
		return RepositorioModelos.repositorioModelos().get(modeloDescripcion);
	}
	// ********************************************************
	// ** Altas y bajas
	// ********************************************************
	public void create(String pNombre, Integer pNumero, ModeloCelular pModeloCelular, Boolean pRecibeResumenCuenta) {
		this.create(new Celular(pNombre,pNumero,pModeloCelular,pRecibeResumenCuenta));
	}

	@Override 
	public void validateCreate(Celular celular) {
		celular.validar();
		validarClientesDuplicados(celular);
	}

	/**
	 * Valida que no haya dos clientes con el mismo número de celular
	 */
	public void validarClientesDuplicados(Celular celular) {
		int numero = celular.getNumero();
		if (!this.search(numero).isEmpty()) {
			throw new UserException("Ya existe un celular con el número: " + numero);
		}
	}

	// ********************************************************
	// ** Búsquedas
	// ********************************************************
	public List<Celular>search(Integer numero) {
		return this.search(numero, null);
	}

	/**
	 * Busca los celulares que coincidan con los datos recibidos. Tanto número como nombre pueden ser nulos,
	 * en ese caso no se filtra por ese atributo.
	 *
	 * Soporta búsquedas por substring, por ejemplo el celular (12345, "Juan Gonzalez") será contemplado por
	 * la búsqueda (23, "Gonza")
	 */
	public List<Celular> search(Integer numero, String nombre) {
		return allInstances().stream().filter(celular -> this.match(numero, celular.getNumero()) && this.match(nombre, celular.getNombre())).collect(Collectors.toList());
	}

	public boolean match(Object expectedValue, Object realValue) {
		if (expectedValue == null) {
			return true;
		}
		if (realValue == null) {
			return false;
		}
		return realValue.toString().toLowerCase().contains(expectedValue.toString().toLowerCase());
	}

	@Override
	public Class<Celular> getEntityType() {
		return Celular.class;
	}

	@Override
	public Celular createExample() {
		return new Celular();
	}

	@Override
	protected Predicate<Celular> getCriterio(Celular example) {
		return null;
	}
}
