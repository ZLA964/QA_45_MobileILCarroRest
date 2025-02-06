package dto;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Setter
@Getter
@Builder
@ToString

public class CarDto {
    private String serialNumber; //": "string",
    private String manufacture; //": "string",
    private String model; //": "string",
    private String year; //": "string",
    private String fuel; //": "string",
    //Fuel fuel;
    private int seats; //": 0,
    private String carClass; //": "string",
    private double pricePerDay;//": 0,
    private String about; //: "string",
    private String city; //": "string",
    private double lat; //": 0,
    private double lng; //": 0,
    private String image; //": "string",
    private String owner; //": "string",
    private List<BookedDto> bookedPeriods;

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) return false;

        CarDto carDto = (CarDto) object;
        return serialNumber.equals(carDto.serialNumber) && manufacture.equals(carDto.manufacture) && model.equals(carDto.model);
    }

    @Override
    public int hashCode() {
        int result = serialNumber.hashCode();
        result = 31 * result + manufacture.hashCode();
        result = 31 * result + model.hashCode();
        return result;
    }
}
