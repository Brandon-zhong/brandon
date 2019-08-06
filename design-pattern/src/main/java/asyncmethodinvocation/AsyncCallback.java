package asyncmethodinvocation;

import java.util.Optional;

/**
 * @author brandon
 * create on 2019-08-06
 * desc:
 */
public interface AsyncCallback<T> {

    void onComplete(T value, Optional<Exception> ex);

}
