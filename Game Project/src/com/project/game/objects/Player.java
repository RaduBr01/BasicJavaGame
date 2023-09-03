package com.project.game.objects;

import java.awt.Color;
import java.awt.Graphics;

import java.awt.Rectangle;
import java.util.LinkedList;

import com.project.game.framework.GameObject;
import com.project.game.framework.ObjectId;
import com.project.game.levels.Levels;
import com.project.game.window.Animation;
import com.project.game.window.*;


public class Player extends GameObject{


	public static int killCount=0;
	private final float MAX_SPEED = 10;
	private float width = 32, height = 32;
	private float gravity = 0.5f;
	private Handler handler;

	public static int health;
	public Levels level;
	private Game game;
	private static Animation playerWalk_f, playerIdle_f, playerWalk_r, playerIdle_r;
	
	public static Animation getPlayerIdle_f() {
		return playerIdle_f;
	}


	public Player(float x, float y,Handler handler, ObjectId id, Game game) {
		super(x, y, id);
		this.handler = handler;
		Player.health = 100;
		this.game = game;
		level = this.game.getLevel();
		playerIdle_f = new Animation(10, Game.getTex().player_idle_f);
		playerWalk_f = new Animation(10, Game.getTex().player_run_f);
		playerIdle_r = new Animation(10, Game.getTex().player_idle_r);
		playerWalk_r = new Animation(10, Game.getTex().player_run_r);
	}

	public int getKillCount() {
		return killCount;
	}


	@Override
	public void tick(LinkedList<GameObject> object) {
		x += velX;
		y += velY;
		
		if(velX < 0) {
			facing = -1;
		}else if(velX > 0) {
			facing = 1;
		}
		
		if(falling || jumping) {
			velY += gravity;
			if(velY > MAX_SPEED)
				velY = MAX_SPEED;
		}
		
		Collision(object);
		playerIdle_f.runAnimation();
		playerWalk_f.runAnimation();
		playerIdle_r.runAnimation();
		playerWalk_r.runAnimation();
		
		if(Player.health <= 0) {
			handler.clearLevel();
			Window.dispose();
			new Gameover();
			Player.health = 100;
		
		}
		
		if(y > Game.HEIGHT) {
			getHit();
		}
	}
	
	private void Collision(LinkedList<GameObject> object) {
		for (int i  = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ObjectId.Block) {
				if(getBoundsTop().intersects(tempObject.getBounds())) {
					y = tempObject.getY() + height/2;
					velY = 0;
				}
				if(getBounds().intersects(tempObject.getBounds())) {
					y = tempObject.getY() - height;
					velY = 0;
					falling = false;
					jumping = false;
				}
				else {
					falling = true;
				}
				
				//Right
				if(getBoundsRight().intersects(tempObject.getBounds())) {
					x = tempObject.getX() - width;
				}
				
				//Left
				if(getBoundsLeft().intersects(tempObject.getBounds())) {
					x = tempObject.getX() + width;
				}
					
			}
			if(tempObject.getId() == ObjectId.Win_Block) {
				if(getBounds().intersects(tempObject.getBounds()) ||
				   getBoundsLeft().intersects(tempObject.getBounds())) {
					level.changeLevel();
					Menu.databaseManager.insertData(killCount,(int)x,(int)y);
					Menu.databaseManager.printData();

				}
					
			}
			if(tempObject.getId() == ObjectId.Enemy) {
				if(getBounds().intersects(tempObject.getBounds()) ||
				   getBoundsLeft().intersects(tempObject.getBounds())) {
					getHit();
				}
					
			}
			
		}
	}


	
	@Override
	public void render(Graphics g) {	
		if(velX > 0)
			playerWalk_f.drawAnimation(g, (int)x, (int)y);
		else if(velX < 0)
			playerWalk_r.drawAnimation(g, (int)x, (int)y);
		else {
			if(facing == -1)
				playerIdle_r.drawAnimation(g, (int)x, (int)y);
			else if(facing == 1)
				playerIdle_f.drawAnimation(g, (int)x, (int)y);

		}
		
		//HEALTH BAR
		g.setColor(Color.white);
		g.fillRect((int)-game.getCam().getX() + 5, (int)-game.getCam().getY() + 5, 100, 20);

		g.setColor(Color.green);
		g.fillRect((int)-game.getCam().getX() + 5, (int)-game.getCam().getY() + 5, health, 20);



	}


	@Override
	public Rectangle getBounds() {
		return new Rectangle((int)((int)x + width/2 - width/4), (int)((int)y + height/2), (int)width/2, (int)height/2);
	}
	public Rectangle getBoundsTop() {
		return new Rectangle(((int)(((int)x) + width/2 - width/4)), (int)y, (int)width/2, (int)height/2);
	}
	public Rectangle getBoundsRight() {
		return new Rectangle((int) ((int)x + width - 6.5f), (int)y + 5, 6, (int)height -10);
	}
	public Rectangle getBoundsLeft() {
		return new Rectangle((int)(x + 1), (int)y + 5, 6, (int)height - 10);
	}

	@Override
	protected void getHit() {
		health--;
		if(health < 0) {
			handler.clearLevel();
		}
	}
	
	
}
