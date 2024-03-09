package net.protsenko.shortlinkservice.links;

import net.protsenko.shortlinkservice.common.ShortLink;

public interface LinksService {

    void save(ShortLink link);

    void remove(String link);

    ShortLink get(String code);

    ShortLink randomPull();

    String randomKey();

}
