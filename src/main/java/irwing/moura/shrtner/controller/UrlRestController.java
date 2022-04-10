package irwing.moura.shrtner.controller;

import irwing.moura.shrtner.exception.ExpiredUrlException;
import irwing.moura.shrtner.exception.InvalidUrlException;
import irwing.moura.shrtner.model.Url;
import irwing.moura.shrtner.request.UrlRequest;
import irwing.moura.shrtner.response.UrlResponse;
import irwing.moura.shrtner.service.UrlService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

@RestController()
@RequestMapping("/api")
public class UrlRestController {

    @Autowired
    private UrlService urlService;

    @PostMapping("/generate")
    public ResponseEntity<UrlResponse> generateShortLink(@RequestBody UrlRequest request) throws Exception {

        Url urlToRet = urlService.generateShortLink(request);
        if(urlToRet != null) {
            UrlResponse response = new UrlResponse();
            response.setOriginalUrl(urlToRet.getOriginalLink());
            response.setExpirationDate(urlToRet.getExpirationDate());
            response.setShortLink(urlToRet.getShortLink());

            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        throw new Exception("Something happened and we couldn't generate your url");
    }

    @GetMapping("/{shortLink}")
    public ResponseEntity<?> redirectToOriginalUrl(@PathVariable String shortLink, HttpServletResponse response) throws ExpiredUrlException, IOException {

        if(StringUtils.isEmpty(shortLink)) {
            throw new InvalidUrlException();
        }

        Url urlToRet = urlService.getEncodedUrl(shortLink);

        if(urlToRet== null) {
            throw new ExpiredUrlException();
        }

        if(urlToRet.getExpirationDate().isBefore(LocalDateTime.now())) {
            urlService.deleteShortLink(urlToRet);
            throw new ExpiredUrlException();
        }

        response.sendRedirect(urlToRet.getOriginalLink());
        return null;
    }

}
