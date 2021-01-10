package witchmod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;


public class Graveburst extends AbstractWitchCard {
    public static final String ID = "Graveburst";
    public static final String NAME = "Graveburst";
    public static final String IMG = "cards/graveburst.png";
    public static final String DESCRIPTION = "Retain. Deal damage to all enemies equal to twice the number of Attacks in your discard pile.";
    public static final String[] EXTENDED_DESCRIPTION = new String[]{" ( !D! damage.)"};

    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.ALL_ENEMY;
    private static final CardType TYPE = CardType.ATTACK;

    private static final int POOL = 1;

    private static final int COST = 1;
    private static final int COST_UPGRADED = 0;
    private static final int POWER = 2;

    public Graveburst() {
        super(ID, IMG, COST, TYPE, RARITY, TARGET);
        this.baseDamage = POWER;
        this.isMultiDamage = true;
        this.retain = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(p, multiDamage, damageTypeForTurn, AttackEffect.FIRE, true));
    }

    public AbstractCard makeCopy() {
        return new Graveburst();
    }

    @Override
    public void atTurnStart() {
        retain = true;
    }

    @Override
    public void applyPowers() {
        int count = 0;
        for (AbstractCard c : AbstractDungeon.player.discardPile.group) {
            if (c.type == CardType.ATTACK) {
                count++;
            }
        }
        baseDamage = count * POWER;
        super.applyPowers();
        rawDescription = DESCRIPTION;
        rawDescription += EXTENDED_DESCRIPTION[0];
        initializeDescription();
    }


    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        super.calculateCardDamage(mo);
        rawDescription = DESCRIPTION;
        rawDescription += EXTENDED_DESCRIPTION[0];
        initializeDescription();
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBaseCost(COST_UPGRADED);
        }
    }


}
