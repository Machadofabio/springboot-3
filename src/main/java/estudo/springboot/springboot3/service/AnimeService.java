package estudo.springboot.springboot3.service;

import estudo.springboot.springboot3.domain.Anime;
import estudo.springboot.springboot3.dto.request.PostAnimeDto;
import estudo.springboot.springboot3.dto.request.UpdateAnimeDto;
import estudo.springboot.springboot3.exception.DataNotFoundException;
import estudo.springboot.springboot3.repository.AnimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnimeService {

    @Autowired
    private AnimeRepository animeRepository;

    public List<Anime> listAll() {
        return animeRepository.findAll();
    }

    public Anime findById(Long id) {
        return animeRepository.findById(id).orElseThrow(() -> new DataNotFoundException("Anime not found for id: " + id));
    }

    public Anime save(PostAnimeDto anime) {
        return animeRepository.save(Anime.builder().name(anime.getName()).build());
    }

    public void delete(Long id) {
        animeRepository.delete(findById(id));
    }

    public void update(UpdateAnimeDto updateAnimeDto) {
       Optional<Anime> optionalAnime = animeRepository.findById(updateAnimeDto.getId());
       if(optionalAnime.isPresent()){
           Anime animeFound = optionalAnime.get();
           animeFound.setId(updateAnimeDto.getId());
           animeFound.setName(updateAnimeDto.getName());
           animeRepository.save(animeFound);
       } else {
           throw new DataNotFoundException("Anime not found for id: " + updateAnimeDto.getId());
       }
    }
}
