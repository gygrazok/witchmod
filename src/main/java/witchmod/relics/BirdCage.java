package witchmod.relics;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import witchmod.powers.SummonFamiliarPower;

public class BirdCage extends AbstractWitchRelic {
	public static final String ID = "PetCage";
	private static final RelicTier TIER = RelicTier.RARE;
	private static final String IMG = "relics/birdcage.png";
	private static final LandingSound SOUND = LandingSound.CLINK;

	public BirdCage() {
		super(ID, IMG, TIER, SOUND);
	}

	@Override
	public String getUpdatedDescription() {
		return DESCRIPTIONS[0];
	}

	@Override
	public void atTurnStart() {
		AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(SummonFamiliarPower.getRandomFamiliarCard()));
		AbstractDungeon.player.getRelic(ID).flash();
	}

	@Override
	public AbstractRelic makeCopy() {
		return new BirdCage();
	}
}

