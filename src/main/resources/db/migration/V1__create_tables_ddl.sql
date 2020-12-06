CREATE TABLE range_time(
    id UUID PRIMARY KEY,
    period_start VARCHAR(10),
    period_end VARCHAR(10),
    created_at TIMESTAMP WITH TIME ZONE ,
    updated_at TIMESTAMP WITH TIME ZONE
);

CREATE TABLE campaign(
    id UUID PRIMARY KEY,
    name VARCHAR(255),
    created_at TIMESTAMP WITH TIME ZONE ,
    updated_at TIMESTAMP WITH TIME ZONE
);

CREATE TABLE duration(
    range_time_id UUID,
    campaign_id UUID,
    created_at TIMESTAMP WITH TIME ZONE ,
    updated_at TIMESTAMP WITH TIME ZONE
);

CREATE TABLE team_campaign(
    id UUID PRIMARY KEY,
    team_id UUID,
    campaign_id UUID,
    created_at TIMESTAMP WITH TIME ZONE ,
    updated_at TIMESTAMP WITH TIME ZONE,
    FOREIGN KEY(campaign_id) REFERENCES campaign(id)
);