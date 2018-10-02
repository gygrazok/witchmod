package witchmod.relics;

import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.AbstractCard.CardType;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import witchmod.actions.BlackCatAction;

public class BlackCat extends AbstractWitchRelic {
	public static final String ID = "BlackCat";
	private static final RelicTier TIER = RelicTier.STARTER;
	private static final String IMG = "relics/blackcat.png";
	private static final LandingSound SOUND = LandingSound.FLAT;

	public BlackCat() {
		super(ID, IMG, TIER, SOUND);
	}

	@Override
	public String getUpdatedDescription() {
		return DESCRIPTIONS[0];
	}

	@Override
	public void atBattleStartPreDraw() {
		AbstractDungeon.actionManager.addToBottom(new BlackCatAction());
		AbstractDungeon.actionManager.addToBottom(new RelicAboveCreatureAction(AbstractDungeon.player, this));
		flash();
	}

	@Override
	public void onCardDraw(AbstractCard drawnCard) {
		if (drawnCard.type == CardType.CURSE){
			AbstractDungeon.actionManager.addToBottom(new GainEnergyAction(1));
			flash();
		}

	}


	@Override
	public AbstractRelic makeCopy() {
		return new BlackCat();
	}
}

