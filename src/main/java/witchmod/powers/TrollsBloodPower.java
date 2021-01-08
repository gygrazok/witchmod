package witchmod.powers;

import java.util.List;

import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.AbstractCard.CardType;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class TrollsBloodPower extends AbstractWitchPower {
    private static final String POWER_ID = "TrollsBlood";
    //public static final String NAME = "Troll's Blood";
    //public static final String[] DESCRIPTIONS = new String[]{"At the end of your turn gain "," health if you played at least an attack this turn."};

    public TrollsBloodPower(AbstractCreature owner, int regenAmt) {
        super(POWER_ID);
        this.owner = owner;
        this.amount = regenAmt;
        this.updateDescription();
        this.loadRegion("regen");
        this.type = AbstractPower.PowerType.BUFF;
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
    	List<AbstractCard> cards = AbstractDungeon.actionManager.cardsPlayedThisTurn;
    	for (AbstractCard card : cards) {
    		if (card.type == CardType.ATTACK) {
    	        flash();
    	        AbstractDungeon.actionManager.addToBottom(new HealAction(owner, owner, amount));
    			return;
    		}
    	}
    }
}

