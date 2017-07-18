package org.uqbar.edu.paiu.examples.celulares.applicationModel;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.uqbar.commons.applicationContext.ApplicationContext;

import ar.edu.celulares.applicationModel.BuscadorCelular;
import ar.edu.celulares.domain.Celular;
import ar.edu.celulares.repo.RepositorioCelulares;

public class TestBuscadorCelular extends AbstractTestBuscadorCelular {

	private BuscadorCelular buscadorFallido;
	
	@Before
	@Override 
	public void init() {
		super.init();
		buscadorFallido = new BuscadorCelular();
		buscadorFallido.setNombre("XXXX");
		ApplicationContext.getInstance().configureSingleton(Celular.class, new RepositorioCelulares());
		//RepositorioCelulares.repositorioCelulares();
	}

	@Test
	public void buscarSinResultados() {
		this.buscadorFallido.search();
		assertEquals(0, buscadorFallido.getResultados().size());
	}
	
	@Test
	public void buscarDodinos() {
		this.searcher.search();
		assertEquals(2, searcher.getResultados().size());
	}

	@Test
	public void buscarDodinosConNumeroErroneo() {
		this.searcher.setNumero(17715274);
		this.searcher.search();
		assertEquals(0, searcher.getResultados().size());
	}

}
