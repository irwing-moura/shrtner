package irwing.moura.shrtner.service;

import com.google.common.hash.Hashing;
import irwing.moura.shrtner.model.Url;
import irwing.moura.shrtner.request.UrlRequest;
import irwing.moura.shrtner.repository.UrlRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

@Service
public class UrlService {

    @Autowired
    UrlRepository urlRepository;

    public Url generateShortLink(UrlRequest request) {

        if(StringUtils.isNotBlank(request.getUrl())) {
            String encodedUrl = encodedUrl(request.getUrl());
            Url urlToPersist = new Url();
            urlToPersist.setCreationDate(LocalDateTime.now());
            urlToPersist.setOriginalLink(request.getUrl());
            urlToPersist.setShortLink(encodedUrl);
            urlToPersist.setExpirationDate(getExpirationDate(request.getExpirationDate(), urlToPersist.getCreationDate()));

            return persisteShortLink(urlToPersist);

        }

        return null;
    }

    private LocalDateTime getExpirationDate(String expirationDate, LocalDateTime creationDate) {

        //ADICIONA 60 SEGUNDOS PARA A URL EXPIRAR, CASO O USUARIO NÃO PASSE O TEMPO DESEJADO
        if(StringUtils.isBlank(expirationDate))
            return creationDate.plusSeconds(60);

        return LocalDateTime.parse(expirationDate);

    }

    private String encodedUrl(String url) {
        String encodedUrl = "";
        LocalDateTime time = LocalDateTime.now();
        encodedUrl = Hashing.murmur3_32_fixed().hashString(url.concat(time.toString()), StandardCharsets.UTF_8)
        .toString();

        return encodedUrl;
    }

    public Url persisteShortLink(Url url) {
        return urlRepository.save(url);
    }
     public Url getEncodedUrl(String url) {
        return urlRepository.findByShortLink(url);
    }
     public void deleteShortLink(Url url) {
        urlRepository.delete(url);
    }

}
