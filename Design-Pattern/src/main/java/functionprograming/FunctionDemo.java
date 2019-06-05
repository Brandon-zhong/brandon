package functionprograming;

import java.util.Arrays;
import java.util.List;

/**
 * @author brandon
 * create on 2019-06-05
 * desc:
 */
public class FunctionDemo {

    private String data;

    public FunctionDemo(String data) {
        this.data = data;
    }

    public List<String> getList(String data) {
        return Arrays.asList(data.split(","));
    }

    public static String getString(String data) {
        return data;
    }


}
