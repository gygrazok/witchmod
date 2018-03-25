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
import com.megacrit.cardcrawl.powers.AbstractPower;

import witchmod.WitchMod;

public class RitualOfDecayPower extends AbstractPower {
    public static final String POWER_ID = "RitualOfDecayPower";
    public static final String NAME = "Ritual of Decay";
    public static final String[] DESCRIPTIONS = new String[]{ "When you play the first card each turn, all enemies lose health equal to the cost of that card."," NL Used for this turn."};
    public static final String IMG = "powers/athamesoffering.png";
    private boolean used = false;
    public RitualOfDecayPower(AbstractCreature owner) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.updateDescription();
        this.img = new Texture(WitchMod.getResourcePath(IMG));
        this.type = PowerType.BUFF;
        
    }

    @Override
    public void updateDescription() {
    	this.description = DESCRIPTIONS[0];
    	if (used) {
    		this.description += DESCRIPTIONS[1];
    	}
    }
    
    @Override
    public void onPlayCard(AbstractCard card, AbstractMonster m) {
    	if (used) {
    		return;
    	}
    	if (card.type == CardType.ATTACK || card.type == CardType.SKILL || card.type == CardType.POWER) {
    		int finalDamage;
    		if (card.cost == -2) { //X
    			finalDamage = AbstractDungeon.player.energy.energy;
    		} else if (card.cost == -1) {
    			finalDamage = 0;
    		} else {
    			finalDamage = card.cost;
    		}
    		if (finalDamage > 0) {
    			AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(owner, DamageInfo.createDamageMatrix(finalDamage, true), DamageType.HP_LOSS, AbstractGameAction.AttackEffect.BLUNT_LIGHT, true));
        		this.flash();	
    		}
    		used = true;
    		updateDescription();
    	}
    }
    
    @Override
    public void atStartOfTurn(){
    	used = false;
    	updateDescription();
    }
    
}

