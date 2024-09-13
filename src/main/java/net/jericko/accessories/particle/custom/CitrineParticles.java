package net.jericko.accessories.particle.custom;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class CitrineParticles extends TextureSheetParticle {

    protected CitrineParticles(ClientLevel level, double xCoord, double yCoord, double zCoord, SpriteSet spriteSet, double xD, double yD, double zD) {
        super(level, xCoord, yCoord, zCoord, xD, yD, zD);

        this.friction = 1f;
        this.xd = xd;
        this.yd = yd;
        this.zd = zd;
        this.quadSize *= 20;
        this.lifetime = 200;
        this.setSpriteFromAge(spriteSet);

        this.rCol = 1f;
        this.gCol = 1f;
        this.bCol = 1f;
    }

    @Override
    public void tick() {
        super.tick();
    }

    @Override
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }

    @OnlyIn(Dist.CLIENT)
    public static class Provider implements ParticleProvider<SimpleParticleType>{
        private final SpriteSet sprites;

        public Provider(SpriteSet spriteSet){
            this.sprites = spriteSet;
        }

        public Particle createParticle(SimpleParticleType particleType, ClientLevel level,
                                       double x, double y, double z, double dx, double dy, double dz){
            return new CitrineParticles(level, x, y, z, sprites, dx, dy, dz);
        }
    }

}
