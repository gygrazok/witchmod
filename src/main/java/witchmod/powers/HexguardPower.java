package witchmod.powers;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.ArtifactPower;

import witchmod.WitchMod;

public class HexguardPower extends AbstractWitchPower {
	public static final String POWER_ID = "HexguardPower";
	public static final String NAME = "Hexguard";
	public static final String[] DESCRIPTIONS = new String[]{ "Temporarily gained #b"," Artifact."};
	public static final String IMG = "powers/hexguard.png";

	public HexguardPower(AbstractCreature owner, int amount) {
		this.name = NAME;
		this.ID = POWER_ID;
		this.owner = owner;
		this.amount = amount;
		this.updateDescription();
		this.img = new Texture(WitchMod.getResourcePath(IMG));
		this.type = PowerType.BUFF;
	}

	@Override
	public void updateDescription() {
		description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
	}

	@Override
	public void onInitialApplication() {
		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(owner, owner, new ArtifactPower(owner, amount), amount));
	}

	@Override
	public void atStartOfTurn() {
		flash();
		AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(owner, owner, ArtifactPower.POWER_ID, amount));
		AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(owner, owner, POWER_ID));
	}    
}

