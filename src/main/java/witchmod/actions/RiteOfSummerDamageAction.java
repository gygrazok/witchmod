package witchmod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;

public class RiteOfSummerDamageAction extends AbstractGameAction{
	private DamageInfo info;
	public RiteOfSummerDamageAction(AbstractCreature target, DamageInfo info, int amount) {
		this.target = target;
		this.info = info;
		this.amount = amount;
		this.duration = Settings.ACTION_DUR_FAST;
		this.actionType = AbstractGameAction.ActionType.CARD_MANIPULATION;
		this.attackEffect = AttackEffect.FIRE;
	}

	@Override
    public void update() {
        if (target == null) {
            isDone = true;
            return;
        }
        if (AbstractDungeon.getCurrRoom().monsters.areMonstersBasicallyDead()) {
            AbstractDungeon.actionManager.clearPostCombatActions();
            isDone = true;
            return;
        }
        if (target.currentHealth > 0) {
            target.damageFlash = true;
            target.damageFlashFrames = 4;
            AbstractDungeon.effectList.add(new FlashAtkImgEffect(target.hb.cX, target.hb.cY, attackEffect));
            info.applyPowers(info.owner, target);
            target.damage(this.info);
            if (amount > 1 && !AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
            	amount--;
                AbstractDungeon.actionManager.addToTop(new RiteOfSummerDamageAction(AbstractDungeon.getMonsters().getRandomMonster(true), info, amount));
            }
            AbstractDungeon.actionManager.addToTop(new WaitAction(0.1f));
        }
        isDone = true;
    }
}