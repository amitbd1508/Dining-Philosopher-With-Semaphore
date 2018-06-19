/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package philosoperWithSemafore;


/**
 *
 * @author Amit
 */


public class DiningPhilosophers {
  // Number of philosophers
  final static int n = 5;

  final static Philosopher[] philosophers = new Philosopher[n];

  final static Semaphore mutex = new Semaphore(1,1);

  public static void main(String[] args) {
    
    
    philosophers[0] = new Philosopher(0);
    for (int i = 1; i < n; i++) {
      philosophers[i] = new Philosopher(i);
    }
    new Thread(philosophers[0],"Philosoper 0").start();
    new Thread(philosophers[1],"Philosoper 1").start();
    new Thread(philosophers[2],"Philosoper 2").start();
    new Thread(philosophers[3],"Philosoper 3").start();
    new Thread(philosophers[4],"Philosoper 4").start();
    
   
  }

  
}
