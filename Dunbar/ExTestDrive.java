class MyEx extends Exception{}

public class ExTestDrive{
  public static void main(String[] args){
    String test = args[0];
    
    try{
      System.out.print("t");
      System.out.print("h");
      
      doRisky(test);
      
      System.out.print("a");
    }catch(MyEx e){
      System.out.print("r");
      System.out.print("o");
    }finally{
      System.out.print("w");
      System.out.println("s");
    }
  }
  
  public static void doRisky(String t) throws MyEx{
    if("yes".equals(t)){
      return;
    }
    
    throw new MyEx();
  }
}