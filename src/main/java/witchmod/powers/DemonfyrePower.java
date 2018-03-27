package witchmod.powers;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;

import witchmod.WitchMod;

public class DemonfyrePower extends AbstractPower {
    public static final String POWER_ID = "DemonfyrePower";
    public static final String NAME = "Demonfyre";
    public static final String[] DESCRIPTIONS = new String[]{ "Increases the damage of Demonfyre by #b"};
    public static final String IMG = "powers/demonfyre.png";
    public DemonfyrePower(AbstractCreature owner, int amount) {
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
        this.description = DESCRIPTIONS[0]+this.amount;
    }
}

