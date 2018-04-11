package witchmod.powers;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import witchmod.WitchMod;

public class GrimVengeancePower extends AbstractWitchPower {
	public static final String POWER_ID = "GrimVengeancePower";
	public static final String NAME = "Grim Vengeance";
	public static final String[] DESCRIPTIONS = new String[]{ "When you are attacked apply #b"," Decrepit to the attacker."};
	public static final String IMG = "powers/grimvengeance.png";
	public GrimVengeancePower(AbstractCreature owner, int amount) {
		this.name = NAME;
		this.ID = POWER_ID;
		this.owner = owner;
		this.amount = amount;
		this.updateDescription();
		this.img = new Texture(WitchMod.getResourcePath(IMG));
		this.type = PowerType.BUFF;
	}

	@Override
	public int onAttacked(DamageInfo info, int damageAmount) {
		if (info.owner != null && info.type != DamageInfo.DamageType.THORNS && info.type != DamageInfo.DamageType.HP_LOSS && info.owner != this.owner) {
			flash();
			AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(info.owner, owner, new DecrepitPower(info.owner, amount, true), amount, true));
		}
		return damageAmount;
	}

	@Override
	public void updateDescription() {
		description = DESCRIPTIONS[0]+amount+DESCRIPTIONS[1];
	}
}