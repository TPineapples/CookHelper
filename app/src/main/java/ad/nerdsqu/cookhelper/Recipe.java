package ad.nerdsqu.cookhelper;



public class Recipe {
    String recipeName ;
    String[] ingredients;
    String cookTime;
    String prepTime;
    String category;
    String recipeType;
    String Directions;


    public Recipe(String Recipe, String[] Ingredients, String prepTime,
                  String cookTime, String category, String recipeType, String Directions) {
        this.recipeName  = Recipe;
        this.ingredients = Ingredients;
        this.cookTime = cookTime;
        this.prepTime = prepTime;
        this.category = category;
        this.recipeType = recipeType;
        this.Directions = Directions;

    }

    public String getRecipeName () {
        return recipeName ;
    }

    public String[] getIngredients() {
        return ingredients;
    }

    public String getIngredient(int i) {
        return ingredients[i];
    }

    public String getCookTime() {
        return cookTime;
    }

    public String getPrepTime() {
        return prepTime;
    }

    public String getRecipeType() {
        return recipeType;
    }

    public String getDirections() {
        return Directions;
    }

    public String getCategory() {
        return category;
    }

    public boolean hasIngredient(String ingredient) {
        ingredient = ingredient.toLowerCase();
        for (String ingred : ingredients) {
            if (ingred.toLowerCase().contains(ingredient)) {
                return true;
            }
        }
        return false;
    }
    
    public void setRecipeName (String Recipe1) {
        recipeName  = Recipe1;
    }

    public void setIngredients(String[] ingredients1) {
        ingredients = ingredients1;
    }

    public void setIngredient(int i, String newIngredient) {
        ingredients[i] = newIngredient;
    }

    public void setCookTime(String new_cookTime) {
        cookTime = new_cookTime;
    }

    public void setPrepTime(String new_prepTime) {
        prepTime = new_prepTime;
    }

    public void setRecipeType(String recipeType2) {
        recipeType = recipeType2;
    }

    public void setDirections(String NewDirections) {
        Directions = NewDirections;
    }

    public void setCategory(String newCategory) {
        category = newCategory;
    }

    public String ConcatenateIngredientNames() {
        String ConcatenatedIngredients = "";
        for (int i = 0; i < ingredients.length; i++) {
            if (i != ingredients.length - 1) {
                ConcatenatedIngredients += ingredients[i] + ",";
            } else {
                ConcatenatedIngredients += ingredients[i];
            }


        }
        return ConcatenatedIngredients;
    }


    public static String[] IngredientsStringToArray(String ingredientsString) {
        String[] ingredientsArray = ingredientsString.split(",");
        return ingredientsArray;


    }



    public boolean equals(Recipe recipe) {
        if (!this.recipeName .equals(recipe.recipeName )) {

            return false;
        }
        if (!CompareIngredientsArray(recipe)) {

            return false;
        }
        if (!this.cookTime.equals(recipe.getCookTime()))    {

            return false;
        }

        if (! this.prepTime.equals(recipe.getPrepTime())){

            return false;
        }
        if (!this.recipeType.equals(recipe.getRecipeType())) {

            return false;
        }

        if (!this.category.equals(recipe.getCategory())) {

            return false;
        }
        if (!this.Directions.equals(recipe.getDirections())) {

            return false;
        }

        return true;
    }

    public boolean CompareIngredientsArray(Recipe recipe) {
        if (this.ingredients.length != recipe.ingredients.length) {

            return false;
        }
        for (int i = 0; i <this.ingredients.length; i++ ) {
            if (!this.ingredients[i].equals( recipe.ingredients[i])) {
                return false;
            }

        }
        return true;
        
    }
    public String toString() {
        String toReturn = "";
        toReturn += "Recipe Name: " + getRecipeName () + "\n";
        toReturn += "Ingredients: " + ConcatenateIngredientNames() + "\n";
        toReturn += "Preparation Time: " + getPrepTime() + "\n";
        toReturn += "Cook Time: " + getCookTime() + "\n";
        toReturn += "Category: " + getCategory() + "\n";
        toReturn += "Recipe Type: " + getRecipeType() + "\n";
        toReturn += "Directions: " + getDirections();
        
        return toReturn;
    }
}