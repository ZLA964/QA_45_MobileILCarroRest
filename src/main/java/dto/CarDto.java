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
    public final boolean equals(Object object) {
        if (!(object instanceof CarDto carDto)) return false;

        return seats == carDto.seats && Double.compare(pricePerDay, carDto.pricePerDay) == 0 && serialNumber.equals(carDto.serialNumber) && manufacture.equals(carDto.manufacture) && model.equals(carDto.model) && year.equals(carDto.year) && fuel.equals(carDto.fuel) && carClass.equals(carDto.carClass) && about.equals(carDto.about) && city.equals(carDto.city);
    }

    @Override
    public int hashCode() {
        int result = serialNumber.hashCode();
        result = 31 * result + manufacture.hashCode();
        result = 31 * result + model.hashCode();
        result = 31 * result + year.hashCode();
        result = 31 * result + fuel.hashCode();
        result = 31 * result + seats;
        result = 31 * result + carClass.hashCode();
        result = 31 * result + Double.hashCode(pricePerDay);
        result = 31 * result + about.hashCode();
        result = 31 * result + city.hashCode();
        return result;
    }
}
