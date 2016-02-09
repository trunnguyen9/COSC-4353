package assignment4;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import java.util.stream.Collectors;

public class FormSequence {  
    
    public Avatar createAvatar(String avatarName)
    {
        try {
            return Class.forName("assignment4." + avatarName).asSubclass(Avatar.class).newInstance();
        } catch(Exception e) {
            return null;
        }  
    }
    
    public List<Avatar> createAvatarList(List<String> nameList)
    {
        return nameList.stream().map(item -> createAvatar(item)).collect(Collectors.toList());        
    }
    
    public List<Avatar> inputFromFile(String fileName) throws FileNotFoundException
    {        
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        return br.lines().filter(item -> item != null).map(item -> createAvatar(item)).collect(Collectors.toList());        
    }
}
