package bartlomiejfraczak.foodapp.komunikacja;

import bartlomiejfraczak.foodapp.config.ConfigReader;
import bartlomiejfraczak.foodapp.encje.Miara;
import bartlomiejfraczak.foodapp.encje.Przepis;
import bartlomiejfraczak.foodapp.encje.PrzepisSzczegolowy;
import bartlomiejfraczak.foodapp.encje.Skladnik;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author thefr
 */
public class SpoonacularAPI {

    private static SpoonacularAPI instancja;
    private String apiKey;
    private int number;

    public static SpoonacularAPI getInstancja() {
        if (instancja == null) {
            instancja = new SpoonacularAPI();
        }
        return instancja;
    }

    private SpoonacularAPI() {
        apiKey = ConfigReader.getInstancja().getApiKey();
        number = ConfigReader.getInstancja().getIloscOdpowiedzi();
    }

    private String url2json(String urlString) {
        if (urlString == null || urlString.isEmpty()) {
            return null;
        }
        URL url = null;
        try {
            url = new URL(urlString);
        } catch (MalformedURLException ex) {
            Logger.getLogger(SpoonacularAPI.class.getName()).log(Level.SEVERE, null, ex);
        }
        StringBuilder json = new StringBuilder();
        try {
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            try {
                conn.setRequestMethod("GET");
            } catch (ProtocolException ex) {
                Logger.getLogger(SpoonacularAPI.class.getName()).log(Level.SEVERE, null, ex);
            }
            conn.connect();
            int responseCode = conn.getResponseCode();

            if (responseCode != 200) {
                throw new RuntimeException(":( HttpResponseCode: " + responseCode);
            } else {
                Scanner scanner = new Scanner(url.openStream());

                while (scanner.hasNext()) {
                    json.append(scanner.nextLine());
                }
                scanner.close();
            }

        } catch (IOException ex) {
            Logger.getLogger(SpoonacularAPI.class.getName()).log(Level.SEVERE, null, ex);
        }
        return json.toString();
    }

    private List<Przepis> json2przepisLista(String json) {
        JSONObject obj = new JSONObject(json);
        JSONArray arr = obj.optJSONArray("results");
        List<Przepis> wynik = new ArrayList<>();
        for (int i = 0; i < arr.length(); i++) {
            wynik.add(new Przepis(
                    arr.optJSONObject(i).optInt("id"),
                    arr.optJSONObject(i).optString("title"),
                    arr.optJSONObject(i).optString("image"),
                    arr.optJSONObject(i).optString("imageType")
            ));
        }
        return wynik;
    }

