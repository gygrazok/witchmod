package witchmod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.AbstractCard.CardType;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class CrystalResonanceAction extends AbstractGameAction{
	public CrystalResonanceAction() {
		this.duration = 0.0f;
	}

	@Override
	public void update() {
        for (AbstractCard c : AbstractDungeon.player.hand.group) {
        	if (c.type == CardType.SKILL) {
        		c.modifyCostForTurn(-1);
        	}
        }
		this.isDone = true;
	}
}