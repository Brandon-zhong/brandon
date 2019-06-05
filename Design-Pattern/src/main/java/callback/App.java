package callback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author brandon
 * Created on 2019-06-05.
 * desc: 回调,将一个方法作为参数传给其他代码,希望在是某个时间执行
 */
public class App {

    private static final Logger LOG = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {

        Task task = new SimpleTask();
        //生成回调代码
        CallBack callBack = () -> LOG.info("this is call back code");
        task.executeWith(callBack);

    }

}
