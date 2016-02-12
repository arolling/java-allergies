import java.util.HashMap;

import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import static spark.Spark.*;

import java.util.ArrayList;

public class Allergies {
  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";

    get("/allergy", (request, response) -> {
      HashMap model = new HashMap();

      model.put("template", "templates/allergy.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/allergyresult", (request, response) -> {
      HashMap model = new HashMap();
      Integer score = Integer.parseInt(request.queryParams("scoreEntry"));
      ArrayList<String> foundAllergies = new ArrayList<String>();
      foundAllergies = Allergies.findAllergies(score);
      String allergyString = Allergies.allergyPrintout(foundAllergies);
      model.put("scoreEntry", score);
      model.put("allergyList", allergyString);
      model.put("template", "templates/allergyresult.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());
  }

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

  public static String allergyPrintout(ArrayList<String> allergyArray) {
    String prettyResult = "";
    for (Integer i = 0 ; i < allergyArray.size() ; i++) {
      prettyResult += "<li>" + allergyArray.get(i) + "</li>";
    }
    return prettyResult;
  }
}
