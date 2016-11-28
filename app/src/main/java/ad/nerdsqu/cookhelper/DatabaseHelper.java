package ad.nerdsqu.cookhelper;
import android.content.Context;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.content.ContentValues;

/**
 * Created by James on 2016-11-14.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "ProjectDatabse";


    public static final String TABLE1_NAME = "Login_Table";
    public static final String TABLE1_COL1 = "User_Names";
    public static final String TABLE1_COL2 = "Password";

    public static final String TABLE2_NAME = "Recipe_Table";
    public static final String TABLE2_COL1 = "Recipe_Name";
    public static final String TABLE2_COL2 = "Ingredient_Names";
    public static final String TABLE2_COL3 = "Preparation_Time";
    public static final String TABLE2_COL4 = "Cook_Time";
    public static final String TABLE2_COL5 = "Category";
    public static final String TABLE2_COL6 = "Type";
    public static final String TABLE2_COL7 = "Directions";


    public static final String CreateTable1String = "create table " + TABLE1_NAME + " (" + TABLE1_COL1 + " text primary key not null, "
            + TABLE1_COL2 + " text not null);";


    public static final String CreateTable2String = "create table " + TABLE2_NAME + " (" + TABLE2_COL1 + " text primary key not null, "
            + TABLE2_COL2 + " text not null, " + TABLE2_COL3 + " text not null, " + TABLE2_COL4 + " text not null, " + TABLE2_COL5
            + " text not null, " + TABLE2_COL6 + " text not null, " + TABLE2_COL7 + " text not null);";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CreateTable1String);
        db.execSQL(CreateTable2String);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


        Log.w(DatabaseHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE1_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE2_NAME);
        onCreate(db);


    }


    public void DeleteBothTables () {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + TABLE1_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE2_NAME);
        db.execSQL(CreateTable1String);
        db.execSQL(CreateTable2String);
    }

    public void DeleteRecipeTableValuesAndRemake(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + TABLE2_NAME);
        db.execSQL(CreateTable2String);
    }

    public void DeleteLoginTableValuesAndRemake() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(CreateTable1String);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE1_NAME);
    }


    public boolean addLogin(Login login) {
        SQLiteDatabase Db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TABLE1_COL1, login.getUser_Name());
        values.put(TABLE1_COL2, login.getPassword());
        try {
            Db.insertOrThrow(TABLE1_NAME, null, values);
        } catch (SQLiteConstraintException ex) {
            System.out.println("Could not add login");
            return false;
        }
        return true;

    }


    public boolean addRecipe(Recipe recipe) {
        SQLiteDatabase Db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TABLE2_COL1, recipe.getRecipeName());
        String ConcatenatedIngredients = recipe.ConcatenateIngredientNames();
        values.put(TABLE2_COL2, ConcatenatedIngredients);
        values.put(TABLE2_COL3, recipe.getPreparation_Time());
        values.put(TABLE2_COL4, recipe.getCook_Time());
        values.put(TABLE2_COL5, recipe.getCategory());
        values.put(TABLE2_COL6, recipe.getRecipe_Type());
        values.put(TABLE2_COL7, recipe.getDirections());
        try {
            Db.insertOrThrow(TABLE2_NAME, null, values);
        } catch (SQLiteConstraintException ex) {
            System.out.println("Could not add recipe");
            return false;
        }
        return true;

    }

    public boolean IsValidLogin(String UserName, String Password) {

        SQLiteDatabase Db = this.getWritableDatabase();

        String QueryString = "Select Password FROM Login_Table WHERE User_Names = ?";
        Cursor c = Db.rawQuery(QueryString, new String[]{UserName});

        if (c.getCount() == 0) {
            return false;
        } else {
            c.moveToFirst();
            String p = c.getString(0);
            if (p.equals(Password)) {
                return true;
            } else {
                return false;
            }
        }
    }


    public Login getLoginFromUsername(String UserName) {

        SQLiteDatabase Db = this.getWritableDatabase();
        String QueryString = "Select Password FROM Login_Table WHERE User_Names = ?";
        Cursor c = Db.rawQuery(QueryString, new String[]{UserName});


        if (c.getCount()==0) {
            return null;
        } else {
            System.out.println(c.getCount());
            c.moveToFirst();
            Login login = new Login(UserName, c.getString(0));
            return login;
        }

    }
    public void setPassword (String pass,String user){
        SQLiteDatabase Db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("Password", pass);
        Db.update("Login_Table", cv, TABLE1_COL1 + "= ?", new String[] {user});
    }

    public boolean isRecipeInDatabase(String RecipeName) {

        SQLiteDatabase Db = this.getWritableDatabase();
        String QueryString = "Select Password FROM Recipe_Table WHERE Recipe_Name = ?";
        Cursor c = Db.rawQuery(QueryString, new String[]{RecipeName});

        if (c.getCount()==0) {
            return false;
        } else {
            return true;


        }

    }


    public Recipe getRecipeFromName(String RecipeName) {

        SQLiteDatabase Db = this.getWritableDatabase();
        String QueryString = "Select * FROM Recipe_Table WHERE Recipe_Name = ?";
        Cursor c = Db.rawQuery(QueryString, new String[]{RecipeName});

        if (c.getCount()==0) {
            return null;
        } else {
            c.moveToFirst();

            String[] Ingredients = Recipe.IngredientsStringToArray(c.getString(1));
            String Preparation_Time = c.getString(2);
            String Cook_Time = c.getString(3);
            String Category = c.getString(4);
            String Recipe_Type = c.getString(5);
            String Directions = c.getString(6);
            Recipe recipe = new Recipe(RecipeName, Ingredients, Cook_Time, Preparation_Time, Category, Recipe_Type, Directions);
            return recipe;

        }

    }


    public ArrayList<Login> getAllLogins() {
        ArrayList<Login> AllLogins = new ArrayList<Login>();

        String selectQuery = "SELECT  * FROM " + TABLE1_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                String Login = cursor.getString(0);
                String Password = cursor.getString(1);
                Login login = new Login(Login, Password);
                AllLogins.add(login);
            }
            while (cursor.moveToNext());
        }

        return AllLogins;
    }

    public ArrayList<Recipe> getAllRecipes() {
        ArrayList<Recipe> AllRecipes = new ArrayList<Recipe>();
        String selectQuery = "SELECT  * FROM " + TABLE2_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                String RecipeName = cursor.getString(0);
                String[] Ingredients = Recipe.IngredientsStringToArray(cursor.getString(1));
                String Cook_Time = cursor.getString(3);
                String Preparation_Time = cursor.getString(2);
                String Category = cursor.getString(4);
                String Recipe_Type = cursor.getString(5);
                String Directions = cursor.getString(6);
                Recipe recipe = new Recipe(RecipeName, Ingredients, Cook_Time, Preparation_Time, Category, Recipe_Type, Directions);
                AllRecipes.add(recipe);
            } while (cursor.moveToNext());
        }


        return SortRecipeList(AllRecipes);

    }


    public boolean IsIngredientInArray(String Ingredient, String[] Ingredients) {

        for (int i = 0; i < Ingredients.length; i++) {

            if (Ingredient.equals(Ingredients[i])) {
                return true;
            }

        }
        return false;

    }


    public ArrayList<Recipe> searchBySingleIngredient(String Ingredient) {
        ArrayList<Recipe> AllRecipes = (ArrayList<Recipe>) getAllRecipes();
        ArrayList<Recipe> ReturnedRecipes = new ArrayList<Recipe>();

        ListIterator<Recipe> it = AllRecipes.listIterator();
        while (it.hasNext()) {
            Recipe recipe = it.next();
            if (IsIngredientInArray(Ingredient, recipe.getIngredients())) {
                ReturnedRecipes.add(recipe);
            }

        }


        return SortRecipeList(ReturnedRecipes);

    }


    public ArrayList<Recipe> searchByMultipleIngredients(String[] RequiredIngredients) {
        ArrayList<Recipe> AllRecipes = (ArrayList<Recipe>) getAllRecipes();
        ArrayList<Recipe> ReturnedRecipes = new ArrayList<Recipe>();

        ListIterator<Recipe> it = AllRecipes.listIterator();
        while (it.hasNext()) {
            Recipe newRecipe = it.next();
            boolean AllIngredients = true;
            for (int i = 0; i < RequiredIngredients.length; i++) {
                if (!IsIngredientInArray(RequiredIngredients[i], newRecipe.getIngredients())) {
                    AllIngredients = false;
                }
            }
            if (AllIngredients) {
                ReturnedRecipes.add(newRecipe);
            }


        }
        return SortRecipeList(ReturnedRecipes);

    }


    public ArrayList<Recipe> searchByCategory(String Category) {


        SQLiteDatabase Db = this.getWritableDatabase();
        String QueryString = "Select * FROM Recipe_Table WHERE Category = ?";
        Cursor c = Db.rawQuery(QueryString, new String[]{Category});
        ArrayList<Recipe> CategoryRecipes = new ArrayList<Recipe>();
        if (c.getCount()==0) {
            return null;
        } else {
            c.moveToFirst();
            String RecipeName = c.getString(0);
            String[] Ingredients = Recipe.IngredientsStringToArray(c.getString(1));
            String Cook_Time = c.getString(3);
            String Preparation_Time = c.getString(2);
            String Recipe_Type = c.getString(5);
            String Directions = c.getString(6);
            Recipe recipe = new Recipe(RecipeName, Ingredients, Cook_Time, Preparation_Time, Category, Recipe_Type, Directions);
            CategoryRecipes.add(recipe);

        }

        return SortRecipeList(CategoryRecipes);
    }

    public ArrayList<Recipe> searchByType(String Type) {


        SQLiteDatabase Db = this.getWritableDatabase();
        String QueryString = "Select * FROM Recipe_Table WHERE Type = ?";
        Cursor c = Db.rawQuery(QueryString, new String[]{Type});
        ArrayList<Recipe> CategoryRecipes = new ArrayList<Recipe>();
        if (c.getCount()==0) {
            return null;
        } else {
            c.moveToFirst();
            String RecipeName = c.getString(0);
            String[] Ingredients = Recipe.IngredientsStringToArray(c.getString(1));
            String Cook_Time = c.getString(3);
            String Preparation_Time = c.getString(2);
            String Recipe_Type = c.getString(5);
            String Category = c.getString(4);
            String Directions = c.getString(6);
            Recipe recipe = new Recipe(RecipeName, Ingredients, Cook_Time, Preparation_Time, Category, Type, Directions);
            CategoryRecipes.add(recipe);

        }

        return SortRecipeList(CategoryRecipes);
    }

    public ArrayList<Recipe> GeneralSearch(String Category, String Type, String[] Ingredients) {

        ArrayList<Recipe> List1 = searchByMultipleIngredients(Ingredients);
        ArrayList<Recipe> List2 = searchByCategory(Category);
        ArrayList<Recipe> List3 = searchByType(Type);
        ArrayList<Recipe> returnList = new ArrayList<Recipe>();
        ListIterator<Recipe> it1 = List1.listIterator();


        while (it1.hasNext()) {
            ListIterator<Recipe> it2 = List2.listIterator();
            ListIterator<Recipe> it3 = List3.listIterator();
            boolean firstComp = false;
            boolean secondComp = false;
            Recipe recipe1 = it1.next();

            while (it2.hasNext()) {
                Recipe recipe2 = it2.next();
                if (recipe1.equals(recipe2)) {

                    firstComp = true;
                }
            }

            while (it3.hasNext()) {
                Recipe recipe3 = it3.next();
                if (recipe1.equals(recipe3)) {
                    secondComp = true;
                }
            }
            if (firstComp && secondComp) {

                returnList.add(recipe1);
            }


        }
        return SortRecipeList(returnList);
    }



    public String[] getAllCategories() {
        List<Recipe> RecipeList = getAllRecipes();
        Set<String> StringList = new HashSet<String>();
        ListIterator<Recipe> it = RecipeList.listIterator();
        while (it.hasNext()) {
            StringList.add(it.next().getCategory());

        }
        String[] CategoryStrings = new String[StringList.size()];
        Iterator it2 = StringList.iterator();
        int counter = 0;
        while (it2.hasNext()){
            CategoryStrings[counter] = (String)it2.next();
            counter++;
        }
        return sortStringArray(CategoryStrings);
    }


    public String[] getAllTypes() {
        ArrayList<Recipe> RecipeList = getAllRecipes();
        Set<String> StringList = new HashSet<String>();
        ListIterator<Recipe> it = RecipeList.listIterator();
        while (it.hasNext()) {
            StringList.add(it.next().getRecipe_Type());

        }
        String[] TypeStrings = new String[StringList.size()];
        Iterator it2 = StringList.iterator();
        int counter = 0;
        while (it2.hasNext()){
            TypeStrings[counter] = (String)it2.next();
            counter++;
        }
        return sortStringArray(TypeStrings);
    }


    public String[] getAllIngredients() {
        List<Recipe> RecipeList = getAllRecipes();
        Set<String> StringList = new HashSet<String>();
        ListIterator<Recipe> it = RecipeList.listIterator();
        while (it.hasNext()) {
            String[] RecipeList2 = it.next().getIngredients();
            for (int i = 0; i < RecipeList2.length; i++) {
                StringList.add(RecipeList2[i]);
            }

        }
        String[] IngredientStrings = new String[StringList.size()];
        Iterator it2 = StringList.iterator();
        int counter = 0;
        while (it2.hasNext()){

            IngredientStrings[counter] = (String)it2.next();
            counter++;
        }
        return sortStringArray(IngredientStrings);
    }






    public  String[] sortStringArray(String[] ArrayToSort) {





        for(int j=0; j<ArrayToSort.length;j++)
        {
            for (int i=j+1 ; i<ArrayToSort.length; i++)
            {
                if(ArrayToSort[i].compareTo(ArrayToSort[j])<0)
                {
                    String temp= ArrayToSort[j];
                    ArrayToSort[j]= ArrayToSort[i];
                    ArrayToSort[i]=temp;


                }
            }


        }

        return ArrayToSort;
    }



    public ArrayList<Recipe> SortRecipeList (ArrayList<Recipe> unSortedList) {

        Recipe[] unSortedRecipeArray = new Recipe[unSortedList.size()];

        for (int i = 0; i < unSortedList.size(); i++) {
            unSortedRecipeArray[i] = unSortedList.get(i);

        }

        for(int j=0; j<unSortedRecipeArray.length;j++)
        {
            for (int i=j+1 ; i<unSortedRecipeArray.length; i++)
            {
                if (unSortedRecipeArray[i].getRecipeName().compareTo(unSortedRecipeArray[j+1].getRecipeName()) < 0)
                {
                    Recipe temp = unSortedRecipeArray[j];
                    unSortedRecipeArray[j]= unSortedRecipeArray[i];
                    unSortedRecipeArray[i]=temp;


                }
            }


        }

        ArrayList<Recipe> SortedRecipeList = new ArrayList<Recipe>();
        for (int i = 0; i < unSortedRecipeArray.length; i++) {
            SortedRecipeList.add(unSortedRecipeArray[i]);
        }

        return SortedRecipeList;
    }





    public void printAllLogins() {
        List<Login> allLogins = getAllLogins();
        ListIterator<Login> it = allLogins.listIterator();
        while (it.hasNext()) {
            System.out.println( it.next().toString());
        }


    }



    public void printAllRecipes() {
        List<Recipe> allRecipes = getAllRecipes();
        ListIterator<Recipe> it = allRecipes.listIterator();
        while (it.hasNext()){
            System.out.println(it.next().toString());
        }

    }

}
