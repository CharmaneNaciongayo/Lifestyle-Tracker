/**

This file defines the methods that need to be executed based on the user input in console.

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
    in.close(): https://stackoverflow.com/questions/12519335/resource-leak-in-is-never-closed

    My rubber ducks: Larissa Ang (BS MIS, 2nd Year) and Carmencita Dolina (BS CS-DGDD, 4th year)
        Explaining my code to them helped me to notice flaws in my own program logic.
*/

import java.util.Scanner;

public class TrackerConsole {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        LifestyleTracker tracker = new LifestyleTracker();
        System.out.printf("Welcome to %s's Lifestyle Tracker!\n", args[0]);

        String action;
        String object;
        double value;
        
        while (true) {

            action = in.next();

            if (action.equalsIgnoreCase("food")) {
                object = in.next();
                value = in.nextDouble();
                System.out.println(tracker.addFood(object, value));

            } else if (action.equalsIgnoreCase("activity")) {
                object = in.next();
                value = in.nextDouble();
                System.out.println(tracker.addActivity(object, value));

            } else if (action.equalsIgnoreCase("perform")) {
                object = in.next();
                value = in.nextDouble();
                System.out.println(tracker.perform(object, value));

            } else if (action.equalsIgnoreCase("eat")) {
                object = in.next();
                value = in.nextDouble();
                System.out.println(tracker.eat(object, value));

            } else if (action.equalsIgnoreCase("report")) {
                System.out.println(tracker.report());
                break;

            } else if (action.equalsIgnoreCase("edit")) {
                System.out.println(tracker.editRecord());

            } else if (action.equalsIgnoreCase("delete")) {
                System.out.println(tracker.deleteRecord());
                
            } else if (action.equalsIgnoreCase("recover")) {
                System.out.println(tracker.recover());

            } else if (action.equalsIgnoreCase("current")){
                System.out.println(tracker.current());

            } else if (action.equalsIgnoreCase("bmi")) {
                System.out.println(tracker.bmi());

            } else {
                System.out.println("Invalid command. Please choose from commands Food, Activity, Perform, Eat, Report, Edit, Delete, Recover, Current, and BMI.");
            }
        }
        in.close();
    }
}