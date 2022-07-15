package com.Pidev.GUI.User;

import com.Pidev.Restaurant.Entities.User;
import com.Pidev.Restaurant.Services.UserService;
import com.codename1.components.ImageViewer;
import com.codename1.ui.*;
import com.codename1.ui.layouts.BoxLayout;

import java.io.IOException;
import java.util.ArrayList;

public class ListUserForm extends Form {

    ArrayList<User> users = new ArrayList<>();
    Form current;

    public ListUserForm(Form previous) {
        current=this; //Back
        setTitle("List users");
        setLayout(BoxLayout.y());

        Button btnAddUser = new Button("Ajouter user");
        btnAddUser.addActionListener(e-> new AddUserForm(current).show());
        add(btnAddUser);

        UserService srv = UserService.getInstance();
        users = srv.getAllUsers();
        for (User user : users) {
            try {
                add(addItem(user));
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
    }

    public Container addItem(User u) throws IOException {
        Container c1 = new Container(BoxLayout.x());
        String link = "file://" + u.getImage();
        ImageViewer img = new ImageViewer(Image.createImage(link));
        //img.setSize(new Dimension(10, 10));
        img.setWidth(10);
        img.setHeight(10);
        Label lb = new Label(u.getName() + " " + u.getEmail()+" " + u.getPhone());

        c1.addAll(img, lb);
        Container c2 = new Container(BoxLayout.y());
        c2.addAll(c1);
        return c2;
    }

}
