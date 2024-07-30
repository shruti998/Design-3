//Problem 1
//TC O(n)
//SC O(n)
/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *
 *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
 *     public boolean isInteger();
 *
 *     // @return the single integer that this NestedInteger holds, if it holds a single integer
 *     // Return null if this NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // @return the nested list that this NestedInteger holds, if it holds a nested list
 *     // Return empty list if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 */
public class NestedIterator implements Iterator<Integer> {
Stack<Iterator<NestedInteger>> s;
NestedInteger nextEl;
    public NestedIterator(List<NestedInteger> nestedList) {
        s=new Stack();
        s.push(nestedList.iterator());
    }

    @Override
    public Integer next() {
        return nextEl.getInteger();
    }

    @Override
    public boolean hasNext() {
        while(!s.isEmpty())
        {
            if(!s.peek().hasNext())
            {
s.pop();
            }
            else if((nextEl=s.peek().next()).isInteger()) return true;
            else{
                s.push(nextEl.getList().iterator());
            }
        }
        return false;
    }
}

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */

//Problem 2
//TC O(1)
//SC O(n)

class LRUCache {
class Node{
    int key,val;
    Node next,prev;
    public Node(int key,int val)
    {
        this.key=key;
        this.val=val;
    }
}
Node head,tail;
HashMap<Integer,Node> map;
int capacity;
private void addToHead(Node node)
{
    node.next=head.next;
    node.prev=head;
    node.next.prev=node;
    head.next=node;
}
private void removeNode(Node node)
{
    node.prev.next=node.next;
    node.next.prev=node.prev;
}
    public LRUCache(int capacity) {
      map=new HashMap<>();
      head=new Node(-1,-1);
      tail=new Node(-1,-1);
      head.next=tail;
      tail.prev=head;  
      this.capacity=capacity;
    }
    
    public int get(int key) {
       if(!map.containsKey(key))
       {
        return -1;
       } 
       Node node=map.get(key);
       removeNode(node);
       addToHead(node);
       return node.val;
    }
    
    public void put(int key, int value) {
        if(map.containsKey(key))
        {
            Node node=map.get(key);
            removeNode(node);
            node.val=value;
            addToHead(node);
            return;
        }
        // if capacity is reached
        if(capacity==map.size())
        {
            Node tailprev=tail.prev;
            // because tail is -1,-1
            removeNode(tailprev);
            map.remove(tailprev.key);

        }
        Node node=new Node(key,value);
        addToHead(node);
        map.put(key,node);
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */