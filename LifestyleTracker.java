import java.util.ArrayList;
import java.util.Scanner;

public class LifestyleTracker {
    Scanner in = new Scanner(System.in);

    private ArrayList<Food> foods;
    private ArrayList<Activity> activities;
    private ArrayList<Food> consumptionTally;    
    private ArrayList<Activity> performanceTally;
    private ArrayList<String> consumptionLog;
    private ArrayList<String> performanceLog;

    private String addFoodOutput;
    private String eatOutput;
    private String performOutput;
    private String addActivityOutput;
    private String editOutput;
    private String deleteOutput;
    private String recoverOutput;
    private String bmiOutput;
    private String currentOuput = "";
    private String reportOutput = "";

    private double gainedCalories;
    private double burnedCalories;
    private double kcal;

    private ArrayList<String> consumptionDeletedString;
    private ArrayList<Food> consumptionDeletedObjects;
    private ArrayList<String> performanceDeletedString;
    private ArrayList<Activity> performanceDeletedObjects;

    public LifestyleTracker() {
        foods = new ArrayList<>();
        activities = new ArrayList<>();
        consumptionLog = new ArrayList<>();
        consumptionTally = new ArrayList<>();
        performanceLog = new ArrayList<>();
        performanceTally = new ArrayList<>();
        consumptionDeletedObjects = new ArrayList<>();
        consumptionDeletedString = new ArrayList<>();
        performanceDeletedObjects = new ArrayList<>();
        performanceDeletedString = new ArrayList<>();
        gainedCalories = 0;
        burnedCalories = 0;
        kcal = 0.00012959782; 
    }
    
    // Methods
    public String addFood(String n, double c) {  
        addFoodLoop: while (true) {
			if (c >= 0) {
                foods.add(new Food(n, c));
                for (Food foodItem : foods) {
                    if (foods.indexOf(foodItem) == foods.size()-1) {
                        addFoodOutput = String.format("Added Food %s with %.2f kcal", n, c);
                        break addFoodLoop;
                    } else if ((foodItem.getFoodName()).equalsIgnoreCase(n)) {
                        foodItem.updateCalories(c);
                        addFoodOutput = String.format("Updated Food %s with %.2f kcal", n, c);
                        foods.remove(foods.size()-1);
                        break addFoodLoop;
                    }
                }
            } else {
				System.out.print("Number of calories cannot be negative. Please input another value: ");
				c = in.nextDouble();
			}
        }
        return addFoodOutput;
    }
   
    public String addActivity(String n, double c) {
        addActivityLoop: while (true) {
			if (c >= 0) {
                activities.add(new Activity(n, c));
                for (Activity activityItem : activities) {
                    if (activities.indexOf(activityItem) == activities.size()-1) {
                        addActivityOutput = String.format("Added Activity %s with %.2f kcal", n, c);
                        break addActivityLoop;
                    } else if ((activityItem.getActivityName()).equalsIgnoreCase(n)) {
                        activityItem.updateCalories(c);
                        addActivityOutput = String.format("Updated Activity %s with %.2f kcal", n, c);
                        activities.remove(activities.size()-1);
                        break addActivityLoop;
                    }
                }
            } else {
				System.out.print("Number of calories cannot be negative. Please input another value: ");
				c = in.nextDouble();
			}
        }
        return addActivityOutput;
    }

