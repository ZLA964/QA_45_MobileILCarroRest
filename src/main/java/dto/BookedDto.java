package dto;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@Builder
@ToString

public class BookedDto {
    private String email;
    private String startDate;
    private String endDate;

}
