package com.Pidev.GUI.Event;

import com.Pidev.Restaurant.Entities.Event;
import com.Pidev.Restaurant.Services.EventService;
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

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 *
 * @author m.rhouma
 */
public class AddEventForm extends Form {

    private String filePath;

    public AddEventForm(Form previous) {
        setTitle("Ajouter Evenement");
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

        Label date = new Label("date");
        TextField dateInp = new TextField();

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
                    EventService rs = EventService.getInstance();
                    //java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
                    String strDate = dateInp.getText();
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");
                    java.util.Date parsed = format.parse(strDate);
                    java.sql.Date sqlDate = new java.sql.Date(parsed.getTime());
                    Event r = new Event(Integer.parseInt(capacityInp.getText()), nameInp.getText(), adrInp.getText(), villeInp.getText(), paysInp.getText(), sqlDate, Strings.removePrefix(filePath, "file://"));
                    rs.addEvent(r);
                } catch (Exception e) {
                    Dialog.show("Error", e.getMessage(), "OK", null);
                }

                Dialog.show("Info", "Evenement ajouté avec succéss", "OK", null);
            }
        });
        addAll(name, nameInp, adresse, adrInp, ville, villeInp, pays, paysInp, capacity, capacityInp, date, dateInp, uploadImg, addBtn);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
    }
}