    public String eat(String foodName, double servings) {
        double newFoodCalories;
        String askAddFood;
        eatLoop: while (true) {
            if (servings > 0) {
                if (foods.size() == 0) {
                    System.out.printf("There are no items in your Food list. Do you want to add %s? (Y/N): ", foodName);
                    while (true) {
                        askAddFood = in.next();
                        if (askAddFood.equalsIgnoreCase("Y")) {
                            System.out.printf("Calories in 1 serving of %s: ", foodName);
                            while (true) {
                                newFoodCalories = in.nextDouble();
                                if (newFoodCalories >= 0) {
                                    foods.add(new Food(foodName, newFoodCalories));
                                    System.out.printf("%s has been added to your Food list. Eating again.\n", foodName);
                                    continue eatLoop;
                                } else {
                                    System.out.print("Number of calories cannot be negative. Please input another value: ");
                                }
                            }
                        } else if (askAddFood.equalsIgnoreCase("N")) {
                            eatOutput = String.format("%s was not added to your Food list.", foodName);
                            break eatLoop;
                        } else {
                            System.out.print("Invalid input. Please input Y or N: ");
                        }
                    }
                } else {
                    for (Food foodItem : foods) {
                        if ((foodItem.getFoodName()).equalsIgnoreCase(foodName)) {
                            consumptionTally.add(new Food(foodName, foodItem.getFoodCalories(), servings));
                            consumptionLog.add(String.format("%.2f serving(s) of %s, %.2f kcal", servings, foodName, foodItem.getFoodCalories() * servings));
                            eatOutput = String.format("Ate %.2f serving(s) of %s, %.2f kcal", servings, foodName, foodItem.getFoodCalories() * servings);
                            break eatLoop;
                        } else if (foods.indexOf(foodItem) == foods.size() - 1) {
                            System.out.printf("The specified food does not exist. Do you want to add %s? (Y/N): ", foodName);
                            while (true) {
                                askAddFood = in.next();
                                if (askAddFood.equalsIgnoreCase("Y")) {
                                    System.out.printf("Calories in 1 serving of %s: ", foodName);
                                    while (true) {
                                        newFoodCalories = in.nextDouble();
                                        if (newFoodCalories >= 0) {
                                            foods.add(new Food(foodName, newFoodCalories));
                                            System.out.printf("%s has been added to your Food list. Eating again.\n", foodName);
                                            continue eatLoop;
                                        } else {
                                            System.out.print("Number of calories cannot be negative. Please input another value: ");
                                        }
                                    }
                                } else if (askAddFood.equalsIgnoreCase("N")) {
                                    eatOutput = String.format("Failed to eat %s. It was not added to your Food list.", foodName);
                                    break eatLoop;
                                } else {
                                    System.out.print("Invalid input. Please input Y or N: ");
                                }
                            }
                        }
                    }
                }
            } else {
                System.out.print("Number of servings cannot be negative. Please input another value: ");
				servings = in.nextDouble();
            }
        }
    return eatOutput;
    }
    
    public String perform(String actName, double hours) {
        double newActivityCalories;
        String askAddActivity;
        performLoop: while (true) {
            if (hours > 0) {
                if (activities.size() == 0) {
                    System.out.printf("There are no items in your Activity list. Do you want to add %s? (Y/N): ", actName);
                    while (true) {
                        askAddActivity = in.next();
                        if (askAddActivity.equalsIgnoreCase("Y")) {
                            System.out.printf("Calories burned in 1 hour of %s: ", actName);
                            while (true) {
                                newActivityCalories = in.nextDouble();
                                if (newActivityCalories >= 0) {
                                    activities.add(new Activity(actName, newActivityCalories));
                                    System.out.printf("%s has been added to your Activity list. Performing again.\n", actName);
                                    continue performLoop;
                                } else {
                                    System.out.print("Number of calories cannot be negative. Please input another value: ");
                                }
                            }
                        } else if (askAddActivity.equalsIgnoreCase("N")) {
                            performOutput = String.format("Failed to perform %s. It was not added to your Activity list.", actName);
                            break performLoop;
                        } else {
                            System.out.print("Invalid input. Please input Y or N: ");
                        }
                    }
                } else {
                    for (Activity activityItem : activities) {
                        if ((activityItem.getActivityName()).equalsIgnoreCase(actName)) {
                            performanceTally.add(new Activity(actName, activityItem.getActivityCalories(), hours));
                            performanceLog.add(String.format("%.2f hour(s) of %s, %.2f kcal", hours, actName, activityItem.getActivityCalories() * hours));
                            performOutput = String.format("Performed %.2f hour(s) of %s, %.2f kcal", hours, actName, activityItem.getActivityCalories() * hours);
                            break performLoop;
                        } else if (activities.indexOf(activityItem) == activities.size() - 1) {
                            System.out.printf("The specified activity does not exist. do you want to add %s? (Y/N): ", actName);
                            while (true) {
                                askAddActivity = in.next();
                                if (askAddActivity.equalsIgnoreCase("Y")) {
                                    System.out.printf("Calories burned in 1 hour of %s: ", actName);
                                    while (true) {
                                        newActivityCalories = in.nextDouble();
                                        if (newActivityCalories >= 0) {
                                            activities.add(new Activity(actName, newActivityCalories));
                                            System.out.printf("%s has been added. Performing again.\n", actName);
                                            continue performLoop;
                                        } else {
                                            System.out.print("Number of calories cannot be negative. Please input a non-negative number: ");
                                        }
                                    }
                                } else if (askAddActivity.equalsIgnoreCase("N")) {
                                    performOutput = String.format("Failed to perform %s. It was not added to your Activity list.", actName);
                                    break performLoop;
                                } else {
                                    System.out.print("Invalid input. Please input Y or N: ");
                                }
                            }
                        }
                    }
                }
            } else {
                System.out.print("Number of hours cannot be negative. Please input another value: ");
				hours = in.nextDouble();
            }
        }
    return performOutput;
    }
    
