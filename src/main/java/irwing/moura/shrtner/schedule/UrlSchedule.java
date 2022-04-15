package irwing.moura.shrtner.schedule;

import irwing.moura.shrtner.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class UrlSchedule {

    @Autowired
    private UrlService urlService;

    @Scheduled(cron = "* 0 * * * *", zone = "America/Sao_Paulo")
    public void cleanTableUrl() {
        urlService.executeSchedule();
    }

}
