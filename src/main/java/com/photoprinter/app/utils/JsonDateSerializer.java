package com.photoprinter.app.utils;


import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class JsonDateSerializer extends JsonSerializer<LocalDate> {

    @Override
    public void serialize(LocalDate date, JsonGenerator jsonGenerator, SerializerProvider provider)
            throws IOException, JsonProcessingException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/d/yyyy");
        String dateString = formatter.format(date);
        jsonGenerator.writeString(dateString);

    }
}
