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
package org.auraframework.impl.system;

import java.util.Set;

import org.auraframework.def.DefDescriptor;
import org.auraframework.def.Definition;
import org.auraframework.def.DescriptorFilter;
import org.auraframework.impl.source.SourceFactory;
import org.auraframework.service.CompilerService;
import org.auraframework.system.CacheableDefFactory;
import org.auraframework.system.Source;
import org.auraframework.throwable.quickfix.QuickFixException;

public class CacheableDefFactoryImpl<D extends Definition> extends DefFactoryImpl<D> implements CacheableDefFactory<D> {
    private final SourceFactory sourceFactory;
    private final CompilerService compilerService;

    public CacheableDefFactoryImpl(SourceFactory sourceFactory, CompilerService compilerService) {
        this.sourceFactory = sourceFactory;
        this.compilerService = compilerService;
    }

    @Override
    public D getDef(DefDescriptor<D> descriptor) throws QuickFixException {
        Source<D> source = sourceFactory.getSource(descriptor);
        if (source != null) {

            // Update the descriptor to respect the canonical case from the source.
            descriptor = source.getDescriptor();
            return compilerService.compile(descriptor, source);
        }
        return null;
    }

    @Override
    public Source<D> getSource(DefDescriptor<D> descriptor) {
        return sourceFactory.getSource(descriptor);
    }

    @Override
    public long getLastMod(DefDescriptor<?> descriptor) {
        return sourceFactory.getSource(descriptor).getLastModified();
    }

    @Override
    public boolean exists(DefDescriptor<D> descriptor) {
        Source<D> s = getSource(descriptor);
        return s != null;
    }

    @Override
    public boolean hasFind() {
        return true;
    }

    @Override
    public Set<DefDescriptor<?>> find(DescriptorFilter filter) {
        return sourceFactory.find(filter);
    }
}
