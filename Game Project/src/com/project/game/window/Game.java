package com.project.game.window;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.util.Random;

import com.project.game.framework.KeyInput;
import com.project.game.framework.ObjectId;
import com.project.game.framework.Texture;
import com.project.game.levels.Levels;


public class Game extends Canvas implements Runnable{
	private static final long serialVersionUID = 1L;

	private boolean running = false;
	private Thread thread;
	public static int WIDTH, HEIGHT;
	
	public synchronized void start() {
		if(running)
			return;
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	public synchronized void stop() {
		if(!running)
			return;
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public Handler handler;
	static Texture tex;
	public static boolean Start = false;
	public Levels level;

	Camera cam;
	Random rand = new Random(); 
	public KeyInput keyInput;

	@Override
	public void run() {
		init();
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amtOfTicks = 60;
		double ns = 1000000000/amtOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		int updates = 0;
		while(running) {
			long now = System.nanoTime();
			delta += (now - lastTime)/ns;
			lastTime = now;
			while(delta >= 1) {
				tick();
				updates++;
				delta--;
			}
			render();
			frames++;
			if(System.currentTimeMillis() - timer > 1000){
				timer += 1000;
				System.out.println("FPS: " + frames + " TICKS: " + updates);
				frames = 0;
				updates = 0;
			}
		}
		stop();
	}
	
	private void init() {
		WIDTH = getWidth();
		HEIGHT = getHeight();
		tex = new Texture();
		handler = new Handler();
		keyInput = new KeyInput(handler);
		level = new Levels(0, this);
		cam = new Camera(0, 0);
		level.loadWorld();
		addKeyListener(keyInput);
	}

	public Handler getHandler() {
		return handler;
	}

	public void tick() {
		handler.tick();
		for(int i = 0; i < handler.object.size(); i++) {
			if(handler.object.get(i).getId() == ObjectId.Player) {
				cam.tick(handler.object.get(i));
			}
		}
	}

	public static Texture  getTex() {
		return tex;
	}

	public Camera getCam() {
		return cam;
	}

	public void setCam(Camera cam) {
		this.cam = cam;
	}

	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		Graphics2D g2d = (Graphics2D) g;
		/////////////////////////////////
		g.setColor(Color.black);
		g.fillRect(0, 0, getWidth(), getHeight());
		g2d.translate(cam.getX(), cam.getY());
		handler.render(g);
		g2d.translate(-cam.getX(), -cam.getY());
		/////////////////////////////////
		g.dispose();
		bs.show();
	}

	public Levels getLevel() {
		return level;
	}
}
