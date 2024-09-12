package dte.hostagechecker.hostage.listprovider;

import dte.hostagechecker.hostage.Hostage;

import java.util.Collection;
import java.util.concurrent.CompletableFuture;

public interface HostageListProvider
{
    String getName();

    CompletableFuture<Collection<Hostage>> fetchHostages();
}