package estudo.springboot.springboot3.mapper;

import estudo.springboot.springboot3.domain.Anime;
import estudo.springboot.springboot3.dto.request.PostAnimeDto;
import estudo.springboot.springboot3.dto.request.UpdateAnimeDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AnimeMapper {

    public static final AnimeMapper INSTANCE = Mappers.getMapper(AnimeMapper.class);
    public abstract Anime toAnime(PostAnimeDto postAnimeDto);
    public abstract Anime toAnime(UpdateAnimeDto updateAnimeDto);
}
