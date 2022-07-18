package cl.ucn.disc.dsm.pictwin.backend.dao;

import cl.ucn.disc.dsm.pictwin.backend.model.Pic;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Benjamin Millas
 * Interface PicRepository
 */
@Repository
public interface PicRepository extends ListCrudRepository<Pic, Long> {

}

