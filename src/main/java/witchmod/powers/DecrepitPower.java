package witchmod.powers;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;

import witchmod.WitchMod;

public class DecrepitPower extends AbstractPower {
	public static final String POWER_ID = "Decrepit";
	public static final String NAME = "Decrepit";
	public static final String[] DESCRIPTIONS = new String[]{ "All incoming damage increased by #b"," (calculated after Vulnerability)"};
	public static final String IMG = "powers/decrepit.png";
    private boolean justApplied = false;
	public DecrepitPower(AbstractCreature owner, int amount, boolean justApplied) {
		this.name = NAME;
		this.ID = POWER_ID;
		this.owner = owner;
		this.amount = amount;
		this.updateDescription();
		this.img = new Texture(WitchMod.getResourcePath(IMG));
		this.isTurnBased = true;
		this.type = PowerType.DEBUFF;
		this.justApplied = true;
	}

	@Override
	public void updateDescription() {
		description = DESCRIPTIONS[0]+amount+DESCRIPTIONS[1];
	}
	
    @Override
    public void atEndOfRound() {
        if (this.justApplied) {
            this.justApplied = false;
            return;
        }
        if (this.amount == 0) {
            AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(owner, owner, DecrepitPower.POWER_ID));
        } else {
            AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(owner, owner, DecrepitPower.POWER_ID, 1));
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


