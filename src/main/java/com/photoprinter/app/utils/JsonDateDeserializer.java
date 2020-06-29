package com.photoprinter.app.utils;



import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class JsonDateDeserializer extends JsonDeserializer<LocalDate> {

    @Override
    public LocalDate deserialize(JsonParser jsonParser, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        String strDate = jsonParser.getValueAsString();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/d/yyyy");
        LocalDate parse = LocalDate.parse(strDate, formatter);
        return parse;
    }
}
