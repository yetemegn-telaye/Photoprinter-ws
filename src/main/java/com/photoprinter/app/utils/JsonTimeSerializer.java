package com.photoprinter.app.utils;


import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class JsonTimeSerializer extends JsonSerializer<LocalTime> {
    @Override
    public void serialize(LocalTime value, JsonGenerator gen, SerializerProvider serializers) throws IOException, JsonProcessingException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("H:mm");
        String timeString = formatter.format(value);
        gen.writeString(timeString);
    }
}
