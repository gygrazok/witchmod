package witchmod.relics;

import java.util.List;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.AbstractCard.CardRarity;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;

public class ToyHorse extends AbstractWitchRelic {
    public static final String ID = "ToyHorse";
	private static final RelicTier TIER = RelicTier.COMMON;
	private static final String IMG = "relics/birdcage.png";
	private static final LandingSound SOUND = LandingSound.CLINK;
	public boolean cardSelected = false;
    public ToyHorse() {
		super(ID, IMG, TIER, SOUND);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    @Override
    public void onEquip() {
    	CardGroup group = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
        List<AbstractCard> list = CardLibrary.getAllCards();
        for (AbstractCard c : list){
        	if (c.rarity == CardRarity.BASIC && c.pool != 0) {
        		group.addToBottom(c);
        		UnlockTracker.markCardAsSeen(c.cardID);
        	}
        }
        AbstractDungeon.gridSelectScreen.open(group, 1, "Select a card to add to your deck", false);
    }
    
    @Override
    public void update() {
        super.update();
        if (!cardSelected && !AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
            cardSelected = true;
            AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(AbstractDungeon.gridSelectScreen.selectedCards.get(0).makeCopy(), (float)Settings.WIDTH / 2.0f, (float)Settings.HEIGHT / 2.0f));
            AbstractDungeon.gridSelectScreen.selectedCards.clear();
        }
    }

    @Override
    public AbstractRelic makeCopy() {
        return new ToyHorse();
    }
}

