package cl.ucn.disc.dsm.pictwin.backend.services;

import cl.ucn.disc.dsm.pictwin.backend.model.Pic;
import cl.ucn.disc.dsm.pictwin.backend.model.Twin;
import cl.ucn.disc.dsm.pictwin.backend.model.User;
import jakarta.transaction.Transactional;
import lombok.NonNull;

public interface PicTwin {

    User create(User user, String password);

    User authenticate(String email, String password);

    Twin createTwin(Pic pic, Long idUser);

    void dislike(Long idTwin, Long idUser);

    long getUsersize();
}
