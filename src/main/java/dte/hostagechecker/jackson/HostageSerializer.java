package dte.hostagechecker.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import dte.hostagechecker.hostage.Age;
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
        Age age = hostage.age();

        generator.writeStringField("first_name", hostage.firstName());
        generator.writeStringField("last_name", hostage.lastName());
        generator.writeObjectField("age", age.isKnown() ? age.value() : "Unknown");
        generator.writeStringField("captivity_status", hostage.captivityStatus().name());
        generator.writeStringField("is_alive", hostage.lifeStatus().name());
    }
}
