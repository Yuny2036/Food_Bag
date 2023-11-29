package dr.foody.food.dto;

import lombok.Data;

@Data
public class FoodNutritionDto {
    private Integer idx;
    private Float kcal;
    private Float carbo;
    private Float protein;
    private Float fat;
    private Float salt;
    private Float chole;
    private Integer food_idx;
}
