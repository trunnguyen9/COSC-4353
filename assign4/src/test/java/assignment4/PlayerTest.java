package assignment4;


import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import java.util.*;

public class PlayerTest {
    Avatar bike = new Bike();
    Avatar secondBike = new Bike();    
    List<Avatar> formSequence = new ArrayList<Avatar>(Arrays.asList(bike, secondBike));
    Player player = new Player(bike, formSequence);
    
    @Test
    public void testBikeAction()
    {
       player = new Player(spy(bike), formSequence);
       player.performAction();        
       verify(player.getAvatar()).performAction();
    }
        
    @Test
    public void testNextTransformationFirstToSecond()
    {
        player.transformNext();
        assertEquals(secondBike, player.getAvatar());
    }
    
    @Test
    public void testNextTransformationOneCycle()
    {
        player.transformNext();
        player.transformNext();
        assertEquals(bike, player.getAvatar());
    } 
    
    @Test 
    public void testTransformBackFromFirstToLast()
    {
        player.transformBack();
        assertEquals(secondBike, player.getAvatar());
    }
    
    @Test
    public void testTransformBackOneCycle()
    {
        player.transformBack();
        player.transformBack();
        assertEquals(bike, player.getAvatar());
    }
}