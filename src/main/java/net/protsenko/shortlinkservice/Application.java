package net.protsenko.shortlinkservice;

import net.protsenko.shortlinkservice.links.LinksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class Application {

    private final LinksService linksService;

    @Autowired
    public Application(LinksService linksService) {
        this.linksService = linksService;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }


    @GetMapping
    public int status() {
        linksService.randomPull();
        return Thread.activeCount();
    }
}
