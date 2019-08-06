package asyncmethodinvocation;

/**
 * @author brandon
 * create on 2019-08-06
 * desc:
 */
public interface AsyncResult<T> {

    boolean isCompleted();

    T getValue();

    void await();

}
