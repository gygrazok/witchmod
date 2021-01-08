package witchmod.powers;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.core.AbstractCreature;

import witchmod.WitchMod;

public class AthamePower extends AbstractWitchPower {
    public static final String POWER_ID_FULL = "WitchMod:AthamesOffering";
    private static final String POWER_ID = "AthamesOffering";
    //public static final String NAME = "Athame's Offering";
    //public static final String[] DESCRIPTIONS = new String[]{ "Chance to obtain a rare card increased by #b", "%." };
    public static final String IMG = "powers/athame.png";
    public AthamePower(AbstractCreature owner, int amount) {
        super(POWER_ID);
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
}

