/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Pidev.GUI;

import com.Pidev.GUI.Restaurant.AddRestaurantForm;
import com.Pidev.GUI.Restaurant.ListRestaurantForm;
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
        btnRestaurant.addActionListener(e-> new ListRestaurantForm(current).show());
        add(btnRestaurant);
    }
}
