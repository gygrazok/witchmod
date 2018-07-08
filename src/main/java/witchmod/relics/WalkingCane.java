package witchmod.relics;

import com.megacrit.cardcrawl.relics.AbstractRelic;

public class WalkingCane extends AbstractWitchRelic {
	public static final String ID = "WalkingCane";
	private static final RelicTier TIER = RelicTier.RARE;
	private static final String IMG = "relics/walkingcane.png";
	private static final LandingSound SOUND = LandingSound.SOLID;

	public WalkingCane() {
		super(ID, IMG, TIER, SOUND);
	}

	@Override
	public String getUpdatedDescription() {
		return DESCRIPTIONS[0];
	}

	@Override
	public AbstractRelic makeCopy() {
		return new WalkingCane();
	}
}

