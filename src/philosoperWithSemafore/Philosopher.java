/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package philosoperWithSemafore;


import java.util.logging.Level;
import java.util.logging.Logger;
import static philosoperWithSemafore.DiningPhilosophers.mutex;
import static philosoperWithSemafore.DiningPhilosophers.n;
import static philosoperWithSemafore.DiningPhilosophers.philosophers;
import java.util.concurrent.Semaphore;

/**
 *
 * @author Amit
 */
public  class Philosopher extends Thread {

     public  final static int THINKING=0;
    public final static int HUNGRY=1;
    public final static int EATING=2;

    private final int i;
    private int state;
    private final Semaphore s;

    Philosopher(int id) {
      this.i = id;
      s = new Semaphore(1);
      state = THINKING;
    }
    
    private Philosopher left() {
      return philosophers[(i+n-1)%n];
    }

    private Philosopher right() {
      return philosophers[(i + 1) % n];
    }
    
    public void run() {
      try {
        while (true) {
          statusShow();
          
          if(state==THINKING)
          {
            thinking();
            mutex.down();
            state = HUNGRY; 
            
          }
          
          else if(state==HUNGRY)
          {
            test(this);
            mutex.up();
            s.acquire();
            state = EATING;
            
          }
          else 
          {
            eating();
            mutex.down();
            state = THINKING;
            
            test(left());  
            test(right());
            mutex.up();
             
          }
          
        }
      } catch(InterruptedException e) {}
    }

    void test(Philosopher p) {
      if (p.left().state != EATING && p.state == HUNGRY &&
          p.right().state != EATING) {
          try {
              p.state = EATING;
              
              p.s.release();
          } catch (Exception ex) {
              Logger.getLogger(Philosopher.class.getName()).log(Level.SEVERE, null, ex);
          }
      }
    }

    void eating() {
      try {
        
        Thread.sleep((long) Math.round(Math.random() * 1000));
        
      } catch (InterruptedException e) {}
    }
    void thinking() {
      try {
         
        Thread.sleep((long) Math.round(Math.random() * 1000));
       
      } catch (InterruptedException e) {}
    }

    void statusShow() {
      String status;
      if(state==THINKING)
      {
          status="Thinking";
      }
      else if(state==EATING)
      {
          status="Eating";
      }
      else 
      {
          status="Hungry";
      }
        
      System.out.println("Philosopher " + i + " is " + status);
    }
  }