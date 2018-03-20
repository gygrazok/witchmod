package witchmod.relics;

import com.badlogic.gdx.graphics.Texture;

import basemod.abstracts.CustomRelic;
import witchmod.WitchMod;

public abstract class AbstractWitchRelic extends CustomRelic{

	public AbstractWitchRelic(String id, String img, RelicTier tier, LandingSound sfx) {
		super(id, new Texture(WitchMod.getResourcePath(img)), tier, sfx);
	}


}
