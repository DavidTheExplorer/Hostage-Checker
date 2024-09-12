package dte.hostagechecker.services;

import dte.hostagechecker.hostage.Hostage;
import dte.hostagechecker.hostage.listprovider.HostageListProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class HostageService
{
    private final HostageListProvider hostageListProvider;

    private static final Logger LOGGER = LoggerFactory.getLogger(HostageService.class);

    public HostageService(HostageListProvider hostageListProvider)
    {
        this.hostageListProvider = hostageListProvider;
    }

    public Collection<Hostage> getHostages()
    {
        LOGGER.info("Fetching hostage list from {}.", this.hostageListProvider.getName());

        return this.hostageListProvider.fetchHostages().join();
    }
}
