package functionprograming;

/**
 * @author brandon
 * create on 2019-06-05
 * desc:
 */
@FunctionalInterface
public interface FunctionConvert<F, T> {
    T convert(F f);
}
