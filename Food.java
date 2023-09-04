/**

This file creates the Food object and sets constructors for it, as well as accessor methods to access Food data.

@author Maria Charmane Rose E. Naciongayo (214152)
@version December 5, 2022
**/

/*
I have not discussed the Java language code in my program 
with anyone other than my instructor or the teaching assistants 
assigned to this course.

I have not used Java language code obtained from another student, 
or any other unauthorized source, either modified or unmodified.

If any Java language code or documentation used in my program 
was obtained from another source, such as a textbook or website, 
that has been clearly noted with a proper citation in the comments 
of my program.

Citations and Sources:
    My rubber ducks: Larissa Ang (BS MIS, 2nd Year) and Carmencita Dolina (BS CS-DGDD, 4th year)
        Explaining my code to them helped me to notice flaws in my own program logic.
*/

public class Food {

    private String foodName;
    private double foodCalories;
    private double servings;

    public Food (String f, double c) {
        foodName = f;
        foodCalories = c;
    }

    public Food (String f, double c, double s) {
        foodName = f;
        foodCalories = c;
        servings = s;
    }

    public void updateCalories(double c) {
        foodCalories = c;
    }

    public String getFoodName() {
        return foodName;
    }

    public double getFoodCalories() {
        return foodCalories;
    }

    public double getServings() {
        return servings;
    }
}