/*
 * Copyright (C) 2013 salesforce.com, inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.auraframework.impl.clientlibrary.handler;

import org.auraframework.def.DefDescriptor;
import org.auraframework.def.ResourceDef;
import org.auraframework.impl.DefinitionAccessImpl;
import org.auraframework.impl.clientlibrary.ResourceDefImpl;
import org.auraframework.system.AuraContext;
import org.auraframework.system.TextSource;
import org.auraframework.throwable.quickfix.QuickFixException;

/**
 * ResourceDef handler.
 */
public class ResourceDefHandler {

    private ResourceDefImpl.Builder builder = new ResourceDefImpl.Builder();

    public ResourceDefHandler(DefDescriptor<ResourceDef> descriptor, TextSource<ResourceDef> source) {

        this.builder.setDescriptor(descriptor);
        this.builder.setLocation(source.getSystemId(), source.getLastModified());
        this.builder.setOwnHash(source.getHash());

        this.builder.setSource(source);
        this.builder.setAccess(new DefinitionAccessImpl(AuraContext.Access.PUBLIC));
    }

    public ResourceDef createDefinition() throws QuickFixException {
        return builder.build();
    }
}
