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
 * @author Michael Stummvoll
 * @author Ilya Selivanov
 */
public class HashCodeCollision {

    public List<String> genCollisionString(Integer len) {
        String str = "ab";
        str += str.toUpperCase();
        return genCollisionString(len, str);
    }

    public List<String> genCollisionString(Integer len, String alphabet) {
        Map<Integer, List<String>> hashMap = new HashMap<>();
        List<String> alphabet_list = new ArrayList<>();
        boolean isPrevSurrogatePair = false;
        for (int i = 1; i < alphabet.length(); ++i) {
            if (Character.isSurrogatePair(alphabet.charAt(i - 1), alphabet.charAt(i)) == true) {
                int code = Character.toCodePoint(alphabet.charAt(i - 1), alphabet.charAt(i));
                alphabet_list.add(String.valueOf((char) code));
                isPrevSurrogatePair = true;
                i += 1;
            } else {
                alphabet_list.add(String.valueOf(alphabet.charAt(i - 1)));
                isPrevSurrogatePair = false;
            }
        }
        if (isPrevSurrogatePair == false) {
            alphabet_list.add(String.valueOf(alphabet.charAt(alphabet.length() - 1)));
        }

        StringBuilder[] permutation = new StringBuilder[(int) Math.pow(alphabet_list.size(), len)];
        for (int idx = 0; idx < permutation.length; ++idx) {
            permutation[idx] = new StringBuilder();
            int idx_copy = idx;
            for (int i = 0; i < len; ++i) {
                permutation[idx].append(alphabet_list.get(idx_copy % alphabet_list.size()));
                idx_copy /= alphabet_list.size();
            }
            String permut_elem = permutation[idx].reverse().toString();
            Integer hash = permut_elem.hashCode();
            if (!hashMap.containsKey(hash)) {
                hashMap.put(hash, new ArrayList<String>());
            }
            hashMap.get(hash).add(permut_elem);
        }
        int max_size = 0;
        List<String> answer = null;
        for (List<String> elem : hashMap.values()) {
            if (elem != null) {
                if (answer == null) {
                    max_size = elem.size();
                    answer = elem;
                    continue;
                }
                if (elem.size() > max_size) {
                    max_size = elem.size();
                    answer = elem;
                }
            }
        }
        if (answer == null) {
            answer = new ArrayList<>();
        }
        return answer;
    }

}
