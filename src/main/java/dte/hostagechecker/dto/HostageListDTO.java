package dte.hostagechecker.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import dte.hostagechecker.hostage.Hostage;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Collection;

import static java.util.function.Predicate.not;

public record HostageListDTO(
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime lastUpdated,
        int stillInCaptive,
        Collection<Hostage> hostages)
{
    public static HostageListDTO of(Collection<Hostage> hostages)
    {
        LocalDateTime currentIsraelDate = LocalDateTime.now(ZoneId.of("Asia/Jerusalem"));

        int stillInCaptive = (int) hostages.stream()
                .filter(not(Hostage::wasReturned))
                .count();

        return new HostageListDTO(currentIsraelDate, stillInCaptive, hostages);
    }
}

