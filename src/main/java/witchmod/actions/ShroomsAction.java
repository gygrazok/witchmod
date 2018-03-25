package witchmod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.EmptyDeckShuffleAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class ShroomsAction extends AbstractGameAction{
	public ShroomsAction() {
		this.duration = 0.0f;
		this.actionType = AbstractGameAction.ActionType.CARD_MANIPULATION;
	}

	@Override
	public void update() {
		if (AbstractDungeon.player.drawPile.size() + AbstractDungeon.player.discardPile.size() == 0) {
			this.isDone = true;
			return;
		}
		if (AbstractDungeon.player.drawPile.isEmpty()) {
			AbstractDungeon.actionManager.addToTop(new ShroomsAction());
			AbstractDungeon.actionManager.addToTop(new EmptyDeckShuffleAction());
			this.isDone = true;
			return;
		}
		AbstractCard c = AbstractDungeon.player.drawPile.getTopCard();
		int newCost = AbstractDungeon.cardRandomRng.random(3);
        if (c.cost > -1 && c.color != AbstractCard.CardColor.CURSE && c.type != AbstractCard.CardType.STATUS && c.cost != newCost) {
            c.costForTurn = c.cost = newCost;
            c.isCostModified = true;
        }
		AbstractDungeon.actionManager.addToBottom(new DrawCardAction(AbstractDungeon.player, 1));
		this.isDone = true;
	}
}