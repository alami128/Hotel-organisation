/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */

/**
 *
 * @author simo
 */
public enum MenuOption {
    SORT(1,"SORT") ,
    SEARCH(2,"SEARCH") ,
    ADD_RECORD(3,"ADD_RECORD") , 
    CREATE_TREE(4,"CREATE_TREE") ,
    EXIT(5,"EXIT");
   
private final int number ; 
private final String label ;

MenuOption ( int number , String label) {
    this.number = number ;
    this.label = label ;
 
}

    public int getNumber() {
        return number;
    }

    public String getLabel() {
        return label;
    }
    
}
   
    
    
}
