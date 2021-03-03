import java.util.*;
/**
 * Initiates program, validates expression, and displays results
 *
 * @author Bryce Baker
 * @version 2018/11/22
 */
public class asmt4<T extends Comparable<T>>
{
    Queue<String> params  = new Queue();
    


//Run method to handle main calling of methods
    public void run()
    {
        Tree<String> t1 = new Tree<String>(); 
       
        
        
        
     
      
     System.out.println("Enter infix expression");
      Scanner scan = new Scanner(System.in);
      String in = scan.next();
      String in2 = postfixConvert(in);
      
     boolean pfr = pfValidator(in2);
     if(pfr == false)
     {
         System.out.println("Invalid postfix expression");
         
         
        }
        else
        {
              System.out.println("valid postfix expression");
           
              while(params.dequeue() != null)
              {
                  String test = params.dequeue();
                  if(test.replaceAll("[^a-zA-Z0-1]", "").length() > 0)
                  {
                      
                      t1.treeAdd(test);
                    
                    }
                  else
                  {
                     t1.treeCon(test);
                     
                    }
                  
                }
              t1.setRoot();
              System.out.println("In order:");
              t1.inOrder();
              System.out.println("Post order:");
              t1.postOrder();
              System.out.println("Pre order:");
              t1.preOrder();
              boolean val = t1.validator();
              if(val = true)
              {
                System.out.println("Tree is true");
                }
                else
                {System.out.println("Tree is false");}
         
        }
     
    }
    //Validate postfix expression
    public boolean pfValidator(String in)
    {
        List<String> bin = Arrays.asList("&","^","|");
        List<String> un = Arrays.asList("!");
       
        boolean res = true;
        int count = 0;
        int resCount = 0;
        int length = in.length() - 1;
        while(count <= length)
        {
            String tmp = (in.substring(count, count + 1));

                

                   if(tmp.replaceAll("[^a-zA-Z]", "").length() > 0)
                   {
                       //System.out.println("Define value for " + tmp); 
                       resCount++;
                       params.enqueue(tmp);
                    }
                    else if(tmp.replaceAll("[^0-1]", "").length() > 0)
                    {
                      resCount++;
                       params.enqueue(tmp);  
                    }
                    else
                    {
                        if(bin.contains(tmp))
                        {
                                            resCount = (resCount - 2);
                if(resCount < 0)
                {res = false;}
                resCount++;
                count++;   
                params.enqueue(tmp);
                        }
                
                        else if(un.contains(tmp))
                        {
                         resCount--;
                 if(resCount < 0)
                 {res = false;}
                 resCount++;
                 count++;   
                        }
                        else
                        {System.out.println("bad substring:" + tmp);
                       res = false;
                            count = length +1; }
                    }
            
            
        }
        if(resCount == 1 && res != false)
        {res = true;}
        return res;
    }
    //Convert infix expression to postfix
       public String postfixConvert(String in)
   {
       HashMap<String, Integer> opPri = new HashMap<String, Integer>();
       opPri.put("!", 3);
       opPri.put("&", 2);
       opPri.put("^", 1);
       opPri.put("|", 0);
       //TD: test both of these also finish it
        List<String> op = Arrays.asList("&","^","|","!","(");
       Queue<String> bq  = new Queue();
       Stack<String> ls = new Stack();
       
        String res = "";
        int count = 0;
       
        int length = in.length() - 1;
        while(count <= length)
        {
            String tmp = (in.substring(count, count + 1));

             
                   if(tmp.replaceAll("[^0-1]", "").length() > 0)
                   {
                       
                       bq.enqueue(tmp);
                    }
                    else if(tmp.replaceAll("[^a-zA-Z]", "").length() > 0)
                    {
                   
                        bq.enqueue(tmp);
                    }
                    else
                    {
                        
                        if(op.contains(tmp))
                        {
                if(ls.size() == 0)
                {}
                else
                {
                int opTst = opPri.get(tmp);
                boolean cont = false;
                while(cont == false && ls.peek() != null)
            {
                int stackTst = opPri.get(ls.peek());
                if(opTst < stackTst)
                {bq.enqueue(ls.pop());}
                else
                {
                 cont = true;   
                }
                
                }
                
            }
           
                ls.push(tmp);
            }
            else if(tmp.equals(")"))
            {
            while((!ls.peek().equals("(")) && ls.peek() != null)
            {
            bq.enqueue(ls.pop());
            }
            }
                        else
                        {System.out.println("bad substring:" + tmp);
                       
                            count = length +1; }
                    }
            
            count++;
        }
                while(ls.pop() != null)
        {
         bq.enqueue(ls.pop());
         
        }
        while(bq.dequeue() != null)
        {
         res = res + bq.dequeue();
         
         
        }

        return res;
    }
}
