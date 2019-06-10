package converter;

import java.util.function.Function;

/**
 * @author brandon
 * create on 2019-06-06
 * desc:
 */
public class UserConverter extends Converter<UserEntity, UserDTO> {

    public UserConverter() {
        super((entity) -> new UserDTO(entity.getId(), entity.getFirstName(), entity.getLastName(), entity.getAge(), entity.getEmail()),
                (dto) -> new UserEntity(dto.getId(), dto.getFirstName(), dto.getLastName(), dto.getAge(), dto.getEmail()));
    }

}
