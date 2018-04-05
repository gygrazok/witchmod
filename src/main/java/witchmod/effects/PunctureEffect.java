package witchmod.effects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.AdditiveSlashImpactEffect;

public class PunctureEffect
extends AbstractGameEffect {
    private static TextureAtlas.AtlasRegion img;
    private float x;
    private float y;
    private float sX;
    private float sY;
    private float tX;
    private float tY;
    private float targetAngle;
    private float startingAngle;
    private float targetScale;
    private boolean shownSlash = false;

    public PunctureEffect(float x, float y) {
        if (img == null) {
            img = ImageMaster.vfxAtlas.findRegion("combat/stake");
        }
        float randomAngle = 0.017453292f * MathUtils.random(-50.0f, 230.0f);
        this.x = MathUtils.cos(randomAngle) * MathUtils.random(200.0f, 600.0f) * Settings.scale + x;
        this.y = MathUtils.sin(randomAngle) * MathUtils.random(200.0f, 500.0f) * Settings.scale + y;
        this.duration = 0.5f;
        this.scale = 0.01f;
        this.targetScale = MathUtils.random(0.1f, 0.5f);
        this.targetAngle = MathUtils.atan2(y - this.y, x - this.x) * 57.295776f + 90.0f;
        this.rotation = this.startingAngle = MathUtils.random(0.0f, 360.0f);
        this.x -= (float)(PunctureEffect.img.packedWidth / 2);
        this.y -= (float)(PunctureEffect.img.packedHeight / 2);
        this.sX = this.x;
        this.sY = this.y;
        this.tX = x - (float)(PunctureEffect.img.packedWidth / 2);
        this.tY = y - (float)(PunctureEffect.img.packedHeight / 2);
        this.color = new Color(0.2f,0.2f,0.2f,0.0f);
    }

    @Override
    public void update() {
        this.rotation = Interpolation.elasticIn.apply(this.targetAngle, this.startingAngle, this.duration);
        if (this.duration > 0.5f) {
            this.scale = Interpolation.elasticIn.apply(this.targetScale, this.targetScale * 10.0f, (this.duration - 0.5f) * 2.0f) * Settings.scale;
            this.color.a = Interpolation.fade.apply(1.0f, 0.35f, (this.duration - 0.5f) * 2.0f);
        } else {
            this.x = Interpolation.exp10Out.apply(this.tX, this.sX, this.duration * 2.0f);
            this.y = Interpolation.exp10Out.apply(this.tY, this.sY, this.duration * 2.0f);
        }
        if (this.duration < 0.05f && !this.shownSlash) {
            AbstractDungeon.effectsQueue.add(new AdditiveSlashImpactEffect(this.tX + (float)PunctureEffect.img.packedWidth / 2.0f, this.tY + (float)PunctureEffect.img.packedHeight / 2.0f, this.color.cpy()));
            this.shownSlash = true;
        }
        this.duration -= Gdx.graphics.getDeltaTime();
        if (this.duration < 0.0f) {
            this.isDone = true;
            CardCrawlGame.sound.play("ATTACK_FAST", 0.2f);
        }
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setBlendFunction(770, 1);
        sb.setColor(this.color);
        sb.draw(img, this.x, this.y, (float)PunctureEffect.img.packedWidth / 2.0f, (float)PunctureEffect.img.packedHeight / 2.0f, PunctureEffect.img.packedWidth, PunctureEffect.img.packedHeight, this.scale * MathUtils.random(1.0f, 1.2f), this.scale * MathUtils.random(1.0f, 1.2f), this.rotation);
        sb.draw(img, this.x, this.y, (float)PunctureEffect.img.packedWidth / 2.0f, (float)PunctureEffect.img.packedHeight / 2.0f, PunctureEffect.img.packedWidth, PunctureEffect.img.packedHeight, this.scale * MathUtils.random(0.9f, 1.1f), this.scale * MathUtils.random(0.9f, 1.1f), this.rotation);
        sb.setBlendFunction(770, 771);
    }
}

