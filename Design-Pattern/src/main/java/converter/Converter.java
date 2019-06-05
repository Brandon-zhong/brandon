package converter;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author brandon
 * Created on 2019-06-05.
 * desc: 转换器类  T泛型表示entity  R泛型表示DTO
 * function 函数式接口
 */
public class Converter<T, R> {

    private Function<T, R> fromEntity;

    private Function<R, T> fromDTO;

    public Converter(Function<T, R> fromEntity, Function<R, T> fromDTO) {
        this.fromEntity = fromEntity;
        this.fromDTO = fromDTO;
    }

    public R converFromEntity(T t) {
        return fromEntity.apply(t);
    }

    public T converFromDTO(R r) {
        return fromDTO.apply(r);
    }

    public List<R> createFromEntitys(Collection<T> entitys) {
        return entitys.stream().map(this::converFromEntity).collect(Collectors.toList());
    }

    public List<T> createFromDTO(Collection<R> dtos) {
        return dtos.stream().map(this::converFromDTO).collect(Collectors.toList());
    }


}