    public String editRecord() {
        System.out.print("Which record do you want to edit? (Eaten/Performed): ");
        editLoop: while (true) {
            String whatToEdit = in.next();
            if (whatToEdit.equalsIgnoreCase("Eaten")) {
                System.out.println("----------------");
                System.out.println("FOODS EATEN");
                System.out.println( "----------------");
                int i = -1;
                if (consumptionTally.size() == 0) {
                    System.out.println("No entries found.");
                    System.out.println( "----------------");
                    editOutput = "Edit was canceled. Nothing to edit.";
                    break editLoop;
                } else {
                    for (Food consumedfood : consumptionTally) {
                        i += 1;
                        System.out.printf("[%d] %.2f servings(s) of %s %.2f\n", i, consumedfood.getServings(), consumedfood.getFoodName(), consumedfood.getFoodCalories() * consumedfood.getServings());
                    }
                }
                System.out.println( "----------------");
                System.out.print("Which entry do you want to edit? (Entry #): ");
                int recordNumber;
                while (true) {
                    recordNumber = in.nextInt();
                    if (recordNumber > consumptionTally.size() - 1 || recordNumber < 0) {
                        System.out.print("Entry does not exist. Please choose from the listed entry numbers: ");
                    } else {
                        break;
                    }
                }
                System.out.printf("Editing Entry #%d. What data do you want to edit? (Food/Servings): ", recordNumber);
                String whichFoodData;
                Food recordToEdit;
                while (true) {
                    whichFoodData = in.next();
                    recordToEdit = consumptionTally.get(recordNumber);
                    if (whichFoodData.equalsIgnoreCase("Servings")) {
                        double updatedServings;
                        while (true) {
                        System.out.print("Updated number of servings: ");
                        updatedServings = in.nextDouble();
                            if (updatedServings < 0) {
                                System.out.print("Number of servings cannot be negative. Please input another value: ");
                            } else {
                                break;
                            }
                        }
                        consumptionLog.set(recordNumber, (String.format("%.2f serving(s) of %s, %.2f kcal", updatedServings, recordToEdit.getFoodName(), recordToEdit.getFoodCalories() * updatedServings)));
                        consumptionTally.set(recordNumber, (new Food(recordToEdit.getFoodName(), recordToEdit.getFoodCalories(), updatedServings)));
                        editOutput = "Your record has been updated.";
                        break;
                    } else if (whichFoodData.equalsIgnoreCase("Food")) {
                        String updatedFood;
                        System.out.print("Updated food: ");
                        Food reference = null;
                        updateFood: while (true) {
                            updatedFood = in.next();
                            for (Food foodItem : foods){
                                if ((foodItem.getFoodName()).equalsIgnoreCase(updatedFood)) {
                                    reference = foodItem;
                                    break;
                                } else if (foods.indexOf(foodItem) == foods.size() - 1) {
                                    System.out.print("The specified food does not exist. Please input another food: ");
                                    continue updateFood;
                                }
                            }
                            break;
                        }
                        consumptionLog.set(recordNumber, (String.format("%.2f serving(s) of %s, %.2f kcal", recordToEdit.getServings(), updatedFood, reference.getFoodCalories() * recordToEdit.getServings())));
                        consumptionTally.set(recordNumber, (new Food(updatedFood, reference.getFoodCalories(), recordToEdit.getServings())));
                        editOutput = "Your record has been updated.";
                        break;
                    } else if (whichFoodData.equalsIgnoreCase("Calories")) {
                        System.out.print("You cannot edit the number of calories using the Edit command. Cancel this edit? (Y/N): ");
                        String editCaloriesPrompt;
                        while (true) {
                            editCaloriesPrompt = in.next();
                            if (editCaloriesPrompt.equalsIgnoreCase("Y")) {
                                editOutput = "Canceling this edit. Use the Food command to update calories.";
                                break editLoop;
                            } else if (editCaloriesPrompt.equalsIgnoreCase("N")) {
                                System.out.print("Continuing edit. What data do you want to edit? (Food/Servings): ");
                                continue editLoop;
                            } else {
                                System.out.print("Invalid input. Please input Y or N: ");
                            }  
                        }
                    } else {
                        System.out.print("Invalid input. Please input Servings or Food: ");
                    }
                }  
                } else if (whatToEdit.equalsIgnoreCase("Performed")) {
                System.out.println("----------------");
                System.out.println("ACTIVITIES PERFORMED");
                System.out.println( "----------------");
                int i = -1;
                if (performanceTally.size() == 0) {
                        System.out.println("No entries found.");
                        System.out.println( "----------------");
                        editOutput = "Edit was canceled. Nothing to edit.";
                        break editLoop;
                    } else {
                        for (Activity performedActivity : performanceTally) {
                            i += 1;
                            System.out.printf("[%d] %.2f hour(s) of %s %.2f\n", i, performedActivity.getHours(), performedActivity.getActivityName(), performedActivity.getActivityCalories() * performedActivity.getHours());
                        }
                    }
                System.out.println( "----------------");
                System.out.print("Which entry do you want to edit? (Entry #): ");
                int recordNumber;
                while (true) {
                    recordNumber = in.nextInt();
                    if (recordNumber > performanceTally.size() - 1 || recordNumber < 0) {
                        System.out.print("Entry does not exist. Please choose from the listed entry numbers: ");
                    } else {
                        break;
                    }
                }
                System.out.printf("Editing Entry #%d. What data do you want to edit? (Activity/Hours): ", recordNumber);
                String whichActivityData;
                Activity recordToEdit;
                while (true) {
                    whichActivityData = in.next();
                    recordToEdit = performanceTally.get(recordNumber);
                    if (whichActivityData.equalsIgnoreCase("Hours")) {
                        double updatedHours;
                        while (true) {
                        System.out.print("Updated number of hours: ");
                        updatedHours = in.nextDouble();
                            if (updatedHours < 0) {
                                System.out.print("Number of hours cannot be negative. Please input another value: ");
                            } else {
                                break;
                            }
                        }
                        performanceLog.set(recordNumber, (String.format("%.2f hour(s) of %s, %.2f kcal", updatedHours, recordToEdit.getActivityName(), recordToEdit.getActivityCalories() * updatedHours)));
                        performanceTally.set(recordNumber, (new Activity(recordToEdit.getActivityName(), recordToEdit.getActivityCalories(), updatedHours)));
                        editOutput = "Your record has been updated.";
                        break;
                    } else if (whichActivityData.equalsIgnoreCase("Activity")) {
                        String updatedActivity;
                        System.out.print("Updated activity: ");
                        Activity reference = null;
                        updateActivity: while (true) {
                            updatedActivity = in.next();
                            for (Activity activityItem : activities){
                                if ((activityItem.getActivityName()).equalsIgnoreCase(updatedActivity)) {
                                    reference = activityItem;
                                    break;
                                } else if (activities.indexOf(activityItem) == activities.size() - 1) {
                                    System.out.print("The specified activity does not exist. Please input another activity: ");
                                    continue updateActivity;
                                }
                            }
                            break;
                        }
                        performanceLog.set(recordNumber, (String.format("%.2f hour(s) of %s, %.2f kcal", recordToEdit.getHours(), updatedActivity, reference.getActivityCalories() * recordToEdit.getHours())));
                        performanceTally.set(recordNumber, (new Activity(updatedActivity, reference.getActivityCalories(), recordToEdit.getHours())));
                        editOutput = "Your record has been updated.";
                        break;
                    } else if (whichActivityData.equalsIgnoreCase("Calories")) {
                        System.out.print("You cannot edit the number of calories using the Edit command. Cancel this edit? (Y/N): ");
                        String editCaloriesPrompt;
                        while (true) {
                            editCaloriesPrompt = in.next();
                            if (editCaloriesPrompt.equalsIgnoreCase("Y")) {
                                editOutput = "Canceling this edit. Use the Activity command to update calories.";
                                break editLoop;
                            } else if (editCaloriesPrompt.equalsIgnoreCase("N")) {
                                System.out.print("Continuing edit. What data do you want to edit? (Activity/Hours): ");
                                continue editLoop;
                            } else {
                                System.out.print("Invalid input. Please input Y or N: ");
                            }  
                        }
                    } else {
                        System.out.print("Invalid input. Please input Activity or Hours: ");
                    }
                }
            } else {
                System.out.print("Invalid input. Please input Eaten or Performed: ");
                continue editLoop;
            }
            break;
        }
        return editOutput;
    }
    
