package witchmod.powers;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.StrengthPower;

import witchmod.WitchMod;

public class SkullFlaskPower extends AbstractWitchPower {
    private static final String POWER_ID = "SkullFlaskPower";
    //public static final String NAME = "Skull Flask";
    //public static final String[] DESCRIPTIONS = new String[]{ "Temporarily gained #b"," Strength."};
    public static final String IMG = "powers/skullflask.png";

    public SkullFlaskPower(AbstractCreature owner, int amount) {
        super(POWER_ID);
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
    public void atEndOfTurn(boolean isPlayer) {
    	flash();
		AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(owner, owner, StrengthPower.POWER_ID, amount));
        AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(owner, owner, ID));
    }
    
}

