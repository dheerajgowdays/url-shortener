package com.dheeraj.urlshortener;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

import com.dheeraj.urlshortener.util.Base62Encoder;

class Base62EncoderTest {

    @Test
    void shouldEncodeZero() {
        assertEquals("0", Base62Encoder.encode(0));
    }

    @Test
    void shouldEncodeSimpleNumber() {
        assertEquals("21", Base62Encoder.encode(125));
    }
    @Test
    void shouldEncodeSimpleNumbers(){
        assertEquals("3ed", Base62Encoder.encode(12413));
    }

    @Test
    void shouldRejectNegativeNumber() {
        assertThrows(
                IllegalArgumentException.class,
                () -> Base62Encoder.encode(-1)
        );
    }
}