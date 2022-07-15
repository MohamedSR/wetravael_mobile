/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Pidev.GUI.Restaurant;

import com.Pidev.Restaurant.Entities.Restaurant;
import com.Pidev.Restaurant.Services.RestaurantService;
import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BoxLayout;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author m.rhouma
 */
public class ListRestaurantForm  extends Form {
    
    ArrayList<Restaurant> restos = new ArrayList<>();
    Form current;

    public ListRestaurantForm(Form previous) {
        current=this; //Back 
        setTitle("List restaurants");
        setLayout(BoxLayout.y());
        
        Button btnAddResto = new Button("Ajouter restaurant");
        btnAddResto.addActionListener(e-> new AddRestaurantForm(current).show());
        add(btnAddResto);
        
        RestaurantService srv = RestaurantService.getInstance();
        restos = srv.getAllRestaurants();
        for (Restaurant resto : restos) {
            try {
                add(addItem(resto));
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
    }
    
    public Container addItem(Restaurant r) throws IOException {
        Container c1 = new Container(BoxLayout.x());
        String link = "file://" + r.getImage();
        ImageViewer img = new ImageViewer(Image.createImage(link));
        //img.setSize(new Dimension(10, 10));
        img.setWidth(10);
        img.setHeight(10);
        Label lb = new Label(r.getName() + " " + r.getVille());

        c1.addAll(img, lb);
        Container c2 = new Container(BoxLayout.y());
        c2.addAll(c1);
        return c2;
    }
    
}
