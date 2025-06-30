package dte.hostagechecker.hostage;

public record Hostage(String firstName,
                      String lastName,
                      Gender gender,
                      Double age,
                      CaptivityStatus captivityStatus,
                      LifeStatus lifeStatus)
{
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
        return this.age != null && this.age < 18;
    }
}
