package com.project.game.objects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;
import com.project.game.objects.Player;

import com.project.game.framework.GameObject;
import com.project.game.framework.ObjectId;
import com.project.game.window.Animation;
import com.project.game.window.Game;
import com.project.game.window.Handler;

public class Enemy extends GameObject{
	private int width = 32, height = 32;

	private Handler handler;
    private int health;
	private Game game;
	
	private static Animation enemyIdleR, enemyIdle2R, enemyIdle3R;
	

	public Enemy(float x, float y, ObjectId id, Handler handler, Game game) {
		super(x, y, id);
		this.game = game;
		this.handler = handler;
		health = 10;
		enemyIdleR = new Animation(10, Game.getTex().enemy_left);
		enemyIdle2R = new Animation(10, Game.getTex().enemy_left2);
		enemyIdle3R = new Animation(10, Game.getTex().enemy_left3);

	}

	@Override
	public void tick(LinkedList<GameObject> object) {
		
		Collision(object);
		if(this.game.level.level == 0){
			enemyIdleR.runAnimation();
		}else if(this.game.level.level == 1){
			enemyIdle2R.runAnimation();
		}else if(this.game.level.level == 2){
			enemyIdle3R.runAnimation();
		}
	}

	@Override
	public void render(Graphics g) {
		if(this.game.level.level == 0){
			enemyIdleR.drawAnimation(g,(int) x,(int) y);
		}
		if(this.game.level.level == 1){
			enemyIdle2R.drawAnimation(g,(int) x,(int) y);
		}
		if(this.game.level.level == 2){
			enemyIdle3R.drawAnimation(g,(int) x,(int) y);
		}
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

			if(tempObject.getId() == ObjectId.Bullet) {
				if(getBounds().intersects(tempObject.getBounds())){
					getHit();
					handler.object.remove(tempObject);
				}
			}
			
		}
	}
	
	@Override
	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, width, height);
	}

	@Override
	protected void getHit() {
		this.health--;
		if(this.health < 0) {
			handler.object.remove(this);
			Player.killCount++;

		}
	}
	
}
