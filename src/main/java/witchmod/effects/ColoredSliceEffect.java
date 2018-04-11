package witchmod.effects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

public class ColoredSliceEffect extends AbstractGameEffect {
	private float x;
	private float startX;
	private float y;
	private float startY;
	private float targetX;
	private float targetY;
	private static final float DUR = 0.4f;
	private TextureAtlas.AtlasRegion img = ImageMaster.DAGGER_STREAK;
	private boolean playedSound = false;

	public ColoredSliceEffect(float x, float y, float targetX, float targetY, Color color) {    	
		this.x = this.startX = x;
		this.y = this.startY = y;
		this.targetX = targetX;
		this.targetY = targetY;
		this.startingDuration = DUR;
		this.duration = DUR;
		this.scale = Settings.scale;
		this.rotation = MathUtils.random(-3.0f, 3.0f);
		this.color = color;
	}


	private void playRandomSfX() {
		int roll = MathUtils.random(5);
		switch (roll) {
		case 0: {
			CardCrawlGame.sound.play("ATTACK_DAGGER_1");
			break;
		}
		case 1: {
			CardCrawlGame.sound.play("ATTACK_DAGGER_2");
			break;
		}
		case 2: {
			CardCrawlGame.sound.play("ATTACK_DAGGER_3");
			break;
		}
		case 3: {
			CardCrawlGame.sound.play("ATTACK_DAGGER_4");
			break;
		}
		case 4: {
			CardCrawlGame.sound.play("ATTACK_DAGGER_5");
			break;
		}
		default: {
			CardCrawlGame.sound.play("ATTACK_DAGGER_6");
		}
		}
	}

	@Override
	public void update() {
		this.x = Interpolation.fade.apply(this.targetX, this.startX, this.duration / this.startingDuration);
		this.y = Interpolation.fade.apply(this.targetY, this.startY, this.duration / this.startingDuration);
		if (!this.playedSound) {
			this.playRandomSfX();
			this.playedSound = true;
		}
		this.duration -= Gdx.graphics.getDeltaTime();
		if (this.duration < 0.0f) {
			this.isDone = true;
		}
		this.color.a = this.duration > 0.2f ? Interpolation.fade.apply(1.0f, 0.0f, (this.duration - 0.2f) * 5.0f) : Interpolation.fade.apply(0.0f, 1.0f, this.duration * 5.0f);
	}

	@Override
	public void render(SpriteBatch sb) {
		sb.setColor(this.color);
		sb.draw(this.img, this.x, this.y, (float)this.img.packedWidth * 0.85f, (float)this.img.packedHeight / 2.0f, this.img.packedWidth, this.img.packedHeight, this.scale, this.scale * 1.5f, this.rotation);
		sb.setBlendFunction(770, 1);
		sb.draw(this.img, this.x, this.y, (float)this.img.packedWidth * 0.85f, (float)this.img.packedHeight / 2.0f, this.img.packedWidth, this.img.packedHeight, this.scale * 0.75f, this.scale * 0.75f, this.rotation);
		sb.setBlendFunction(770, 771);

	}
}

