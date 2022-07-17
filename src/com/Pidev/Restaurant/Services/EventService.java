package com.Pidev.Restaurant.Services;

import com.Pidev.Restaurant.Entities.Event;
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
public class EventService {

    public ArrayList<Event> events;
    public static final String BASE_URL = "http://localhost:3000/api/v1/events";
    public static EventService instance;
    public boolean resultOK;

    public EventService() {}

    public static EventService getInstance() {
        if (instance == null) {
            instance = new EventService();
        }
        return instance;
    }

    public boolean addEvent(Event r) {
        ConnectionRequest req = new ConnectionRequest();
        String url = BASE_URL;
        req.setUrl(url);
        req.setPost(true);
        req.addRequestHeader("Content-Type", "application/json");
        req.setRequestBody(r.toJSON());
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

    public ArrayList<Event> parseEvent(String jsonText) {
        try {
            events = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> eventsListJson
                    = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) eventsListJson.get("root");
            for (Map<String, Object> obj : list) {
                Event r = new Event();
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

                if (obj.get("date") == null) {
                    r.setImage("null");
                } else {
                    r.setImage(obj.get("date").toString());
                }
                if (obj.get("image") == null) {
                    r.setImage("null");
                } else {
                    r.setImage(obj.get("image").toString());
                }
                events.add(r);
            }

        } catch (IOException ex) {

        }
        return events;
    }

    public ArrayList<Event> getAllEvents() {
        ConnectionRequest req = new ConnectionRequest();
        String url = BASE_URL;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(e -> {
                    events = parseEvent(new String(req.getResponseData()));
                }
        );
        NetworkManager.getInstance().addToQueueAndWait(req);
        return events;
    }
}
