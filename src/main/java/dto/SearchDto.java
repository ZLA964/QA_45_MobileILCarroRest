package dto;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@Builder

public class SearchDto {
    private String city;
    private String startDate;
    private String endDate;
}

