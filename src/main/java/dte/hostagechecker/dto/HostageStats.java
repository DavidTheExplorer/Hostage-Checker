package dte.hostagechecker.dto;

import dte.hostagechecker.hostage.Hostage;
import dte.hostagechecker.utils.CollectionUtils;

import java.util.Collection;
import java.util.List;

import static java.util.function.Predicate.not;

public record HostageStats(int inCaptive, int minorsInCaptive, int adultsInCaptive, int murdered)
{
    public static HostageStats of(Collection<Hostage> hostages)
    {
        List<Hostage> inCaptive = hostages.stream()
                .filter(not(Hostage::wasReturned))
                .toList();

        int minorsInCaptive = CollectionUtils.count(inCaptive, Hostage::isMinor);
        int adultsInCaptive = inCaptive.size() - minorsInCaptive;
        int murdered = CollectionUtils.count(hostages, not(Hostage::isAlive));

        return new HostageStats(inCaptive.size(), minorsInCaptive, adultsInCaptive, murdered);
    }
}
