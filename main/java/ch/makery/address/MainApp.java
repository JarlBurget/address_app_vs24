package ch.makery.address.repository;

import ch.makery.address.model.Person;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class PersonRepository {

    /**
     * The data as an observable list of Persons.
     */
    private ObservableList<Person> persons = FXCollections.observableArrayList();

    public PersonRepository() {
        // Add some sample data
        this.add(new Person("Hans", "Muster"));
        this.add(new Person("Ruth", "Mueller"));
        this.add(new Person("Heinz", "Kurz"));
        this.add(new Person("Cornelia", "Meier"));
        this.add(new Person("Werner", "Meyer"));
        this.add(new Person("Lydia", "Kunz"));
        this.add(new Person("Anna", "Best"));
        this.add(new Person("Stefan", "Meier"));
        this.add(new Person("Martin", "Mueller"));
    }

    public ObservableList<Person> getPersons() {
        return this.persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons.setAll(persons == null ? List.of() : persons);
    }

    public void add(Person person) {
        this.persons.add(person);
    }

    public void remove(Person person) {
        this.persons.remove(person);
    }

    public void clear() {
        this.persons.clear();
    }
}