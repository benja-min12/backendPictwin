package cl.ucn.disc.dsm.pictwin.backend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;


import java.time.ZonedDateTime;

/**
 * @author Benjamin Millas
 * Class pic
 */
@Entity
@Table(name = "pics")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public final class Pic {

    /**
     * The Id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    private Long id;

    /**
     * The Instante when the Pic was saved.
     */
    @Getter
    @Builder.Default
    private ZonedDateTime timestamp = ZonedDateTime.now();

    /**
     * The Dislikes
     * **/
    @Getter
    @Builder.Default
    private Integer dislikes = 0;

    /**
     * The Latitude.
     */
    @Getter
    private Double latitude;

    /**
     * The Longitude.
     */
    @Getter
    private Double longitude;

    /**
     * The Error.
     */
    @Getter
    private Double error;

    /**
     * The Views.
     */
    @Getter
    @Builder.Default
    private Integer views = 0;
    /**
     * The Name.
     */
    @Getter
    private String name;

    /**
     * The Picture.
     */
    @Getter
    private byte[] picture;

    /**
     * The Owner.
     */
    @Getter
    @Setter
    @ManyToOne(optional = false)
    @JsonBackReference
    private User owner;

    /**
     * Increment in one the dislikes.
     *
     * @return the dislikes number.
     */
    public Integer incrementDislikes() {
        this.dislikes++;
        return this.dislikes; }

    /**
     * Increment in one the views.
     *
     * @return the views number.l
     */
    public Integer incrementViews() {
        this.views++;
        return this.views;
    }

}


