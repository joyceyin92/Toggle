package toggle;

/**
 * aaron    x
 * bobby    x
 * evan
 * jim
 * roy      x
 * tina     
 * ----------
 * aaron, bobby, roy
 * 
 * aaron -> bobby -> roy
 * 
 * toggle(friend) -> o(1)
 *      1. add
 *      2. remove
 * isSelected(friend) o(1)
 * getOrderedRecipients -> o(n)
 */
 import java.util.*;
 public class Solution {
     public static class Node {
         public Node next;
         public Node prev;
         public String name;
         Node(String name){
             this.name = name;
         }
     }
     
     public static Node head = null;
     public static Node tail = head;
     
     public static HashMap<String, Node> map = new HashMap<>();
     
     public static void toggle(String name){
         
         if (!map.containsKey(name)){
             map.put(name, new Node(name));
         }
         Node cur = map.get(name);
         
        // add
        if (head == null || cur.next == null && cur.prev == null && head!= cur){
        if (head == null){
            head = cur;
            tail = cur;
            head.prev = null;
            head.next = null;
            return;
        }
        else {
            tail.next = cur;
            cur.prev = tail;
            tail = cur;
            return;
            }
        }
         // remove
         
         else {
             if (map.containsKey(name) && map.get(name).next!=null && map.get(name).prev != null){ //in the middle
             Node del = map.get(name);
             Node preNode = del.prev;
             Node nxtNode = del.next;
             preNode.next = nxtNode;
             nxtNode.prev = preNode;
             del.prev = null;
             del.next = null;
         }
         else if (map.containsKey(name) && tail == map.get(name)){ // is tail
            Node del = map.get(name);
            tail = del.prev;
            tail.next = null;
            del.prev = null;
         }
         else if (map.containsKey(name) &&  head == map.get(name)){ // is head
            Node del = map.get(name);
            head = del.next;
            head.prev = null;
            del.next = null;
         }
         }
     }
     public static boolean isSelected(String name){
         Node n = map.get(name);
         return (!(n.prev==null&&n.next==null ) || head == n);
     }
     public static String getOrderedRecipients(){
         Node loop = head;
         StringBuilder result = new StringBuilder();
         while(loop!= null){
             String s = loop.name;
             result.append(s);
             loop = loop.next;
         }
         String ret = new String();
         ret = result.toString();
         return ret;
     }
     
     public static void main(String[] args){
         //test
    	 toggle("arron");
         toggle("bobby");
         toggle("roy");
         toggle("arron");
         toggle("tina");
         toggle("roy");
         System.out.println(isSelected("roy"));
         String result = getOrderedRecipients();
         System.out.println(result);
     }
 }