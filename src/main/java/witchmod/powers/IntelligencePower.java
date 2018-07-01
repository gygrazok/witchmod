package witchmod.powers;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import witchmod.WitchMod;
import witchmod.actions.DrawFromDiscardPileAction;

public class IntelligencePower extends AbstractWitchPower {
	public static final String POWER_ID = "Intelligence";
	public static final String NAME = "Intelligence";
	public static final String[] DESCRIPTIONS = new String[]{ "At the start of your turn, draw", "random card from your discard pile.", "random cards from your discard pile." };
	public static final String IMG = "powers/intelligence.png";
	public IntelligencePower(AbstractCreature owner, int amount) {
		this.name = NAME;
		this.ID = POWER_ID;
		this.owner = owner;

		this.img = new Texture(WitchMod.getResourcePath(IMG));
		this.type = PowerType.BUFF;
		this.amount = amount;
		this.updateDescription();

	}

	@Override
	public void updateDescription() {
		if (amount == 1) {
			description = DESCRIPTIONS[0] + " 1 " + DESCRIPTIONS[1];
		} else {
			description = DESCRIPTIONS[0] + " "+amount+" " + DESCRIPTIONS[2];
		}

	}

	@Override
	public void atStartOfTurn() {
		AbstractDungeon.actionManager.addToBottom(new DrawFromDiscardPileAction(amount,true,false));
	}

	
}

