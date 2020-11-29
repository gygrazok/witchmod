
package witchmod.effects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.FireBurstParticleEffect;
import com.megacrit.cardcrawl.vfx.combat.LightFlareParticleEffect;

public class IgniteEffect extends AbstractGameEffect {
    private int count = 25;
    private float x;
    private float y;

    public IgniteEffect(float x, float y, Color color, int count) {
        this.x = x;
        this.y = y;
        this.color = color;
        this.count = count;
    }

    @Override
    public void update() {
        for (int i = 0; i < count; ++i) {
            AbstractDungeon.effectsQueue.add(new FireBurstParticleEffect(x, y));
            AbstractDungeon.effectsQueue.add(new LightFlareParticleEffect(x, y, color));
        }
        this.isDone = true;
    }

    @Override
    public void render(SpriteBatch sb) {
    }

    @Override
    public void dispose() {

    }
}

