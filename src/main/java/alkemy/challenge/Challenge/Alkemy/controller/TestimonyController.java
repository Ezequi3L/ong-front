package alkemy.challenge.Challenge.Alkemy.controller;

import alkemy.challenge.Challenge.Alkemy.model.Testimony;
import alkemy.challenge.Challenge.Alkemy.service.TestimonyService;
import alkemy.challenge.Challenge.Alkemy.util.Message;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/testimonials")
public class TestimonyController {

    @Autowired
    private TestimonyService testimonyService;

    @PostMapping
    public ResponseEntity<?> newTestimony(@RequestBody @Valid Testimony testimony) {
        if (StringUtils.isBlank(testimony.getName())) {
            return new ResponseEntity(new Message("El campo 'nombre' está vacío"),
                    HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(testimony.getContent())) {
            return new ResponseEntity(new Message("El campo 'content' está vacío"),
                    HttpStatus.BAD_REQUEST);
        }
        testimonyService.save(testimony);
        return ResponseEntity.ok("testimonio creado con éxito");
    }

    // The endpoint of type PUT to update the resource from testimony
    @PutMapping("/{id}")
    public ResponseEntity<?> saveResource(@RequestBody Testimony testimony,
                                          @PathVariable Long id) {
        //calls the method save from the repository
        Optional<Testimony> testimonyAux = testimonyService.findById(id);
        if (testimonyAux.isEmpty()){
            return new ResponseEntity(new Message("no existe un testimonio con el id: "+id),
                    HttpStatus.NOT_FOUND);
        }
        testimonyService.testimonialUpdate(testimony, testimonyAux.get());
        // prints a ok message
        return ResponseEntity.ok("testimonio actualizado con exito");

    }

    @GetMapping
    public Page<Testimony> listTestimony(@PageableDefault(size = 10, page = 0) Pageable pageable){
        Page<Testimony> list = testimonyService.getPageTestimony(pageable);
        if (list.isEmpty()) {
            return null;
        } else{
            return list;
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity deleteTestimony(@PathVariable Long id) {
        Optional<Testimony> testimony = testimonyService.findById(id);
        if (testimony.isEmpty()) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        testimonyService.softDelete(testimony.get());
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

}
