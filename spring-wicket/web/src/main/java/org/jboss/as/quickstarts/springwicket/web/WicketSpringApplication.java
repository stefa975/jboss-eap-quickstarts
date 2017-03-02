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
package org.jboss.as.quickstarts.springwicket.web;

import org.apache.wicket.Page;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import org.jboss.as.quickstarts.springwicket.web.config.WicketConfiguration;
import org.jboss.as.quickstarts.springwicket.web.pages.InsertContact;
import org.jboss.as.quickstarts.springwicket.web.pages.ListPersons;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


/**
 *
 * @author Ondrej Zizka
 */
public class WicketSpringApplication extends WebApplication {

    @Override
    public Class<? extends Page> getHomePage() {
        return ListPersons.class;
    }

    @Override
    protected void init() {
        super.init();

        super.init();

        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(WicketConfiguration.class);
        ctx.refresh();
        getComponentInstantiationListeners().add(new SpringComponentInjector(this, ctx));


        // Mount the InsertContact page at /insert
        mountPage("/person", InsertContact.class);
    }

}
