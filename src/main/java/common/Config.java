package common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Config {
    public static List<String> filters = new ArrayList<>();

    public  static void filter(String filter){
        filters = Arrays.asList(filter.split(";"));
        filters.removeIf(item -> item.trim().equals(""));
    }
}
