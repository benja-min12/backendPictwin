package cl.ucn.disc.dsm.pictwin.backend.services;

import cl.ucn.disc.dsm.pictwin.backend.dao.PicRepository;
import cl.ucn.disc.dsm.pictwin.backend.dao.TwinRepository;
import cl.ucn.disc.dsm.pictwin.backend.dao.UserRepository;
import cl.ucn.disc.dsm.pictwin.backend.model.Pic;
import cl.ucn.disc.dsm.pictwin.backend.model.Twin;
import cl.ucn.disc.dsm.pictwin.backend.model.User;
import jakarta.transaction.Transactional;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

/**
 * @author Benjamin Millas
 * Class PicTwinImpl Implements the methods to PicTwin
 */
@Slf4j
@Service
public class PicTwinImpl implements PicTwin {
    /**
     * encoder to encode passwords
     */
    private final static PasswordEncoder PASSWORD_ENCODER = new Argon2PasswordEncoder();
    /**
     * Random number generator
     */
    private final static Random RANDOM = new Random();
    /**
     * Pic repository
     */
    private final  PicRepository picRepository ;
    /**
     * Twin repository
     */
    private final  TwinRepository twinRepository ;

    /**
     * User repository
     */
    private final UserRepository userRepository ;

    /**
     * Constructor PicTwinImpl
     * @param picRepository Pic repository
     * @param twinRepository Twin repository
     * @param userRepository User repository
     */
    @Autowired
    public PicTwinImpl(PicRepository picRepository, TwinRepository twinRepository, UserRepository userRepository) {
        this.picRepository = picRepository;
        this.twinRepository = twinRepository;
        this.userRepository = userRepository;
    }
    /**
     * Function to create a user
     * @param user User to create
     * @param password Password to create the user
     * @return User created
     */
    @Override
    @Transactional
    public User create(@NonNull User user, @NonNull String password) {
        if(this.userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new IllegalArgumentException("The User with email <" + user.getEmail() + "> it's already in the " + "system");

        }
        String passwordHash = PASSWORD_ENCODER.encode(password);
        user.setPassword(passwordHash);
        return this.userRepository.save(user);
    }
    /**
     * Function to authenticate a user
     * @param email Email to authenticate
     * @param password Password to authenticate
     * @return User authenticated
     */
    @Override
    @Transactional
    public User authenticate(@NonNull String email, @NonNull String password) {
        Optional<User> userOptional = this.userRepository.findByEmail(email);
        log.debug("User: {}", userOptional);
        return userOptional.orElseThrow(() -> new RuntimeException("Wrong Credendials or User Not Found"));
    }
    /**
     * Function to create a twin
     * @param pic Pic to create
     * @param idUser Id of the user to create the twin
     * @return Twin created
     */
    @Override
    @Transactional
    public Twin createTwin(@NonNull Pic pic, Long idUser) {
        User owner = this.userRepository.findById(idUser).orElseThrow(() -> new RuntimeException("User Not Found"));
        log.debug("Pics: {} in User: {}", owner.getTwins().size(), owner.getEmail());
        pic.setOwner(owner);
        this.picRepository.save(pic);
        List<Pic> pics = this.picRepository.findAll();
        log.debug(" Number of Pics in the database: {}", pics.size());
        List<Pic> picsFiltered = pics.stream().filter(p -> !p.getOwner().getId().equals(idUser)).toList();
        if(picsFiltered.size() == 0) {
            log.warn("Re-using Pics from database.");
            picsFiltered = pics;
        }

        Pic your =  picsFiltered.size() == 0 ? pic : picsFiltered.get(RANDOM.nextInt(picsFiltered.size()));
        your.incrementViews();

        this.picRepository.save(your);

        Twin twin = Twin.builder()
                .my(pic)
                .yours(your)
                .owner(owner)
                .build();
        this.twinRepository.save(twin);

        owner.add(twin);
        this.userRepository.save(owner);
        return twin;
    }
    /**
     * Function to add dislike to a twin
     * @param idTwin Id of the twin to dislike
     * @return Twin disliked
     */
    @Override
    @Transactional
    public void dislike(@NonNull Long idTwin, @NonNull Long idUser) {
        Optional<Twin> oTwin = this.twinRepository.findById(idTwin);
        Twin twin = oTwin.orElseThrow(() -> new RuntimeException("Can't find Twin with id:" + idTwin));
        if (!idUser.equals(twin.getMy().getOwner().getId())) {
            throw new RuntimeException("Twin id<" + idTwin + "> not owned by User id<" + idUser + ">!");
        }
        twin.setDislike(true);
        this.twinRepository.save(twin);
        Pic yours = twin.getYours();
        yours.incrementDislikes();
        this.picRepository.save(yours);

        User user = yours.getOwner();
        user.incrementStrikes();
        this.userRepository.save(user);
    }

    /**
     * @return count of users in the database
     */
    @Override
    public long getUsersize() {
        return this.userRepository.count();
    }
}



