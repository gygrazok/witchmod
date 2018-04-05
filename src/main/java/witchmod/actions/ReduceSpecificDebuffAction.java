package witchmod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class ReduceSpecificDebuffAction extends AbstractGameAction {
	private String powerID;

	public ReduceSpecificDebuffAction(AbstractCreature target, AbstractCreature source, String powerID, int amount) {
		this.setValues(target, source, amount);
		this.duration = Settings.ACTION_DUR_FAST;
		this.powerID = powerID;
		this.actionType = AbstractGameAction.ActionType.REDUCE_POWER;
	}

	@Override
	public void update() {
		if (powerID == null) {
			isDone = true;
		}
		if (duration == Settings.ACTION_DUR_FAST) {
			for (AbstractPower p : target.powers) {
				if (!p.ID.equals(powerID)) continue;
				if (amount < p.amount) {
					p.reducePower(amount);
					p.updateDescription();
					AbstractDungeon.onModifyPower();
					break;
				}
				AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(target, source, powerID));
				break;
			}
		}
		tickDuration();
	}

}

