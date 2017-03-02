package org.jboss.as.quickstarts.springwicket.ejb.spring;

import org.jboss.as.quickstarts.springwicket.ejb.model.Person;

import java.util.List;

/**
 * Created by Stefan on 2017-03-02.
 */
public interface PersonManager {
    void remove(Person modelObject);

    List<Person> getPersosn();

    Person getPerson( long id);

    void addPerson(String name, String email);
}
