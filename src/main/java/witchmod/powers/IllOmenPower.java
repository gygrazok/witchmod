package witchmod.powers;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.AbstractCard.CardType;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.combat.VerticalAuraEffect;

import witchmod.WitchMod;

public class IllOmenPower extends AbstractWitchPower {
    private static final String POWER_ID = "IllOmenPower";
    //public static final String NAME = "Ill Omen";
    //public static final String[] DESCRIPTIONS = new String[]{ "The next time you draw a curse deal #b"," damage to ALL enemies."};
    public static final String IMG = "powers/illomen.png";
    private boolean triggered = false;
    public IllOmenPower(AbstractCreature owner, int amount) {
        super(POWER_ID);
        this.owner = owner;
        this.amount = amount;
        this.updateDescription();
        this.img = new Texture(WitchMod.getResourcePath(IMG));
        this.type = PowerType.BUFF;
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0]+amount+DESCRIPTIONS[1];
    }
    
    @Override
    public void onCardDraw(AbstractCard card) {
    	if (card.type == CardType.CURSE && !triggered) {
    		flash();
    		triggered = true;
    		AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(owner, owner, ID));
    		AbstractDungeon.actionManager.addToTop(new VFXAction(AbstractDungeon.player, new VerticalAuraEffect(Color.BLACK, AbstractDungeon.player.hb.cX, AbstractDungeon.player.hb.cY),0.1f));
    		AbstractDungeon.actionManager.addToTop(new DamageAllEnemiesAction(owner, DamageInfo.createDamageMatrix(amount, true), DamageType.THORNS, AttackEffect.POISON));
    	}
    }
    
}


