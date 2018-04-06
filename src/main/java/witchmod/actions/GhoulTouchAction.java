package witchmod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.GainStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

public class GhoulTouchAction extends AbstractGameAction {
	private DamageInfo info;

	public GhoulTouchAction(AbstractCreature target, DamageInfo info) {
		this.info = info;
		this.setValues(target, info);
		this.actionType = AbstractGameAction.ActionType.DAMAGE;
		this.duration = 0;
	}

	@Override
	public void update() {
		if (target == null || target.isDying) {
			isDone = true;
			return;
		}
		int effect = getUnblockedDamage();
		if (effect > 0) {
	        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(target, AbstractDungeon.player, new StrengthPower(target, -effect), -effect));
	        if (!target.hasPower("Artifact")) {
	            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(target, AbstractDungeon.player, new GainStrengthPower(target, effect), effect));
	        }
		}
        target.damage(info);
        isDone = true;
	}
	
	private int getUnblockedDamage(){
		info.applyPowers(AbstractDungeon.player, target);
		int baseDamage = info.output;
		baseDamage -= target.currentBlock;
		if (baseDamage > 0) {
			return baseDamage;
		} else {
			return 0;
		}
	}
}

