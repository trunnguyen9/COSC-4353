package assignment4;

import java.util.List;

public class Player {
    private Avatar avatar;
    private List<Avatar> formSequence;
    public Player(Avatar _avatar, List<Avatar> _formSequence)
    {
        avatar = _avatar;
        formSequence = _formSequence;
    }
    
    public Avatar getAvatar()
    {
        return avatar;
    }
        
    public void performAction()
    {
        System.out.println("Calling performAction on " + avatar.getClass().getName());
        avatar.performAction();
    }
    
    public void transformNext()
    {
        int index = formSequence.indexOf(avatar);
        avatar = formSequence.get(Math.floorMod(index + 1, formSequence.size()));
    }
    
    public void transformBack()
    {
        int index = formSequence.indexOf(avatar);
        avatar = formSequence.get(Math.floorMod(index - 1, formSequence.size()));
    }
}


