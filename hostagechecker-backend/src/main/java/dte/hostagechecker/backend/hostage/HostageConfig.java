package dte.hostagechecker.backend.hostage;

import dte.hostagechecker.backend.hostage.jackson.HostageMixin;
import dte.hostagechecker.hostage.Hostage;
import dte.hostagechecker.hostage.repository.HostageRepository;
import dte.hostagechecker.hostage.repository.N12Repository;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HostageConfig
{
    @Bean
    public HostageRepository hostageRepository()
    {
        return new N12Repository();
    }

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer objectMapperCustomizer()
    {
        return customizer -> customizer.mixIn(Hostage.class, HostageMixin.class);
    }
}
