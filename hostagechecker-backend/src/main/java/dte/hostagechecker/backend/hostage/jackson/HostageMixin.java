package dte.hostagechecker.backend.hostage.jackson;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import dte.hostagechecker.hostage.CaptivityStatus;
import dte.hostagechecker.hostage.Gender;
import dte.hostagechecker.hostage.LifeStatus;

public interface HostageMixin
{
    @JsonProperty("first_name")
    String firstName();

    @JsonProperty("last_name")
    String lastName();

    @JsonProperty("gender")
    Gender gender();

    @JsonProperty("age")
    @JsonSerialize(using = AgeSerializer.class, nullsUsing = AgeSerializer.class)
    Double age();

    @JsonProperty("captivity_status")
    CaptivityStatus captivityStatus();

    @JsonProperty("is_alive")
    LifeStatus lifeStatus();

    @JsonIgnore
    boolean isAlive();

    @JsonIgnore
    boolean isMinor();
}
