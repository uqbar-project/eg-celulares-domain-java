package org.uqbar.edu.paiu.examples.celulares.applicationModel;

import org.junit.Before;
import org.uqbar.commons.applicationContext.ApplicationContext;

import ar.edu.celulares.applicationModel.BuscadorCelular;
import ar.edu.celulares.domain.ModeloCelular;
import ar.edu.celulares.repo.RepositorioModelos;

public class AbstractTestBuscadorCelular {
	protected BuscadorCelular searcher;

	@Before
	public void init() {
		searcher = new BuscadorCelular();
		searcher.setNombre("Dodi");
		ApplicationContext.getInstance().configureSingleton(ModeloCelular.class, new RepositorioModelos());
	}
}
