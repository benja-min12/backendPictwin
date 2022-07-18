package cl.ucn.disc.dsm.pictwin.backend.dao;

import cl.ucn.disc.dsm.pictwin.backend.model.Twin;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Benjamin Millas
 * Interface TwinRepository
 */
@Repository
public interface TwinRepository extends ListCrudRepository<Twin, Long> {
}

