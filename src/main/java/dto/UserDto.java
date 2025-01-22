package dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@Builder
@ToString

public class UserDto {
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    
}
