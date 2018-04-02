package witchmod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class KillMonsterAction extends AbstractGameAction {
	AbstractMonster monster;
	public KillMonsterAction(AbstractMonster target) {
		this.monster = target;
		this.actionType = AbstractGameAction.ActionType.SPECIAL;
		this.duration = 0;
	}

	@Override
	public void update() {
		monster.damage(new DamageInfo(monster, monster.currentHealth));
		monster.currentHealth = 0;
		monster.die();
        isDone = true;
	}
	

}

