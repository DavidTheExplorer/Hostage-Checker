package dte.hostagechecker.backend.hostage;

import dte.hostagechecker.backend.hostage.jackson.HostageMixin;
import dte.hostagechecker.hostage.Hostage;
import dte.hostagechecker.hostage.listprovider.HostageListProvider;
import dte.hostagechecker.hostage.listprovider.N12Provider;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HostageConfig
{
    @Bean
    public HostageListProvider hostageListProvider()
    {
        return new N12Provider();
    }

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer objectMapperCustomizer()
    {
        return customizer -> customizer.mixIn(Hostage.class, HostageMixin.class);
    }
}
