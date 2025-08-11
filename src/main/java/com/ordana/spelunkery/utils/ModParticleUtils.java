package com.ordana.spelunkery.utils;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.util.Mth;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class ModParticleUtils
{
	
	
	public static void spawnSulfuricVentParticles (Level level, BlockPos pos, ParticleOptions particleOptions)
	{
		Vec3 vec = Vec3.atCenterOf(pos);
		
		var x = (vec.x == 0 ? vec.x + 0 : vec.x);
		var y = (vec.y == 0 ? vec.y + 0.5 : vec.y);
		var z = (vec.z == 0 ? vec.z + 0 : vec.z);
		
		for (int j = 0; j < 20; ++j)
		{
			var xSpeed = Mth.nextDouble(level.random, -0.1D, 0.1D);
			var ySpeed = Mth.nextDouble(level.random, 0.8D, 0.9D);
			var zSpeed = Mth.nextDouble(level.random, -0.1D, 0.1D);
			
			level.addAlwaysVisibleParticle(particleOptions, true, x, y, z, xSpeed, ySpeed, zSpeed);
		}
		
	}
	
	
}
