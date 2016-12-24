package GameLogic;

import com.jon.Entities.Bonus;
import com.jon.Entities.BossCercei;
import com.jon.Entities.EntityA;
import com.jon.Entities.EntityB;

public class Physics {
	
	public static boolean Collision(EntityA entityA, EntityB entityB){	
		
		if(entityA.getBounds().intersects(entityB.getBounds())){
				return true;
		}
		
		return false;
		}
	
	public static boolean Collision(EntityA entityA, BossCercei bossCercei){	
			
			if(entityA.getBounds().intersects(bossCercei.getBounds())){
					return true;
			}
			
		return false;
		}
	
	public static boolean Collision(EntityA entityA, Bonus bonus){	
		
		if(entityA.getBounds().intersects(bonus.getBounds())){
				return true;
		}
		
	return false;
	}
	
}
