public class CircularQueue {
    int maxSize; //circular queue size
    int front, rear;
    Passenger[] items;

    CircularQueue(int maxSize){
        this.maxSize = maxSize;
        this.items = new Passenger[maxSize];
        front = -1;
        rear = -1;
    }

    //to check if queue is full
    public boolean isFull(){
        if(front == 0 && rear == maxSize -1){
            return true;
        }
        return front == rear + 1;
    }

    //to check if queue is empty
    public boolean isEmpty(){
        return front == -1;
    }

    //adding an element to queue
    public void enQueue(Passenger element){
        if(isFull()){
            System.out.println("Queue is full!");
        }
        else {
            if(front == -1){
                front = 0;
            }
            rear = (rear + 1) % maxSize;
            items[rear] = element;
        }
    }

    //removing an element fom queue
    public Passenger deQueue(){
        Passenger element;
        if(isEmpty()){
            System.out.println("Queue is empty");
        }
        else{
            element = items[front];
            if(front == rear){
                front = -1;
                rear = -1;
            }
            else {
                front = (front + 1) % maxSize;
            }
            return element;
        }
        return null;
    }

/*   public static void main(String[] args){
        circularQueue q = new circularQueue(3);
        Passenger p1 = new Passenger("gigi", "kiki", 200);
        Passenger p2 = new Passenger("kiki", "tiffy", 200);
        Passenger p3 = new Passenger("tiki", "oil", 200);
        q.enQueue(p1);
        q.enQueue(p2);
        q.enQueue(p3);
        q.deQueue();
    }*/
}

