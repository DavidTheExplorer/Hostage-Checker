package dte.hostagechecker.hostage;

public record Hostage(String firstName, String lastName, Gender gender, Double age, CaptivityStatus captivityStatus, LifeStatus lifeStatus)
{
    public boolean hasKnownAge()
    {
        return this.age != null;
    }

    public boolean wasReturned()
    {
        return this.captivityStatus == CaptivityStatus.RETURNED;
    }

    public boolean isAlive()
    {
        return this.lifeStatus == LifeStatus.ALIVE;
    }

    public boolean isMinor()
    {
        if(!hasKnownAge())
            return false;

        return this.age < 18;
    }
}
