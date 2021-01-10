package witchmod.actions;

import java.util.ArrayList;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.screens.CardRewardScreen;
import com.megacrit.cardcrawl.ui.buttons.SingingBowlButton;
import com.megacrit.cardcrawl.ui.buttons.SkipCardButton;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToDiscardEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToDrawPileEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToHandEffect;

import basemod.ReflectionHacks;

public class BlackCatAction extends AbstractGameAction{
    private boolean retrieveCard = false;
	public BlackCatAction() {
        this.actionType = AbstractGameAction.ActionType.CARD_MANIPULATION;
        this.duration = Settings.ACTION_DUR_FAST;
	}
	
    
    @Override
    public void update() {
        if (duration == Settings.ACTION_DUR_FAST) {
        	AbstractDungeon.cardRewardScreen.reset();
        	
        	AbstractDungeon.cardRewardScreen.rItem = null;
        	AbstractDungeon.cardRewardScreen.discoveryCard = null;
            AbstractDungeon.cardRewardScreen.codexCard = null;
            ReflectionHacks.setPrivate(AbstractDungeon.cardRewardScreen, CardRewardScreen.class, "discovery", true);
            ((SingingBowlButton) ReflectionHacks.getPrivate(AbstractDungeon.cardRewardScreen, CardRewardScreen.class, "bowlButton")).hide();
            ((SkipCardButton) ReflectionHacks.getPrivate(AbstractDungeon.cardRewardScreen, CardRewardScreen.class, "skipButton")).hide();
            AbstractDungeon.topPanel.unhoverHitboxes();
            ArrayList<AbstractCard> derp = new ArrayList<AbstractCard>();
            while (derp.size() != 3) {
                boolean dupe = false;
                AbstractCard tmp = AbstractDungeon.returnRandomCurse();
                for (AbstractCard c : derp) {
                    if (!c.cardID.equals(tmp.cardID)) continue;
                    dupe = true;
                    break;
                }
                if (dupe) continue;
                derp.add(tmp.makeCopy());
            }
            AbstractDungeon.cardRewardScreen.rewardGroup = derp;
            AbstractDungeon.isScreenUp = true;
            AbstractDungeon.screen = AbstractDungeon.CurrentScreen.CARD_REWARD;
            AbstractDungeon.dynamicBanner.appear("Add a curse to your draw pile");
            AbstractDungeon.overlayMenu.showBlackScreen();
            placeCards((float)Settings.WIDTH / 2.0f, Settings.HEIGHT * 0.45f);
            for (AbstractCard c : AbstractDungeon.cardRewardScreen.rewardGroup) {
                UnlockTracker.markCardAsSeen(c.cardID);
            }
            tickDuration();
            return;
        }
        if (!retrieveCard) {
            if (AbstractDungeon.cardRewardScreen.discoveryCard != null) {
                AbstractCard curse = AbstractDungeon.cardRewardScreen.discoveryCard.makeStatEquivalentCopy();
                if (curse.isInnate) {
                    AbstractDungeon.effectList.add(0,new ShowCardAndAddToDrawPileEffect(curse, Settings.WIDTH/2, Settings.HEIGHT/2, false));
                } else {
                    AbstractDungeon.effectList.add(0,new ShowCardAndAddToDrawPileEffect(curse, Settings.WIDTH/2, Settings.HEIGHT/2, true));
                }
                AbstractDungeon.cardRewardScreen.discoveryCard = null;
            }
            retrieveCard = true;
        }
        tickDuration();
    }
    
    private void placeCards(float x, float y) {
    	AbstractDungeon.cardRewardScreen.rewardGroup.get((int)0).target_x = (float)Settings.WIDTH / 2.0f - AbstractCard.IMG_WIDTH - 40.0f * Settings.scale;
    	AbstractDungeon.cardRewardScreen.rewardGroup.get((int)1).target_x = (float)Settings.WIDTH / 2.0f;
    	AbstractDungeon.cardRewardScreen.rewardGroup.get((int)2).target_x = (float)Settings.WIDTH / 2.0f + AbstractCard.IMG_WIDTH + 40.0f * Settings.scale;
    	AbstractDungeon.cardRewardScreen.rewardGroup.get((int)0).target_y = y;
        AbstractDungeon.cardRewardScreen.rewardGroup.get((int)1).target_y = y;
        AbstractDungeon.cardRewardScreen.rewardGroup.get((int)2).target_y = y;

        for (AbstractCard c : AbstractDungeon.cardRewardScreen.rewardGroup) {
            c.drawScale = 0.75f;
            c.targetDrawScale = 0.75f;
            c.current_x = x;
            c.current_y = y;
        }
    }
    
}
