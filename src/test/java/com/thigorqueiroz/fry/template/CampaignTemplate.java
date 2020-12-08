package com.thigorqueiroz.fry.template;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import com.thigorqueiroz.fry.domain.model.campaign.Campaign;

import java.time.OffsetDateTime;
import java.util.UUID;

public class CampaignTemplate implements TemplateLoader {
    @Override
    public void load() {
        var rule = new Rule();
        rule.add("id", UUID.randomUUID());
        rule.add("name", rule.regex("\\w{9}"));
        rule.add("createdAt", OffsetDateTime.now());
        rule.add("updatedAt", OffsetDateTime.now());
        Fixture.of(Campaign.class).addTemplate("default", rule);
    }
}
