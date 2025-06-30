package dte.hostagechecker.hostage.listprovider;

import dte.hostagechecker.hostage.Hostage;

import java.util.Collection;
import java.util.concurrent.CompletableFuture;

@FunctionalInterface
public interface HostageListProvider
{
    CompletableFuture<Collection<Hostage>> fetchHostages();
}