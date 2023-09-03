package com.project.game.window;

import com.project.game.framework.GameObject;
//functie pentru camera, adica camera sa urmareasca personajul in timpul nivelului
public class Camera {
	private float x, y;

	public Camera(float x, float y) {
		this.x = x;
		this.y = y;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public void tick(GameObject player) {
		if(player.getX() < Game.WIDTH/2)
			x = 0;
		else if(player.getX() > (2 * Game.WIDTH )+ Game.WIDTH/16)
			return;
		else
			x = -(player.getX() - Game.WIDTH/2);
	}
}
