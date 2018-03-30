package witchmod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.PoisonPower;

import witchmod.powers.DecrepitPower;

public class CorruptBloodAction extends AbstractGameAction {

	public CorruptBloodAction(AbstractCreature target, AbstractCreature source, int amount) {
		this.setValues(target, source, amount);
		this.actionType = AbstractGameAction.ActionType.DEBUFF;
		this.duration = 0;
	}

	@Override
	public void update() {
		if (target.hasPower(PoisonPower.POWER_ID)) {
			int poisonStacks = target.getPower(PoisonPower.POWER_ID).amount;
			AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(target, source, PoisonPower.POWER_ID));
			AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(target, source, new DecrepitPower(target, poisonStacks, true), poisonStacks, true));
		} else {
			AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(target, source, new PoisonPower(target, source, amount), amount, true));
		}
		isDone = true;
	}
}

