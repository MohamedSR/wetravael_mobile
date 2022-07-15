package com.Pidev.GUI.User;

import com.Pidev.Restaurant.Entities.User;
import com.Pidev.Restaurant.Services.UserService;
import com.Pidev.Restaurant.Utils.Strings;
import com.codename1.ui.*;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;

import static com.codename1.ui.CN1Constants.GALLERY_IMAGE;

public class AddUserForm extends Form{
    private String filePath;

    public AddUserForm(Form previous) {
        setTitle("Ajouter User");
        setLayout(BoxLayout.y());

        Label name = new Label("Nom");
        TextField nameInp = new TextField();

        Label email = new Label("Email");
        TextField mailInp = new TextField();

        Label password = new Label("Password");
        TextField passwordInp = new TextField();

        Label phone = new Label("Phone");
        TextField phoneInp = new TextField();

        Label role = new Label("Role");
        TextField roleInp = new TextField();

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
        Button plus = new Button("Ajouter");
        plus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e2) {
                try {
                    UserService us = UserService.getInstance();
                    User u = new User(nameInp.getText(), roleInp.getText(), mailInp.getText(), passwordInp.getText(), phoneInp.getText(), Strings.removePrefix(filePath,"file://"));
                    us.addUser(u);

                        Dialog.show("Info", "Utilisateur ajouté avec succéss","ok",null);
                } catch (Exception e) {
                    Dialog.show("Error", e.getMessage(), "OK", null);
                }
                Dialog.show("Info", "User ajouté avec succéss", "OK", null);
            }
        });
        addAll(name, nameInp, email, mailInp, password, passwordInp, phone, phoneInp, role, roleInp, uploadImg, plus);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
    }
}
