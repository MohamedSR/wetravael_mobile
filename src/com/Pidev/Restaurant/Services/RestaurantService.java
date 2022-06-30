/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Pidev.Restaurant.Services;

import com.Pidev.Restaurant.Entities.Restaurant;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author m.rhouma
 */
public class RestaurantService {

    public ArrayList<Restaurant> restos;
    public static final String BASE_URL = "http://localhost:4443/api/v1/restaurants";
    public static RestaurantService instance;
    public boolean resultOK;
    private ConnectionRequest req;

    public RestaurantService() {
        req = new ConnectionRequest();
    }

    public static RestaurantService getInstance() {
        if (instance == null) {
            instance = new RestaurantService();
        }
        return instance;
    }

    public boolean addRestaurant(Restaurant r) {
        String url = BASE_URL;
        req.setUrl(url);
        req.setPost(true);
        req.addRequestHeader("Content-Type", "application/json");
        req.setRequestBody(r.toJSON());
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                System.out.println(req.getResponseErrorMessage());
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

    public ArrayList<Restaurant> parseRestaurant(String jsonText) {
        try {
            restos = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> restaurantsListJson
                    = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) restaurantsListJson.get("root");
            for (Map<String, Object> obj : list) {
                Restaurant r = new Restaurant();
                float id = Float.parseFloat(obj.get("id").toString());
                r.setId((int) id);
                if (obj.get("name") == null) {
                    r.setName("null");
                } else {
                    r.setName(obj.get("name").toString());
                }

                if (obj.get("name") == null) {
                    r.setName("null");
                } else {
                    r.setName(obj.get("name").toString());
                }
                if (obj.get("capacity") == null) {
                    r.setCapacity(0);
                } else {
                    float capacity = Float.parseFloat(obj.get("capacity").toString());
                    r.setCapacity((int) capacity);
                }

                if (obj.get("ville") == null) {
                    r.setVille("null");
                } else {
                    r.setVille(obj.get("ville").toString());
                }

                if (obj.get("pays") == null) {
                    r.setPays("null");
                } else {
                    r.setPays(obj.get("pays").toString());
                }

                if (obj.get("image") == null) {
                    r.setImage("null");
                } else {
                    r.setImage(obj.get("image").toString());
                }
                restos.add(r);
            }

        } catch (IOException ex) {

        }
        return restos;
    }

    public ArrayList<Restaurant> getAllRestaurants() {
        req = new ConnectionRequest();
        String url = BASE_URL;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(e -> {
            restos = parseRestaurant(new String(req.getResponseData()));
        }
        );
        NetworkManager.getInstance().addToQueueAndWait(req);
        return restos;
    }
}
