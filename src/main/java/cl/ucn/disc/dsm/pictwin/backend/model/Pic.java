/*
 * Copyright (C) 2022 Benjamin Millas
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in
 * the Software without restriction, including without limitation the rights to
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
 * the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
 * FOR A PARTICULAR PURPOSE AND NON INFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
 * IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
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


