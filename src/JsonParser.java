import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JsonParser {

    private static final Pattern REGEX_ITEMS = Pattern.compile(".*\\[(.+)\\].*");
    private static final Pattern REGEX_ATRIBUTTES_JSON = Pattern.compile("\"(.+?)\":\"(.*?)\"");
    
    public List<Map<String, String>> parse(String json) {

        Matcher matcher = REGEX_ITEMS.matcher(json);
        if (!matcher.find()) {

            throw new IllegalArgumentException("Doesn't found items.");
        }

        String[] items = matcher.group(1).split("\\},\\{");

        List<Map<String, String>> data = new ArrayList<>();

        for (String item : items) {

            Map<String, String> atributtesItem = new HashMap<>();

            Matcher matcherAtributtesJson = REGEX_ATRIBUTTES_JSON.matcher(item);
            while (matcherAtributtesJson.find()) {
                String atributte = matcherAtributtesJson.group(1);
                String value = matcherAtributtesJson.group(2);
                atributtesItem.put(atributte, value);
            }

            data.add(atributtesItem);
        }

        return data;

    }
}
