
package assignment4;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class FormSequenceTest {
    FormSequence formSequence = new FormSequence();
    
    public boolean compareTwoListsOfAvatar(List<Avatar> actualAvatarList, List<Avatar> expectAvatarList)
    {
        for (int i = 0; i < actualAvatarList.size(); i++)
        {
            if (!isTwoAvatarsEqual(expectAvatarList.get(i), actualAvatarList.get(i)))  
                return false;
        }
        return true;
    }
    
    public boolean isTwoAvatarsEqual(Avatar avatar1, Avatar avatar2)
    {
        String avatarName1 = (avatar1 == null ? "" : avatar1.getClass().getName());
        String avatarName2 = (avatar2 == null ? "" : avatar2.getClass().getName());
        return avatarName1.equals(avatarName2);
    }

    @Test
    public void testCreateValidBikeAvatar()
    {
        assertTrue(formSequence.createAvatar("Bike") instanceof Bike);
    }
    
    @Test
    public void testCreateInvalidAvatar()
    {
        assertEquals(null, formSequence.createAvatar("Invalid"));
    }

    @Test
    public void testCreateValidAvatarList()
    {
        List<String> validNameList = Arrays.asList("Bike", "Bike");
        List<Avatar> validAvatarList = Arrays.asList(new Bike(), new Bike());
        assertTrue(compareTwoListsOfAvatar(validAvatarList, formSequence.createAvatarList(validNameList)));
    }
    
    @Test
    public void testCreateInvalidAvatarList()
    {
        List<String> invalidNameList = Arrays.asList("Bike", "Invalid");
        List<Avatar> invalidAvatarList = Arrays.asList(new Bike(), null);
        assertTrue(compareTwoListsOfAvatar(invalidAvatarList, formSequence.createAvatarList(invalidNameList)));
    }
    
    @Test
    public void testGetAvatarListWithValidFileName() throws Exception {        
        String fileName = "input.txt";
        List<Avatar> actualAvatarList = new ArrayList<Avatar>();        
        actualAvatarList = formSequence.inputFromFile(fileName);
        List<Avatar> expectAvatarList = new ArrayList<Avatar>(Arrays.asList(new Bike(), new Bike(), new Car()));
        assertTrue(compareTwoListsOfAvatar(actualAvatarList, expectAvatarList));
    }
    
    @Test(expected = FileNotFoundException.class)
    public void testGetAvatarListWithInvalidFileName() throws Exception { 
        String fileName = "invalidInput.txt";
        List<Avatar> actualAvatarList = new ArrayList<Avatar>();        
        actualAvatarList = formSequence.inputFromFile(fileName);
    }
    
}
