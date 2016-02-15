package ar.edu.celulares.repo;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.collections15.Predicate;
import org.uqbar.commons.model.CollectionBasedRepo;
import org.uqbar.commons.model.UserException;
import org.uqbar.commons.utils.Observable;

import ar.edu.celulares.domain.ModeloCelular;

@Observable
public class RepositorioModelos  extends CollectionBasedRepo<ModeloCelular>{
	private static RepositorioModelos instance = new RepositorioModelos();

	public static RepositorioModelos repositorioModelos() {
		return instance;
	}
	
	public RepositorioModelos() {
		this.init();
	}
	
	/**
	 * Inicialización del juego de datos del repositorio
	 */
	public void init() {
		this.create("NOKIA ASHA 501", new BigDecimal(700), true);
		this.create("LG OPTIMUS L5 II", new BigDecimal(920), false);
		this.create("LG OPTIMUS L3 II", new BigDecimal(450), true);
		this.create("NOKIA LUMIA 625", new BigDecimal(350), true);
		this.create("MOTOROLA RAZR V3", new BigDecimal(350), false);
	}
	
	public void create(String descripcion, BigDecimal costo, boolean requiereResumenCuenta) {
		ModeloCelular modelo = new ModeloCelular(descripcion, costo, requiereResumenCuenta);
		this.create(modelo);
	}

   public ModeloCelular get(String descripcion) {
      return this.getModelos()
            .stream()
            .filter(m -> m.getDescripcion().equals(descripcion))
            .findAny()
            .orElseThrow(() -> //
                  new UserException("No se encontró el modelo de celular: " + descripcion));
   }
   
	public List<ModeloCelular> getModelos() {
		return allInstances();
	}

	@Override
	public Class<ModeloCelular> getEntityType() {
		return ModeloCelular.class;
	}

	@Override
	public ModeloCelular createExample() {
		return new ModeloCelular();
	}

	@Override
	protected Predicate<ModeloCelular> getCriterio(ModeloCelular example) {
		return null;
	}
	
}
