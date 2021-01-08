package witchmod.powers;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import witchmod.WitchMod;
import witchmod.relics.WalkingCane;

public class DecrepitPower extends AbstractWitchPower {
	private static final String POWER_ID = "Decrepit";
	//public static final String NAME = "Decrepit";
	//public static final String[] DESCRIPTIONS = new String[]{ "All damage from attacks is increased by #b"};
	public static final String IMG = "powers/decrepit.png";
	private boolean justApplied = false;
	public DecrepitPower(AbstractCreature owner, int amount, boolean justApplied) {
		super(POWER_ID);
		this.owner = owner;
		this.amount = amount;
		this.updateDescription();
		this.img = new Texture(WitchMod.getResourcePath(IMG));
		this.isTurnBased = true;
		this.type = PowerType.DEBUFF;
		this.justApplied = justApplied;
	}

	@Override
	public void updateDescription() {
		description = DESCRIPTIONS[0]+amount+".";
	}

	@Override
	public void atEndOfRound() {
		if (justApplied) {
			justApplied = false;
			return;
		}
		if (AbstractDungeon.player.hasRelic(WalkingCane.ID)) {
			AbstractDungeon.player.getRelic(WalkingCane.ID).flash();
			return;
		}
		if (amount <= 1) {
			AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(owner, owner, ID));
		} else {
			AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(owner, owner, ID, 1));
		}
	}

	@Override
	public float atDamageReceive(float damage, DamageType damageType) {
		if (damageType == DamageType.NORMAL) {
			return damage + amount;
		} else {
			return damage;
		}
	}


}


