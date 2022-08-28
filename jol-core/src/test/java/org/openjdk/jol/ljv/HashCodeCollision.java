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
package org.openjdk.jol.ljv;

import java.util.*;

/**
 * @author Victor Petrosyan
 * @author Ilya Selivanov
 */
public class HashCodeCollision {

    public List<String> genCollisionString(Integer amountOfCollisions){
        StringBuilder sBuilder = new StringBuilder();
        for(int i = 0; i < amountOfCollisions + 1; ++i){
            sBuilder.append("MIPT");
        }
        sBuilder.trimToSize();

        ArrayList<String> collisions = new ArrayList<>();
        for(int i = 0; i < sBuilder.capacity(); i += 4){
            int randomNum = 1;
            if( i % 8 == 0){
                randomNum = 2;
            }
            char current = sBuilder.charAt(i);
            char next = sBuilder.charAt(i + 1);

            sBuilder.setCharAt(i, (char) (current + randomNum));
            sBuilder.setCharAt(i + 1, (char) (next - (31 * randomNum)));
            collisions.add(sBuilder.toString());
        }
        return collisions;
    }


}
