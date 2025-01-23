package dte.hostagechecker.hostage;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import dte.hostagechecker.jackson.AgeSerializer;

public record Hostage(String firstName,
                      String lastName,
                      Gender gender,
                      @JsonSerialize(using = AgeSerializer.class, nullsUsing = AgeSerializer.class) Double age,
                      CaptivityStatus captivityStatus,
                      @JsonProperty("is_alive") LifeStatus lifeStatus)
{
    public boolean wasReturned()
    {
        return this.captivityStatus == CaptivityStatus.RETURNED;
    }

    @JsonIgnore
    public boolean isAlive()
    {
        return this.lifeStatus == LifeStatus.ALIVE;
    }

    @JsonIgnore
    public boolean isMinor()
    {
        return this.age != null && this.age < 18;
    }
}
