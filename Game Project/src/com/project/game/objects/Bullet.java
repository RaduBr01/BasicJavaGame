package com.project.game.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import com.project.game.framework.GameObject;
import com.project.game.framework.ObjectId;

public class Bullet extends GameObject{

	public Bullet(float x, float y, ObjectId id, int velX) {
		super(x, y, id);
		this.velX = velX;
	}



	public void render(Graphics g) {
		g.setColor(Color.red);
		g.fillRect((int)x, (int) y, 5, 5);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int) y, 5, 5);
	}



	public void tick(LinkedList<GameObject> object) {
		x += velX;
	}

	@Override
	protected void getHit() {
		
	}

}
