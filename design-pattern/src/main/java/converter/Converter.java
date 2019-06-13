package converter;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author brandon
 * Created on 2019-06-05.
 * desc: 转换器类  T泛型表示entity  R泛型表示DTO
 * function 函数式接口  function 的apply方法可以用来接收任意符合apply模式的方法作为参数，栗子如下：
 */
public class Converter<T, R> {

    private Function<T, R> fromEntity;

    private Function<R, T> fromDTO;

    public Converter(Function<T, R> fromEntity, Function<R, T> fromDTO) {
        this.fromEntity = fromEntity;
        this.fromDTO = fromDTO;
    }

    /**
     * 调用function的apply方法即可以调用function接收的方法
     *
     * @param entity 参数
     * @return 返回值
     */
    public R converFromEntity(T entity) {
        return fromEntity.apply(entity);
    }

    /**
     * 调用function的apply方法即可以调用function接收的方法
     *
     * @param dto 参数
     * @return 返回值
     */
    public T converFromDTO(R dto) {
        return fromDTO.apply(dto);
    }

    public List<R> createFromEntitys(Collection<T> entitys) {
        return entitys.stream().map(this::converFromEntity).collect(Collectors.toList());
    }

    public List<T> createFromDTO(Collection<R> dtos) {
        return dtos.stream().map(this::converFromDTO).collect(Collectors.toList());
    }


}
