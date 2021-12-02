import java.net.URI;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

/**
 *
 * @author gill
 */
public class AnalyzeImage {
    
    public static void main(String[] args) {
        
        String uriPrediction = "https://swcusvis.cognitiveservices.azure.com/customvision/v3.0/Prediction/f7c2e557-6de5-4ed0-ab45-2c99cc218a10/classify/iterations/Herramientas_demo1/url";
        String imageToAnalyze = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTssEAZ8k99aqZb90TJ3ZkCxkqUs2BVgFq53w&usqp=CAU";
        
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        try {
            URIBuilder builder = new URIBuilder(uriPrediction);

            // Prepare the URI for the REST API method.
            URI uri = builder.build();
            HttpPost request = new HttpPost(uri);

            // Request headers.
            request.setHeader("Content-Type", "application/json");
            request.setHeader("Prediction-Key", "6ccf7c6eaa264803bc0bcee88ddeec11");

            // Request body.
            StringEntity requestEntity
                    = new StringEntity("{\"url\":\"" + imageToAnalyze + "\"}");
            request.setEntity(requestEntity);

            // Call the REST API method and get the response entity.
            HttpResponse response = httpClient.execute(request);
            HttpEntity entity = response.getEntity();

            if (entity != null) {
                // Format and display the JSON response.
                String jsonString = EntityUtils.toString(entity);
                JSONObject json = new JSONObject(jsonString);
                System.out.println("REST Response:\n");
                System.out.println(json.toString(2));
            }
        } catch (Exception e) {
            // Display error message.
            System.out.println(e.getMessage());
        }
    }
}
