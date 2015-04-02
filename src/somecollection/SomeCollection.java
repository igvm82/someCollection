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
    
    Bucket[] map;
    int length;
    int identifier;
    private int index;
    private  int arraySize;

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
while(!exit){
String next = scanner.next();
switch (next) {
case "1":
  System.out.println("type an Integer: ");   // This is where I want to call the class
  Integer intToAdd=Integer.parseInt(scanner.next());
    System.out.println("the value was added with an identifier: "+col.insert(intToAdd)) ;
    printMenu();
  break;
case "2":
  System.out.println("type an identifier to delete:");  // this is where I want to call the class
  Integer intToDelete=Integer.parseInt(scanner.next());
    System.out.println("the value was deleted : "+col.remove(intToDelete)) ;
    printMenu();
  break;
case "3":
  System.out.println("type an identifier to delete:");  // this is where I want to call the class
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
  break; // This break is not really necessary
}
}
    }
    

    private static void printMenu() {
        // Display menu graphics
        System.out.println("");
        System.out.println("");
        System.out.println("");
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
    }

    public SomeCollection() {
        this.map = new Bucket[10];
        this.length=0;
        this.index=0;
        this.identifier=100;
        this.arraySize=10;
    }
    
    public Integer insert(Integer element){
        if (element==null){
            return null;
        }
        
        if(this.length==this.arraySize){
            growUpArray();
        }
        
        int ident=getIdentifier();
        map[index]=new Bucket(ident,element);
        length ++;
        index ++;
        return ident;
    }
    
    
    public Integer remove(Integer identifier){
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
    
    public Integer removeLargest(){
        Integer large=findLargest();
        removeAll(large);
        return large;
    }
    
    private void removeAll(Integer value){
        for (int i = 0; i < this.length; i++) {
            if(map[i].value==value){
                
                index(i);
                length--;
                index--;
                
            }
        }
    }
    
    private void index(int index){
        for (int i = index; i < length-1; i++) {
            map[i]=map[i+1];
            map[i+1]=null;
        }
    }
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

    private void growUpArray() {
        Bucket[] temp=new Bucket[arraySize+10];
        System.arraycopy(map, 0, temp, 0, length);
        arraySize=arraySize+10;
        map=temp;
        System.out.println("map has grown to "+arraySize);
        System.out.println(map.length);
    }
    
    
    class Bucket{
        public int identifier;
        public Object value;

        public Bucket(int identifier, Object value) {
            this.identifier = identifier;
            this.value = value;
        }
        
        
    }
    
}
