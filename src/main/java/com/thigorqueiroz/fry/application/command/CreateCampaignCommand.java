package com.thigorqueiroz.fry.application.command;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CreateCampaignCommand {

    public final String name;
    public final String periodStart;
    public final String periodEnd;


    public CreateCampaignCommand(String name, String periodStart, String periodEnd) {
        this.name = name;
        this.periodStart = periodStart;
        this.periodEnd = periodEnd;
    }

    public String getName() {
        return name;
    }

    public LocalDate getPeriodStart() {
        //TODO:change to static
        return LocalDate.parse(periodStart, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    public LocalDate getPeriodEnd() {
        //TODO:change to static
        return LocalDate.parse(periodEnd, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }
}
