package witchmod.actions;

import java.util.ArrayList;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class RavenAction extends AbstractGameAction{
	private AbstractPlayer player;
	public static final String TEXT = "Pick a card";
	private ArrayList<AbstractCard> eligible = new ArrayList<>();
    private ArrayList<AbstractCard> oldHand;
	public RavenAction() {
		this.duration = Settings.ACTION_DUR_FAST;
		this.actionType = AbstractGameAction.ActionType.CARD_MANIPULATION;
		this.player = AbstractDungeon.player;
	}

	 @Override
	    public void update() {
	        if (duration == Settings.ACTION_DUR_FAST) {
	            for (AbstractCard c : player.hand.group) {
	                if (c.canUpgrade() || c.costForTurn > 0) {
	                	eligible.add(c);
	                }
	            }
	            if (eligible.size() == 0) {
	                isDone = true;
	                return;
	            }
	            if (eligible.size() == 1) {
	            	ravenize(eligible.get(0));
                    isDone = true;
                    return;
	            }
	            oldHand = player.hand.group;
	            player.hand.group = eligible;
	            
                AbstractDungeon.handCardSelectScreen.open(TEXT, 1, false, false, false, true);
                this.tickDuration();
                return;
	        }
	        if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {
	            for (AbstractCard c : AbstractDungeon.handCardSelectScreen.selectedCards.group) {
	            	ravenize(c);
	            }
	            this.returnCards();
	            AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
	            AbstractDungeon.handCardSelectScreen.selectedCards.group.clear();
	            this.isDone = true;
	        }
	        this.tickDuration();
	    }

	    private void returnCards() {
	    	player.hand.group = oldHand;
	        player.hand.refreshHandLayout();
	    }
	    
	    private void ravenize(AbstractCard c){
            if (c.canUpgrade()) {
                c.upgrade();
            }
            if (c.costForTurn > 0) {
           		c.modifyCostForCombat(-1);
            }
	    }
}