package net.protsenko.shortlinkservice;

import net.protsenko.shortlinkservice.common.ShortLink;
import net.protsenko.shortlinkservice.links.LinksService;
import net.protsenko.shortlinkservice.links.LinksServiceGeneral;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Queue;

@Component
@EnableScheduling
public class LinkJob {

    private final LinksService linksService;
    private final Queue<String> linksQueue;

    public LinkJob(LinksServiceGeneral linksService, Queue<String> linksQueue) {
        this.linksService = linksService;
        this.linksQueue = linksQueue;
    }

    @Scheduled(fixedRate = 1000L)
    public void freeLink() {
        ShortLink shortLink = linksService.randomPull();
        if (shortLink != null) {
            linksService.remove(shortLink.getCode());
            linksQueue.add(shortLink.getCode());
        }
    }

    @Scheduled(fixedRate = 500L)
    public void bookLink() {
        String lnk = linksQueue.poll();
        if (lnk != null) {
            linksService.save(new ShortLink(lnk, "google.com"));
        }
    }

    @Scheduled(fixedRate = 500L)
    public void useLink() {
        ShortLink shortLink = linksService.randomPull();
        System.out.println(shortLink);
    }

}
