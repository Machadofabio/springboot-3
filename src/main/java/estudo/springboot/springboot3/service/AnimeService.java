package estudo.springboot.springboot3.service;

import estudo.springboot.springboot3.domain.Anime;
import estudo.springboot.springboot3.dto.request.PostAnimeDto;
import estudo.springboot.springboot3.dto.request.UpdateAnimeDto;
import estudo.springboot.springboot3.exception.DataNotFoundException;
import estudo.springboot.springboot3.repository.AnimeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AnimeService {

    @Autowired
    private AnimeRepository animeRepository;

    @Autowired
    private ModelMapper modelMapper;

    public Page<Anime> listAll(Pageable pageable) {
        return animeRepository.findAll(pageable);
    }

    public Anime findById(Long id) {
        return animeRepository.findById(id).orElseThrow(() -> new DataNotFoundException("Anime not found for id: " + id));
    }

    // A anotação Transactional serve para commitar no banco de dados SOMENTE após o método ser finalizado. Isso impede de dados serem criados no banco de dados após alguma exception.
    public Anime save(PostAnimeDto anime) {
        return animeRepository.save(modelMapper.map(anime, Anime.class));
    }

    public void delete(Long id) {
        animeRepository.delete(findById(id));
    }

    public void update(UpdateAnimeDto updateAnimeDto) {
       Anime animeFound = findById(updateAnimeDto.getId());
       animeFound.setId(updateAnimeDto.getId());
       animeFound.setName(updateAnimeDto.getName());
       animeRepository.save(animeFound);
    }

    public Anime findByName(String name) {
        return animeRepository.findByName(name).orElseThrow(() -> new DataNotFoundException("Anime not found for name: " + name));
    }
}
