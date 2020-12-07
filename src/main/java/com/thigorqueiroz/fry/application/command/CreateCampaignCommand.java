package com.thigorqueiroz.fry.application.command;

import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.stream.Collectors;

public class CreateCampaignCommand {

    public final String name;
    public final String periodStart;
    public final String periodEnd;

    public CreateCampaignCommand(String name, String periodStart, String periodEnd) {
        this.name = name;
        this.periodStart = periodStart;
        this.periodEnd = periodEnd;
    }

    public  Date toDate(@NotNull String period) {
      var dateArray = Arrays.stream(period.split("/")).map(Integer::valueOf).collect(Collectors.toUnmodifiableList());
      var calendar = Calendar.getInstance();
      calendar.set(Calendar.YEAR, dateArray.get(2));
      calendar.set(Calendar.MONTH, dateArray.get(1));
      calendar.set(Calendar.DATE, dateArray.get(0));

      return calendar.getTime();
    }
}
