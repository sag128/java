class Thrr implements Runnable{

  Thrr(){
  Thread  t1=new Thread(this,"Sagar"); //You are referring to the constructor Thread(Runnable target, String name), where first parameter target is any Runnable instance having a run() method.
  t1.start();
  }

/*synchronized*/ public void run(){

    System.out.println(" (in run) Child Thread name is " +Thread.currentThread().getName());
    System.out.println(" (in run) Child Thread id is " +Thread.currentThread().getId());


  for(int j=0;j<5;j++){
    try {


  System.out.println("child thread " +j+ " -"+Thread.currentThread().getName());
      Thread.sleep(1000);}catch(InterruptedException e)
{
  System.out.println("child interrupted ");
}
  }
}
}
class Thrr2 implements Runnable{
  Thrr2(){

    Thread tt=new Thread(this,"Another Thread");
    tt.start();
  }
  /* synchronized*/ public void run(){

    System.out.println(" (in run) Another Child Thread name is " +Thread.currentThread().getName());
      System.out.println("(in run) id is "+Thread.currentThread().getId());
    try{
      for(int j=0;j<7;j++){
        System.out.println("hi " +j+ " -"+Thread.currentThread().getName());
        Thread.sleep(1200);
      }
    }catch(InterruptedException e){
      System.out.println(e);
    }
  }
}

class Th{
  public static void main(String[] args) {

  new  Thrr(); // for child thread
    //tt.run(); re-running
    Thread t5 = new Thread(new Thrr(),"Thread-1");
    t5.start();
    Thread t2 = new Thread(new Thrr2(),"Thread-2"); //id 16 before 15 Thread name Another Thread (tt) is main to Thread 2 sometimes
    t2.start();
    /*exec order(ideally)
    0.    main called by t1(method class) t5 executed once 1. 2 sagar called (by t1 and t5) started at same time target=  run of Thrr
    2. Thread 1 by t5 target = run of Thrr
    3. Thread 0(Another Thread) by t2 target= run of Thrr2 (constructor called)
    4. Thread 2 by t2 target=  run of Thrr2
    Total 6 Threads including main exec  in any random order
        */



    try{
        System.out.println("(In main) Main thread is "+Thread.currentThread());
        System.out.println("(In main) Main Thread id is " +Thread.currentThread().getId());
      for(int i=0;i<5;i++)
      {

      System.out.println("main thread  " +i+ " -"+Thread.currentThread().getName());
          Thread.sleep(1000);
    }
  }catch(InterruptedException e){
    System.out.println("main caught");

  }
  System.out.println("exit main");
  }
}
