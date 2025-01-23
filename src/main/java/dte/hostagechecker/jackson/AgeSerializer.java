package dte.hostagechecker.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class AgeSerializer extends JsonSerializer<Double>
{
    @Override
    public void serialize(Double age, JsonGenerator generator, SerializerProvider serializers) throws IOException
    {
        if(age == null)
            generator.writeString("Unknown");
        else
            generator.writeNumber(age.intValue());
    }
}
