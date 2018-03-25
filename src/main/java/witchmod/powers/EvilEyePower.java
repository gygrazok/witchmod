package witchmod.powers;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;

import witchmod.WitchMod;

public class EvilEyePower extends AbstractPower {
    public static final String POWER_ID = "EvilEyePower";
    public static final String NAME = "Evil Eye";
    public static final String[] DESCRIPTIONS = new String[]{ "Whenever you apply a Debuff to an enemy, gain #b"," block"};
    public static final String IMG = "powers/athamesoffering.png";

    public EvilEyePower(AbstractCreature owner, int amount) {
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
    	this.description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }
    

    @Override
    public void onApplyPower(AbstractPower power, AbstractCreature target, AbstractCreature source) {
        if (power.type == AbstractPower.PowerType.DEBUFF && !power.ID.equals("Shackled") && source == this.owner && target != this.owner && !target.hasPower("Artifact")) {
            this.flash();
            AbstractDungeon.actionManager.addToBottom(new GainBlockAction(owner, target, amount));
        }
    }
}

