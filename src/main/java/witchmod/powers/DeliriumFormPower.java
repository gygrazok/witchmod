package witchmod.powers;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import witchmod.WitchMod;

public class DeliriumFormPower extends AbstractWitchPower {
	private static final String POWER_ID = "DeliriumFormPower";
	//public static final String NAME = "Delirium Form";
	//public static final String[] DESCRIPTIONS = new String[]{ "Whenever you play a non-Cost 0 card, draw "," card."," cards."};
	public static final String IMG = "powers/deliriumform.png";
	public DeliriumFormPower(int amount) {
		super(POWER_ID);
		this.owner = AbstractDungeon.player;
		this.amount = amount;
		this.updateDescription();
		this.img = new Texture(WitchMod.getResourcePath(IMG));
		this.type = PowerType.BUFF;
	}

	@Override
	public void updateDescription() {
		if (amount == 1) {
			description	= DESCRIPTIONS[0]+amount+DESCRIPTIONS[1];
		} else {
			description	= DESCRIPTIONS[0]+amount+DESCRIPTIONS[2];
		}
	}


	@Override
	public void onAfterCardPlayed(AbstractCard usedCard) {
		if (usedCard.costForTurn != 0) {
			AbstractDungeon.actionManager.addToBottom(new DrawCardAction(AbstractDungeon.player, amount));
		}
	}
}

