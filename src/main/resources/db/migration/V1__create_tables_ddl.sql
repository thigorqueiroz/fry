CREATE TABLE range_time(
    id UUID PRIMARY KEY,
    period_start VARCHAR(10),
    period_end VARCHAR(10),
    created_at TIMESTAMP WITH TIME ZONE ,
    updated_at TIMESTAMP WITH TIME ZONE
);

CREATE TABLE team(
    id UUID PRIMARY KEY,
    name VARCHAR(255)
);

CREATE TABLE campaign(
    id UUID PRIMARY KEY,
    name VARCHAR(255),
    range_time_id UUID,
    created_at TIMESTAMP WITH TIME ZONE ,
    updated_at TIMESTAMP WITH TIME ZONE,
    FOREIGN KEY(range_time_id) REFERENCES range_time(id)
);

CREATE TABLE fan_user(
    id UUID PRIMARY KEY,
    name TEXT,
    email VARCHAR(255),
    birth_day VARCHAR(10),
    heart_team_id UUID,
    created_at TIMESTAMP WITH TIME ZONE ,
    updated_at TIMESTAMP WITH TIME ZONE,
    FOREIGN KEY(heart_team_id) REFERENCES team(id)
);

CREATE TABLE subscription_campaign(
    team_id UUID,
    campaign_id UUID,
    created_at TIMESTAMP WITH TIME ZONE ,
    updated_at TIMESTAMP WITH TIME ZONE,
    FOREIGN KEY(team_id) REFERENCES team(id),
    FOREIGN KEY(campaign_id) REFERENCES campaign(id)
);