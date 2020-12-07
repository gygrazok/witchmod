package witchmod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;

public class BoneCarving extends AbstractWitchCard {
    public static final String ID = "BoneCarving";
    public static final String NAME = "Bone Carving";
    public static final String IMG = "cards/bonecarving.png";
    public static final String DESCRIPTION = "When drawn apply 1 Vulnerable to a random enemy !M! times. NL Deal !D! damage.";

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;

    private static final int POOL = 1;

    private static final int COST = 1;
    private static final int POWER = 6;
    private static final int UPGRADED_BONUS = 3;

    private static final int MAGIC = 2;
    private static final int MAGIC_UPGRADE_BONUS = 1;

    public BoneCarving() {
        super(ID, IMG, COST, TYPE, RARITY, TARGET);
        this.magicNumber = this.baseMagicNumber = MAGIC;
        baseDamage = POWER;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AttackEffect.SLASH_HORIZONTAL));
    }

    public AbstractCard makeCopy() {
        return new BoneCarving();
    }

    @Override
    public void triggerWhenDrawn() {
        int counter = magicNumber;
        while (counter > 0) {
            AbstractMonster monster = AbstractDungeon.getRandomMonster();
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(monster, AbstractDungeon.player, new VulnerablePower(monster, 1, false), 1, true));
            counter--;
        }
        flash();
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(UPGRADED_BONUS);
            upgradeMagicNumber(MAGIC_UPGRADE_BONUS);
        }
    }
}
