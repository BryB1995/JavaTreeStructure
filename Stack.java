/**
 * Constructor and handler for Stack data structure
 * 
 * @author Bryce Baker
 * @version 2018/11/22
 */
public class Stack<T extends Comparable<T>>
{

   SLL<T> s = new SLL<T>();

   /** Push an element e of type T onto the stack. 
    *  @param e the element to push
    */
   public void push( T e)
   {
      s.addHead( e);
   }

   /** Pop the top element off the stack and return a pointer to it. 
    *  @return a pointer to the top element on the stack, null if the stack is empty. 
    */
   public T pop()
   {
      return s.deleteHead();
   }

   /** Return a pointer to the top element on the stack, without removing it. 
    *  @return a pointer to the top element on the stack, null if the stack is empty.
    */
   public T peek()
   {
      return s.get( 0);
   }

   /** Return true if the stack is empty, false otherwise. 
    *  @return boolean true if stack is empty. 
    */
   public boolean isEmpty() { return s.size() == 0; }

   /** Return the number of elements in the stack. 
    *  @return int
    */
   public int size() { return s.size(); }

}
