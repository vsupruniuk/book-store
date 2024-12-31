package bookstore.mappers.user;

import bookstore.config.MapperConfig;
import bookstore.dtos.user.UserRegistrationRequestDto;
import bookstore.dtos.user.UserResponseDto;
import bookstore.models.User;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface IUserMapper {
    UserResponseDto toResponseDto(User user);

    User toEntity(UserRegistrationRequestDto userRegistrationRequestDto);
}