    // Bonus
    public String deleteRecord() {
        System.out.print("Which record do you want to delete entries from? (Eaten/Performed): ");
        deleteLoop: while (true) {
            String whatToDelete = "";
            whatToDelete = in.next();
            if (whatToDelete.equalsIgnoreCase("Eaten")) {
                System.out.println("----------------");
                System.out.println("FOODS EATEN");
                System.out.println( "----------------");
                int i = -1;
                if (consumptionTally.size() == 0) {
                    System.out.println("No entries found.");
                    System.out.println( "----------------");
                    deleteOutput = "Deletion was canceled. Nothing to delete.";
                    break deleteLoop;
                } else {
                    for (Food consumedFood : consumptionTally) {
                        i += 1;
                        System.out.printf("[%d] %.2f serving(s) of %s %.2f\n", i, consumedFood.getServings(), consumedFood.getFoodName(), consumedFood.getFoodCalories() * consumedFood.getServings());
                    }
                }
                System.out.println( "----------------");
                System.out.print("Which entry do you want to delete? (Entry #): ");
                int recordNumber;
                while (true) {
                    recordNumber = in.nextInt();
                    if (recordNumber > consumptionTally.size() - 1 || recordNumber < 0) {
                        System.out.print("Entry does not exist. Please choose from the listed entry numbers: ");
                    } else {
                        break;
                    }
                }
                System.out.printf("Are you sure you want to delete Entry #%d? (Y/N): ", recordNumber);
                while (true) {
                    String sureDelete = in.next();
                    if (sureDelete.equalsIgnoreCase("Y")) {
                        consumptionDeletedString.add(consumptionLog.get(recordNumber));
                        consumptionDeletedObjects.add(consumptionTally.get(recordNumber));
                        consumptionLog.remove(recordNumber);
                        consumptionTally.remove(recordNumber);
                        deleteOutput = String.format("Entry has been deleted.", recordNumber);
                        break;
                    } else if (sureDelete.equalsIgnoreCase("N")) {
                        deleteOutput = "Entry was not deleted.";
                        break;
                    } else {
                        System.out.print("Invalid input. Please input Y or N: ");
                    }
                }
            } else if (whatToDelete.equalsIgnoreCase("Performed")) {
                System.out.println("----------------");
                System.out.println("ACTIVITIES PERFORMED");
                System.out.println( "----------------");
                int i = -1;
                if (performanceTally.size() == 0) {
                    System.out.println("No entries found.");
                    System.out.println( "----------------");
                    deleteOutput = "Deletion was canceled. Nothing to delete.";
                    break deleteLoop;
                } else {
                    for (Activity performedActivity : performanceTally) {
                        i += 1;
                        System.out.printf("[%d] %.2f hour(s) of %s %.2f\n", i, performedActivity.getHours(), performedActivity.getActivityName(), performedActivity.getActivityCalories() * performedActivity.getHours());
                    }
                }
                System.out.println( "----------------");
                System.out.print("Which entry do you want to delete? (Entry #): ");
                int recordNumber;
                while (true) {
                    recordNumber = in.nextInt();
                   
                    if (recordNumber > performanceTally.size() - 1 || recordNumber < 0) {
                        System.out.print("Entry does not exist. Please choose from the listed entry numbers: ");
                    } else {
                        break;
                    }
                }
                String sureDelete = "";
                System.out.printf("Are you sure you want to delete Entry #%d? (Y/N): ", recordNumber);
                while (true) {
                    sureDelete = in.next();
                    if (sureDelete.equalsIgnoreCase("Y")) {
                        performanceDeletedString.add(performanceLog.get(recordNumber));
                        performanceDeletedObjects.add(performanceTally.get(recordNumber));
                        performanceLog.remove(recordNumber);
                        performanceTally.remove(recordNumber);
                        deleteOutput = String.format("Entry has been deleted.", recordNumber);
                        break;
                    } else if (sureDelete.equalsIgnoreCase("N")) {
                        deleteOutput = "Entry was not deleted.";
                        break;
                    } else {
                        System.out.print("Invalid input. Please input Y or N: ");
                    }
                }
            } else {
                System.out.print("Invalid input. Please input Eaten or Performed: ");
                continue deleteLoop;
            }
            break;
        } 
        return deleteOutput;
    }
    
