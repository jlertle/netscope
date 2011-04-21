package kernel;
import java.util.TreeSet;
/** 
 * As the simulator is event based there is a need for a queue to handle 
 * the occurring events. This general priority queue is based on a
 * a TreeSet.
*/
public class JxBaseEventQueue {

	private TreeSet queue = new TreeSet();

	/** Adds an item to the queue, item must be Comparable
	 * @param item The item to be added to the queue
	*/
	public void add( Comparable item ){  //������Ŀ
		queue.add( item );
	}

	/**
	 * @return Returns the first element and removes it form the queue 
	 */
	public Object getAndRemoveFirst(){
		if( queue.size() > 0 ){
			Object first = queue.first(); //�õ���һ��Ԫ��
			queue.remove( first ); //�Ƴ���һ��
			return first;            
		}else{
			return null;
		}
	}

	/**
	 * Clears the queue 
	 */
	public void clear(){ 
		queue.clear();
	}

	/**
	 * @return Returns the number of items in the queue
	*/
	public int size(){       
		return queue.size();
	}
}
