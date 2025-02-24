package dte.hostagechecker.services;

import dte.hostagechecker.dto.HostageListDTO;
import dte.hostagechecker.exceptions.HostageFetchingException;
import dte.hostagechecker.hostage.listprovider.HostageListProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

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
    public HostageListDTO getHostages()
    {
        LOGGER.debug("Fetching hostage list from \"{}\".", this.hostageListProvider.getClass().getSimpleName());

        try
        {
            return this.hostageListProvider.fetchHostages()
                    .thenApply(HostageListDTO::of)
                    .join();
        }
        catch(CompletionException exception)
        {
            throw new HostageFetchingException(exception.getCause(), this.hostageListProvider);
        }
    }

    @Scheduled(fixedDelay = 30, timeUnit = MINUTES)
    @CacheEvict("hostages")
    void clearHostageCache()
    {
        LOGGER.debug("Hostages cache was cleared.");
    }
}