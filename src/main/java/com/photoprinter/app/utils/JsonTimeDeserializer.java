package com.photoprinter.app.utils;


import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class JsonTimeDeserializer extends JsonDeserializer<LocalTime> {
    @Override
    public LocalTime deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        String strTime = p.getValueAsString();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("H:mm");
        LocalTime parse = LocalTime.parse(strTime, formatter);
        return parse;
    }
}
