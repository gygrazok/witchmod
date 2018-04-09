package witchmod.relics;

import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.AbstractCard.CardType;
import com.megacrit.cardcrawl.cards.curses.Clumsy;
import com.megacrit.cardcrawl.cards.curses.Decay;
import com.megacrit.cardcrawl.cards.curses.Doubt;
import com.megacrit.cardcrawl.cards.curses.Injury;
import com.megacrit.cardcrawl.cards.curses.Normality;
import com.megacrit.cardcrawl.cards.curses.Parasite;
import com.megacrit.cardcrawl.cards.curses.Writhe;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class BlackCat extends AbstractWitchRelic {
    public static final String ID = "BlackCat";
    private static final RelicTier TIER = RelicTier.STARTER;
    private static final String IMG = "relics/blackcat.png";
    private static final LandingSound SOUND = LandingSound.FLAT;

    public BlackCat() {
        super(ID, IMG, TIER, SOUND);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    @Override
    public void atBattleStartPreDraw() {
        AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDrawPileAction(AbstractDungeon.player,null,getCurse(),1,true,true));
        AbstractDungeon.actionManager.addToBottom(new RelicAboveCreatureAction(AbstractDungeon.player, this));
        AbstractDungeon.player.getRelic(ID).flash();
    }
    
    @Override
    public void onCardDraw(AbstractCard drawnCard) {
    	if (drawnCard.type == CardType.CURSE){
    		AbstractDungeon.actionManager.addToBottom(new GainEnergyAction(1));
    		AbstractDungeon.player.getRelic(ID).flash();
    	}
    	
    }
    
    protected AbstractCard getCurse() {
    	//we don't want to spawn pain or regret as they are far too punishing in the early game
    	//maybe add them in higher floors?
    	AbstractCard[] pool = new AbstractCard[] { new Clumsy(), new Decay(), new Doubt(), new Injury(), new Normality(), new Parasite(), new Writhe()};
    	return pool[MathUtils.random(0, pool.length - 1)];
    }

    @Override
    public AbstractRelic makeCopy() {
        return new BlackCat();
    }
}

