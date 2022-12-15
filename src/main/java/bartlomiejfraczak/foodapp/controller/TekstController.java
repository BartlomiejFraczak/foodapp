/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bartlomiejfraczak.foodapp.controller;

import bartlomiejfraczak.foodapp.config.ConfigReader;
import bartlomiejfraczak.foodapp.encje.PrzepisSzczegolowy;
import bartlomiejfraczak.foodapp.repo.dao.TekstDao;
import bartlomiejfraczak.foodapp.encje.Tekst;
import bartlomiejfraczak.foodapp.encje.Uzytkownik;
import bartlomiejfraczak.foodapp.komunikacja.SpoonacularAPI;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Scanner;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author thefr
 */
@RestController
@RequestMapping("/tekst")
public class TekstController {

    @Autowired
    private TekstDao TekstDao;

    public TekstController() throws MalformedURLException, IOException, ParseException {

//        PrzepisSzczegolowy ps = SpoonacularAPI.getInstancja().getRecipeInformation("638166");
//        System.out.println(ps);
//        String json = SpoonacularAPI.getInstancja().searchRecipes(null, null, null, "dairy,eggs", null, true);
//        System.out.println("json: " + json);
//            StringBuilder sb = new StringBuilder();
//            sb.append("{\"results\":[{\"id\":10011111,\"name\":\"banana leaves\",\"image\":\"banana-leaf.jpg\"},{\"id\":14211111,\"name\":\"banana extract\",\"image\":\"extract.png\"},{\"id\":98903,\"name\":\"banana pepper rings\",\"image\":\"wax-peppers.png\"},{\"id\":11976,\"name\":\"banana pepper\",\"image\":\"pepperoncini.jpg\"},{\"id\":10211643,\"name\":\"pink banana squash\",\"image\":\"pink-banana-squash.jpg\"},{\"id\":98987,\"name\":\"banana blossoms\",\"image\":\"banana-blossoms.jpg\"},{\"id\":10011677,\"name\":\"banana shallot\",\"image\":\"banana-shallots.jpg\"},{\"id\":9040,\"name\":\"banana\",\"image\":\"bananas.jpg\"},{\"id\":1009040,\"name\":\"mashed banana\",\"image\":\"bananas.jpg\"},{\"id\":18019,\"name\":\"banana bread\",\"image\":\"quick-bread.png\"}],\"offset\":0,\"number\":10,\"totalResults\":14}");
//
//            System.out.println("surowy json: " + sb);
//
////            JSONParser parser = new JSONParser();
////            JSONArray dataObject = (JSONArray) parser.parse(String.valueOf(sb));
//
//            JSONParser parser = new JSONParser();
//            Object obj = parser.parse(sb.toString());
//            JSONArray array = new JSONArray();
//            array.add(obj);
//
//            System.out.println("rozmiar: " + array.size());
//
//            System.out.println("pierwszy surowy: " + (JSONObject) array.get(0));
//            System.out.println("drugi surowy: " + (JSONObject) array.get(1));
//
//            System.out.println("pierwsza nazwa: " + ((JSONObject) array.get(0)).get("name"));
//            System.out.println("druga nazwa: " + ((JSONObject) array.get(1)).get("name"));
//        }
    }

    @GetMapping("/getall")
    public List<Tekst> getAllTekst() {
        return TekstDao.getTeksty();
    }

}
