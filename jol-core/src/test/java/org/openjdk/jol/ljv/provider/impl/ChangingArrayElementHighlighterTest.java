package org.openjdk.jol.ljv.provider.impl;

import org.junit.jupiter.api.condition.EnabledOnJre;
import org.junit.jupiter.api.condition.JRE;
import org.openjdk.jol.ljv.provider.impl.ChangingArrayElementHighlighter;
import org.testng.annotations.Test;

import static org.openjdk.jol.ljv.provider.impl.ChangingArrayElementHighlighter.HIGHLIGHT;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ChangingArrayElementHighlighterTest {

    ChangingArrayElementHighlighter provider = new ChangingArrayElementHighlighter();

    @Test
    @EnabledOnJre(JRE.JAVA_11)
    void checksChangedElements() {
        int[] arr = new int[]{1, 2, 3};
        for (int i = 0; i < arr.length; i++) {
            assertEquals("", provider.getAttribute(arr, i));
        }
        arr[0] = 2;
        arr[2] = 4;

        assertEquals(HIGHLIGHT, provider.getAttribute(arr, 0));
        assertEquals("", provider.getAttribute(arr, 1));
        assertEquals(HIGHLIGHT, provider.getAttribute(arr, 2));
    }
}