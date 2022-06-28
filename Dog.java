/**

@Author: Madhu Madhavan

**/

import java.util.*;
public class Dog 
{
    // Strings for breed/colour/name/description of dog
    private String breed;
    private String colour;
    private String description;

    // variables below are based on a scale of 1 - 10
    private byte bark;
    private byte sleep;
    private byte eat;
    private byte playful;
    private byte exercise;

    public Dog (String newBreed, String newColour, byte newBark, byte newSleep, byte newEat
    , byte newPlayful, byte newExercise)
    {
        this.breed = newBreed;
        this.colour = newColour;
        this.bark = newBark;
        this.sleep = newSleep;
        this.eat = newEat;
        this.playful = newPlayful;
        this.exercise = newExercise;
    }

    public void setDescription (String newDescription)
    {
        this.description = newDescription;
    }

    public String getDescription ()
    {
        return description;
    }

    public void setColour (String newColour)
    {
        this.colour = newColour;
    }

    public String getColour ()
    {
        return colour;
    }

    public void setBark (byte newBark)
    {
        this.bark = newBark;
    }
    
    public byte getBark ()
    {
        return bark;
    }
    
    public void setSleep (byte newSleep)
    {
        this.sleep = newSleep;
    }
    
    public byte getSleep ()
    {
        return sleep;
    }
    
    public void setEat (byte newEat)
    {
        this.eat = newEat;
    }
    
    public byte getEat ()
    {
        return eat;
    }
    
    public void setPlayful (byte newPlayful)
    {
        this.playful = newPlayful;
    }
    
    public byte getPlayful ()
    {
        return playful;
    }
    
    public void setExercise (byte newExercise)
    {
        this.exercise = newExercise;
    }
    
    public byte getExercise ()
    {
        return exercise;
    }
    
    public String toString()
    {
        String output = String.format("Breed: %s%n"
                                        + "Colour: %s%n"
                                        + "Description: %s%n"
                                        + "Following values are based on a scale of 1-10%n"
                                        + "Amount of barking: %d%n"
                                        + "Amount of sleep: %d%n"
                                        + "Amount eaten: %d%n"
                                        + "How playful: %d%n"
                                        + "Amount of exercise required: %d%n"
                                        , breed, colour, description, bark, sleep, eat, playful, exercise);
                                        
        return output;
    }
}