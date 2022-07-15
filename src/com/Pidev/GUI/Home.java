/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Pidev.GUI;

import com.Pidev.GUI.Restaurant.AddRestaurantForm;
import com.Pidev.GUI.Restaurant.ListRestaurantForm;
import com.Pidev.GUI.User.ListUserForm;
import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;

/**
 *
 * @author m.rhouma
 */
public class Home extends Form {

    Form current;

    public Home() {
        current = this;
        setTitle("Home");
        setLayout(BoxLayout.y());
        Button btnRestaurant = new Button("Restaurants");
        Button btnUser = new Button("Users");
        btnRestaurant.addActionListener(e-> new ListRestaurantForm(current).show());
        btnUser.addActionListener(e-> new ListUserForm(current).show());
        add(btnRestaurant);
        add(btnUser);
    }
}
