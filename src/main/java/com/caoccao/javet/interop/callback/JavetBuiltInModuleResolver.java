/*
 * Copyright (c) 2023-2025. caoccao.com Sam Cao
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.caoccao.javet.interop.callback;

import com.caoccao.javet.exceptions.JavetException;
import com.caoccao.javet.interop.NodeRuntime;
import com.caoccao.javet.interop.V8Runtime;
import com.caoccao.javet.node.modules.NodeModuleAny;
import com.caoccao.javet.utils.StringUtils;
import com.caoccao.javet.values.reference.IV8Module;
import com.caoccao.javet.values.reference.V8ValueObject;

/**
 * The type Javet built in module resolver is for resolving the Node.js built-in modules.
 *
 * @since 3.0.1
 */
public class JavetBuiltInModuleResolver implements IV8ModuleResolver {

    /**
     * The constant PREFIX_NODE.
     *
     * @since 3.0.1
     */
    public static final String PREFIX_NODE = "node:";
    /**
     * The constant DEFAULT.
     *
     * @since 3.1.0
     */
    public static final String DEFAULT = "default";

    @Override
    public IV8Module resolve(V8Runtime v8Runtime, String resourceName, IV8Module v8ModuleReferrer)
            throws JavetException {
        IV8Module iV8Module = null;
        // It only works for Node.js runtime and module names starting with "node:".
        if (v8Runtime.getJSRuntimeType().isNode()
                && StringUtils.isNotEmpty(resourceName)
                && resourceName.startsWith(PREFIX_NODE)) {
            NodeRuntime nodeRuntime = (NodeRuntime) v8Runtime;
            NodeModuleAny nodeModuleAny = nodeRuntime.getNodeModule(resourceName, NodeModuleAny.class);
            V8ValueObject v8ValueObject = nodeModuleAny.getModuleObject();
            /*
             * https://github.com/caoccao/Javet/issues/376
             *
             * Sometimes the module object has 'default' property, but that property seems to be weird.
             * Resetting the 'default' property resolves the issue.
             */
            v8ValueObject.set(DEFAULT, v8ValueObject);
            iV8Module = v8Runtime.createV8Module(resourceName, v8ValueObject);
        }
        return iV8Module;
    }
}
