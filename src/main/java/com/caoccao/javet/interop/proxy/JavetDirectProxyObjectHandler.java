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

package com.caoccao.javet.interop.proxy;

import com.caoccao.javet.exceptions.JavetException;
import com.caoccao.javet.interop.V8Runtime;
import com.caoccao.javet.interop.callback.JavetCallbackContext;
import com.caoccao.javet.interop.callback.JavetCallbackType;
import com.caoccao.javet.values.V8Value;
import com.caoccao.javet.values.primitive.V8ValueBoolean;
import com.caoccao.javet.values.reference.V8ValueArray;

/**
 * The type Javet direct proxy object handler.
 *
 * @param <T> the type parameter
 * @param <E> the type parameter
 * @since 2.2.0
 */
public class JavetDirectProxyObjectHandler<T extends IJavetDirectProxyHandler<E>, E extends Exception>
        extends BaseJavetDirectProxyHandler<T, E> {
    /**
     * Instantiates a new Base javet direct proxy object handler.
     *
     * @param v8Runtime    the V8 runtime
     * @param targetObject the target object
     * @since 2.2.0
     */
    public JavetDirectProxyObjectHandler(V8Runtime v8Runtime, T targetObject) {
        super(v8Runtime, targetObject);
    }

    @Override
    public V8ValueBoolean deleteProperty(V8Value target, V8Value property) throws JavetException, E {
        V8ValueBoolean v8ValueBoolean = targetObject.proxyDeleteProperty(target, property);
        if (v8ValueBoolean != null) {
            return v8ValueBoolean;
        }
        return super.deleteProperty(target, property);
    }

    @Override
    public V8Value get(V8Value target, V8Value property, V8Value receiver) throws JavetException, E {
        V8Value v8Value = targetObject.proxyGet(target, property, receiver);
        if (v8Value != null) {
            return v8Value;
        }
        return super.get(target, property, receiver);
    }

    @Override
    public JavetCallbackContext[] getCallbackContexts() {
        return new JavetCallbackContext[]{
                new JavetCallbackContext(
                        PROXY_FUNCTION_NAME_DELETE_PROPERTY, this, JavetCallbackType.DirectCallNoThisAndResult,
                        (NoThisAndResult<E>) (v8Values) -> deleteProperty(v8Values[0], v8Values[1])),
                new JavetCallbackContext(
                        PROXY_FUNCTION_NAME_GET, this, JavetCallbackType.DirectCallNoThisAndResult,
                        (NoThisAndResult<E>) (v8Values) -> get(v8Values[0], v8Values[1], v8Values[2])),
                new JavetCallbackContext(
                        PROXY_FUNCTION_NAME_GET_OWN_PROPERTY_DESCRIPTOR, this, JavetCallbackType.DirectCallNoThisAndResult,
                        (NoThisAndResult<E>) (v8Values) -> getOwnPropertyDescriptor(v8Values[0], v8Values[1])),
                new JavetCallbackContext(
                        PROXY_FUNCTION_NAME_GET_PROTOTYPE_OF, this, JavetCallbackType.DirectCallNoThisAndResult,
                        (NoThisAndResult<E>) (v8Values) -> getPrototypeOf(v8Values[0])),
                new JavetCallbackContext(
                        PROXY_FUNCTION_NAME_HAS, this, JavetCallbackType.DirectCallNoThisAndResult,
                        (NoThisAndResult<E>) (v8Values) -> has(v8Values[0], v8Values[1])),
                new JavetCallbackContext(
                        PROXY_FUNCTION_NAME_OWN_KEYS, this, JavetCallbackType.DirectCallNoThisAndResult,
                        (NoThisAndResult<E>) (v8Values) -> ownKeys(v8Values[0])),
                new JavetCallbackContext(
                        PROXY_FUNCTION_NAME_SET, this, JavetCallbackType.DirectCallNoThisAndResult,
                        (NoThisAndResult<E>) (v8Values) -> set(v8Values[0], v8Values[1], v8Values[2], v8Values[3])),
        };
    }

    @Override
    public V8Value getOwnPropertyDescriptor(V8Value target, V8Value property) throws JavetException, E {
        V8Value v8Value = targetObject.proxyGetOwnPropertyDescriptor(target, property);
        if (v8Value != null) {
            return v8Value;
        }
        return super.getOwnPropertyDescriptor(target, property);
    }

    @Override
    public V8Value getPrototypeOf(V8Value target) throws JavetException, E {
        V8Value v8Value = targetObject.proxyGetPrototypeOf(target);
        if (v8Value != null) {
            return v8Value;
        }
        return super.getPrototypeOf(target);
    }

    @Override
    public V8ValueBoolean has(V8Value target, V8Value property) throws JavetException, E {
        V8ValueBoolean v8ValueBoolean = targetObject.proxyHas(target, property);
        if (v8ValueBoolean != null) {
            return v8ValueBoolean;
        }
        return super.has(target, property);
    }

    @Override
    public V8ValueArray ownKeys(V8Value target) throws JavetException, E {
        V8ValueArray v8ValueArray = targetObject.proxyOwnKeys(target);
        if (v8ValueArray != null) {
            return v8ValueArray;
        }
        return super.ownKeys(target);
    }

    @Override
    public V8ValueBoolean set(
            V8Value target,
            V8Value propertyKey,
            V8Value propertyValue,
            V8Value receiver)
            throws JavetException, E {
        V8ValueBoolean v8ValueBoolean = targetObject.proxySet(target, propertyKey, propertyValue, receiver);
        if (v8ValueBoolean != null) {
            return v8ValueBoolean;
        }
        return super.set(target, propertyKey, propertyValue, receiver);
    }
}
