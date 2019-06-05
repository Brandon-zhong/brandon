package callback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author brandon
 * Created on 2019-06-05.
 * desc: 简单任务类,继承Task抽象类 , 实现execute方法,由父类task调用
 */
public class SimpleTask extends Task {

    private final Logger LOG = LoggerFactory.getLogger(SimpleTask.class);

    @Override
    public void execute() {
        LOG.info("this is simple task execute!!");
    }
}
