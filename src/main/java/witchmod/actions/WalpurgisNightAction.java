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
	public WalpurgisNightAction() {
		this.actionType = AbstractGameAction.ActionType.CARD_MANIPULATION;
	}


	@Override
	public void update() {
		AbstractPlayer player = AbstractDungeon.player;
		List<AbstractCard> cardsToReshuffle = new ArrayList<>();
		for (AbstractCard card : player.exhaustPile.group) {
			if (card.type != CardType.STATUS && !card.cardID.equals(WalpurgisNight.ID)) {
				//throws concurrentmodificationexception if i move them to deck here
				cardsToReshuffle.add(card);
			}
		}
		for (AbstractCard card : cardsToReshuffle) {
            card.stopGlowing();
            card.unhover();
            card.unfadeOut();
			player.exhaustPile.moveToDeck(card, true);
		}

		isDone = true;

	}
}

