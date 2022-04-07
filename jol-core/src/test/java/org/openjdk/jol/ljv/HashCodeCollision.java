package org.openjdk.jol.ljv;

import java.util.*;

/**
 * @author Michael Stummvoll
 * @author Ilya Selivanov
 */
public class HashCodeCollision {
/*
    public List<String> genCollisionString(Integer len) {
        String str = "ab";
        str += str.toUpperCase();
        return genCollisionString(len, str);
    }

    public List<String> genCollisionString(Integer len, String alphabet) {
        Map<Integer, List<String>> hashMap = new HashMap<>();
//        List<String> alphabet_list = alphabet.codePoints().mapToObj(c -> String.valueOf((char) c)).collect(Collectors.toUnmodifiableList());
        List<String> alphabet_list = alphabet.codePoints().mapToObj(c -> String.valueOf((char) c))
                .collect(Collectors.collectingAndThen(Collectors.toList(), x -> Collections.unmodifiableList(x)));
        Stream<String> permutation = alphabet_list.stream();

        for (int i = 0; i < len - 1; i++) {
            permutation = permutation
                    .flatMap(permutation_el -> alphabet_list.stream()
                            .map(alphabet_x -> permutation_el + alphabet_x));
        }

        permutation.forEach(permutation_el -> {
            Integer hash = permutation_el.hashCode();
            if (!hashMap.containsKey(hash)) {
                hashMap.put(hash, new ArrayList<>());
            }
            hashMap.get(hash).add(permutation_el);
        });

        ArrayList<String> res = null;
        for (List<String> value : hashMap.values()) {
            if (value != null) {
                if (res == null) {
                    res = (ArrayList<String>) value;
                    continue;
                }
                if (value.size() > res.size()) {
                    res = (ArrayList<String>) value;
                }
            }
        }
        if (res == null){
            res = new ArrayList<>();
        }
        return res;
    }
 */
}
