package dte.hostagechecker.dto;

import dte.hostagechecker.hostage.Hostage;

import java.util.Collection;

import static java.util.function.Predicate.not;

public record HostageListDTO(int stillInCaptive, Collection<Hostage> hostages)
{
    public static HostageListDTO of(Collection<Hostage> hostages)
    {
        int stillInCaptive = (int) hostages.stream()
                .filter(not(Hostage::wasReturned))
                .count();

        return new HostageListDTO(stillInCaptive, hostages);
    }
}

