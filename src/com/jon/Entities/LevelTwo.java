package com.jon.Entities;

import java.awt.Graphics;
import java.util.LinkedList;
import java.util.Random;

import GameLogic.GameEngine;
import GameLogic.Physics;

public class LevelTwo implements Level {

private GameEngine gameEngine;
	
	private LinkedList<Sword> swords;
	private LinkedList<Soldier> enemies;
	
	private BossCercei cercei;
	
	private Sword tempSword;
	private Soldier tempEnemy;
	
	private boolean isBossReleased = false;
	private boolean isBossDead = false;
	
	private Random random = new Random();

	private int numEnemyKilled;
	int temp1;
	
	public LevelTwo()
	{
		gameEngine = GameEngine.getInstance();
		enemies = new LinkedList<Soldier>();
		cercei = new BossCercei(420,50);
		swords = new LinkedList<Sword>();
		
		temp1 = 240;
		numEnemyKilled = 0;
		
			for(int y = 0; y < 50; y++)
			{
				addEnemyEntity(new Soldier(random.nextInt(gameEngine.getFrame().getWidth() - 20), 0));
				temp1 = temp1 + 50;
			}
	}
	
	public void move()
	{		
		for(int i = 0; i < enemies.size(); i++)
		{		
			enemies.get(i).move();
			
			if(Physics.Collision(gameEngine.getPlayer(), enemies.get(i))){
				removeEnemyEntity(enemies.get(i));
				numEnemyKilled++;
				gameEngine.getPlayer().decrementLife(1); 
			}
		}
		
		for(int i = 0; i < swords.size(); i++)
		{
			tempSword = swords.get(i);
			tempSword.move();
			for(int j = 0; j < enemies.size(); j++){
				if(Physics.Collision(tempSword, enemies.get(j))){
					removeSwordEntity(swords.get(i));
					removeEnemyEntity(enemies.get(j));
					numEnemyKilled++;
				}
			}
		}
		
		if(isBossReleased && !(isBossDead)){
			cercei.move();
					
			for(int i = 0; (i < swords.size()) && !(isBossDead); i++)
			{
				tempSword = swords.get(i);
				tempSword.move();
				
				if(Physics.Collision(tempSword, cercei)){
					removeSwordEntity(swords.get(i));
					cercei.setHealth(cercei.getHealth() - 1);
					
					if(cercei.getHealth() == 0){
						isBossDead = true;
						cercei = null;
					}
				}
			}
			
		}
	}
	public void render(Graphics g)
	{
		for(int i = 0; i < enemies.size(); i++)
		{
			tempEnemy = enemies.get(i);
			tempEnemy.render(g);
		}
		
		for(int i = 0; i < swords.size(); i++)
		{
			tempSword = swords.get(i);
			tempSword.render(g);
		}
		
		if(isBossReleased && !(isBossDead)){
			cercei.render(g);
		}
	}
	
	public void releaseTheBoss(){
		isBossReleased = true;
	}
	
	public void addEnemyEntity(Soldier soldier)
	{
		enemies.add(soldier);
	}
	
	public void removeEnemyEntity(Soldier soldier)
	{
		enemies.remove(soldier);
	}
	
	public void addSwordEntity(Sword sword)
	{
		swords.add(sword);
	}
	
	public void removeSwordEntity(Sword sword)
	{
		swords.remove(sword);
	}
	
	public LinkedList<Sword> getSwords(){
		return swords;
	}
	
	public LinkedList<Soldier> getEnemies(){
		return enemies;
	}
	
	public BossCercei getBoss(){
		return cercei;
	}
	
	public boolean isBossDead(){
		return isBossDead;
	}

	@Override
	public int getNumEnemyKilled() {
		return numEnemyKilled;
		
	}

	@Override
	public void setNumEnemyKilled(int killed) {
		numEnemyKilled = killed;
	}
}
