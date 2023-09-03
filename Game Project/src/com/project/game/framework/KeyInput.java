package com.project.game.framework;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import com.project.game.objects.Bullet;
import com.project.game.window.Handler;
// cu listener si adapter butoanele
public class KeyInput extends KeyAdapter{

	Handler handler;
	
	public KeyInput(Handler handler) {
		this.handler = handler;
	}
	
	public int keyPress(KeyEvent e) {
		int key = e.getKeyCode();
		return key;
	}
	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ObjectId.Player) {
				if(key == KeyEvent.VK_D) tempObject.setVelX(5);
				if(key == KeyEvent.VK_RIGHT) tempObject.setVelX(5);
				if(key == KeyEvent.VK_LEFT) tempObject.setVelX(-5);
				if(key == KeyEvent.VK_A) tempObject.setVelX(-5);
				
				if(key == KeyEvent.VK_UP && !tempObject.isJumping()) { 
					tempObject.setJumping(true);
					tempObject.setVelY(-10);
				}
				if(key == KeyEvent.VK_W && !tempObject.isJumping()) { 
					tempObject.setJumping(true);
					tempObject.setVelY(-10);
				}
				if(key == KeyEvent.VK_SPACE) { 
					handler.addObject(new Bullet(tempObject.getX(), tempObject.getY() + 25, ObjectId.Bullet, (int)tempObject.getFacing()*10));
				}
			}
		}
		
		
		if(key == KeyEvent.VK_ESCAPE)
			System.exit(0);
	}
	
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ObjectId.Player) {
				if(key == KeyEvent.VK_D) tempObject.setVelX(0);
				if(key == KeyEvent.VK_RIGHT) tempObject.setVelX(0);
				if(key == KeyEvent.VK_LEFT) tempObject.setVelX(0);
				if(key == KeyEvent.VK_A) tempObject.setVelX(0);
				
				//if(key == KeyEvent.VK_SPACE) tempObject.setVelY(0);
			}
		}
	}
}
