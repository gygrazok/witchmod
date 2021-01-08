package witchmod.powers;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.DexterityPower;

public class LoseDexterityPower extends AbstractWitchPower {
    public static final String POWER_ID = "Twitch";
    public static final String NAME = "Twitch";
    public static final String[] DESCRIPTIONS = new String[] { "Temporarily gained #b"," #yDexterity."};

    public LoseDexterityPower(AbstractCreature owner, int newAmount) {
        super(POWER_ID);
        this.owner = owner;
        this.amount = newAmount;
        this.updateDescription();
        this.renderColor = Color.CHARTREUSE;
        this.loadRegion("flex");
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        flash();
		AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(owner, owner, DexterityPower.POWER_ID, amount));
        AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(owner, owner, ID));
    }
}

