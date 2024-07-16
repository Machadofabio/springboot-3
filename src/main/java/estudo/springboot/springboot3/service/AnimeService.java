package estudo.springboot.springboot3.service;

import estudo.springboot.springboot3.domain.Anime;
import estudo.springboot.springboot3.dto.request.PostAnimeDto;
import estudo.springboot.springboot3.dto.request.UpdateAnimeDto;
import estudo.springboot.springboot3.exception.DataNotFoundException;
import estudo.springboot.springboot3.mapper.AnimeMapper;
import estudo.springboot.springboot3.repository.AnimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AnimeService {

    @Autowired
    private AnimeRepository animeRepository;

    public Page<Anime> listAll(Pageable pageable) {
        return animeRepository.findAll(pageable);
    }

    public Anime findById(Long id) {
        return animeRepository.findById(id).orElseThrow(() -> new DataNotFoundException("Anime not found for id: " + id));
    }

    @Transactional // A anotação Transactional serve para commitar no banco de dados SOMENTE após o método ser finalizado. Isso impede de dados serem criados no banco de dados após alguma exception.
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

    public Anime findByName(String name) {
        return animeRepository.findByName(name).orElseThrow(() -> new DataNotFoundException("Anime not found for name: " + name));
    }
}
