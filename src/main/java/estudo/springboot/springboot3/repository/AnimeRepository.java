package estudo.springboot.springboot3.repository;

import estudo.springboot.springboot3.domain.Anime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AnimeRepository extends JpaRepository<Anime, Long> {

    Optional<Anime> findByName(String name);
}
