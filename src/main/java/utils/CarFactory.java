package utils;

import dto.BookedDto;
import dto.Car;
import dto.CityDto;
import net.datafaker.Faker;


import java.time.LocalDate;

public class CarFactory {
    static Faker faker = new Faker();

    public static BookedDto generateBookedPeriod() {
        return BookedDto.builder()
                .email(faker.internet().emailAddress())
                .startDate(LocalDate.now()
                        .plusDays(faker.number().numberBetween(1, 10))
                        .toString())
                .endDate(LocalDate.now()
                        .plusDays(faker.number().numberBetween(11, 20))
                        .toString())
                .build();
    }

    public static BookedDto[] generateBookedPeriods() {
        int count = faker.number().numberBetween(1, 3);
        BookedDto[] periods = new BookedDto[count];

        for (int i = 0; i < count; i++) {
            periods[i] = generateBookedPeriod();
        }

        return periods;
    }


    public static Car positiveCar() {
        return Car.builder()
                .serialNumber(faker.vehicle().licensePlate())
                .city("Haifa")
                .manufacture(faker.vehicle().manufacturer())
                .model(faker.vehicle().model())
                .year(Integer.toString(faker.number().numberBetween(0,
                        LocalDate.now().getYear())))
                .fuel(faker.options().option("Petrol", "Diesel", "Hybrid", "Electric"))
                .seats(faker.number().numberBetween(2, 20))
                .carClass(faker.vehicle().carType())
                .pricePerDay(faker.number()
                        .randomDouble(2, 0, 1000))
                .about(faker.text().text(0, 500))
                .bookedPeriods(generateBookedPeriods())
                .build();
    }

}