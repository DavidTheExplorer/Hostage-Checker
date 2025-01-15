package dte.hostagechecker.utils;

import java.util.Collection;
import java.util.function.Predicate;

public class CollectionUtils
{
    public static <E> int count(Collection<E> collection, Predicate<E> filter)
    {
        return (int) collection.stream()
                .filter(filter)
                .count();
    }
}
