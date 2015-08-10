package org.uqbar.edu.paiu.examples.celulares.domain;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import org.uqbar.commons.model.UserException;
import org.junit.Before;
import org.junit.Test;

public class TestCelular {
	Celular celularNoResumen;
	Celular celularSiResumen;
	
	@Before
	public void init() {
		celularNoResumen = new Celular();
		celularNoResumen.setNumero(17256124);
		celularNoResumen.setNombre("Juan Carlos Perez");
		ModeloCelular nokia = new ModeloCelular("NOKIA",new BigDecimal(150));
		nokia.setRequiereResumenCuenta(false);
		celularNoResumen.setModeloCelular(nokia); 
		celularSiResumen = new Celular();
		celularSiResumen.setNumero(23563269);
		celularSiResumen.setNombre("Mariana Moretti");
		ModeloCelular nokia11 = new ModeloCelular("NOKIA 11",new BigDecimal(350));
		nokia11.setRequiereResumenCuenta(true);
		celularSiResumen.setModeloCelular(nokia11);
	}
	
	@Test
	public void celularNoRecibeResumenCuenta() {
		assertFalse(celularNoResumen.getRecibeResumenCuenta());
	}
	
	@Test
	public void celularSiRecibeResumenCuenta() {
		assertTrue(celularSiResumen.getRecibeResumenCuenta());
	}
	
	@Test
	public void celularCambiaRecibeResumenCuenta() {
		ModeloCelular modeloQueRequiereResumen = new ModeloCelular("prueba", new BigDecimal(1), true);
		celularNoResumen.setModeloCelular(modeloQueRequiereResumen);
		assertTrue(celularNoResumen.getRecibeResumenCuenta());
	}
	
	@Test(expected = UserException.class)
	public void celularNumeroGrande() {
		Celular celularMalo = new Celular();
		celularMalo.setNumero(891724861);
		celularMalo.validar();
	}

	@Test(expected = UserException.class)
	public void celularNoIngresoNumero() {
		Celular celularMalo = new Celular();
		celularMalo.setNombre("Pepe");
		celularMalo.validar();
	}

	@Test(expected = UserException.class)
	public void celularNoIngresoNombre() {
		Celular celularMalo = new Celular();
		celularMalo.setNumero(19246218);
		celularMalo.validar();
	}

	@Test(expected = UserException.class)
	public void celularNoIngresoModelo() {
		Celular celularMalo = new Celular();
		celularMalo.setNumero(19246218);
		celularMalo.setNombre("Pepe");
		celularMalo.validar();
	}
	
	@Test
	public void celularValidacionOk() {
		celularNoResumen.validar();
	}

}
