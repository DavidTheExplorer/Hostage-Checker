package dte.hostagechecker.hostage.listprovider;

import com.fasterxml.jackson.databind.JsonNode;
import dte.hostagechecker.hostage.CaptivityStatus;
import dte.hostagechecker.hostage.Gender;
import dte.hostagechecker.hostage.Hostage;
import dte.hostagechecker.hostage.LifeStatus;
import dte.hostagechecker.utils.NumberUtils;

import java.net.URI;
import java.util.Collection;
import java.util.List;

import static dte.hostagechecker.hostage.CaptivityStatus.IN_CAPTIVE;
import static dte.hostagechecker.hostage.CaptivityStatus.RETURNED;
import static dte.hostagechecker.hostage.Gender.FEMALE;
import static dte.hostagechecker.hostage.Gender.MALE;
import static dte.hostagechecker.hostage.LifeStatus.ALIVE;
import static dte.hostagechecker.hostage.LifeStatus.DEAD;

public class N12Provider extends OnlineListProvider
{
    //these words may appear in first and last names
    private static final Collection<String> EXCLUDED_WORDS = List.of(
            "ז\"ל"
            ,"אל\"ם" ,"אל\"מ" ,"סרן" ,"רס\"ב" ,"רס\"ם" ,"רס\"מ" ,"רס\"ל" ,"סמ\"ר" ,"סמל" ,"רב\"ט");

    public N12Provider()
    {
        super(URI.create("https://n12-kidnappedfromisrael.cdn-il.com/website%2Fdata.json?v=1"));
    }

    @Override
    protected JsonNode navigateToHostageArray(JsonNode bodyNode)
    {
        return bodyNode.get("rows"); //are the hostages just database rows?
    }

    @Override
    protected Hostage parseHostage(JsonNode hostageNode)
    {
        String firstName = hostageNode.get("b").asText();
        String lastName = hostageNode.get("c").asText();
        Gender gender = hostageNode.get("e").asText().equals("אישה") ? FEMALE : MALE;
        Double age = NumberUtils.parseDouble(hostageNode.get("d").asText()).orElse(null);
        CaptivityStatus captivityStatus = hostageNode.get("status").asInt() == 1 ? IN_CAPTIVE : RETURNED;
        LifeStatus lifeStatus = lastName.endsWith("ז\"ל") ? DEAD : ALIVE;

        //sanitization
        firstName = sanitize(firstName);
        lastName = sanitize(lastName);

        return new Hostage(firstName, lastName, gender, age, captivityStatus, lifeStatus);
    }

    private String sanitize(String text)
    {
        for(String word : EXCLUDED_WORDS)
            text = text.replace(word, "");

        return text.trim();
    }
}
