package guru.springframework.converters;

import guru.springframework.commands.Command;
import org.junit.Before;
import org.junit.Test;
import org.springframework.core.convert.converter.Converter;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public abstract class BaseConverterTest<C extends Converter, I extends Command> {

    C target;

    @Before
    public void setUp() throws Exception {
        target = provideConverter();
    }

    @Test
    public void testNullParameter() {
        assertNull(target.convert(null));
    }

    @Test
    public void testEmptyObject() {
        assertNotNull(target.convert(provideEmpty()));
    }

    protected abstract C provideConverter();

    protected abstract I provideEmpty();
}
