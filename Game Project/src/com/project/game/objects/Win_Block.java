package com.project.game.objects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import com.project.game.framework.GameObject;
import com.project.game.framework.ObjectId;
import com.project.game.window.Animation;
import com.project.game.window.Game;

public class Win_Block extends GameObject{

	private int width = 32, height = 64;
	private Animation flag;

	public Win_Block(float x, float y, ObjectId id) {
		super(x, y, id);
		flag = new Animation(8, Game.getTex().flag);
	}


	
	@Override
	public void tick(LinkedList<GameObject> object) {
		flag.runAnimation();
	}

	@Override
	public void render(Graphics g) {

		flag.drawAnimation(g,(int) x, (int)y, 32, 32);
		
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, width, height);
	}



	@Override
	protected void getHit() {
		
	}
}