    public String recover() {
        int recordNumber;
        System.out.print("Which record do you want to recover an entry for? (Eaten/Performed): ");
        recoveryLoop: while (true) {
            String record = in.next();
            if (record.equalsIgnoreCase("Eaten")) {
                System.out.println("----------------");
                System.out.println("DELETED FOOD ENTRIES");
                System.out.println("----------------");
                if (consumptionDeletedString.size() == 0) {
                    System.out.println("No entries found.");
                    System.out.println("----------------");
                    recoverOutput = "Recovery was canceled. Nothing to recover.";
                    break recoveryLoop;
                } else {
                    for (String deletedFoodEntry : consumptionDeletedString) {
                    System.out.printf("[%d] %s\n", consumptionDeletedString.indexOf(deletedFoodEntry), deletedFoodEntry);
                    }
                }
                System.out.println("----------------");
                System.out.print("Which deleted entry do you want to recover? (Entry #): ");
                while (true) {
                    recordNumber = in.nextInt();
                    if ((recordNumber > consumptionDeletedString.size() - 1) || recordNumber < 0) {
                        System.out.print("Entry does not exist. Please choose from the listed entry numbers: ");
                    } else {
                        break;
                    }
                }
                System.out.print("Are you sure you want to recover this entry? (Y/N): ");
                while (true) { 
                    String toRecover;
                    toRecover = in.next();
                    if (toRecover.equalsIgnoreCase("Y")) {
                        consumptionLog.add(consumptionDeletedString.get(recordNumber));
                        consumptionTally.add(consumptionDeletedObjects.get(recordNumber));
                        consumptionDeletedString.remove(recordNumber);
                        consumptionDeletedObjects.remove(recordNumber);
                        recoverOutput = "Entry has been recovered.";
                        break;
                    } else if (toRecover.equalsIgnoreCase("N")) {
                        recoverOutput = "Entry was not recovered.";
                        break;
                    } else {
                        System.out.print("Invalid input. Please input Y or N: ");
                    }
                }
                break;
            } else if (record.equalsIgnoreCase("Performed")) {
                System.out.println("----------------");
                System.out.println("DELETED ACTIVITY ENTRIES");
                System.out.println("----------------");
                if (performanceDeletedString.size() == 0) {
                    System.out.println("No entries found.");
                    System.out.println("----------------");
                    recoverOutput = "Recovery was canceled. Nothing to recover.";
                    break recoveryLoop;
                } else {
                    for (String deletedActivityEntry : performanceDeletedString) {
                    System.out.printf("[%d] %s\n", performanceDeletedString.indexOf(deletedActivityEntry), deletedActivityEntry);
                    }
                }
                System.out.println("----------------");
                System.out.print("Which deleted entry do you want to recover? (Entry #): ");
                while (true) {
                    recordNumber = in.nextInt();
                    if ((recordNumber > performanceDeletedString.size() - 1) || recordNumber < 0) {
                        System.out.print("Entry does not exist. Please choose from the listed entry numbers: ");
                    } else {
                        break;
                    }
                }
                System.out.print("Are you sure you want to recover this entry? (Y/N): ");
                while (true) { 
                    String toRecover;
                    toRecover = in.next();
                    if (toRecover.equalsIgnoreCase("Y")) {
                        performanceLog.add(performanceDeletedString.get(recordNumber));
                        performanceTally.add(performanceDeletedObjects.get(recordNumber));
                        performanceDeletedString.remove(recordNumber);
                        performanceDeletedObjects.remove(recordNumber);
                        recoverOutput = "Entry has been recovered.";
                        break;
                    } else if (toRecover.equalsIgnoreCase("N")) {
                        recoverOutput = "Entry was not recovered.";
                        break;
                    } else {
                        System.out.print("Invalid input. Please input Y or N: ");
                    }
                }
                break;
            } else {
                System.out.print("Invalid input. Please input Eaten or Performed: ");
            }
        }
        return recoverOutput;
    }

