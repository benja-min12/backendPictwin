package cl.ucn.disc.dsm.pictwin.backend.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;


import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public final class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    private Long id;

    @Getter
    @NotNull
    @Column(unique = true)
    private String email;

    @Getter
    @Setter
    private String password;

    @Getter
    private Integer strikes;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    @Getter
    @Setter
    private State state = State.ACTIVE;


    @OneToMany(mappedBy = "owner", fetch = FetchType.EAGER)
    @Builder.Default
    @Getter
    @JsonManagedReference
    private List<Twin> twins = new ArrayList<>();

    public Integer incrementStrikes() {
        this.strikes++;
        return this.strikes;
    }

    public void add(final Twin twin) {
        this.twins.add(twin);
    }
}

