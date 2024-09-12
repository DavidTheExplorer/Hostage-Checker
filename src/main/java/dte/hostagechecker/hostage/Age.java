package dte.hostagechecker.hostage;

public record Age(Double value)
{
    public boolean isKnown()
    {
        return this.value != null;
    }

    @Override
    public String toString()
    {
        if(this.value == null)
            return "Unknown";

        return String.valueOf(this.value);
    }
}