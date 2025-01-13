package dte.hostagechecker.controllers;

import dte.hostagechecker.dto.HostageListDTO;
import dte.hostagechecker.services.HostageService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/hostages")
public class HostagesController
{
    private final HostageService hostageService;

    public HostagesController(HostageService hostageService)
    {
        this.hostageService = hostageService;
    }

    @GetMapping
    public HostageListDTO getHostages()
    {
        return this.hostageService.getHostages();
    }
}
