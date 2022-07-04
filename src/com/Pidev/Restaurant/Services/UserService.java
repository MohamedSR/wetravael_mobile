package com.Pidev.Restaurant.Services;

import com.Pidev.Restaurant.Entities.User;
import com.codename1.io.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UserService {

    public ArrayList<User> users;
    public static final String BASE_URL = "http://localhost:4443/api/v1/users";
    public static UserService instance;
    public boolean resultOK;
    private ConnectionRequest req;

    public UserService() {
        req = new ConnectionRequest();
    }

    public static UserService getInstance() {
        if (instance == null) {
            instance = new UserService();
        }
        return instance;
    }

    public List<Map<String, Object>> addUser(User u) throws IOException {
        String url = BASE_URL+"/create";
        req.setUrl(url);
        req.setPost(false);
        req.addArgument("name",u.getName());
        req.addArgument("phone",u.getPhone());
        req.addArgument("email",u.getEmail());
        req.addArgument("password",u.getPassword());
        req.addArgument("role",u.getRole());
        req.addArgument("image",u.getImage());
        NetworkManager.getInstance().addToQueueAndWait(req);
        Map<String,Object> result = new JSONParser().parseJSON(new InputStreamReader( new ByteArrayInputStream(req.getResponseData()),"UTF-8"));
        Map<String, Object> response =(Map<String, Object>) result.get("response");
        return (java.util.List<Map<String, Object>>)response.get("listings");
    }
    public ArrayList<User> parseUser(String jsonText) {
        try {
            users = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> usersListJson
                    = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) usersListJson.get("root");
            for (Map<String, Object> obj : list) {
                User u = new User();
                float id = Float.parseFloat(obj.get("id").toString());
                u.setId((int) id);
                if (obj.get("name") == null) {
                    u.setName("null");
                } else {
                    u.setName(obj.get("name").toString());
                }
                if (obj.get("name") == null) {
                    u.setName("null");
                } else {
                    u.setName(obj.get("name").toString());
                }
                if (obj.get("role") == null) {
                    u.setRole("null");
                } else {
                    u.setRole(obj.get("role").toString());
                }
                if (obj.get("email") == null) {
                    u.setEmail("null");
                } else {
                    u.setEmail(obj.get("email").toString());
                }
                if (obj.get("password") == null) {
                    u.setPassword("null");
                } else {
                    u.setPassword(obj.get("password").toString());
                }
                if(obj.get("phone") == null){
                    u.setPhone("null");
                } else {
                    u.setPhone(obj.get("phone").toString());
                }
                if (obj.get("image") == null) {
                    u.setImage("null");
                } else {
                    u.setImage(obj.get("image").toString());
                }
                users.add(u);
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return users;
    }

    public ArrayList<User> getAllUsers() {
        req = new ConnectionRequest();
        String url = BASE_URL+"/listUsers";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(e -> {
                    users = parseUser(new String(req.getResponseData()));
                }
        );
        NetworkManager.getInstance().addToQueueAndWait(req);
        return users;
    }
}
