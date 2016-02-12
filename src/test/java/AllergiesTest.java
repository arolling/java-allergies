import org.junit.*;
import static org.junit.Assert.*;

import java.util.ArrayList;

import org.fluentlenium.adapter.FluentTest;
import org.junit.ClassRule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import static org.assertj.core.api.Assertions.assertThat;

public class AllergiesTest extends FluentTest {
  public WebDriver webDriver = new HtmlUnitDriver();
  public WebDriver getDefaultDriver() {
      return webDriver;
  }

  @ClassRule
  public static ServerRule server = new ServerRule();
// Unit testing

  @Test
  public void findAllergies_whenAllergyScoreIs128Return_cats() {
    ArrayList<String> allergies = new ArrayList<String>();
    allergies.add("cats");
    assertEquals(allergies, Allergies.findAllergies(128));
  }

  @Test
  public void findAllergies_whenAllergyScoreIsOneReturn_Eggs() {
    ArrayList<String> allergies = new ArrayList<String>();
    allergies.add("eggs");
    assertEquals(allergies, Allergies.findAllergies(1));
  }

  @Test
  public void findAllergies_whenAllergyScoreIs165Return_SeveralItems() {
    ArrayList<String> allergies = new ArrayList<String>();
    allergies.add("cats");
    allergies.add("chocolate");
    allergies.add("shellfish");
    allergies.add("eggs");
    assertEquals(allergies, Allergies.findAllergies(165));
  }

//Integration testing
  // @Test
  // public void rootTest() {
  //     goTo("http://localhost:4567/");
  //     assertThat(pageSource()).contains("Leap year detector");
  // }
}
