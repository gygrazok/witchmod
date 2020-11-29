package witchmod.effects;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

public class SkullFlaskEffect extends AbstractGameEffect {
	private static TextureAtlas.AtlasRegion img;
	private float sX;
	private float sY;
	private float cX;
	private float cY;
	private float dX;
	private float dY;
	private float yOffset;
	private float bounceHeight;
	private static final float DUR = 0.6f;
	private boolean playedSfx = false;
	private ArrayList<Vector2> previousPos = new ArrayList<>();

	public SkullFlaskEffect(float srcX, float srcY, float destX, float destY) {
		if (img == null) {
			img = ImageMaster.vfxAtlas.findRegion("combat/flask");
		}
		this.sX = srcX;
		this.sY = srcY;
		this.cX = this.sX;
		this.cY = this.sY;
		this.dX = destX;
		this.dY = destY;
		this.rotation = 0.0f;
		this.duration = DUR;
		this.color = new Color(1.0f, 1.0f, 1.0f, 0.0f);
		this.bounceHeight = this.sY > this.dY ? 400.0f * Settings.scale : this.dY - this.sY + 400.0f * Settings.scale;
	}

	@Override
	public void update() {
		if (!this.playedSfx) {
			this.playedSfx = true;
			CardCrawlGame.sound.play("BLOOD_SPLAT",0.3f);
		}
		this.cX = Interpolation.linear.apply(this.dX, this.sX, this.duration / 0.6f);
		this.cY = Interpolation.linear.apply(this.dY, this.sY, this.duration / 0.6f);
		this.previousPos.add(new Vector2(this.cX + MathUtils.random(-30.0f, 30.0f) * Settings.scale, this.cY + this.yOffset + MathUtils.random(-30.0f, 30.0f) * Settings.scale));
		if (this.previousPos.size() > 20) {
			this.previousPos.remove(this.previousPos.get(0));
		}
		this.rotation = this.dX > this.sX ? (this.rotation -= Gdx.graphics.getDeltaTime() * 1000.0f) : (this.rotation += Gdx.graphics.getDeltaTime() * 1000.0f);
		if (this.duration > 0.3f) {
			this.color.a = Interpolation.exp5In.apply(1.0f, 0.0f, (this.duration - 0.3f) / 0.3f) * Settings.scale;
			this.yOffset = Interpolation.circleIn.apply(this.bounceHeight, 0.0f, (this.duration - 0.3f) / 0.3f) * Settings.scale;
		} else {
			this.yOffset = Interpolation.circleOut.apply(0.0f, this.bounceHeight, this.duration / 0.3f) * Settings.scale;
		}
		this.duration -= Gdx.graphics.getDeltaTime();
		if (this.duration < 0.0f) {
			this.isDone = true;
		}
	}

	@Override
	public void render(SpriteBatch sb) {
		sb.setBlendFunction(770, 1);
		sb.setColor(new Color(1.0f, 1.0f, 1.0f, this.color.a / 3.0f));
		for (int i = 5; i < this.previousPos.size(); ++i) {
			sb.draw(ImageMaster.POWER_UP_2, this.previousPos.get((int)i).x - (float)(SkullFlaskEffect.img.packedWidth / 2), this.previousPos.get((int)i).y - (float)(SkullFlaskEffect.img.packedHeight / 2), (float)SkullFlaskEffect.img.packedWidth / 2.0f, (float)SkullFlaskEffect.img.packedHeight / 2.0f, SkullFlaskEffect.img.packedWidth, SkullFlaskEffect.img.packedHeight, this.scale / (40.0f / (float)i), this.scale / (40.0f / (float)i), this.rotation);
		}
		sb.setColor(this.color);
		sb.draw(img, this.cX - (float)(SkullFlaskEffect.img.packedWidth / 2), this.cY - (float)(SkullFlaskEffect.img.packedHeight / 2) + this.yOffset, (float)SkullFlaskEffect.img.packedWidth / 2.0f, (float)SkullFlaskEffect.img.packedHeight / 2.0f, SkullFlaskEffect.img.packedWidth, SkullFlaskEffect.img.packedHeight, this.scale, this.scale, this.rotation);
		sb.draw(img, this.cX - (float)(SkullFlaskEffect.img.packedWidth / 2), this.cY - (float)(SkullFlaskEffect.img.packedHeight / 2) + this.yOffset, (float)SkullFlaskEffect.img.packedWidth / 2.0f, (float)SkullFlaskEffect.img.packedHeight / 2.0f, SkullFlaskEffect.img.packedWidth, SkullFlaskEffect.img.packedHeight, this.scale, this.scale, this.rotation);
		sb.setBlendFunction(770, 771);
	}

	@Override
	public void dispose() {

	}
}