    public String current() {
        currentOuput = "";
        currentOuput += "----------------\n";
        currentOuput += "CURRENT RECORDS\n";
        currentOuput += "----------------\n";
        currentOuput += "Eaten so far:\n";
        if (consumptionLog.size() == 0) {
            currentOuput += "No entries found.\n";
        } else {
            for (String consumedFoodString : consumptionLog) {
                currentOuput += consumedFoodString + "\n";
            }
        }
        currentOuput += "----------------\n";
        currentOuput += "Performed so far:\n";
        if (performanceLog.size() == 0) {
            currentOuput += "No entries found.\n";
        } else {
            for (String performedActivityString : performanceLog) {
                currentOuput += performedActivityString + "\n";
            }
        }
        currentOuput += "----------------\n";
        currentOuput += "Deleted so far:\n";
        if (consumptionDeletedString.size() == 0 && performanceDeletedString.size () == 0) {
            currentOuput += "No entries found.\n";
        } else {
            for (String deletedFoodString : consumptionDeletedString) {
                currentOuput += "Food Entry: " + deletedFoodString + "\n";
            }
            for (String deletedActivityString : performanceDeletedString) {
                currentOuput += "Activity Entry: " + deletedActivityString + "\n";
            }
        }
        currentOuput += "----------------";
        return currentOuput;
    }

