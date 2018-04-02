package witchmod.relics;

import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import basemod.ReflectionHacks;
import witchmod.powers.DecrepitPower;

public class WalkingCane extends AbstractWitchRelic {
    public static final String ID = "WalkingCane";
    private static final RelicTier TIER = RelicTier.COMMON;
    private static final String IMG = "relics/blackcat.png";
    private static final LandingSound SOUND = LandingSound.SOLID;

    public WalkingCane() {
        super(ID, IMG, TIER, SOUND);
    }

    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

	@Override
	public void onPlayerEndTurn() {
		for (AbstractMonster m : AbstractDungeon.getCurrRoom().monsters.monsters) {
			setNonTurnBased(m.getPower(WeakPower.POWER_ID));
			setNonTurnBased(m.getPower(VulnerablePower.POWER_ID));
			setNonTurnBased(m.getPower(DecrepitPower.POWER_ID));
		}
	}
	
	@Override
	public void atTurnStart() {
		for (AbstractMonster m : AbstractDungeon.getCurrRoom().monsters.monsters) {
			setNonTurnBased(m.getPower(WeakPower.POWER_ID));
			setNonTurnBased(m.getPower(VulnerablePower.POWER_ID));
			setNonTurnBased(m.getPower(DecrepitPower.POWER_ID));
		}
	}
	
	private void setNonTurnBased(AbstractPower power) {
		if (power != null) {
			//justApplied gets resetted every turn and determines if the debuff should decrease or not
			this.flash();
			ReflectionHacks.setPrivate(power, AbstractPower.class, "justApplied", true);
		}
	}

    @Override
    public AbstractRelic makeCopy() {
        return new WalkingCane();
    }
}

