package dte.hostagechecker.hostage.repository;

import dte.hostagechecker.hostage.Hostage;

import java.util.Collection;
import java.util.concurrent.CompletableFuture;

@FunctionalInterface
public interface HostageRepository
{
    CompletableFuture<Collection<Hostage>> getHostages();
}