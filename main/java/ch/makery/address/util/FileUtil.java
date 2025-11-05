package ch.makery.address.util;

import ch.makery.address.MainApp;
import ch.makery.address.model.Person;
import ch.makery.address.model.PersonListWrapper;
import ch.makery.address.repository.PersonRepository;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

import java.io.File;
import java.util.Collections;
import java.util.List;
import java.util.prefs.Preferences;

public class FileUtil {
    public File getPersonFilePath() {
        Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
        String filePath = prefs.get("filePath", null);
        if (filePath != null) {
            return new File(filePath);
        } else {
            return null;
        }
    }

    /**
     * Sets the file path of the currently loaded file. The path is persisted in
     * the OS specific registry.
     *
     * @param file the file or null to remove the path
     */
    public void setPersonFilePath(File file) {
        Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
        if (file != null) {
            prefs.put("filePath", file.getPath());
        } else {
            prefs.remove("filePath");
        }
    }

    public void loadPersonDataFromFile(File file, PersonRepository repository) {
        try {
            if (file == null || !file.isFile() || file.length() == 0) {
                // personData.clear();
                repository.setPersons(Collections.emptyList());
                setPersonFilePath(null);
                System.out.println("No data" +  "File is empty" +  "Loaded empty dataset.");
                return;
            }

            JAXBContext context = JAXBContext
                    .newInstance(PersonListWrapper.class);
            Unmarshaller um = context.createUnmarshaller();

            // Reading XML from the file and unmarshalling.
            PersonListWrapper wrapper = (PersonListWrapper) um.unmarshal(file);

            List<Person> loaded = (wrapper != null && wrapper.getPersons() != null)
                    ? wrapper.getPersons()
                    : Collections.emptyList();

            repository.setPersons(loaded);

            // Save the file path to the registry.
            setPersonFilePath(file);

            System.out.println("[LOAD] Laetud: " + loaded.size() + " (" + file.getAbsolutePath() + ")");

        } catch (Exception e) { // catches ANY exception
            e.printStackTrace(); // jäta arenduse ajaks alles
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Could not load data");
            alert.setContentText("Could not load data from file:\n" + file.getPath());

            alert.showAndWait();
        }
    }

    public void savePersonDataToFile(File file, ObservableList<Person> personData) {
        try {
            JAXBContext context = JAXBContext
                    .newInstance(PersonListWrapper.class);
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            // Wrapping our person data.
            PersonListWrapper wrapper = new PersonListWrapper();
            wrapper.setPersons(personData);

            // Marshalling and saving XML to the file.
            m.marshal(wrapper, file);

            // Save the file path to the registry.
            setPersonFilePath(file);
        } catch (Exception e) { // catches ANY exception
            System.out.println(e.getMessage());
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Could not save data");
            alert.setContentText("Could not save data to file:\n" + file.getPath());

            alert.showAndWait();
        }
    }
}