package witchmod.cards;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import basemod.ReflectionHacks;
import witchmod.actions.GraveburstAction;

public class Graveburst extends AbstractWitchCard{
	public static final String ID = "Graveburst";
	public static final	String NAME = "Graveburst";
	public static final	String IMG = "cards/placeholder_attack.png";
	public static final	String DESCRIPTION = "For each card of different type in your discard pile, shuffle it in your draw pile and deal !D! damage to all enemies.";
	public static final	String EXTENDED_DESCRIPTION[] = new String[] {" NL (You have "," different card types in your discard pile)"," card type in your discard pile)"};
	
	private static final CardRarity RARITY = CardRarity.RARE;
	private static final CardTarget TARGET = CardTarget.ALL_ENEMY;
	private static final CardType TYPE = CardType.ATTACK;
	
	private static final int POOL = 1;
	
	private static final int COST = 2;
	private static final int POWER = 3;
	private static final int UPGRADE_BONUS = 4;

	public Graveburst() {
		super(ID,NAME,IMG,COST,DESCRIPTION,TYPE,RARITY,TARGET,POOL);
		this.baseDamage = POWER;
		this.isMultiDamage = true;
	}

	public void use(AbstractPlayer p, AbstractMonster m) {
		AbstractDungeon.actionManager.addToBottom(new GraveburstAction(multiDamage,this.damageTypeForTurn));      
	}
	
	public AbstractCard makeCopy() {
		return new Graveburst();
	}
	
    @Override
    public void applyPowers() {
        super.applyPowers();
        int count = 0;
        Map<CardType, Boolean> types = new HashMap<>();
        for (AbstractCard c : AbstractDungeon.player.discardPile.group) {
        	types.put(c.type, true);
        }
        count = types.size();
        this.rawDescription = DESCRIPTION;
        this.rawDescription += EXTENDED_DESCRIPTION[0] + count;
        this.rawDescription += (count == 1 ? EXTENDED_DESCRIPTION[2] : EXTENDED_DESCRIPTION[1]);
        this.initializeDescription();
    }
	
	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeDamage(UPGRADE_BONUS);
		}
	}
	
	//ugly reflection hack
	private void useSmallFont() {
		try {
			Method getDescFontMethod = AbstractCard.class.getDeclaredMethod("getDescFont");
			getDescFontMethod.setAccessible(true);
		} catch (NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
	}
}
