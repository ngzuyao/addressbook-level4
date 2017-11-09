# ngzuyao
###### \java\seedu\address\ui\PersonCard.java
``` java
    /**
     * Binds the individual UI elements to observe their respective {@code Person} properties
     * so that they will be notified of any changes.
     */
    private void bindListeners(ReadOnlyPerson person) {
        name.textProperty().bind(Bindings.convert(person.nameProperty()));
        phone.textProperty().bind(Bindings.convert(person.phoneProperty()));
        /* Commented out to remove address, email and tags from Person Card
        address.textProperty().bind(Bindings.convert(person.addressProperty()));
        email.textProperty().bind(Bindings.convert(person.emailProperty()));
        person.tagProperty().addListener((observable, oldValue, newValue) -> {
            tags.getChildren().clear();
            initTags(person);
        });*/

    }
```
###### \java\seedu\address\ui\PersonInformationPanel.java
``` java
package seedu.address.ui;

import java.util.HashMap;
import java.util.Random;
import java.util.logging.Logger;

import com.google.common.eventbus.Subscribe;

import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.events.ui.PersonPanelSelectionChangedEvent;
import seedu.address.model.person.ReadOnlyPerson;

/**
 * The person information panel of the app.
 */
public class PersonInformationPanel extends UiPart<Region> {

    private static final String FXML = "PersonInformationPanel.fxml";
    private static String[] colors = {"red", "yellow", "blue", "orange", "brown", "green", "pink", "black", "grey"};
    private static HashMap<String, String> tagColors = new HashMap<String, String>();
    private static Random random = new Random();

    private final Logger logger = LogsCenter.getLogger(this.getClass());

    private ReadOnlyPerson person;

    @FXML
    private VBox informationPane;
    @FXML
    private FlowPane tags;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label phone;
    @FXML
    private Label address;
    @FXML
    private Label email;

    public PersonInformationPanel() {
        super(FXML);
        loadDefaultScreen();
        registerAsAnEventHandler(this);
    }

    private static String getColorForTag(String tagValue) {
        if (!tagColors.containsKey(tagValue)) {
            tagColors.put(tagValue, colors[random.nextInt(colors.length)]);
        }

        return tagColors.get(tagValue);
    }

    private void loadDefaultScreen() {
        this.person = null; }

    /**
     * binds the person's information to that of the person card.
     * @param person
     * @param personid
     */
    private void bindListeners(ReadOnlyPerson person, int personid) {
        person.tagProperty().addListener((observable, oldValue, newValue) -> {
            tags.getChildren().clear();
            initTags();
        });

    }

    /**
     * loads the selected person's information to be displayed.
     * @param person
     * @param personid
     */
    private void loadPersonInformation(ReadOnlyPerson person, int personid) {
        this.person = person;
        tags.getChildren().clear();
        initTags();
        name.textProperty().bind(Bindings.convert(person.nameProperty()));
        phone.textProperty().bind(Bindings.convert(person.phoneProperty()));
        address.textProperty().bind(Bindings.convert(person.addressProperty()));
        email.textProperty().bind(Bindings.convert(person.emailProperty()));
        id.setText(Integer.toString(personid));

    }

    /**
     * Sets a background color for each tag.
     * @param
     */
    private void initTags() {
        person.getTags().forEach(tag -> {
            Label tagLabel = new Label(tag.tagName);
            tagLabel.setStyle("-fx-background-color: " + getColorForTag(tag.tagName));
            tags.getChildren().add(tagLabel);
        });
    }

    @Subscribe
    private void handlePersonPanelSelectionChangedEvent(PersonPanelSelectionChangedEvent event) {
        logger.info(LogsCenter.getEventHandlingLogMessage(event));
        loadPersonInformation(event.getNewSelection().person, event.getNewSelection().stringid);
        bindListeners(event.getNewSelection().person, event.getNewSelection().stringid);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof PersonInformationPanel)) {
            return false;
        }

        // state check
        PersonInformationPanel card = (PersonInformationPanel) other;
        return id.getText().equals(card.id.getText())
                && person.equals(card.person);
    }
}
```
###### \resources\view\PersonInformationPanel.fxml
``` fxml
<?xml version="1.0" encoding="UTF-8"?>

<?import javafx.scene.control.Label?>
<?import javafx.scene.layout.AnchorPane?>
<?import javafx.scene.layout.FlowPane?>
<?import javafx.scene.layout.HBox?>
<?import javafx.scene.layout.VBox?>

<AnchorPane xmlns="http://javafx.com/javafx/8.0.102" xmlns:fx="http://javafx.com/fxml/1">
   <children>
      <VBox>
         <children>
            <Label fx:id="id" styleClass="label-header" />
            <Label fx:id="name" styleClass="label-header" />
            <HBox>
               <children>
                  <Label styleClass="label-bright" text="Tags: " />
                  <FlowPane fx:id="tags" />
               </children>
            </HBox>
            <HBox>
               <children>
                  <Label styleClass="label-bright" text="Phone: " />
                  <Label fx:id="phone" styleClass="label-bright" />
               </children>
            </HBox>
            <HBox>
               <children>
                  <Label styleClass="label-bright" text="Address: " />
                  <Label fx:id="address" styleClass="label-bright" />
               </children>
            </HBox>
            <HBox>
               <children>
                  <Label styleClass="label-bright" text="Email: " />
                  <Label fx:id="email" styleClass="label-bright" />
               </children>
            </HBox>
         </children>
      </VBox>
   </children>
</AnchorPane>
```
###### \java\seedu\address\ui\PersonInformationPanel.java
``` java
    private void setLabelIndentation() {
        phoneLabel.setMinHeight(MIN_HEIGHT);
        phoneLabel.setMinWidth(MIN_WIDTH);
        phone.setMinHeight(MIN_HEIGHT);
        addressLabel.setMinHeight(MIN_HEIGHT);
        addressLabel.setMinWidth(MIN_WIDTH);
        address.setMinHeight(MIN_HEIGHT);
        emailLabel.setMinHeight(MIN_HEIGHT);
        emailLabel.setMinWidth(MIN_WIDTH);
        email.setMinHeight(MIN_HEIGHT);
        //customFieldsLabel.setMinWidth(MIN_WIDTH);
        //customFieldsLabel.setMinHeight(MIN_HEIGHT);
    }

    private void setIndentation(Label label) {
        label.setMinWidth(MIN_WIDTH);
        label.setMinHeight(MIN_HEIGHT);
    }
```