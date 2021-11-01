package opendtb.omer;


import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;

public class TC0602 {
    Response response;
    JsonPath json;
    String endPoint = "https://opentdb.com/api.php";
    List<Map<String, String>> myList = new ArrayList<>();

    String amount;
    String category;
    String difficulty;
    String type;


    @Test
    public void test01() {
        response = given()
                .contentType(ContentType.JSON)
                .when()
                .get(endPoint);
        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertEquals(response.prettyPrint(), "");
    }

    @Test
    public void test02() {
        response = given()
                .queryParam("amount", 10)
                .contentType(ContentType.JSON)
                .when()
                .get(endPoint);
        json = response.jsonPath();
        //response.prettyPrint();
        myList = response.jsonPath().getList("results");
        System.out.println(myList);
        System.out.println();
        System.out.println("*************");
        System.out.println();
//myList.stream().map(t->t.get("question")).forEach(System.out::println);
myList.stream().filter(t->t.get("difficulty").equals("easy")).forEach(System.out::println);

        //1
        Assert.assertEquals( myList.stream().map(t -> t.get("question")).count(),10);

        //2
        List<Object> category = myList.stream().map(t -> t.get("category")).collect(Collectors.toList());
        List<Object> difficulty = myList.stream().map(t -> t.get("difficulty")).collect(Collectors.toList());
        Assert.assertNotEquals(category, difficulty);

        //3  false olarak degistirdim
        Assert.assertFalse(myList.stream().map(t -> t.get("type")).allMatch(t -> t.equals("multiple")));
       // response.prettyPrint();
    }

    @Test
    public void test03() {
        response = given()
                .queryParam("amount", 10)
                .queryParam("category", 27)
                .contentType(ContentType.JSON)
                .when()
                .get(endPoint);
        json = response.jsonPath();
        myList = response.jsonPath().getList("results");
        //response.prettyPrint();

        //1
        Assert.assertTrue(myList.stream().map(t->t.get("category")).allMatch(t->t.equals("Animals")));

        //2
        Assert.assertNotEquals(myList.stream().map(t->t.get("type")).distinct().count(),1);

    }

    @Test
    public void test04() {
        response = given()
                .queryParam("amount", 10)
                .queryParam("type", "boolean")
                .contentType(ContentType.JSON)
                .when()
                .get(endPoint);
        json = response.jsonPath();
        myList = response.jsonPath().getList("results");
        //response.prettyPrint();

        //1
        Assert.assertEquals(myList.stream().map(t->t.get("question")).count(),10);

        //2
        Assert.assertTrue(myList.stream().map(t->t.get("type")).allMatch(t->t.equals("boolean")));

    }
}
