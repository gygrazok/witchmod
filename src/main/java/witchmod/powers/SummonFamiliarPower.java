package witchmod.powers;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.random.Random;

import witchmod.WitchMod;
import witchmod.cards.familiar.BatFamiliar;
import witchmod.cards.familiar.CatFamiliar;
import witchmod.cards.familiar.FamiliarCardEnum;
import witchmod.cards.familiar.OwlFamiliar;
import witchmod.cards.familiar.RatFamiliar;
import witchmod.cards.familiar.ToadFamiliar;

public class SummonFamiliarPower extends AbstractPower {
	public static final String POWER_ID = "SummonFamiliar";
	public static final String NAME = "Summon Familiar";
	public static final String[] DESCRIPTIONS = new String[]{ "At the start of your turn, add ", "  Familiar card to your hand" };
	public static final String IMG = "powers/athamesoffering.png";
	private FamiliarCardEnum card;
	private boolean upgraded;
	public SummonFamiliarPower(AbstractCreature owner, FamiliarCardEnum card, boolean upgraded) {
		this.name = NAME;
		this.ID = POWER_ID;
		this.owner = owner;
		this.upgraded = upgraded;
		this.updateDescription();
		this.img = new Texture(WitchMod.getResourcePath(IMG));
		this.type = PowerType.BUFF;
		this.card = card;
	}

	@Override
	public void updateDescription() {
		this.description = DESCRIPTIONS[0]+getCardName(card)+DESCRIPTIONS[1];
	}

	@Override
	public void atStartOfTurn() {
		if (!AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
			this.flash();
			AbstractCard toCreate = familiarFactory(card);
			if (upgraded) {
				toCreate.upgraded = true;
				toCreate.upgrade();
			}
			AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(toCreate, 1, false));
		}
	}

	private AbstractCard familiarFactory(FamiliarCardEnum card){
		switch (card) {
		case BAT: return new BatFamiliar();
		case CAT: return new CatFamiliar();
		case OWL: return new OwlFamiliar();
		case RAT: return new RatFamiliar();
		case TOAD: return new ToadFamiliar();
		}
		
		return new CatFamiliar();
	}
	
	private String getCardName(FamiliarCardEnum card){
		switch (card) {
		case BAT: return upgraded?"an upgraded Bat":"a Bat";
		case CAT: return upgraded?"an upgraded Cat":"a Cat";
		case OWL: return upgraded?"an upgraded Owl":"an Owl";
		case RAT: return upgraded?"an upgraded Rat":"a Rat";
		case TOAD: return upgraded?"an upgraded Toad":"a Toad";
		}
		
		return "MISSING CARD "+card.toString();
	}
	
	public static AbstractCard getRandomFamiliarCard(){
		switch ((int)(Math.random()*5)) {
		case 0: return new BatFamiliar();
		case 1: return new RatFamiliar();
		case 2: return new OwlFamiliar();
		case 3: return new ToadFamiliar();
		default: return new CatFamiliar();
		}
	}
}

