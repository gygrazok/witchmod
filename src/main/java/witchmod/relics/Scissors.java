package witchmod.relics;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.AbstractCard.CardRarity;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.vfx.cardManip.PurgeCardEffect;

public class Scissors extends AbstractWitchRelic {
	public static final String ID = "Scissors";
	private static final RelicTier TIER = RelicTier.SHOP;
	private static final String IMG = "relics/birdcage.png";
	private static final LandingSound SOUND = LandingSound.CLINK;

	public Scissors() {
		super(ID, IMG, TIER, SOUND);
	}

	@Override
	public String getUpdatedDescription() {
		return DESCRIPTIONS[0];
	}

	@Override
	public void onObtainCard(AbstractCard c) {
		if (c.rarity == CardRarity.RARE) {
			AbstractCard toRemove = null;
			for (AbstractCard card : AbstractDungeon.player.masterDeck.group) {
				if (card.rarity == CardRarity.BASIC && card.pool == 0) {
					toRemove = card;
					break;
				}
			}
			if (toRemove != null) {
				flash();
                AbstractDungeon.topLevelEffects.add(new PurgeCardEffect(toRemove, Settings.WIDTH / 2, Settings.HEIGHT / 2));
                AbstractDungeon.player.masterDeck.removeCard(toRemove);
			}
		}
	}


	@Override
	public AbstractRelic makeCopy() {
		return new Scissors();
	}
}

