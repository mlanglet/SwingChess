package chess.components;

import javax.swing.JTextField;

public class GameTimer extends Thread {
    protected boolean paused = true;
    
    private JTextField tf;
    
    private double timeLeft = 60;
    
    private Long prev = 0L;

    public GameTimer(JTextField tf){
    	this.tf = tf;
    }
    
    
    public void run() {
        while (true) {
            synchronized (this) {
                while (paused) {
                    try {
                        wait();
                    } catch (Exception e) {
                    }
                }
            }
            
            checkTime();
            
            tf.setText(Double.toString(timeLeft));
            
            Thread.yield();
        }
    }
    
    private void checkTime(){
    	Long current = System.currentTimeMillis();
    	
    	if(current > (prev + 1000)){
    		timeLeft--;
    		System.out.println("Tick!");
    	}
    	
    	prev = System.currentTimeMillis();
    }
}
