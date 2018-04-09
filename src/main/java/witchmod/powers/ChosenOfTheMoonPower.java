package witchmod.powers;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.ArtifactPower;

import witchmod.WitchMod;

public class ChosenOfTheMoonPower extends AbstractWitchPower {
	public static final String POWER_ID = "ChosenOfTheMoonPower";
	public static final String NAME = "Chosen of the Moon";
	public static final String[] DESCRIPTIONS = new String[]{ "Gain #b"," Artifact when your turn begins if you have none."};
	public static final String IMG = "powers/chosenofthemoon.png";
	public ChosenOfTheMoonPower(AbstractCreature owner, int amount) {
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
		description = DESCRIPTIONS[0]+amount+DESCRIPTIONS[1];
	}

	@Override
	public void atStartOfTurn(){
		if (!owner.hasPower(ArtifactPower.POWER_ID)) {
			AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(owner, owner, new ArtifactPower(owner, amount),amount));
		}
	}

}

