package dr.foody.foodNutri.dto;

import lombok.Data;

@Data
public class FoodNutriDto {
    private Integer idx;
    private Float kcal;
    private Float carbo;
    private Float protein;
    private Float fat;
    private Float salt;
    private Float chole;
    private Integer foodIdx;
}
