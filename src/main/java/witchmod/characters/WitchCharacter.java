package witchmod.characters;

import java.util.ArrayList;

import com.badlogic.gdx.math.MathUtils;
import com.esotericsoftware.spine.AnimationState;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.EnergyManager;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.daily.DailyMods;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.screens.CharSelectInfo;
import com.megacrit.cardcrawl.unlock.UnlockTracker;

import basemod.BaseMod;
import basemod.abstracts.CustomPlayer;
import witchmod.WitchMod;
import witchmod.cardgroup.WitchCardGroup;
import witchmod.patches.WitchEnum;
import witchmod.powers.AbstractWitchPower;




public class WitchCharacter extends CustomPlayer{
	
    public static final int ENERGY_PER_TURN = 3;

	public static final String[] orbTextures = {
			"images/char/orb/layer1.png",
			"images/char/orb/layer2.png",
			"images/char/orb/layer3.png",
			"images/char/orb/layer4.png",
			"images/char/orb/layer5.png",
			"images/char/orb/layer6.png",
			"images/char/orb/layer1d.png",
			"images/char/orb/layer2d.png",
			"images/char/orb/layer3d.png",
			"images/char/orb/layer4d.png",
			"images/char/orb/layer5d.png"
	};


	
	public WitchCharacter(String name, PlayerClass setClass) {
		super(name, setClass, orbTextures, "images/char/orb/vfx.png", null, WitchMod.getResourcePath(WitchMod.CHAR_SKELETON_JSON));
		hand = new WitchCardGroup(CardGroup.CardGroupType.HAND);
		
		dialogX = drawX + 0.0f * Settings.scale;
		dialogY = drawY + 220.0f * Settings.scale;
		
		initializeClass(null, WitchMod.getResourcePath(WitchMod.CHAR_SHOULDER_2),
				WitchMod.getResourcePath(WitchMod.CHAR_SHOULDER_1),
				WitchMod.getResourcePath(WitchMod.CHAR_CORPSE), 
				getLoadout(), 20.0F, -10.0F, 220.0F, 290.0F, new EnergyManager(ENERGY_PER_TURN));
		loadAnimation(WitchMod.getResourcePath(WitchMod.CHAR_SKELETON_ATLAS), WitchMod.getResourcePath(WitchMod.CHAR_SKELETON_JSON), 1.0F); 
		if (Settings.dailyModsEnabled() && DailyMods.cardMods.get("Diverse")) {
			masterMaxOrbs = 1;
		}
        AnimationState.TrackEntry e = state.setAnimation(0, "idle", true);
        e.setTime(e.getEndTime() * MathUtils.random());
	}
	
	@Override
	public void applyEndOfTurnTriggers() {
		for (AbstractPower p : powers) {
			p.atEndOfTurn(true);
		}
	}

	public static ArrayList<String> getStartingDeck() {
		ArrayList<String> retVal = new ArrayList<>();
		retVal.add("Strike_Witch");
		retVal.add("Strike_Witch");
		retVal.add("Strike_Witch");
		retVal.add("Strike_Witch");
		retVal.add("Defend_Witch");
		retVal.add("Defend_Witch");
		retVal.add("Defend_Witch");
		retVal.add("Defend_Witch");
		retVal.add("Hexguard");
		retVal.add("ZombieSpit");
		return retVal;
		//return getAllCards();
	}
	
	public static ArrayList<String> getStartingRelics() {
		ArrayList<String> retVal = new ArrayList<>();
		retVal.add("BlackCat");
		UnlockTracker.markRelicAsSeen("BlackCat");
		return retVal;
	}
	
	public static CharSelectInfo getLoadout() {
		return new CharSelectInfo("The Witch", "A cackling sorceress specialized NL in dealing with curses.",
				67, 67, 0, 99, 5,
			WitchEnum.WITCH, getStartingRelics(), getStartingDeck(), false);
	}
	
	

	@Override
	protected int decrementBlock(DamageInfo info, int damageAmount) {
		int actualBlockLost = Math.min(damageAmount, currentBlock);
		int out = super.decrementBlock(info, damageAmount);
        for (AbstractPower p : powers) {
        	if (p instanceof AbstractWitchPower) {
        		((AbstractWitchPower)p).onDamageAbsorbedByBlock(damageAmount,actualBlockLost,currentBlock);
        	}
        }
        return out;
	}

	//for debug
	private static ArrayList<String> getAllCards() {
		ArrayList<String> out = new ArrayList<String>();
		for (AbstractCard card : BaseMod.getCustomCardsToAdd()) {
			out.add(card.cardID);
		}
		return out;
	}
	

}
