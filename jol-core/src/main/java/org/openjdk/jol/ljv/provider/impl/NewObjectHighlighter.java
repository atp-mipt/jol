/*
 * Copyright (c) 2022, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.  Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 */
package org.openjdk.jol.ljv.provider.impl;

import org.openjdk.jol.ljv.provider.ObjectAttributesProvider;

import java.util.IdentityHashMap;
import java.util.Map;

public class NewObjectHighlighter implements ObjectAttributesProvider {
    public static final String HIGHLIGHT = "style=filled,fillcolor=yellow";

    private final Map<Object, Object> known = new IdentityHashMap<>();
    private final Object dummy = new Object();

    @Override
    public String getAttribute(Object o) {
        if (known.put(o, dummy) == null) {
            return HIGHLIGHT;
        } else {
            return "";
        }
    }
}
