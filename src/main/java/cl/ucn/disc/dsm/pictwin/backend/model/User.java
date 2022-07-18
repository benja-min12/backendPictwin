package cl.ucn.disc.dsm.pictwin.backend.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;


import java.util.ArrayList;
import java.util.List;

/**
 * @author Benjamin Millas
 * Class User
 */
@Entity
@Table(name = "users")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public final class User {
    /**
     * The id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    private Long id;

    /**
     * The email
     */
    @Getter
    @NotNull
    @Column(unique = true)
    private String email;

    /**
     * The password
     */
    @Getter
    @Setter
    private String password;

    /**
     * Number of strikes
     */
    @Getter
    private Integer strikes;

    /**
     * State of user
     */
    @Enumerated(EnumType.STRING)
    @Builder.Default
    @Getter
    @Setter
    private State state = State.ACTIVE;


    /**
     * List of Twins to user
     */
    @OneToMany(mappedBy = "owner", fetch = FetchType.EAGER)
    @Builder.Default
    @Getter
    @JsonManagedReference
    private List<Twin> twins = new ArrayList<>();

    /**
     * function that adds strikes to the user
     * @return number of strikes the user
     */
    public Integer incrementStrikes() {
        this.strikes++;
        return this.strikes;
    }

    /**
     * function that adds a twin to a user
     * @param twin Twin add to user
     */
    public void add(final Twin twin) {
        this.twins.add(twin);
    }
}

