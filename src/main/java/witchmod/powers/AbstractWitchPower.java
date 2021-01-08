package witchmod.powers;

import basemod.ReflectionHacks;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import witchmod.WitchMod;

import java.util.ArrayList;

public abstract class AbstractWitchPower extends AbstractPower {
    public static final Logger logger = LogManager.getLogger(WitchMod.class);
    protected Color renderColor = null;
    protected final String[] DESCRIPTIONS;

    public AbstractWitchPower(String id) {
        this.ID = "WitchMod:" + id;
        PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(ID);
        name = powerStrings.NAME;
        DESCRIPTIONS = powerStrings.DESCRIPTIONS;
        logger.info("Loading power desc for " + ID + ": " + name + " " + DESCRIPTIONS[0]);
    }

    public void onCardDraw(AbstractCard card) {
    }

    public void onBlockBreak() {
    }

    @Override
    public void renderIcons(SpriteBatch sb, float x, float y, Color c) {
        if (renderColor == null) {
            sb.setColor(c);
        } else {
            sb.setColor(renderColor);
        }

        if (img != null) {
            sb.draw(img, x - 12.0f, y - 12.0f, 16.0f, 16.0f, 32.0f, 32.0f, Settings.scale, Settings.scale, 0.0f, 0, 0, 32, 32, false, false);
        } else {
            sb.draw(region48, x - (float) region48.packedWidth / 2.0f, y - (float) region48.packedHeight / 2.0f, (float) region48.packedWidth / 2.0f, (float) region48.packedHeight / 2.0f, region48.packedWidth, region48.packedHeight, Settings.scale, Settings.scale, 0.0f);
        }
        ArrayList<AbstractGameEffect> effectList = ReflectionHacks.getPrivate(this, AbstractPower.class, "effect");
        for (AbstractGameEffect e : effectList) {
            e.render(sb, x, y);
        }
    }
}
