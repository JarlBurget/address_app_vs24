package ch.makery.address;

import ch.makery.address.repository.PersonRepository;
import ch.makery.address.util.FileUtil;
import ch.makery.address.view.*;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import java.io.File;


public class MainApp extends Application {

    private Stage primaryStage;

    private ViewManager viewManager = new ViewManager(this);
    private final FileUtil fileUtil = new FileUtil();
    private final PersonRepository personRepository = new PersonRepository();

    public PersonRepository getPersonRepository() {
        return this.personRepository;
    }

    public ViewManager getViewManager() {
        return this.viewManager;
    }

    public FileUtil getFileUtil() {
        return this.fileUtil;
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    // ainult salvestab/loeb eelistuse
    public void setPersonFilePath(File file) {
        fileUtil.setPersonFilePath(file);
        if (file != null) {
            this.primaryStage.setTitle("AddressApp - " + file.getName());
        } else {
            this.primaryStage.setTitle("AddressApp");
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("AddressApp");
        this.primaryStage.getIcons().add(new Image(getClass().getResource("images/address_book_icon.png").toExternalForm()));

        this.viewManager = new ViewManager(this);

        this.viewManager.initRootLayout();
        this.viewManager.showPersonOverview();

        // try to load last opened file
        File file = fileUtil.getPersonFilePath();
        if (file != null) {
            fileUtil.loadPersonDataFromFile(file, this.personRepository);
            this.primaryStage.setTitle("AddressApp - " + file.getName());
        } else {
            this.primaryStage.setTitle("AddressApp");
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}