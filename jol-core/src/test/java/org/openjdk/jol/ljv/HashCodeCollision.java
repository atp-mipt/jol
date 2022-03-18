package org.openjdk.jol.ljv;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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


        return hashMap.values().stream().max(Comparator.comparingInt(List::size)).orElse(new ArrayList<>());
    }
}
