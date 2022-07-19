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

