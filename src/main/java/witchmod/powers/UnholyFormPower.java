package witchmod.powers;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import witchmod.WitchMod;

public class UnholyFormPower extends AbstractWitchPower {
	public static final String POWER_ID = "UnholyForm";
	public static final String NAME = "UnholyForm";
	public static final String[] DESCRIPTIONS = new String[]{ "Enemy attacks won't deal more than ", " damage.", "At the start of your turn add a random Curse to your draw pile." };
	public static final String IMG = "powers/intelligence.png";
	public UnholyFormPower(AbstractCreature owner, int amount) {
		this.name = NAME;
		this.ID = POWER_ID;
		this.owner = owner;

		this.img = new Texture(WitchMod.getResourcePath(IMG));
		this.type = PowerType.BUFF;
		this.amount = amount;
		this.updateDescription();
	}

	@Override
	public void updateDescription() {
		description = DESCRIPTIONS[0] + " "+amount+" " + DESCRIPTIONS[2];
	}

	@Override
	public void atStartOfTurn() {
		AbstractDungeon.actionManager.addToTop(new MakeTempCardInDrawPileAction(AbstractDungeon.returnRandomCurse(),1,false,false));
	}
	
    @Override
    public float atDamageReceive(float damage, DamageInfo.DamageType type) {
        if (damage > amount) {
            damage = amount;
        }
        return damage;
    }


	
}

