package witchmod.actions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import witchmod.powers.AthamePower;

public class AthameAction extends AbstractGameAction {
	private Logger log = LogManager.getLogger(AthameAction.class);
	private DamageInfo info;

	public AthameAction(AbstractCreature target, DamageInfo info, int amount) {
		this.info = info;
		this.setValues(target, info);
		this.amount = amount;
		this.actionType = AbstractGameAction.ActionType.DAMAGE;
		this.duration = 0;
	}

	@Override
	public void update() {
		AbstractDungeon.actionManager.addToBottom(new DamageAction(target, info, AttackEffect.SLASH_VERTICAL));
		
		info.applyPowers(AbstractDungeon.player, target); //not sure if needed
		log.info("Athame: final damage = "+info.output);
		log.info("Athame: enemy stats = "+target.currentBlock+"+"+target.currentHealth);
		if (info.output >= target.currentBlock+target.currentHealth) {
			//enough damage to kill
			log.info("Athame: applying athame power");
			AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new AthamePower(AbstractDungeon.player,this.amount), this.amount));
		}
		AbstractDungeon.actionManager.addToBottom(new DamageAction(target, info));
		this.isDone = true;
	}
}

