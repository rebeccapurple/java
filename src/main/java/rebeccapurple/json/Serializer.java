package rebeccapurple.json;

import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializer;

public abstract class Serializer<T> implements JsonSerializer<T> {
    protected JsonElement create(String v){ return v!=null ? new JsonPrimitive(v) : JsonNull.INSTANCE; }
    protected JsonElement create(Boolean v){ return v!=null ? new JsonPrimitive(v) : JsonNull.INSTANCE; }
    protected JsonElement create(Number v){ return v!=null ? new JsonPrimitive(v) : JsonNull.INSTANCE; }
    protected JsonElement create(Character v){ return v!=null ? new JsonPrimitive(v) : JsonNull.INSTANCE; }
}
