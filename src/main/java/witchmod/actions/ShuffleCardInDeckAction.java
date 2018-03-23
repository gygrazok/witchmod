/*
 * Decompiled with CFR.
 */
package witchmod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class ShuffleCardInDeckAction extends AbstractGameAction {
	private AbstractCard card;
	private AbstractPlayer player;
	public ShuffleCardInDeckAction(AbstractPlayer player, AbstractCard card) {
		this.player = player;
		this.card = card;
		this.duration = 0.0f;
		this.actionType = AbstractGameAction.ActionType.CARD_MANIPULATION;
	}


    @Override
    public void update() {
    	player.hand.moveToDeck(card, true);
    	AbstractDungeon.player.hand.refreshHandLayout();
    	this.isDone = true;
    }
}

