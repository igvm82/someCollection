/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package somecollection;

import java.util.Random;
import java.util.Scanner;



/**
 *
 * @author Ismael Gerardo Vargas Meza <ismael.vargas@facturasi.com>
 */
public class SomeCollection {
    
    Bucket[] map;// my array of Bucket for add elements
    int length; // this is the current length of my collection
    int identifier;
    private int index;
    private  int arraySize;//this is the real size of my array that is gonna be growing according to it needs

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        SomeCollection col=new SomeCollection();
Scanner scanner = new Scanner(System.in);
        printMenu();
boolean exit=false;

// Switch construct

//the programs is running while de exit variable is false
while(!exit){
String next = scanner.next();
switch (next) {
case "1":
  System.out.println("type an Integer: ");   
  Integer intToAdd=Integer.parseInt(scanner.next());
    System.out.println("the value was added with an identifier: "+col.insert(intToAdd)) ;
    printMenu();
  break;
case "2":
  System.out.println("type an identifier to delete:"); 
  Integer intToDelete=Integer.parseInt(scanner.next());
    System.out.println("the value was deleted : "+col.remove(intToDelete)) ;
    printMenu();
  break;
case "3":
  System.out.println("type an identifier to delete:");  
//  Integer intToDelete=Integer.parseInt(scanner.next());
    System.out.println("the biggest value is : "+col.removeLargest()) ;
    printMenu();
  break;
case "4":
  System.out.println("printing...");
    for (int i = 0; i < col.length; i++) {
        System.out.println((Integer)col.map[i].value);
    }
    printMenu();
  break;
case "5":
    System.out.println("how many values do you want add in the collection?: ");
    Integer num=Integer.parseInt(scanner.next());
    Random r = new Random(13);
    long TInicio, TFin, tiempo;
    TInicio=System.currentTimeMillis();
    for (int i = 0; i < num; i++) {
        col.insert(r.nextInt(10000));
    }
    TFin=System.currentTimeMillis();
    tiempo = TFin - TInicio;
    System.out.println(""+num+" values were inserted in "+tiempo+" millis5"
            + "1");
    break;
case "6":
    System.out.println("bye!");
    exit=true;
    break;
default:
//  System.out.println("bye!");
//  exit=true;
  break; 
}
}
    }
    

    private static void printMenu() {
        //  menu 

        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println(" ===============================");
        System.out.println("|            MENU               |");
        System.out.println(" ===============================");
        System.out.println("| Options:                      |");
        System.out.println("|        1. add integer         |");
        System.out.println("|        2. remove identifier   |");
        System.out.println("|        3. remove bigger value |");
        System.out.println("|        4. show collection     |");
        System.out.println("|        5. test                |");
        System.out.println("|        6. exit                |");
        System.out.println(" ===============================");
        System.out.println("");
        System.out.print(">>");
    }

    public SomeCollection() {
        this.map = new Bucket[10];
        this.length=0;
        this.index=0;
        this.identifier=100;
        this.arraySize=10;
    }
    
    //operation that adds the element into the array
    public Integer insert(Integer element){
        if (element==null){
            return null;
        }
        // if necessary increase the size of the array
        if(this.length==this.arraySize){
            growUpArray();
        }
        // get my identifier for the new element
        int ident=getIdentifier();
        map[index]=new Bucket(ident,element);
        length ++;
        index ++;
        return ident;
    }
    
    //operation that remove an element from the array
    public Integer remove(Integer identifier){
        
        // runs through the Array, comparing the identifier searched till it is found from the begining to the end if necessary 
        for (int i = 0; i < this.length; i++) {
            if(map[i].identifier==identifier){
                Integer value=(Integer)map[i].value;
                index(i);
                length--;
                index--;
                return value;
            }
        }
        return null;
    }
    
    //operation used to remove the biggest value 
    public Integer removeLargest(){
        Integer large=findLargest();
//        removeAll(large);
        removeFirst(large);
        return large;
    }
    
    //operation for remove the first element that match with the value sent
    private void removeFirst(Integer value){
        for (int i = 0; i < this.length; i++) {
            if(map[i].value==value){
                
                index(i);
                length--;
                index--;
                return;
            }
        }
    }
    //operation for remove all the elements that match with the value sent
    private void removeAll(Integer value){
        for (int i = 0; i < this.length; i++) {
            if(map[i].value==value){
                
                index(i);
                length--;
                index--;
                removeAll(value);
                return;
            }
        }
    }
    
    //operation used to move the elements through the array, when one is gonna be deleted
    private void index(int index){
        for (int i = index; i < length-1; i++) {
            map[i]=map[i+1];
            map[i+1]=null;
        }
    }
    
    //operation used to find the bigger value into the array
    private Integer findLargest(){
        Integer mayor=-1;
        for (int i = 0; i < length; i++) {
            if(mayor < (Integer)map[i].value){
                mayor=(Integer)map[i].value;
            }
        }
        if(mayor==-1){
            return null;
        }
        return mayor;
    }
    
    
    private int getIdentifier(){
        return identifier++;
    }

    //operation used to grow the Array when it is gonna need to store more items
    private void growUpArray() {
        Bucket[] temp=new Bucket[arraySize+10];
        System.arraycopy(map, 0, temp, 0, length);
        arraySize=arraySize+10;
        map=temp;
//        System.out.println("map has grown to "+arraySize);
    }
    
    //class that represents an item with a value and a identifier
    class Bucket{
        public int identifier;
        public Object value;

        public Bucket(int identifier, Object value) {
            this.identifier = identifier;
            this.value = value;
        }
        
        
    }
    
}
