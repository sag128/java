class Queue{
  int n;
  boolean valueSet=false;
  synchronized int get(){
    if(!valueSet){  //acquiring valueSet
      try{
        wait();
        Thread.sleep(1000);
      }catch(InterruptedException e){
        e.printStackTrace();
      }
    }
    System.out.println("Got: "+n );
    valueSet=false; //releasing valueSet
    notify();
    return n;

  }

  synchronized void put (int n){
    if(valueSet){ //acquiring valueSet
      try{
        wait();
        Thread.sleep(1000);
      }catch(InterruptedException e){
        e.printStackTrace();
      }
    }
    this.n=n;
    System.out.println("Put : "+n);
    valueSet=true; //releasing valueSet
    notify();
  }
}
class Producer implements Runnable{
  Queue q;
  Producer(Queue q){
  this.q=q;
  new Thread(this,"Producer").start();
}
public void run(){
  int i=0;
    while(true){

      q.put(i++);

    }


}
}
class Consumer implements Runnable{
  Queue q;
  Consumer(Queue q){
  this.q=q;
  new Thread(this,"Consumer").start();
}
public void run(){
  while(true){
    q.get();

  }
}
}
class ProducerConsumer{
  public static void main(String[] args) {
    Queue q=new Queue();

    new Producer(q);
    new Consumer(q);
    System.out.println("Press Ctrl C to quit");
  }
}
