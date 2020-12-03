package com.thigorqueiroz.fry.port.adapter.config;

import com.thigorqueiroz.fry.FryApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.data.convert.WritingConverter;
import org.springframework.data.jdbc.core.convert.JdbcCustomConversions;
import org.springframework.data.jdbc.repository.config.AbstractJdbcConfiguration;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;

import java.sql.Timestamp;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;

@Configuration
@EnableJdbcRepositories(basePackageClasses = FryApplication.class)
public class JdbcConfig extends AbstractJdbcConfiguration {

    @Override
    public JdbcCustomConversions jdbcCustomConversions() {
        return new JdbcCustomConversions(
                List.of(
                        new TimestampTzToOffsetDateTimeConverter(),
                        new OffsetDateTimeToTimestampTzConverter()
                )
        );
    }
}

@ReadingConverter
class TimestampTzToOffsetDateTimeConverter implements Converter<Timestamp, OffsetDateTime> {
    @Override
    public OffsetDateTime convert(Timestamp source) {
        return source.toInstant().atOffset(ZoneOffset.UTC);
    }
}

@WritingConverter
class OffsetDateTimeToTimestampTzConverter implements Converter<OffsetDateTime, Timestamp> {
    @Override
    public Timestamp convert(OffsetDateTime source) {
        return Timestamp.from(source.toInstant());
    }
}
