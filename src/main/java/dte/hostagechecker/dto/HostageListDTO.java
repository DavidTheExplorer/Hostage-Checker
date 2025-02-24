package dte.hostagechecker.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import dte.hostagechecker.hostage.Hostage;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Collection;

@JsonPropertyOrder({"lastUpdated", "statistics"})
public record HostageListDTO(
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        LocalDateTime lastUpdated,

        @JsonProperty("statistics")
        HostageStats stats,

        Collection<Hostage> hostages)
{
    public static HostageListDTO of(Collection<Hostage> hostages)
    {
        LocalDateTime currentIsraelDate = LocalDateTime.now(ZoneId.of("Asia/Jerusalem"));
        HostageStats stats = HostageStats.of(hostages);

        return new HostageListDTO(currentIsraelDate, stats, hostages);
    }
}

