package com.Pidev.GUI.Event;

import com.Pidev.GUI.Event.AddEventForm;
import com.Pidev.Restaurant.Entities.Event;
import com.Pidev.Restaurant.Services.EventService;
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
public class ListEventForm  extends Form {

    ArrayList<Event> events = new ArrayList<>();
    Form current;

    public ListEventForm(Form previous) {
        current=this; //Back
        setTitle("List evenements");
        setLayout(BoxLayout.y());

        Button btnAddResto = new Button("Ajouter evenement");
        btnAddResto.addActionListener(e-> new AddEventForm(current).show());
        add(btnAddResto);

        EventService srv = EventService.getInstance();
        events = srv.getAllEvents();
        for (Event event : events) {
            try {
                add(addItem(event));
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
    }

    public Container addItem(Event r) throws IOException {
        Container c1 = new Container(BoxLayout.x());
        //String link = "file://" + "C:/Users/Mouhib/Downloads/ibiza.jpg";
        String link = "file://" + r.getImage();
        ImageViewer img = new ImageViewer(Image.createImage(link));
        //img.setSize(new Dimension(10, 10));
        //img.setWidth(10);
        //img.setHeight(10);
        Label lb = new Label(r.getName() + " " + r.getVille());

        c1.addAll(img, lb);
        Container c2 = new Container(BoxLayout.y());
        c2.addAll(c1);
        return c2;
    }

}
