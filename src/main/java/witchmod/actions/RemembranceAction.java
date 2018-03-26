package witchmod.actions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.AbstractCard.CardType;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class RemembranceAction extends AbstractGameAction {
	public RemembranceAction() {
		this.actionType = AbstractGameAction.ActionType.CARD_MANIPULATION;
		this.duration = 0;
	}

	@Override
	public void update() {
        Map<CardType, List<AbstractCard>> types = new HashMap<>();
        for (AbstractCard c : AbstractDungeon.player.discardPile.group) {
        	List<AbstractCard> list;
        	if (types.containsKey(c.type)) {
        		list = types.get(c.type);
        	} else {
        		list = new ArrayList<>();
        	}
    		list.add(c);
        	types.put(c.type, list);
        }
        List<AbstractCard> cards = new ArrayList<>();
        for (Entry<CardType,List<AbstractCard>> entry : types.entrySet()) {
        	cards.add(getRandomCardFromList(entry.getValue()));
        }
        for (AbstractCard card : cards) {        	
        	AbstractDungeon.actionManager.addToBottom(new ShuffleCardInDeckAction(AbstractDungeon.player, card));
        }
        this.isDone = true;

	}
	
	private AbstractCard getRandomCardFromList(List<AbstractCard> list) {
		return list.get(AbstractDungeon.cardRng.random(list.size()-1));
	}
}

