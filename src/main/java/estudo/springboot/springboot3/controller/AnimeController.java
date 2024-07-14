package estudo.springboot.springboot3.controller;

import estudo.springboot.springboot3.domain.Anime;
import estudo.springboot.springboot3.dto.request.PostAnimeDto;
import estudo.springboot.springboot3.dto.request.UpdateAnimeDto;
import estudo.springboot.springboot3.service.AnimeService;
import estudo.springboot.springboot3.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("animes")
@Slf4j
public class AnimeController {

    @Autowired
    private DateUtil dateUtil;

    @Autowired
    private AnimeService animeService;

    @GetMapping
    public ResponseEntity<List<Anime>> listAll(){
        log.info("Endpoint listAall() accessed at: " + dateUtil.formatLocalDateTimeToDatabaseStyle(LocalDateTime.now()));
        return ResponseEntity.ok(animeService.listAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Anime> findById(@PathVariable Long id){
        log.info("Endpoint findById() accessed at: " + dateUtil.formatLocalDateTimeToDatabaseStyle(LocalDateTime.now()));
        return new ResponseEntity<>(animeService.findById(id), HttpStatus.CREATED);
    }

    @GetMapping("/find")
    public ResponseEntity<Anime> findByName(@RequestParam String name){
        log.info("Endpoint findByName() accessed at: " + dateUtil.formatLocalDateTimeToDatabaseStyle(LocalDateTime.now()));
        return new ResponseEntity<>(animeService.findByName(name), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Anime> saveAnime(@RequestBody PostAnimeDto anime){
        log.info("Endpoint saveAnime() accessed at: " + dateUtil.formatLocalDateTimeToDatabaseStyle(LocalDateTime.now()));
        return ResponseEntity.ok(animeService.save(anime));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAniime(@PathVariable Long id){
        log.info("Endpoint deleteAnime() accessed at: " + dateUtil.formatLocalDateTimeToDatabaseStyle(LocalDateTime.now()));
        animeService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    public ResponseEntity<Void> updateAniime(@RequestBody UpdateAnimeDto updateAnimeDto){
        log.info("Endpoint updateAnime() accessed at: " + dateUtil.formatLocalDateTimeToDatabaseStyle(LocalDateTime.now()));
        animeService.update(updateAnimeDto);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
