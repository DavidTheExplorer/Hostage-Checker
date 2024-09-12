package dte.hostagechecker.config;

import dte.hostagechecker.hostage.listprovider.HostageListProvider;
import dte.hostagechecker.hostage.listprovider.N12Provider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HostageListProviderConfig
{
    @Bean
    public HostageListProvider hostageListProvider()
    {
        return new N12Provider();
    }
}
