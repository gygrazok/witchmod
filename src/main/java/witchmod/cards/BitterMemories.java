package witchmod.cards;

import java.util.HashMap;
import java.util.Map;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import witchmod.actions.BitterMemoriesAction;

public class BitterMemories extends AbstractWitchCard{
	public static final String ID = "BitterMemories";
	public static final	String NAME = "Bitter Memories";
	public static final	String IMG = "cards/placeholder_skill.png";
	public static final	String DESCRIPTION = "Shuffle a random discarded card of each different type into your draw pile and gain !B! block for each of them.";
	public static final	String EXTENDED_DESCRIPTION[] = {" NL You will gain "," block."};
	
	private static final CardRarity RARITY = CardRarity.UNCOMMON;
	private static final CardTarget TARGET = CardTarget.SELF;
	private static final CardType TYPE = CardType.SKILL;
	
	private static final int POOL = 1;
	
	private static final int COST = 1;
	private static final int POWER = 3;
	private static final int POWER_UPGRADED = 4;

	
	public BitterMemories() {
		super(ID,NAME,IMG,COST,DESCRIPTION,TYPE,RARITY,TARGET,POOL);
		this.baseBlock = POWER;
	}

	public void use(AbstractPlayer p, AbstractMonster m) {
		AbstractDungeon.actionManager.addToBottom(new BitterMemoriesAction(block));      
	}
	
	
	public AbstractCard makeCopy() {
		return new BitterMemories();
	}
	
	private int countCardTypes() {
        Map<CardType, Boolean> types = new HashMap<>();
        for (AbstractCard c : AbstractDungeon.player.discardPile.group) {
        	if (!types.containsKey(c.type)) {
        		types.put(c.type, true);
        	}
        }
        return types.size();
	}
	
    @Override
    public void applyPowers() {
        super.applyPowers();
        rawDescription = DESCRIPTION;
        rawDescription += EXTENDED_DESCRIPTION[0]+(block*countCardTypes()+EXTENDED_DESCRIPTION[1]);
        this.initializeDescription();
    }
	


	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeBlock(POWER_UPGRADED);
		}
	}
	
}
