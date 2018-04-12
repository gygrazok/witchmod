package witchmod.actions;

import java.util.ArrayList;
import java.util.List;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.AbstractCard.CardType;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import witchmod.cards.WalpurgisNight;

public class WalpurgisNightAction extends AbstractGameAction {
	public WalpurgisNightAction(int amount) {
		this.amount = amount;
		this.actionType = AbstractGameAction.ActionType.CARD_MANIPULATION;
		this.duration = 0.5f;
	}


	@Override
	public void update() {
		if (duration == 0.5f) {
			AbstractPlayer player = AbstractDungeon.player;
			List<AbstractCard> cardsToReshuffle = new ArrayList<>();
			for (AbstractCard card : player.exhaustPile.group) {
				if (card.type != CardType.STATUS && card.isEthereal == false && !card.cardID.equals(WalpurgisNight.ID)) {
					//throws concurrentmodificationexception if i move them to deck here
					cardsToReshuffle.add(card);
				}
			}
			
			//place some of them on top, so that they are immediately drawn
			int amountToPutOnTop = Math.min(cardsToReshuffle.size(), amount);
			while (amountToPutOnTop > 0) {
				AbstractCard card = cardsToReshuffle.remove((int)(Math.random()*cardsToReshuffle.size()));
	            card.stopGlowing();
	            card.unhover();
	            card.unfadeOut();
				player.exhaustPile.moveToDeck(card, false);
				amountToPutOnTop--;
			}
			
			//shuffle the rest
			for (AbstractCard card : cardsToReshuffle) {
	            card.stopGlowing();
	            card.unhover();
	            card.unfadeOut();
				player.exhaustPile.moveToDeck(card, true);
			}
		}
		tickDuration();
	}
}

