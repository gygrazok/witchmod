package witchmod.actions;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;

import witchmod.powers.RotPower;

public class RotLoseHPAction extends AbstractGameAction {
    private static final float DURATION = 0.33f;
    private boolean contagious;
    
    public RotLoseHPAction(AbstractCreature target, AbstractCreature source, int amount, boolean contagious, AbstractGameAction.AttackEffect effect) {
        this.setValues(target, source, amount);
        this.actionType = AbstractGameAction.ActionType.DAMAGE;
        this.attackEffect = effect;
        this.duration = DURATION;
        this.contagious = contagious;
    }

    @Override
    public void update() {
        if (AbstractDungeon.getCurrRoom().phase != AbstractRoom.RoomPhase.COMBAT) {
            isDone = true;
            return;
        }
        if (duration == DURATION && target.currentHealth > 0) {
            AbstractDungeon.effectList.add(new FlashAtkImgEffect(target.hb.cX, target.hb.cY, attackEffect));
        }
        this.tickDuration();
        if (this.isDone) {
            AbstractPower rotPower = target.getPower(RotPower.POWER_ID_FULL);
            if (rotPower != null) {
                int potency = rotPower.amount;
                if (contagious && !this.target.isPlayer && target.currentHealth <= this.amount) {
                	//spread to another random monster
                    AbstractMonster newTarget = AbstractDungeon.getRandomMonster((AbstractMonster) target);
                    if (newTarget != null) {
                    	if (newTarget.hasPower(RotPower.POWER_ID_FULL)) {
                    		newTarget.getPower(RotPower.POWER_ID_FULL).amount += potency;
                    		((RotPower)newTarget.getPower(RotPower.POWER_ID_FULL)).contagious = true;
                    	} else {
                    		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(newTarget, source, new RotPower(newTarget, source, potency, true), potency));
                    	}
                    }
                }
                if (target.currentHealth > 0) {
                    target.tint.color = Color.BROWN.cpy();
                    target.tint.changeColor(Color.WHITE.cpy());
                    target.damage(new DamageInfo(source, amount, DamageInfo.DamageType.HP_LOSS));
                    rotPower.amount += 1;
                    rotPower.updateDescription();
                }

            }
            if (AbstractDungeon.getCurrRoom().monsters.areMonstersBasicallyDead()) {
                AbstractDungeon.actionManager.clearPostCombatActions();
            }
            AbstractDungeon.actionManager.addToTop(new WaitAction(0.1f));          
        }
    }
}

