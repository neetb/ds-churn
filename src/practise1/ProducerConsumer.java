package practise1;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by abhinav on 19/7/15.
 */
public class ProducerConsumer {


    public static void main(String[] args)   {
      ProducerConsumer pc = new ProducerConsumer();
        pc.test();
    }


    private void test() {
        Queue q  = new LinkedList();
        Thread producer = new Thread(new Producer(q));

        Thread consumer = new Thread(new Consumer(q));
        producer.start();
        consumer.start();

        try {
            Thread.currentThread().join();
        }catch(Exception e) {

        }
    }

    static class Producer implements Runnable {

        private Queue queue;

        public Producer(Queue queue)    {
            this.queue = queue;
        }

        public void run()   {
            synchronized (this.queue)   {
              while(true) {
                  while (queue.size() == 2) {
                      try {
                          queue.wait();
                      } catch (InterruptedException e) {
                          e.printStackTrace();
                      }
                  }

                  System.out.println("Producer : Adding element");
                  queue.add(System.currentTimeMillis());
                  queue.notify();
              }
            }
        }

    }


   static  class Consumer implements Runnable {

        private Queue queue;

        public Consumer(Queue queue)    {
            this.queue = queue;
        }

        public void run()   {

            synchronized (this.queue)   {
             while(true) {
                 while (queue.size() == 0) {
                     try {
                         queue.wait();
                     } catch (InterruptedException e) {
                         e.printStackTrace();
                     }
                 }
                 System.out.println("Consumed : " + queue.remove());
                 queue.notify();
             }
            }
        }
    }

}
