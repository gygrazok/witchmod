package witchmod.relics;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import witchmod.powers.SummonFamiliarPower;

public class PetCage extends AbstractWitchRelic {
    public static final String ID = "PetCage";
    private static final RelicTier TIER = RelicTier.RARE;
    private static final String IMG = "relics/blackcat.png";
    private static final LandingSound SOUND = LandingSound.CLINK;

    public PetCage() {
        super(ID, IMG, TIER, SOUND);
    }

    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    @Override
    public void atBattleStartPreDraw() {
        AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(SummonFamiliarPower.getRandomFamiliarCard()));
        AbstractDungeon.actionManager.addToBottom(new RelicAboveCreatureAction(AbstractDungeon.player, this));
        AbstractDungeon.player.getRelic(ID).flash();
    }
    

    @Override
    public AbstractRelic makeCopy() {
        return new PetCage();
    }
}