    public String bmi() {
        double height = 0;
        double weight = 0;
        System.out.println("Let's find out your Body Mass Index! (bmi)");
        while (true) {
            System.out.print("Height in centimeters): ");
            height = in.nextDouble() * 0.01;
            System.out.print("Weight in kilograms: ");
            weight = in.nextDouble();
            if (weight <= 0 || height <= 0) {
                System.out.println("Height and weight cannot be negative. Please input other values in the next lines.");
            } else {
                double bmiNumber;
                bmiNumber = weight / (height * height);
                double idealWeight1 = 18.5 * (height * height);
                double idealWeight2 = 24.9 * (height * height);
                if (bmiNumber < 18.5) {
                    bmiOutput = String.format("Your bmi is %.2f (Underweight).", bmiNumber);
                } else if (bmiNumber <= 24.9) {
                    bmiOutput = String.format("Your bmi is %.2f (Normal Weight).", bmiNumber);
                } else if (bmiNumber <= 29.9) {
                    bmiOutput = String.format("Your bmi is %.2f (Overweight).", bmiNumber);
                } else if (bmiNumber <= 34.9) {
                    bmiOutput = String.format("Your bmi is %.2f (Obesity Class 1).", bmiNumber);
                } else if (bmiNumber <= 39.9) {
                    bmiOutput = String.format("Your bmi is %.2f (Obesity Class 2).", bmiNumber);
                } else if (bmiNumber >= 40) {
                    bmiOutput = String.format("Your bmi is %.2f (Obesity Class 3).", bmiNumber);
                }
                if (weight > idealWeight2) {
                    bmiOutput += String.format("\nYou need to lose around %.2f to %.2f kilograms to achieve the Normal Weight for your height.", (weight - idealWeight2), (weight - idealWeight1));
                } else if (weight < idealWeight1) {
                    bmiOutput += String.format("\nYou need to gain around %.2f to %.2f kilograms to achieve the Normal Weight for your height.", (idealWeight1 - weight), (idealWeight2 - weight));
                }
                bmiOutput += "\nDISCLAIMER: Factors such as sex and age, among others, entail specific needs for your body. This bmi calculator must only serve as a rough guide for lifestyle changes.";
                break;
            }
        }
        return bmiOutput;
    }