    private PrzepisSzczegolowy json2przepisSzczegolowy(String json) {
        List<String> cuisines = new ArrayList<>();
        List<String> dishTypes = new ArrayList<>();
        List<String> diets = new ArrayList<>();
        List<String> occasions = new ArrayList<>();
        List<String> pairedWines = new ArrayList<>();
        String pairingText = "";
        List<Skladnik> extendedIngredients = new ArrayList<>();

        JSONObject obj = new JSONObject(json);

        JSONArray arr;

        arr = obj.optJSONArray("cuisines");
        for (int i = 0; i < arr.length(); i++) {
            cuisines.add(arr.optString(i));
        }
        arr = obj.optJSONArray("dishTypes");
        for (int i = 0; i < arr.length(); i++) {
            dishTypes.add(arr.optString(i));
        }
        arr = obj.optJSONArray("diets");
        for (int i = 0; i < arr.length(); i++) {
            diets.add(arr.optString(i));
        }
        arr = obj.optJSONArray("occasions");
        for (int i = 0; i < arr.length(); i++) {
            occasions.add(arr.optString(i));
        }
        JSONObject winePairing = obj.optJSONObject("winePairing");
        if (!winePairing.isEmpty()) {
            arr = winePairing.optJSONArray("pairedWines");
            for (int i = 0; i < arr.length(); i++) {
                pairedWines.add(arr.optString(i));
            }
            pairingText = winePairing.optString("pairingText");
        }
        arr = obj.optJSONArray("extendedIngredients");
        JSONObject skladnikJSON;
        JSONObject measuresJSON;
        List<String> meta = new ArrayList<>();
        for (int i = 0; i < arr.length(); i++) {
            skladnikJSON = arr.optJSONObject(i);
            for (int j = 0; j < skladnikJSON.optJSONArray("meta").length(); j++) {
                meta.add(skladnikJSON.optJSONArray("meta").optString(j));
            }
            measuresJSON = skladnikJSON.optJSONObject("measures");
            measuresJSON.optJSONObject("us").optFloat("amount");
            extendedIngredients.add(new Skladnik(
                    skladnikJSON.optInt("id"),
                    skladnikJSON.optString("aisle"),
                    skladnikJSON.optString("image"),
                    skladnikJSON.optString("consistency"),
                    skladnikJSON.optString("name"),
                    skladnikJSON.optString("nameClean"),
                    skladnikJSON.optString("original"),
                    skladnikJSON.optString("originalName"),
                    skladnikJSON.optFloat("amount"),
                    skladnikJSON.optString("unit"),
                    meta,
                    new Miara(
                            measuresJSON.optJSONObject("metric").optFloat("amount"),
                            measuresJSON.optJSONObject("metric").optString("unitShort"),
                            measuresJSON.optJSONObject("metric").optString("unitLong")
                    ),
                    new Miara(
                            measuresJSON.optJSONObject("us").optFloat("amount"),
                            measuresJSON.optJSONObject("us").optString("unitShort"),
                            measuresJSON.optJSONObject("us").optString("unitLong")
                    )
            ));

        }
        return new PrzepisSzczegolowy(
                obj.optInt("id"),
                obj.optString("title"),
                obj.optInt("readyInMinutes"),
                obj.optInt("servings"),
                obj.optString("sourceUrl"),
                obj.optString("image"),
                obj.optString("imageType"),
                obj.optString("summary"),
                cuisines,
                dishTypes,
                diets,
                occasions,
                pairedWines,
                pairingText,
                obj.optString("instructions"),
                obj.optBoolean("vegetarian"),
                obj.optBoolean("vegan"),
                obj.optBoolean("glutenFree"),
                obj.optBoolean("dairyFree"),
                obj.optBoolean("veryHealthy"),
                obj.optBoolean("cheap"),
                obj.optBoolean("veryPopular"),
                obj.optBoolean("sustainable"),
                obj.optBoolean("lowFodmap"),
                obj.optInt("weightWatcherSmartPoints"),
                obj.optString("gaps"),
                obj.optInt("preparationMinutes"),
                obj.optInt("cookingMinutes"),
                obj.optInt("aggregateLikes"),
                obj.optFloat("healthScore"),
                obj.optString("creditsText"),
                obj.optString("license"),
                obj.optString("sourceName"),
                obj.optFloat("pricePerServing"),
                extendedIngredients,
                obj.optString("spoonacularSourceUrl"),
                "",
                false
        );
    }

    private List<PrzepisSzczegolowy> json2przepisSzczegolowyLista(String json) {
        ArrayList<PrzepisSzczegolowy> wynik = new ArrayList<>();
        JSONArray arr = new JSONArray(json);
        for (int i = 0; i < arr.length(); i++) {
            wynik.add(json2przepisSzczegolowy(arr.getJSONObject(i).toString()));
        }
        return wynik;
    }

    /**
     *
     * @param query
     * @param cuisine
     * @param diet
     * @param intolerances
     * @param includeIngredients
     * @param instructionsRequired
     * @return
     */
    public List<Przepis> searchRecipes(String query, String cuisine, String diet, String intolerances, String includeIngredients, boolean instructionsRequired) {
        StringBuilder urlSb = new StringBuilder();
        urlSb.append("https://api.spoonacular.com/recipes/complexSearch")
                .append("?apiKey=").append(apiKey);
        if (query != null) {
            urlSb.append("&query=").append(query);
        }
        if (cuisine != null) {
            urlSb.append("&cuisine=").append(cuisine);
        }
        if (diet != null) {
            urlSb.append("&diet=").append(diet);
        }
        if (intolerances != null) {
            urlSb.append("&intolerances=").append(intolerances);
        }
        if (includeIngredients != null) {
            urlSb.append("&includeIngredients=").append(includeIngredients);
        }
        urlSb.append("&instructionsRequired=").append(instructionsRequired)
                .append("&ignorePantry=true")
                .append("&number=").append(number);
        String json = url2json(urlSb.toString());
        return json2przepisLista(json);
    }

    public PrzepisSzczegolowy getRecipeInformation(String id) {
        StringBuilder urlSb = new StringBuilder();
        urlSb.append("https://api.spoonacular.com/recipes/")
                .append(id)
                .append("/information?apiKey=").append(apiKey);
        String json = url2json(urlSb.toString());
        return json2przepisSzczegolowy(json);

    }

    public List<PrzepisSzczegolowy> getRecipeInformationBulk(List<String> ids) {
        if (ids.isEmpty()) {
            return new ArrayList<>();
        }
        StringBuilder urlSb = new StringBuilder();
        urlSb.append("https://api.spoonacular.com/recipes/informationBulk")
                .append("?apiKey=")
                .append(apiKey)
                .append("&ids=");
        for (String id : ids) {
            urlSb.append(id).append(",");
        }
        urlSb.deleteCharAt(urlSb.length() - 1);
        String json = url2json(urlSb.toString());
        return json2przepisSzczegolowyLista(json);

    }
}
