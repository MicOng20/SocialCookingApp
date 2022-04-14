package my.edu.utar.socialcookingapp.Model;

public class FoodData {
    private String foodName, foodDesc, foodMethod;
    private String image;
    private String ingredient1,amount1,ingredient2,amount2,ingredient3,amount3;

    public FoodData() {

    }

    public FoodData(String foodName, String foodDesc, String ingredient1, String amount1, String ingredient2,
                    String amount2, String ingredient3, String amount3, String foodMethod,
                    String image)
    {
        this.foodName = foodName;
        this.foodDesc = foodDesc;
        this.ingredient1 = ingredient1;
        this.amount1 = amount1;
        this.ingredient2 = ingredient2;
        this.amount2 = amount2;
        this.ingredient3 = ingredient3;
        this.amount3 = amount3;
        this.foodMethod = foodMethod;
        this.image = image;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getFoodDesc() {
        return foodDesc;
    }

    public void setFoodDesc(String foodDesc) {
        this.foodDesc = foodDesc;
    }

    public String getFoodMethod() {
        return foodMethod;
    }

    public void setFoodMethod(String foodMethod) {
        this.foodMethod = foodMethod;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getIngredient1() {
        return ingredient1;
    }

    public void setIngredient1(String ingredient1) {
        this.ingredient1 = ingredient1;
    }

    public String getAmount1() {
        return amount1;
    }

    public void setAmount1(String amount1) {
        this.amount1 = amount1;
    }

    public String getIngredient2() {
        return ingredient2;
    }

    public void setIngredient2(String ingredient2) {
        this.ingredient2 = ingredient2;
    }

    public String getAmount2() {
        return amount2;
    }

    public void setAmount2(String amount2) {
        this.amount2 = amount2;
    }

    public String getIngredient3() {
        return ingredient3;
    }

    public void setIngredient3(String ingredient3) {
        this.ingredient3 = ingredient3;
    }

    public String getAmount3() {
        return amount3;
    }

    public void setAmount3(String amount3) {
        this.amount3 = amount3;
    }
}
