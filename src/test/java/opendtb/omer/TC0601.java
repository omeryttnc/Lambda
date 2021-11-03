package opendtb.omer;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;

public class TC0601 {
    Response response;
    JsonPath json;
    String endPoint = "https://opentdb.com/api.php";
    List<Map<String, Object>> myList = new ArrayList<>();
    List<Map<String, Object>> map = new ArrayList<>();

    int amount;
    int category;
    String difficulty;
    String type;


    @Test
    public void testName() {

        Map<Integer, Object> map = new HashMap<>();
        int id = 1;
        String str = response.jsonPath().getString("results.difficulty");

        for (int i = 0; i < str.length(); i++) {

            map.put(id, new Category(
                            response.jsonPath().getString("results.category")
                            , response.jsonPath().getString("results.type")
                            , response.jsonPath().getString("results.difficulty")
                            , response.jsonPath().getString("results.question")
                            , response.jsonPath().getString("results.correct_answer")
                            , response.jsonPath().getList("results.incorrect_answers")
                    )


            );
            id++;
        }
        System.out.println(map.get(2));

    }


    @BeforeMethod
    public void setup() {
        response = given()
                .queryParam("amount", 10)
                .queryParam("category", 23)
                .queryParam("difficulty", "easy")
                .queryParam("type", "multiple")
                .contentType(ContentType.JSON)
                .when()
                .get(endPoint);
        json = response.jsonPath();
        myList = response.jsonPath().getList("results");
         response.prettyPrint();

    }

    @Test
    public void test01() {
        Assert.assertEquals(response.statusCode(), 200);
    }

    @Test
    public void test02() {
        Assert.assertEquals(myList.stream().map(t -> t.get("question")).count(), 10);
    }

    @Test
    public void test03() {
        Assert.assertTrue(myList.stream().noneMatch(t -> t.get("question") == null));
    }

    @Test
    public void test04() {
        Assert.assertTrue(myList.stream().noneMatch(t -> t.get("correct_answer") == null));

        Assert.assertEquals(myList.stream().map(t -> t.get("correct_answer")).count(),
                myList.stream().map(t -> t.get("question")).count());

    }

    @Test
    public void test05() {
        List<Object> incorrect_answers = myList
                .stream()
                .map(t -> t.get("incorrect_answers"))
                .collect(Collectors.toList());
//        List<Object> incorrect_answers = myList.stream().map(t -> t.get("incorrect_answers")).collect(Collectors.toList());
        // System.out.println(incorrect_answers);
//        System.out.println(cevir(incorrect_answers));
        Assert.assertTrue(cevir(incorrect_answers).stream().allMatch(t -> t.equals(3)));

    }

    public static List<Integer> cevir(List<Object> incorrect_answers) {
        List<Integer> size = new ArrayList<>();
        for (Object incorrect_answer : incorrect_answers) {
            ArrayList<String> str = (ArrayList<String>) incorrect_answer;
            size.add(str.size());
        }
        return size;
    }



}
