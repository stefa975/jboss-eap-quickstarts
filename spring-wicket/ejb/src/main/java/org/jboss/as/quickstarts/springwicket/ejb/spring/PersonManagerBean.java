/*
 * JBoss, Home of Professional Open Source
 * Copyright 2012, Red Hat, Inc. and/or its affiliates, and individual
 * contributors by the @authors tag. See the copyright.txt in the 
 * distribution for a full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,  
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jboss.as.quickstarts.springwicket.ejb.spring;


import org.jboss.as.quickstarts.springwicket.ejb.model.Person;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * A bean which manages Contact entities.
 */
@Stateless(name = "PersonManager")
@Local
public class PersonManagerBean implements PersonManager {

    @PersistenceContext
    private EntityManager em;

    /**
     * Remove a Contact.
     */
    @Override
    public void remove(Person modelObject) {
        Person managed = em.merge(modelObject);
        em.remove(managed);
        em.flush();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Person> getPersosn() {
        return em.createQuery("SELECT p FROM Person p").getResultList();

    }

    /**
     * Get Person by ID.
     */
    @Override
    public Person getPerson(long id) {
        return  em.find(Person.class, id);

    }

    /**
     * Add a new Person.
     */
    @Override
    public void addPerson(String name, String email) {
        em.merge(new Person(null, name, email));
    }
}
