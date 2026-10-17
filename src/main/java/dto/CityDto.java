package dto;

import lombok.*;

@Setter
@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor

public class CityDto {
    private String city;
    private Double lat;
    private Double lng;
}

