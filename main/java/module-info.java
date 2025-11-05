module ch.makery.address {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.prefs;

    requires javafx.base;

    requires jakarta.xml.bind;
    // requires ch.makery.address;
    requires javafx.graphics;
    // JAXB peab nägema su mudeleid runtime'is
    opens ch.makery.address.model to jakarta.xml.bind, javafx.base;

    opens ch.makery.address.view to javafx.fxml;

    exports ch.makery.address.util to org.glassfish.jaxb.core, org.glassfish.jaxb.runtime;
    exports ch.makery.address;
    exports ch.makery.address.view;


    opens ch.makery.address to javafx.fxml;
}