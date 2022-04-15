package irwing.moura.shrtner.repository;

import irwing.moura.shrtner.model.Url;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UrlRepository extends JpaRepository<Url, Long> {

    Url findByShortLink(String shortLink);

    @Query(value = "select * from url " +
            "where GETDATE() > expiration_date", nativeQuery = true)
    List<Url> getAllExpiredUrls();

}
