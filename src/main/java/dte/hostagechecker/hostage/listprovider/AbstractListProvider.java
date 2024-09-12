package dte.hostagechecker.hostage.listprovider;

public abstract class AbstractListProvider implements HostageListProvider
{
    private final String name;

    protected AbstractListProvider(String name)
    {
        this.name = name;
    }

    @Override
    public String getName()
    {
        return this.name;
    }
}
