package lab.weblab4backend.adapters;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Formatter;

public class TimeAdapter extends TypeAdapter<ZonedDateTime> {

    @Override
    public ZonedDateTime read(JsonReader reader) throws IOException {
        if (reader.peek() == JsonToken.NULL) {
            reader.nextNull();
            return null;
        }
        String time = reader.nextString();
        return ZonedDateTime.parse(time);
    }

    @Override
    public void write(JsonWriter writer, ZonedDateTime value) throws IOException {
        if (value == null) {
            writer.nullValue();
            return;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy - HH:mm:ss Z");
        String time = value.format(formatter);
        writer.value(time);
    }
}