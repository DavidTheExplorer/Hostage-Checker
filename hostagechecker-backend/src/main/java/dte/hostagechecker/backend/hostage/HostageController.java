package dte.hostagechecker.backend.hostage;

import dte.hostagechecker.backend.hostage.dto.HostageListDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hostages")
public class HostageController
{
    private final HostageService hostageService;

    public HostageController(HostageService hostageService)
    {
        this.hostageService = hostageService;
    }

    @GetMapping
    public HostageListDTO getHostages()
    {
        return this.hostageService.getHostages();
    }
}
