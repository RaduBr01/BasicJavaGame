package com.project.game.objects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import com.project.game.framework.GameObject;
import com.project.game.framework.ObjectId;
import com.project.game.window.Game;
import com.project.game.window.Gameover;
import com.project.game.window.Handler;
import com.project.game.window.Window;

public class Saw extends GameObject{

	private int width = 32, height = 32;
	
	private Handler handler;
	
	
	public Saw(float x, float y, ObjectId id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
	}

	@Override
	public void tick(LinkedList<GameObject> object) {
		Collision(object);
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Game.getTex().saw,(int) x,(int) y, 32, 32, null);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int) (x + width/4), (int) (y + height/4), width/2, height/2);
	}

	private void Collision(LinkedList<GameObject> object) {
		for (int i  = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ObjectId.Block) {
				if(getBounds().intersects(tempObject.getBounds())) {
					y = tempObject.getY() - height;
					velY = 0;
					falling = false;
					jumping = false;
				}
				else {
					falling = true;
				}
					
			}
			if(tempObject.getId() == ObjectId.Player) {
				if(getBounds().intersects(tempObject.getBounds())){
					System.out.println("You Lose");
					handler.clearLevel();
					Window.dispose();
					new Gameover();
				}
					
			}
		}
	}

	@Override
	protected void getHit() {
	}
	
}
