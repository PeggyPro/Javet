/*
 * Copyright (c) 2021-2025. caoccao.com Sam Cao
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

package com.caoccao.javet.entities;

import com.caoccao.javet.interfaces.IJavetEntityObject;

/**
 * The type Javet entity object is a container for arbitrary object.
 *
 * @param <T> the type parameter
 * @since 3.0.4
 */
public class JavetEntityObject<T> implements IJavetEntityObject<T> {
    /**
     * The Value.
     *
     * @since 3.0.4
     */
    protected T value;

    /**
     * Instantiates a new Javet entity object.
     *
     * @param value the value
     * @since 3.0.4
     */
    public JavetEntityObject(T value) {
        this.value = value;
    }

    @Override
    public T getValue() {
        return value;
    }

    @Override
    public void setValue(T value) {
        this.value = value;
    }
}
