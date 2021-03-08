package css.viewpager;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/***
 *  This is a RecycleView Adapater used to manage the images in the viewpager
 */
public class ImagePagerAdapter extends RecyclerView.Adapter<ImageViewHolder>{

    // This stores the resource ids for all the images from res->drawable
    private List<Integer> imageList = new ArrayList<>();
    private List<String> factList = new ArrayList<>();

    ImagePagerAdapter (Context context) {
        Log.d("CIS 3334", "In ImagePagerAdapter constructor -- Initialize array");
        imageList.add(R.drawable.cat1);
        imageList.add(R.drawable.cat2);
        imageList.add(R.drawable.cat3);
        imageList.add(R.drawable.cat4);
        imageList.add(R.drawable.cat5);
        imageList.add(R.drawable.cat6);
        imageList.add(R.drawable.cat7);
        imageList.add(R.drawable.cat8);
        imageList.add(R.drawable.cat9);
        imageList.add(R.drawable.cat10);
        //factList.add("Waiting for a fact");
        getSingleCatFact(context);
        getSingleCatFact(context);
        getSingleCatFact(context);
        getArrayCatFacts(context, 10);
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.image_layout, parent, false);
        ImageViewHolder holder = new ImageViewHolder(view);
        return new ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
        Log.d("CIS 3334", "In ImagePagerAdapter -- onBindViewHolder position="+position);
        Integer currentImage = imageList.get(position);
        holder.imageView.setImageResource(currentImage);
        holder.textViewCatFact.setText(factList.get(position));
        //holder.imageView.setImageResource(R.drawable.cat1);
        //holder.textViewCatFact.setText("Fun cat fact here");
    }

    @Override
    public int getItemCount() {
        Log.d("CIS 3334", "In ImagePagerAdapter -- getItemCount images = "+imageList.size()+" facts = "+factList.size());
        // return the smaller of the number of images or number of facts
        return Math.min(imageList.size(), factList.size());
    }

    /**
     * Request a single cat fact
     * @param context
     */
    private void getSingleCatFact(Context context) {
        Log.d("CIS 3334", "In ImagePagerAdapter -- getSingleCatFact");
        // Define URL to use. Using Cat Facts API here. Note number of facts set to 1
        // ---- Remember to add the following permission to the AndroidManifest.xml file
        //      <uses-permission android:name="android.permission.INTERNET" />
        String url = "https://cat-fact.herokuapp.com/facts/random?animal_type=cat&amount=1";
        // Create a Volley web request to receive back a JSON object.
        // This requires two listeners for Async response, onResponse() and onErrorResponse()
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url,null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response)
                    {
                        Log.d("CIS 3334", "In ImagePagerAdapter -- getSingleCatFact -- onResponse");
                        String jsonFact= response.toString();
                        // Remember to add the following to the module build.gradle file for the gson library for parsing json files
                        // implementation 'com.google.code.gson:gson:2.8.6'
                        Gson gson = new Gson();
                        Fact fact = gson.fromJson(jsonFact, Fact.class);
                        factList.add(fact.getText());
                        Log.d("CIS 3334", "In ImagePagerAdapter -- getSingleCatFact -- onResponse = "+fact.getText());
                        notifyDataSetChanged();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("CIS 3334", "In ImagePagerAdapter -- getSingleCatFact -- onErrorResponse = "+error);

                    }
                });

        // Create a RequestQueue used to send web requests using Volley
        RequestQueue queue = Volley.newRequestQueue(context);
        queue.add(jsonObjectRequest);
    }
    private void getArrayCatFacts(Context context, Integer numFacts) {
        Log.d("CIS 3334", "In ImagePagerAdapter -- getArrayCatFacts");
        // Define URL to use. Using Cat Facts API here. Note number of facts set to 1
        // ---- Remember to add the following permission to the AndroidManifest.xml file
        //      <uses-permission android:name="android.permission.INTERNET" />
        String url = "https://cat-fact.herokuapp.com/facts/random?animal_type=cat&amount="+numFacts;
        // Create a Volley web request to receive back a JSON object.
        // This requires two listeners for Async response, onResponse() and onErrorResponse()
        JsonArrayRequest jsonArrayRequest  = new JsonArrayRequest (Request.Method.GET, url,null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            // Loop through the array elements
                            for(int i=0;i<response.length();i++){
                                Log.d("CIS 3334", "In ImagePagerAdapter -- getArrayCatFacts -- onResponse with i="+i);
                                // Get current json object
                                JSONObject jsonObject = response.getJSONObject(i);
                                // Remember to add the following to the module build.gradle file for the gson library for parsing json files
                                // implementation 'com.google.code.gson:gson:2.8.6'
                                Gson gson = new Gson();
                                Fact fact = gson.fromJson(jsonObject.toString(), Fact.class);
                                factList.add(fact.getText());
                                notifyDataSetChanged();
                                Log.d("CIS 3334", "In ImagePagerAdapter -- getArrayCatFacts -- onResponse = "+fact.getText());
                            }
                        } catch (JSONException e) {
                            Log.d("CIS 3334", "In ImagePagerAdapter -- getArrayCatFacts -- JSONException = "+e.toString());
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("CIS 3334", "In ImagePagerAdapter -- getArrayCatFacts -- onErrorResponse = "+error);

                    }
                });

        // Create a RequestQueue used to send web requests using Volley
        RequestQueue queue = Volley.newRequestQueue(context);
        queue.add(jsonArrayRequest);
    }
}
