package dte.hostagechecker.backend.hostage.dto;

import dte.hostagechecker.hostage.Hostage;

import java.util.Collection;
import java.util.List;

import static java.util.function.Predicate.not;

public record HostageStats(int inCaptive, int murdered)
{
    public static HostageStats of(Collection<Hostage> hostages)
    {
        List<Hostage> inCaptive = hostages.stream()
                .filter(not(Hostage::wasReturned))
                .toList();

        int murdered = (int) hostages.stream()
                .filter(not(Hostage::isAlive))
                .count();

        return new HostageStats(inCaptive.size(), murdered);
    }
}

