package opendtb;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;

public class TC15 {
    String endpoint = "https://opentdb.com/api.php?amount=10&category=23&difficulty=easy&type=multiple";
    Response response;
    JsonPath json;

    /*
    * Kullanici ilgili endpointle get yaptiginda;

             * Status kodunun 200 oldugunu,
             * Ikinci  objenin ;
               "incorrect_answers" keyin'de  :
                 "1970" senesinin bulunmadigin ve son value'in 1925 oldugunu
    ve valuelerin büyükten kücüge siralandigini  verify eder.
    (Postman de herseferinde farkli valueler geliyor dikkate almadan kod yazarkenki valueler dikkate alinabilir.)
     */
    @Test
    public void task1() {
        response = given().contentType(ContentType.JSON).when().get(endpoint);
        json = response.jsonPath();
        response.prettyPrint();
        List<Map<String, String>> myList = json.getList("results");
        List<List<String>> list = json.getList("results.incorrect_answers");

        List<String> list_modified = list.get(1).stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        List<String> list_origin = list.get(1);

        //200
        Assert.assertEquals(response.statusCode(), 200);

        //"1970" senesinin bulunmadigin
        list.get(1).stream().forEach(t-> Assert.assertNotEquals(t, 1970));
        Assert.assertTrue(list.get(1).stream().noneMatch(t->t.equals("1970")));

        //son value'in 1925 oldugunu
       // Assert.assertEquals(list.get(1).get(list.get(1).size()-1),("1925"));

        //valuelerin büyükten kücüge siralandigin
        //Assert.assertEquals(list_origin, list_modified);
    }
}
