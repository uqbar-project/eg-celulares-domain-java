package org.uqbar.edu.paiu.examples.celulares.applicationModel;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.uqbar.commons.utils.ApplicationContext;
import org.uqbar.edu.paiu.examples.celulares.domain.Celular;
import org.uqbar.edu.paiu.examples.celulares.home.MockHomeCelulares;

public class TestBuscadorCelularHomeMockeado extends
		AbstractTestBuscadorCelular {
	
	@Before
	@Override 
	public void init() {
		super.init();
		ApplicationContext.getInstance().configureSingleton(Celular.class, new MockHomeCelulares());
		//RepositorioCelulares.repositorioCelulares();
	}

	@Test
	public void buscarDodinosEnMockHome() {
		this.searcher.search();
		assertEquals(0, searcher.getResultados().size());
	}

	@Test
	public void buscarRicardoRubenEnMockHome() {
		BuscadorCelular buscadorRicardoRuben = new BuscadorCelular();
		buscadorRicardoRuben.setNombre("Ricardo");
		buscadorRicardoRuben.search();
		assertEquals(1, buscadorRicardoRuben.getResultados().size());
	}
}
