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
package cl.ucn.disc.dsm.pictwin.backend.web;

import cl.ucn.disc.dsm.pictwin.backend.Utils;
import cl.ucn.disc.dsm.pictwin.backend.model.Pic;
import cl.ucn.disc.dsm.pictwin.backend.model.Twin;
import cl.ucn.disc.dsm.pictwin.backend.model.User;
import cl.ucn.disc.dsm.pictwin.backend.services.PicTwin;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Benjamin Millas
 * class PicTwinRestController
 */
@RestController
@Slf4j
public class PicTwinRestController {
    /**
     * PicTwin service
     */
    private final PicTwin picTwin;

    /**
     * Constructor PicTwinRestController
     * @param picTwin PicTwin service
     */
    @Autowired
    public PicTwinRestController(PicTwin picTwin) {
        this.picTwin = picTwin;
    }

    /**
     * Funtion to create a user
     * @param user User to create
     * @param password Password to create the user
     * @return User created
     */
    @RequestMapping(value = {"/users", "/users/"}, method = RequestMethod.POST)
    public User create(@Valid @RequestBody User user, @RequestParam String password) {
        Utils.printObject("User", user);
        return picTwin.create(user, "password");
    }

    /**
     * Funtion to authenticate a user
     * @param email Email to authenticate
     * @param password Password to authenticate
     * @return User authenticated
     */
    @RequestMapping(value = {"/users", "/users/"}, method = RequestMethod.GET)
    public User authenticate(@RequestParam String email, @RequestParam String password) {
        return picTwin.authenticate(email, password);
    }

    /**
     * Funtion to create a twin
     * @param pic "MY" Pic to create
     * @param idUser Id of the user who creates the twin
     * @return  Twin created
     */
    @RequestMapping(value = {"/twins", "/twins/"}, method = RequestMethod.POST)
    public Twin createTwin(@Valid @RequestBody Pic pic, @RequestParam Long idUser) {
        return picTwin.createTwin(pic, idUser);
    }

    /**
     * Funtion to add dislike to a twin
     * @param idTwin Id of the twin to dislike
     * @param idUser Id of the user who dislikes the twin
     */
    @RequestMapping(value = {"/twins", "/twins/"}, method = RequestMethod.PATCH)
    public void dislike(@RequestParam Long idTwin, @RequestParam Long idUser) {
        this.picTwin.dislike(idTwin, idUser);
    }

    /**
     *
     * @param ex
     * @return
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex){
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

}
