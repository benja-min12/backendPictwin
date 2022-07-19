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
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * @author Benjamin Millas
 * Class Twin
 */
@Entity
@Table(name = "twins")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public final class Twin {
    /**
     * The Id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    private Long id;
    /**
     * The dislike
     */
    @Getter
    @Setter
    @Builder.Default
    private Boolean dislike = Boolean.FALSE;

    /**
     * The Pic
     */
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @Getter
    private Pic my;

    /**
     * The Pic
     */
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @Getter
    private Pic yours;

    /**
     75 * The Owner
     76 */
    @ManyToOne(optional = false, fetch=FetchType.EAGER)
    @Getter
    @JsonBackReference
    private User owner;

}

