package dte.hostagechecker.hostage;

public record Hostage(String firstName, String lastName, Double age, CaptivityStatus captivityStatus, LifeStatus lifeStatus)
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
}
