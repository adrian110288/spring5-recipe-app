package guru.springframework.converters;

import guru.springframework.domain.UnitOfMeasure;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class UnitOfMeasureCommandToUnitOfMeasureTest extends BaseConverterTest<UnitOfMeasureCommandToUnitOfMeasure, guru.springframework.commands.UnitOfMeasureCommand> {

    private static final Long ID = 1L;
    private static final String DESCRIPTION = "description";

    @Test
    public void convert() {

        guru.springframework.commands.UnitOfMeasureCommand command = new guru.springframework.commands.UnitOfMeasureCommand();
        command.setId(ID);
        command.setDescription(DESCRIPTION);

        UnitOfMeasure unitOfMeasure = target.convert(command);

        assertNotNull(unitOfMeasure);
        assertEquals(ID, unitOfMeasure.getId());
        assertEquals(DESCRIPTION, unitOfMeasure.getDescription());
    }

    @Override
    protected UnitOfMeasureCommandToUnitOfMeasure provideConverter() {
        return new UnitOfMeasureCommandToUnitOfMeasure();
    }

    @Override
    protected guru.springframework.commands.UnitOfMeasureCommand provideEmpty() {
        return new guru.springframework.commands.UnitOfMeasureCommand();
    }
}