package dte.hostagechecker.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import dte.hostagechecker.hostage.Hostage;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.boot.jackson.JsonObjectSerializer;

import java.io.IOException;

@JsonComponent
public class HostageSerializer extends JsonObjectSerializer<Hostage>
{
    @Override
    protected void serializeObject(Hostage hostage, JsonGenerator generator, SerializerProvider provider) throws IOException
    {
        generator.writeStringField("first_name", hostage.firstName());
        generator.writeStringField("last_name", hostage.lastName());
        generator.writeStringField("gender", hostage.gender().name());
        generator.writeObjectField("age", hostage.hasKnownAge() ? hostage.age() : "Unknown");
        generator.writeStringField("captivity_status", hostage.captivityStatus().name());
        generator.writeStringField("is_alive", hostage.lifeStatus().name());
    }
}
