import java.util.HashMap;

import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import static spark.Spark.*;

import java.util.ArrayList;

public class Allergies {
  public static void main(String[] args) {}

  public static ArrayList<String> findAllergies(Integer allergyScore) {
    ArrayList<String> allergies = new ArrayList<String>();
    HashMap<Integer, String> allergyTable = new HashMap<Integer, String>();
    String[] allergens = {"eggs", "peanuts", "shellfish", "strawberries", "tomatoes", "chocolate", "pollen", "cats"};
    Integer scoreBuilder = 1;
    for (Integer i = 0; i < allergens.length; i++) {
      allergyTable.put(scoreBuilder, allergens[i]);
      scoreBuilder *= 2;
    }
    do {
      if(allergyScore >= scoreBuilder) {
        allergies.add(allergyTable.get(scoreBuilder));
        allergyScore -= scoreBuilder;
      }
      scoreBuilder /= 2;
    } while (allergyScore > 0);
    return allergies;
  }
}
