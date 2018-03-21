/*
 * Decompiled with CFR.
 */
package witchmod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;

import witchmod.powers.AthamePower;

public class AthameAction extends AbstractGameAction {
	private DamageInfo info;

	public AthameAction(AbstractCreature target, DamageInfo info, int amount) {
		this.info = info;
		this.setValues(target, info);
		this.amount = amount;
		this.actionType = AbstractGameAction.ActionType.DAMAGE;
		this.duration = 0.1f;
	}

	@Override
	public void update() {
		if (this.duration == 0.1f && this.target != null) {
			AbstractDungeon.effectList.add(new FlashAtkImgEffect(this.target.hb.cX, this.target.hb.cY, AbstractGameAction.AttackEffect.SLASH_VERTICAL));
			this.target.damage(this.info);
			if (!(!this.target.isDying && this.target.currentHealth > 0 || this.target.halfDead || this.target.hasPower("Minion"))) {
	            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new AthamePower(AbstractDungeon.player,this.amount), this.amount));
			}
			if (AbstractDungeon.getCurrRoom().monsters.areMonstersBasicallyDead()) {
				AbstractDungeon.actionManager.clearPostCombatActions();
			}
		}
		this.tickDuration();
	}
}

