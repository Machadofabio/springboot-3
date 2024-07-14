package estudo.springboot.springboot3.service;

import estudo.springboot.springboot3.domain.Anime;
import estudo.springboot.springboot3.dto.request.PostAnimeDto;
import estudo.springboot.springboot3.dto.request.UpdateAnimeDto;
import estudo.springboot.springboot3.exception.DataNotFoundException;
import estudo.springboot.springboot3.mapper.AnimeMapper;
import estudo.springboot.springboot3.repository.AnimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        return animeRepository.save(AnimeMapper.INSTANCE.toAnime(anime));
    }

    public void delete(Long id) {
        animeRepository.delete(findById(id));
    }

    public void update(UpdateAnimeDto updateAnimeDto) {
       Anime animeFound = findById(updateAnimeDto.getId());
       animeFound.setId(updateAnimeDto.getId());
       animeRepository.save(AnimeMapper.INSTANCE.toAnime(updateAnimeDto));
    }
}
