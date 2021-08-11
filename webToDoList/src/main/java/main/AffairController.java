package main;

import main.model.Affair;
import main.model.AffairRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class AffairController {

    @Autowired(required = true)
    private AffairRepository affairRepository;

    @PostMapping("/affair/")
    public int add(Affair affair) {
        Affair newAffair = affairRepository.save(affair);
        return newAffair.getId();
    }

    @GetMapping("/affair/")
    public List<Affair> list() {
        Iterable<Affair> iterable = affairRepository.findAll();
        List<Affair> affairList = new ArrayList<>();
        iterable.forEach(affairList::add);
        return affairList;
    }

    @GetMapping("/affair/{id}")
    public ResponseEntity<Affair> get(@PathVariable int id) {
        Optional<Affair> optional = affairRepository.findById(id);
        if (!optional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return new ResponseEntity(optional.get(), HttpStatus.OK);
    }

    @DeleteMapping("/affair/")
    public void deleteAll() {
        affairRepository.deleteAll();
    }

    @DeleteMapping("/affair/{id}")
    public void delete(@PathVariable int id) {
        affairRepository.deleteById(id);
    }

    @PatchMapping("/affair/")
    public void path(Affair affair) {
        affairRepository.save(affair);
    }
}
