package my.edu.utar.socialcookingapp.Model;

public class FoodData {
    private String foodName, foodDesc;
    private int image;

    public FoodData(String foodName, String foodDesc, int image)
    {
        this.foodName = foodName;
        this.foodDesc = foodDesc;
        this.image = image;
    }

    //TESTING
    public String getFoodName() {
        return foodName;
    }

    public String getFoodDesc() {
        return foodDesc;
    }

    public int getImage() {
        return image;
    }

}
