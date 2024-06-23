package estudo.springboot.springboot3.service;

import estudo.springboot.springboot3.domain.Anime;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnimeService {
    public List<Anime> listAll() {
        return List.of(Anime.builder().id(1L).name("DBZ").build(), Anime.builder().id(2L).name("Berserk").build());
    }
}
