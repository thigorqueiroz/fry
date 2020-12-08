CREATE TABLE duration(
    id UUID PRIMARY KEY,
    period_start DATE,
    period_end DATE,
    created_at TIMESTAMP WITH TIME ZONE ,
    updated_at TIMESTAMP WITH TIME ZONE
);

CREATE TABLE campaign(
    id UUID PRIMARY KEY,
    name VARCHAR(255),
    duration_id UUID,
    created_at TIMESTAMP WITH TIME ZONE ,
    updated_at TIMESTAMP WITH TIME ZONE,
    FOREIGN KEY(duration_id) REFERENCES duration(id)
);

CREATE TABLE team_campaign(
    id UUID PRIMARY KEY,
    team_id UUID,
    campaign_id UUID,
    created_at TIMESTAMP WITH TIME ZONE ,
    updated_at TIMESTAMP WITH TIME ZONE,
    FOREIGN KEY(campaign_id) REFERENCES campaign(id)
);