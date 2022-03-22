package org.openjdk.jol.ljv.provider.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.openjdk.jol.ljv.provider.impl.NewObjectHighlighter.HIGHLIGHT;

public class NewObjectHighlighterTest {
    @Test
    void newObjectsAreHighlighted() {
        Object o1 = new Object();
        Object o2 = new Object();
        NewObjectHighlighter highlighter = new NewObjectHighlighter();
        Assertions.assertEquals(HIGHLIGHT, highlighter.getAttribute(o1));
        Assertions.assertEquals(HIGHLIGHT, highlighter.getAttribute(o2));
        Assertions.assertEquals("", highlighter.getAttribute(o1));
    }
}