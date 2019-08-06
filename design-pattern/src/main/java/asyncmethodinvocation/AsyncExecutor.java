package asyncmethodinvocation;

import java.util.concurrent.Callable;

/**
 * @author brandon
 * create on 2019-08-06
 * desc:
 */
public interface AsyncExecutor {

    <T> AsyncResult<T> startProcess(Callable<T> task);

    <T> AsyncResult<T> startProcess(Callable<T> task, AsyncCallback<T> callback);

    <T> T endProcess(AsyncResult<T> result);

}
