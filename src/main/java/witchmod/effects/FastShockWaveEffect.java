/*
 * Decompiled with CFR.
 */
package witchmod.effects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ScreenShake;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.BlurWaveAdditiveEffect;
import com.megacrit.cardcrawl.vfx.combat.BlurWaveChaoticEffect;
import com.megacrit.cardcrawl.vfx.combat.BlurWaveNormalEffect;

public class FastShockWaveEffect extends AbstractGameEffect {
    private float x;
    private float y;
    private ShockWaveType type;
    private Color color;

    public FastShockWaveEffect(float x, float y, Color color, ShockWaveType type) {
        this.x = x;
        this.y = y;
        this.type = type;
        this.color = color;
    }

	@Override
    public void update() {
        float speed = MathUtils.random(6000.0f, 8000.0f) * Settings.scale;
        switch (this.type) {
            case ADDITIVE: {
                for (int i = 0; i < 40; ++i) {
                    AbstractDungeon.effectsQueue.add(new BlurWaveAdditiveEffect(this.x, this.y, this.color.cpy(), speed));
                }
                break;
            }
            case NORMAL: {
                for (int i = 0; i < 40; ++i) {
                    AbstractDungeon.effectsQueue.add(new BlurWaveNormalEffect(this.x, this.y, this.color.cpy(), speed));
                }
                break;
            }
            case CHAOTIC: {
                CardCrawlGame.screenShake.shake(ScreenShake.ShakeIntensity.HIGH, ScreenShake.ShakeDur.SHORT, false);
                for (int i = 0; i < 40; ++i) {
                    AbstractDungeon.effectsQueue.add(new BlurWaveChaoticEffect(this.x, this.y, this.color.cpy(), speed));
                }
                break;
            }
        }
        this.isDone = true;
    }

    @Override
    public void render(SpriteBatch sb) {
    }

    public static enum ShockWaveType {
        ADDITIVE,
        NORMAL,
        CHAOTIC;
        

        private ShockWaveType() {
        }
    }

}