    // Final report
    public String report() {
        reportOutput += "----------------\n";
        reportOutput += "LIFESTYLE REPORT\n";
        reportOutput += "----------------\n";
        reportOutput += "Food Consumed:\n";
        if (consumptionLog.size() == 0) {
            reportOutput += "No entries found.\n";
        } else {
            for (String consumedFoodStrings : consumptionLog) {
                reportOutput += consumedFoodStrings + "\n";
            }
        }
        reportOutput += "----------------" + "\n";
        for (Food consumedFood : consumptionTally) {
            gainedCalories += consumedFood.getFoodCalories() * consumedFood.getServings();
        }
        reportOutput += "Total Calories Consumed: " + gainedCalories + " kcal\n";
        reportOutput += "----------------\n";
        reportOutput += "Activities Performed:\n";
        if (performanceLog.size() == 0) {
            reportOutput += "No entries found.\n";
        } else {
            for (String performedActivityString : performanceLog) {
                reportOutput += performedActivityString + "\n";
            }
        }
        reportOutput += "----------------\n";
        for (Activity performedActivity : performanceTally) {
            burnedCalories += performedActivity.getActivityCalories() * performedActivity.getHours();
        }
        reportOutput += "Total Calories Burned: " + burnedCalories + " kcal\n";
        reportOutput += "----------------\n";
        reportOutput += "Net Calories for the Day: " + (gainedCalories - burnedCalories) + " kcal\n";
        reportOutput += "If you keep up this lifestyle...\n";
        if (gainedCalories - burnedCalories > 0) {
            reportOutput += String.format("In a week, you will gain %.2f kilograms.\n", (7 * (gainedCalories - burnedCalories) * kcal));
            reportOutput += String.format("In a month, you will gain %.2f kilograms.\n", (30 * (gainedCalories - burnedCalories) * kcal));
            reportOutput += String.format("In 3 months, you will gain %.2f kilograms.\n", (90 * (gainedCalories - burnedCalories) * kcal));
            reportOutput += String.format("In 6 months, you will gain %.2f kilograms.\n", (180 * (gainedCalories - burnedCalories) * kcal));
        } else if (gainedCalories - burnedCalories < 0){
            reportOutput += String.format("In a week, you will lose %.2f kilograms.\n", (7 * (gainedCalories - burnedCalories) * kcal) * -1);
            reportOutput += String.format("In a month, you will lose %.2f kilograms.\n", (30 * (gainedCalories - burnedCalories) * kcal) * -1);
            reportOutput += String.format("In 3 months, you will lose %.2f kilograms.\n", (90 * (gainedCalories - burnedCalories) * kcal) * -1);
            reportOutput += String.format("In 6 months, you will lose %.2f kilograms.\n", (180 * (gainedCalories - burnedCalories) * kcal) * -1);
        } else {
            reportOutput += String.format("You will maintain your current weight.\n");
        }
        reportOutput += "----------------";
        return reportOutput;
    }
}