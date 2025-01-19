package dte.hostagechecker.utils;

import java.util.Optional;

public class NumberUtils
{
    public static Optional<Double> parseDouble(String text)
    {
        //special case for Kfir - </3
        if(text.equals("שנתיים"))
            return Optional.of(1D);

        try
        {
            return Optional.of(Double.valueOf(text));
        }
        catch(NumberFormatException exception)
        {
            return Optional.empty();
        }
    }
}
