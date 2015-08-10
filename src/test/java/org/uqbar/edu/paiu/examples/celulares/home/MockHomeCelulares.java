package org.uqbar.edu.paiu.examples.celulares.home;

import org.uqbar.edu.paiu.examples.celulares.domain.Celular;

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
