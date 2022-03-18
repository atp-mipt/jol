package org.openjdk.jol.ljv.provider.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledOnJre;
import org.junit.jupiter.api.condition.JRE;

import static org.openjdk.jol.ljv.provider.impl.NewObjectHighlighter.HIGHLIGHT;

class NewObjectHighlighterTest {
    @Test
    @EnabledOnJre(JRE.JAVA_11)
    void newObjectsAreHighlighted() {
        Object o1 = new Object();
        Object o2 = new Object();
        NewObjectHighlighter highlighter = new NewObjectHighlighter();
        Assertions.assertEquals(HIGHLIGHT, highlighter.getAttribute(o1));
        Assertions.assertEquals(HIGHLIGHT, highlighter.getAttribute(o2));
        Assertions.assertEquals("", highlighter.getAttribute(o1));
    }
}