package opendtb;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;

public class TC07_TC08 {

    String endpoint="https://opentdb.com/api.php";
    Response response;
    JsonPath json;
    List<Map<String,String>> results=new ArrayList<>();

    @BeforeMethod
    public void setup(){
        response=given().accept(ContentType.JSON)
                .queryParam("amount","10").contentType(ContentType.JSON).when().get(endpoint);
        response.then().assertThat().statusCode(200).contentType(ContentType.JSON);
        // response.prettyPrint();
        json=response.jsonPath();
        results=json.getList("results");
    }

    @Test
    public void TC_07(){

        // * category ve difficulty degerlerinin birbirinden farkli degrler geldigini dogrulayiniz.

//

        List<String> categories=results.stream().map(x->x.get("category")).collect(Collectors.toList());
        List<String> difficulties=results.stream().map(x->x.get("difficulty")).collect(Collectors.toList());

        Assert.assertNotEquals(categories.size(), categories.stream().distinct().count());
        Assert.assertNotEquals(difficulties.size(), difficulties.stream().distinct().count());

        System.out.println("categories " + categories.size());
        System.out.println("categories distinct " + categories.stream().distinct().count());
        System.out.println("difficulties " + difficulties.size());
        System.out.println("difficulties distinct " + difficulties.stream().distinct().count());

    }


    @Test
    public void TC_08(){
        //* Tum sorularin coktan secmeli olarak geldigini dogrulayiniz
        List<String> types=results.stream().map(x->x.get("type")).collect(Collectors.toList());
        System.out.println("types = " + types);
        Assert.assertTrue(types.stream().allMatch(x->x.equals("multiple")));

    }

    @Test
    public void TC_17(){
        List<Map<String,List<String>>> incorrectAnswers=json.getList("results");

        incorrectAnswers.get(2).get("incorrect_answers").forEach(x-> System.out.println("x = " + x));

        Assert.assertEquals(incorrectAnswers.get(2).get("incorrect_answers").stream().count(),3);

    }





}
