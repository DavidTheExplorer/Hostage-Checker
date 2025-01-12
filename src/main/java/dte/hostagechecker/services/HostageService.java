package dte.hostagechecker.services;

import dte.hostagechecker.exceptions.HostageFetchingException;
import dte.hostagechecker.hostage.Hostage;
import dte.hostagechecker.hostage.listprovider.HostageListProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.concurrent.CompletionException;

import static java.util.concurrent.TimeUnit.MINUTES;

@Service
public class HostageService
{
    private final HostageListProvider hostageListProvider;

    private static final Logger LOGGER = LoggerFactory.getLogger(HostageService.class);

    public HostageService(HostageListProvider hostageListProvider)
    {
        this.hostageListProvider = hostageListProvider;
    }

    @Cacheable("hostages")
    public Collection<Hostage> getHostages()
    {
        LOGGER.info("Fetching hostage list from \"{}\".", this.hostageListProvider.getName());

        try
        {
            return this.hostageListProvider.fetchHostages().join();
        }
        catch(CompletionException exception)
        {
            LOGGER.error("Exception fetching the hostage list from \"{}\"", this.hostageListProvider.getName(), exception.getCause());

            throw new HostageFetchingException();
        }
    }

    @Scheduled(fixedDelay = 30, timeUnit = MINUTES)
    @CacheEvict("hostages")
    void clearHostageCache()
    {
        LOGGER.info("Hostages cache was cleared.");
    }
}