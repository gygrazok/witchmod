package witchmod.characters;

import java.util.ArrayList;

//import com.badlogic.gdx.math.*;
//import com.esotericsoftware.spine.*;
import com.megacrit.cardcrawl.core.EnergyManager;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.screens.CharSelectInfo;
import com.megacrit.cardcrawl.unlock.UnlockTracker;

import basemod.abstracts.CustomPlayer;
import witchmod.WitchMod;
import witchmod.patches.WitchEnum;




public class WitchCharacter extends CustomPlayer{
	
    public static final int ENERGY_PER_TURN = 3;

	public static final String[] orbTextures = {
			"img/char/orb/layer1.png",
			"img/char/orb/layer2.png",
			"img/char/orb/layer3.png",
			"img/char/orb/layer4.png",
			"img/char/orb/layer5.png",
			"img/char/orb/layer6.png",
			"img/char/orb/layer1d.png",
			"img/char/orb/layer2d.png",
			"img/char/orb/layer3d.png",
			"img/char/orb/layer4d.png",
			"img/char/orb/layer5d.png"
	};

	
	public WitchCharacter(String name, PlayerClass setClass) {
		super(name, setClass, orbTextures, "img/char/orb/vfx.png", "img/character.g3dj", "Character_Render|idle");
		
		this.dialogX = this.drawX + 0.0f * Settings.scale;
		this.dialogY = this.drawY + 170.0f * Settings.scale;
		
		initializeClass(null, WitchMod.getResourcePath(WitchMod.CHAR_SHOULDER_2),
				WitchMod.getResourcePath(WitchMod.CHAR_SHOULDER_1),
				WitchMod.getResourcePath(WitchMod.CHAR_CORPSE), 
				getLoadout(), 20.0F, -10.0F, 220.0F, 290.0F, new EnergyManager(ENERGY_PER_TURN));
	}
	
	@Override
	public void applyEndOfTurnTriggers() {
		for (AbstractPower p : this.powers) {
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
		retVal.add("Bone_Wall");
		return retVal;
	}
	
	public static ArrayList<String> getStartingRelics() {
		ArrayList<String> retVal = new ArrayList<>();
		retVal.add("Black_Cat");
		UnlockTracker.markRelicAsSeen("Black_Cat");
		return retVal;
	}
	
	public static CharSelectInfo getLoadout() {
		return new CharSelectInfo("The Witch", "A cackling sorceress specialized in dealing with curses.",
				70, 70, 99, 5,
			WitchEnum.WITCH, getStartingRelics(), getStartingDeck(), false);
	}
	

}
