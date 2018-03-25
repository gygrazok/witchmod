package witchmod.cards;

import basemod.abstracts.CustomCard;
import witchmod.WitchMod;
import witchmod.patches.AbstractCardEnum;

public abstract class AbstractWitchCard extends CustomCard{
	public boolean reshuffleOnUse = false;
	
	public AbstractWitchCard(String id, String name, String img, int cost, String rawDescription, CardType type, CardRarity rarity, CardTarget target, int cardPool){
		super(id, name, WitchMod.getResourcePath(img), cost, rawDescription, type, AbstractCardEnum.WITCH, rarity, target, cardPool);
	}
}
