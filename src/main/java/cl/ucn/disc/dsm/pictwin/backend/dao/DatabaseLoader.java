package cl.ucn.disc.dsm.pictwin.backend.dao;

import cl.ucn.disc.dsm.pictwin.backend.Utils;
import cl.ucn.disc.dsm.pictwin.backend.model.Pic;
import cl.ucn.disc.dsm.pictwin.backend.model.Twin;
import cl.ucn.disc.dsm.pictwin.backend.model.User;
import cl.ucn.disc.dsm.pictwin.backend.services.PicTwin;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @author Benjamin Millas
 * Class DatabaseLoader for seed the data base
 */
@Slf4j
@Component
public class DatabaseLoader implements CommandLineRunner {
    private final PicTwin picTwin;

    /**
     * @param picTwin
     */
    public DatabaseLoader(PicTwin picTwin) {
        this.picTwin = picTwin;
    }

    /**
     * @param args
     * @throws Exception
     */
    @Override
    public void run(String... args)throws Exception{
        log.info("Database DataLoader: Starting seeder...");

        if(this.picTwin.getUsersize() != 0) {
            log.info("Database already seeded, skipping!");
            return;
        }
        log.warn("No data found in database, seeding the database ..");
        User user = User.builder()
                .email("admin@ucn.cl")
                .password("admin123")
                .strikes(0)
                .build();
        Utils.printObject("User to create:", user);
        this.picTwin.create(user, "admin123");
        Utils.printObject("User created:", user);

        Twin twin1 = this.picTwin.createTwin(Pic.builder()
                .name("pic 1")
                .latitude(-33.4378)
                .longitude(-70.6503)
                .error(5.7)
                .owner(user)
                .build(), user.getId());
        Utils.printObject("Twin 1 created:", twin1);

        Twin twin2 = this.picTwin.createTwin(Pic.builder()
                .name("pic 2")
                .latitude(-33.4378)
                .longitude(-70.6503)
                .error(5.7)
                .owner(user)
                .build(), user.getId());
        Utils.printObject("Twin 2 created:", twin2);

        Twin twin3 = this.picTwin.createTwin(Pic.builder()
                .name("pic 3")
                .latitude(-33.4378)
                .longitude(-70.6503)
                .error(5.7)
                .owner(user)
                .build(), user.getId());
        Utils.printObject("Twin 3 created:", twin3);

        Twin twin4 = this.picTwin.createTwin(Pic.builder()
                .name("pic 4")
                .latitude(-33.4378)
                .longitude(-70.6503)
                .error(5.7)
                .owner(user)
                .build(), user.getId());
        Utils.printObject("Twin 4 created:", twin4);

        log.info("Database DataLoader: Seeding finished!");
    }
}
