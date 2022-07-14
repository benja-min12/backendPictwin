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

@RestController
@Slf4j
public class PicTwinRestController {
    private final PicTwin picTwin;

    @Autowired
    public PicTwinRestController(PicTwin picTwin) {
        this.picTwin = picTwin;
    }

    @RequestMapping(value = {"/users", "/users/"}, method = RequestMethod.POST)
    public User create(@Valid @RequestBody User user, @RequestParam String password) {
        Utils.printObject("User", user);
        return picTwin.create(user, "password");
    }

    @RequestMapping(value = {"/users", "/users/"}, method = RequestMethod.GET)
    public User authenticate(@RequestParam String email, @RequestParam String password) {
        return picTwin.authenticate(email, password);
    }
    @RequestMapping(value = {"/twins", "/twins/"}, method = RequestMethod.POST)
    public Twin createTwin(@Valid @RequestBody Pic pic, @RequestParam Long idUser) {
        return picTwin.createTwin(pic, idUser);
    }

    @RequestMapping(value = {"/twins", "/twins/"}, method = RequestMethod.PATCH)
    public void dislike(@RequestParam Long idTwin, @RequestParam Long idUser) {
        this.picTwin.dislike(idTwin, idUser);
    }
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
