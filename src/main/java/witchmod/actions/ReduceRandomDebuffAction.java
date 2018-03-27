package witchmod.actions;

import java.util.ArrayList;
import java.util.List;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.FrailPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;

public class ReduceRandomDebuffAction extends AbstractGameAction {
	private String powerID;

	public ReduceRandomDebuffAction(AbstractCreature target, AbstractCreature source, int amount) {
		this.setValues(target, source, amount);
		this.duration = Settings.ACTION_DUR_FAST;
		this.powerID = getRandomDebuff(target);
		this.actionType = AbstractGameAction.ActionType.REDUCE_POWER;
	}

	@Override
	public void update() {
		if (this.duration == Settings.ACTION_DUR_FAST) {
			for (AbstractPower p : this.target.powers) {
				if (!p.ID.equals(this.powerID)) continue;
				if (this.amount < p.amount) {
					p.reducePower(this.amount);
					p.updateDescription();
					AbstractDungeon.onModifyPower();
					break;
				}
				AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(this.target, this.source, this.powerID));
				break;
			}
		}
		this.tickDuration();
	}

	private String getRandomDebuff(AbstractCreature player) {
		List<String> candidates = new ArrayList<>();
		if (player.hasPower(WeakPower.POWER_ID)){
			candidates.add(WeakPower.POWER_ID);
		}
		if (player.hasPower(FrailPower.POWER_ID)){
			candidates.add(FrailPower.POWER_ID);
		}
		if (player.hasPower(VulnerablePower.POWER_ID)){
			candidates.add(VulnerablePower.POWER_ID);
		}
		
		return candidates.get((int)(Math.random()*candidates.size()));
	}
}

