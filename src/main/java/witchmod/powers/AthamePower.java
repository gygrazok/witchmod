package witchmod.powers;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;

import witchmod.WitchMod;

public class AthamePower extends AbstractPower {
    public static final String POWER_ID = "AthamesOffering";
    public static final String NAME = "Athame's Offering";
    public static final String[] DESCRIPTIONS = new String[]{ "Chance to obtain a rare card increased by #b", " times." };
    public static final String IMG = "powers/athamesoffering.png";
    public AthamePower(AbstractCreature owner, int amount) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.updateDescription();
        this.img = new Texture(WitchMod.getResourcePath(IMG));
        this.type = PowerType.BUFF;
        this.priority = 99;
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0]+this.amount+DESCRIPTIONS[1];
    }
}

