package com.bs.regsystemapi.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @author qpj
 * @date 2022/4/20 16:10
 */
public class DateToTimeSerializer extends JsonSerializer<Date> {
    @Override
    public void serialize(Date date, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        // 截取时间的 HH:mm:ss
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.of("+8");
        LocalDateTime localDateTime = instant.atZone(zoneId).toLocalDateTime();
        String dateTimeStr = localDateTime.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        gen.writeString(dateTimeStr);
    }
}
