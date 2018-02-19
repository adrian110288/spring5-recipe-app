package guru.springframework.converters;

import guru.springframework.commands.NotesCommand;
import guru.springframework.domain.Notes;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class NotesCommandToNotesTest extends BaseConverterTest<NotesCommandToNotes, NotesCommand> {

    private static final Long ID = 1L;
    private static final String NOTES = "notes";

    @Test
    public void convert() {

        NotesCommand command = new NotesCommand();
        command.setId(ID);
        command.setRecipeNotes(NOTES);

        Notes notes = target.convert(command);

        assertNotNull(notes);
        assertEquals(ID, notes.getId());
        assertEquals(NOTES, notes.getRecipeNotes());

    }

    @Override
    protected NotesCommandToNotes provideConverter() {
        return new NotesCommandToNotes();
    }

    @Override
    protected NotesCommand provideEmpty() {
        return new NotesCommand();
    }
}