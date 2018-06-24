package witchmod.powers;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import witchmod.WitchMod;

public class DarkProcessionPower extends AbstractWitchPower {
	public static final String POWER_ID = "DarkProcessionPower";
	public static final String NAME = "Dark Procession";
	public static final String[] DESCRIPTIONS = new String[]{ "At the start of your turn add "," copy "," copies "," of the last played card to your hand.", " NL Last played card: " };
	public static final String IMG = "powers/darkprocession.png";
	private AbstractCard card;
	public DarkProcessionPower(int amount) {
		this.name = NAME;
		this.ID = POWER_ID;
		this.owner = AbstractDungeon.player;
		this.amount = amount;
		this.updateDescription();
		this.img = new Texture(WitchMod.getResourcePath(IMG));
		this.type = PowerType.BUFF;
	}

	@Override
	public void updateDescription() {
		String baseDescription;
		if (amount == 1) {
			baseDescription	= DESCRIPTIONS[0]+amount+DESCRIPTIONS[1]+DESCRIPTIONS[3];
		} else {
			baseDescription	= DESCRIPTIONS[0]+amount+DESCRIPTIONS[2]+DESCRIPTIONS[3];
		}

		if (card != null) {
			description = baseDescription+DESCRIPTIONS[4]+card.name;
		} else {
			description = baseDescription;
		}
	}

	@Override
	public void atStartOfTurn() {
		if (!AbstractDungeon.getMonsters().areMonstersBasicallyDead() && card != null) {
			flash();
			AbstractCard toCreate = card.makeStatEquivalentCopy();
			AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(toCreate, amount, false));
			toCreate.triggerWhenDrawn();
		}
	}

	@Override
	public void onAfterCardPlayed(AbstractCard usedCard) {
		card = usedCard;
		updateDescription();
	}
}

