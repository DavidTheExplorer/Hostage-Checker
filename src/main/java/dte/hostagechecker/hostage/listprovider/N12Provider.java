package dte.hostagechecker.hostage.listprovider;

import com.fasterxml.jackson.databind.JsonNode;
import dte.hostagechecker.hostage.CaptivityStatus;
import dte.hostagechecker.hostage.Gender;
import dte.hostagechecker.hostage.Hostage;
import dte.hostagechecker.hostage.LifeStatus;
import dte.hostagechecker.utils.NumberUtils;

import java.net.URI;

import static dte.hostagechecker.hostage.CaptivityStatus.IN_CAPTIVE;
import static dte.hostagechecker.hostage.CaptivityStatus.RETURNED;
import static dte.hostagechecker.hostage.Gender.FEMALE;
import static dte.hostagechecker.hostage.Gender.MALE;
import static dte.hostagechecker.hostage.LifeStatus.ALIVE;
import static dte.hostagechecker.hostage.LifeStatus.DEAD;

public class N12Provider extends OnlineListProvider
{
    public N12Provider()
    {
        super("N12", URI.create("https://n12-kidnappedfromisrael.cdn-il.com/website%2Fdata.json?v=1"));
    }

    @Override
    protected JsonNode navigateToHostageArray(JsonNode bodyNode) throws Exception
    {
        return bodyNode.get("rows"); //are the hostages just database rows?
    }

    @Override
    protected Hostage parseHostage(JsonNode hostageNode) throws Exception
    {
        String firstName = hostageNode.get("b").asText();
        String lastName = hostageNode.get("c").asText();
        Gender gender = hostageNode.get("e").asText().equals("אישה") ? FEMALE : MALE;
        Double age = NumberUtils.parseDouble(hostageNode.get("d").asText()).orElse(null);
        CaptivityStatus captivityStatus = hostageNode.get("status").asInt() == 1 ? IN_CAPTIVE : RETURNED;
        LifeStatus lifeStatus = lastName.endsWith("ז\"ל") ? DEAD : ALIVE;

        return new Hostage(firstName, sanitizeLastName(lastName), gender, age, captivityStatus, lifeStatus);
    }

    //deletes the ז"ל (╯︵╰,)
    private static String sanitizeLastName(String lastName)
    {
        if(!lastName.endsWith("ז\"ל"))
            return lastName;

        return lastName.substring(0, lastName.indexOf("ז\"ל") -1);
    }
}
