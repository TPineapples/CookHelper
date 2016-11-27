package ad.nerdsqu.cookhelper;



public class Recipe {
    String RecipeName;
    String[] Ingredients;
    String Cook_Time;
    String Preparation_Time;
    String Category;
    String Recipe_Type;
    String Directions;


    public Recipe(String Recipe, String[] Ingredients, String Preparation_Time, String Cook_Time, String Category, String Recipe_Type, String Directions) {
        this.RecipeName = Recipe;
        this.Ingredients = Ingredients;
        this.Cook_Time = Cook_Time;
        this.Preparation_Time = Preparation_Time;
        this.Category = Category;
        this.Recipe_Type = Recipe_Type;
        this.Directions = Directions;

    }

    public String getRecipeName() {
        return RecipeName;
    }

    public String[] getIngredients() {
        return Ingredients;
    }

    public String getIngredient(int i) {
        return Ingredients[i];
    }

    public String getCook_Time() {
        return Cook_Time;
    }

    public String getPreparation_Time() {
        return Preparation_Time;
    }

    public String getRecipe_Type() {
        return Recipe_Type;
    }

    public String getDirections() {
        return Directions;
    }

    public String getCategory() {
        return Category;
    }


    public void setRecipeName(String Recipe1) {
        RecipeName = Recipe1;
    }

    public void setIngredients(String[] Ingredients1) {
        Ingredients = Ingredients1;
    }

    public void setIngredient(int i, String newIngredient) {
        Ingredients[i] = newIngredient;
    }

    public void setCook_Time(String new_Cook_Time) {
        Cook_Time = new_Cook_Time;
    }

    public void setPreparation_Time(String new_Preparation_Time) {
        Preparation_Time = new_Preparation_Time;
    }

    public void setRecipe_Type(String Recipe_Type2) {
        Recipe_Type = Recipe_Type2;
    }

    public void setDirections(String NewDirections) {
        Directions = NewDirections;
    }

    public void setCategory(String newCategory) {
        Category = newCategory;
    }

    public String ConcatenateIngredientNames() {
        String ConcatenatedIngredients = "";
        for (int i = 0; i < Ingredients.length; i++) {
            if (i != Ingredients.length - 1) {
                ConcatenatedIngredients += Ingredients[i] + ",";
            } else {
                ConcatenatedIngredients += Ingredients[i];
            }


        }
        return ConcatenatedIngredients;
    }


    public static String[] IngredientsStringToArray(String IngredientsString) {
        String[] IngredientsArray = IngredientsString.split(",");
        return IngredientsArray;


    }



    public boolean equals(Recipe recipe) {
        if (!this.RecipeName.equals(recipe.RecipeName)) {

            return false;
        }
        if (!CompareIngredientsArray(recipe)) {

            return false;
        }
        if (!this.Cook_Time.equals(recipe.getCook_Time()))    {

            return false;
        }

        if (! this.Preparation_Time.equals(recipe.getPreparation_Time())){

            return false;
        }
        if (!this.Recipe_Type.equals(recipe.getRecipe_Type())) {

            return false;
        }

        if (!this.Category.equals(recipe.getCategory())) {

            return false;
        }
        if (!this.Directions.equals(recipe.getDirections())) {

            return false;
        }

        return true;
    }

    public boolean CompareIngredientsArray(Recipe recipe) {
        if (this.Ingredients.length != recipe.Ingredients.length) {

            return false;
        }
        for (int i = 0; i <this.Ingredients.length; i++ ) {
            if (!this.Ingredients[i].equals( recipe.Ingredients[i])) {
                return false;
            }

        }
        return true;


    }
    public String toString() {
        String toReturn = "";
        toReturn += "Recipe Name: " + getRecipeName() + "\n";
        toReturn += "Ingredients: " + ConcatenateIngredientNames() + "\n";
        toReturn += "Preparation_Time: " + getPreparation_Time() + "\n";
        toReturn += "Cook_Time: " + getCook_Time() + "\n";
        toReturn += "Category: " + getCategory() + "\n";
        toReturn += "Recipe Type: " + getRecipe_Type() + "\n";
        toReturn += "Directions: " + getDirections();



        return toReturn;
    }
}