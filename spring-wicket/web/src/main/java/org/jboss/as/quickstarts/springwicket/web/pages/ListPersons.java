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
package org.jboss.as.quickstarts.springwicket.web.pages;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.jboss.as.quickstarts.springwicket.ejb.model.Person;
import org.jboss.as.quickstarts.springwicket.ejb.spring.PersonManager;

import javax.annotation.Resource;

/**
 * Dynamic behavior for the ListContact page
 * 
 * @author Filippo Diotalevi
 */
@SuppressWarnings("serial")
public class ListPersons extends WebPage {

    // Inject the ContactDao using @Inject
    @SpringBean
    private PersonManager personManager;

    @Resource(name = "welcomeMessage")
    private String welcome;

    // Set up the dynamic behavior for the page, widgets bound by id
    public ListPersons() {

        // Add the dynamic welcome message, specified in web.xml
        add(new Label("welcomeMessage", welcome));
        
        // Populate the table of contacts
        add(new ListView<Person>("contacts", personManager.getPersosn()) {

            @Override
            protected void populateItem(final ListItem<Person> item) {
                Person person = item.getModelObject();
                item.add(new Label("name", person.getName()));
                item.add(new Label("email", person.getEmail()));
                item.add(new Link<Person>("delete", item.getModel()) {

                    // Add a click handler
                    @Override
                    public void onClick() {
                        personManager.remove(item.getModelObject());
                        setResponsePage(ListPersons.class);
                    }
                });
            }
        });
    }

}
