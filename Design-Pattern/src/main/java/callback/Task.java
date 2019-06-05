package callback;

/**
 * @author brandon
 * Created on 2019-06-05.
 * desc: 抽象任务类
 */
public abstract class Task {

    public void executeWith(CallBack callBack) {
        execute();
        if (callBack != null) {
            callBack.call();
        }
    }

    public abstract void execute();

}
