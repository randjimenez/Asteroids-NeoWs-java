package asteroids.nasa.asteroids.controller;

import asteroids.nasa.asteroids.services.AsteroidsService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



@RestController("/")
public class AsteroidsController {

    final AsteroidsService asteroidsService;

    public AsteroidsController(AsteroidsService asteroidsService) {
        this.asteroidsService = asteroidsService;
    }

    @GetMapping("find")
    public ResponseEntity search(@RequestParam String start_date, @RequestParam String end_date){
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(asteroidsService.findAsteroids(start_date,end_date));
    }
}
