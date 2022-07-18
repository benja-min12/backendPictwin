package cl.ucn.disc.dsm.pictwin.backend.dao;

import cl.ucn.disc.dsm.pictwin.backend.model.User;
import lombok.NonNull;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Benjamin Millas
 * Interface UserRepository
 */
@Repository
public interface UserRepository extends ListCrudRepository<User, Long> {

    Optional<User> findByEmail(@NonNull String email);
}

