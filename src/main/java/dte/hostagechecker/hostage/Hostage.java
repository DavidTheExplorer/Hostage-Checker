package dte.hostagechecker.hostage;

public record Hostage(String firstName, String lastName, Age age, CaptivityStatus captivityStatus, LifeStatus lifeStatus)
{
    public boolean wasReturned()
    {
        return this.captivityStatus == CaptivityStatus.RETURNED;
    }

    public boolean isAlive()
    {
        return this.lifeStatus == LifeStatus.ALIVE;
    }
}
