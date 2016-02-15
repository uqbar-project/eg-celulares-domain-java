package org.uqbar.edu.paiu.examples.celulares.home;

import ar.edu.celulares.domain.Celular;
import ar.edu.celulares.repo.RepositorioCelulares;

public class MockHomeCelulares extends RepositorioCelulares {
	@Override 
	public void init() {
		Celular unCelular = new Celular();
		unCelular.setNombre("Ricardo Ruben");
		unCelular.setNumero(44667816);
		unCelular.setModeloCelular(getModelo("NOKIA LUMIA 625"));
		unCelular.setRecibeResumenCuenta(false);
		this.create(unCelular);
	}
}
