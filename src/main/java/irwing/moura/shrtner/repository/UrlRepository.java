package irwing.moura.shrtner.repository;

import irwing.moura.shrtner.model.Url;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UrlRepository extends JpaRepository<Url, Long> {
    Url findByShortLink(String shortLink);
}
