/**

This file creates the Activity object and sets constructors for it, as well as accessor methods to access Activity data.

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

public class Activity {
    
    private String activityName;
    private double activityCalories;
    private double hours;
    
    public Activity(String n, double c) {
        activityName = n;
        activityCalories = c;
    }

    public Activity(String n, double c, double h) {
        activityName = n;
        activityCalories = c;
        hours = h;
    }

    public void updateCalories(double c) {
        activityCalories = c;
    }

    public String getActivityName() {
        return activityName;
    }

    public double getActivityCalories() {
        return activityCalories;
    }

    public double getHours() {
        return hours;
    }
}
