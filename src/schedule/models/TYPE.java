package schedule.models;
/**
 *
 * @author Andris Jansons
 */
public enum TYPE 
{ 
    // This will call enum constructor with one 
    // String argument 
    CLASS(0), FACILITY(1); 
  
    // declaring private variable for getting values 
    private int value; 
  
    // getter method 
    public int getValue() 
    { 
        return this.value; 
    } 
    
    public static TYPE fromInt(int i){
        if(TYPE.CLASS.getValue()==i){
            return TYPE.CLASS;
        }else{
            return TYPE.FACILITY;
        }
    }
  
    // enum constructor - cannot be public or protected 
    private TYPE(int value) 
    { 
        this.value = value; 
    } 
} 