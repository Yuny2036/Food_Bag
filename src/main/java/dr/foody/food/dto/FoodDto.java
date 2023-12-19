package dr.foody.food.dto;

import lombok.Data;

@Data
public class FoodDto {
    private Integer idx;
    private String name;
    private Integer weight;
    private String season;
    private String type;

    private String inType;
    private String notInType;

    private String exceptAllergie;

    private String notInIngre;
}