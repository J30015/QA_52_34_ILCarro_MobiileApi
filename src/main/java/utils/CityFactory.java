package utils;import dto.BookedDto;
import dto.Car;
import dto.CityDto;
import net.datafaker.Faker;


import java.time.LocalDate;

public class CityFactory {
    static Faker faker = new Faker();
    public static CityDto generateCityData() {
        return CityDto.builder()
                .city(faker.address().cityName())
                .lat(Double.parseDouble(faker.address().latitude()))
                .lng(Double.parseDouble(faker.address().longitude()))
                .build();
    }
    public static CityDto[] generateCities() {
        int count = faker.number().numberBetween(1, 5);
        CityDto[] cities = new CityDto[count];

        for (int i = 0; i < count; i++) {
            cities[i] = generateCityData();
        }

        return cities;
    }
}

