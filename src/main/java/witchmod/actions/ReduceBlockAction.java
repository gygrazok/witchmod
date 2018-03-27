package witchmod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.AbstractCreature;

public class ReduceBlockAction extends AbstractGameAction {
	public ReduceBlockAction(AbstractCreature target, AbstractCreature source, int amount) {
		this.setValues(target, source, amount);
		this.actionType = AbstractGameAction.ActionType.SPECIAL;
		this.duration = 0;
	}

	@Override
	public void update() {
		if (target == null || target.isDying) {
			isDone = true;
			return;
		}
		if (target.currentBlock > amount) {
			target.currentBlock -= amount;
		} else {
			target.currentBlock = 0;
		}
        isDone = true;
	}

}

