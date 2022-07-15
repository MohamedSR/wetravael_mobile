/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Pidev.GUI.Restaurant;

import com.Pidev.Restaurant.Entities.Restaurant;
import com.Pidev.Restaurant.Services.RestaurantService;
import com.Pidev.Restaurant.Utils.Strings;
import com.codename1.ui.Button;
import static com.codename1.ui.CN1Constants.GALLERY_IMAGE;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;

/**
 *
 * @author m.rhouma
 */
public class AddRestaurantForm extends Form {

    private String filePath;

    public AddRestaurantForm(Form previous) {
        setTitle("Ajouter Restaurant");
        setLayout(BoxLayout.y());
        
        Label name = new Label("Nom");
        TextField nameInp = new TextField();

        Label adresse = new Label("Adresse");
        TextField adrInp = new TextField();

        Label ville = new Label("Ville");
        TextField villeInp = new TextField();

        Label pays = new Label("Pays");
        TextField paysInp = new TextField();

        Label capacity = new Label("capacité");
        TextField capacityInp = new TextField();

        Button uploadImg = new Button("Selectionner une Image");
        uploadImg.addActionListener(e3 -> {
            Display.getInstance().openGallery(new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    if (evt != null && evt.getSource() != null) {
                        filePath = (String) evt.getSource();
                    }
                }
            }, GALLERY_IMAGE);
        });
        Button addBtn = new Button("Ajouter");
        addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e2) {
                try {
                    RestaurantService rs = RestaurantService.getInstance();
                    Restaurant r = new Restaurant(Integer.parseInt(capacityInp.getText()), nameInp.getText(), adrInp.getText(), villeInp.getText(), paysInp.getText(), Strings.removePrefix(filePath, "file://"));
                    rs.addRestaurant(r);
                } catch (Exception e) {
                    Dialog.show("Error", "Error");
                }
                // Dialog.show("Info", "Utilisateur ajouté avec succéss");
            }
        });
        addAll(name, nameInp, adresse, adrInp, ville, villeInp, pays, paysInp, capacity, capacityInp, uploadImg, addBtn);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
    }
}
