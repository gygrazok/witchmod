package witchmod.powers;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.AbstractCard.CardType;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import witchmod.WitchMod;

public class TwistedMindPower extends AbstractWitchPower {
    private static final String POWER_ID = "TwistedMindPower";
    //public static final String NAME = "Twisted Mind";
    //public static final String[] DESCRIPTIONS = new String[]{ "Whenever you play a card that costs 2 or more, ALL enemies lose "," health."};
    public static final String IMG = "powers/twistedmind.png";
    public TwistedMindPower(AbstractCreature owner, int amount) {
        super(POWER_ID);
        this.owner = owner;
        this.img = new Texture(WitchMod.getResourcePath(IMG));
        this.type = PowerType.BUFF;
        this.amount = amount;
        this.updateDescription();
        this.isTurnBased = false;
    }

    @Override
    public void updateDescription() {
    	description = DESCRIPTIONS[0]+amount+DESCRIPTIONS[1];
    }
    
    @Override
    public void onPlayCard(AbstractCard card, AbstractMonster m) {
    	if (card.type == CardType.ATTACK || card.type == CardType.SKILL || card.type == CardType.POWER) {
    		if (card.costForTurn >= 2) {
    			AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(owner, DamageInfo.createDamageMatrix(amount, true), DamageType.HP_LOSS, AbstractGameAction.AttackEffect.BLUNT_LIGHT, true));
        		flash();	
    		} 
    	}
    }
    
    
}

