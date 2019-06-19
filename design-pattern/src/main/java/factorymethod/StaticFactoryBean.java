package factorymethod;

import java.util.Random;

/**
 * @author brandon
 * Created on 2019-06-19.
 * desc:
 */
public class StaticFactoryBean {

    public static Integer createRandom() {
        return new Random().nextInt();
    }

}
