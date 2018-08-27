package witchmod;

import java.nio.charset.StandardCharsets;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.AbstractCard.CardType;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.helpers.RelicLibrary;
import com.megacrit.cardcrawl.localization.RelicStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

import basemod.BaseMod;
import basemod.ModLabel;
import basemod.ModPanel;
import basemod.interfaces.EditCardsSubscriber;
import basemod.interfaces.EditCharactersSubscriber;
import basemod.interfaces.EditKeywordsSubscriber;
import basemod.interfaces.EditRelicsSubscriber;
import basemod.interfaces.EditStringsSubscriber;
import basemod.interfaces.PostDrawSubscriber;
import basemod.interfaces.PostInitializeSubscriber;
import witchmod.cards.Athame;
import witchmod.cards.Atonement;
import witchmod.cards.BalefulWard;
import witchmod.cards.Bewitch;
import witchmod.cards.BlackBolt;
import witchmod.cards.BlackShield;
import witchmod.cards.BleedOut;
import witchmod.cards.BloodSabbath;
import witchmod.cards.Boline;
import witchmod.cards.BoneCarving;
import witchmod.cards.Broomstick;
import witchmod.cards.ChosenOfTheMoon;
import witchmod.cards.CorruptBlood;
import witchmod.cards.CrystalResonance;
import witchmod.cards.CursedBlade;
import witchmod.cards.DarkProcession;
import witchmod.cards.Decrepify;
import witchmod.cards.Defend_Witch;
import witchmod.cards.Demonfyre;
import witchmod.cards.DireShriek;
import witchmod.cards.EternalThirst;
import witchmod.cards.EvilEye;
import witchmod.cards.FatalRay;
import witchmod.cards.Foresight;
import witchmod.cards.GhoulTouch;
import witchmod.cards.GnarledBody;
import witchmod.cards.Graveburst;
import witchmod.cards.GrimVengeance;
import witchmod.cards.Harmlessness;
import witchmod.cards.Hexguard;
import witchmod.cards.IllOmen;
import witchmod.cards.IllusionOfStrength;
import witchmod.cards.ImpendingDoom;
import witchmod.cards.Intelligence;
import witchmod.cards.KarmaDrain;
import witchmod.cards.LivingBomb;
import witchmod.cards.MagicFang;
import witchmod.cards.Malady;
import witchmod.cards.MementoMori;
import witchmod.cards.MercuryWand;
import witchmod.cards.MortusClaw;
import witchmod.cards.MysticUnburial;
import witchmod.cards.NighInvulnerability;
import witchmod.cards.PainBolt;
import witchmod.cards.Pillage;
import witchmod.cards.PlagueSpreader;
import witchmod.cards.Puncture;
import witchmod.cards.RiteOfAutumn;
import witchmod.cards.RiteOfSpring;
import witchmod.cards.RiteOfSummer;
import witchmod.cards.RiteOfWinter;
import witchmod.cards.RoilingBarrier;
import witchmod.cards.RustWall;
import witchmod.cards.SaltCircle;
import witchmod.cards.Schadenfreude;
import witchmod.cards.Shrooms;
import witchmod.cards.SkullFlask;
import witchmod.cards.SoulBarrier;
import witchmod.cards.StrangeBrew;
import witchmod.cards.Strike_Witch;
import witchmod.cards.SummonBatFamiliar;
import witchmod.cards.SummonCatFamiliar;
import witchmod.cards.SummonOwlFamiliar;
import witchmod.cards.SummonRatFamiliar;
import witchmod.cards.SummonRavenFamiliar;
import witchmod.cards.SummonToadFamiliar;
import witchmod.cards.Thundercloud;
import witchmod.cards.TrollsBlood;
import witchmod.cards.TwistedMind;
import witchmod.cards.Twitch;
import witchmod.cards.UnholyForm;
import witchmod.cards.UnluckySeven;
import witchmod.cards.UnnaturalEnergy;
import witchmod.cards.VileEgg;
import witchmod.cards.WalpurgisNight;
import witchmod.cards.WickedThoughts;
import witchmod.cards.WretchedNails;
import witchmod.cards.ZombieSpit;
import witchmod.characters.WitchCharacter;
import witchmod.patches.AbstractCardEnum;
import witchmod.patches.WitchEnum;
import witchmod.powers.AbstractWitchPower;
import witchmod.relics.BirdCage;
import witchmod.relics.BlackCat;
import witchmod.relics.Scissors;
import witchmod.relics.ToyHorse;
import witchmod.relics.WalkingCane;


@SpireInitializer
public class WitchMod implements PostInitializeSubscriber, EditCardsSubscriber, EditStringsSubscriber, EditRelicsSubscriber, EditCharactersSubscriber, EditKeywordsSubscriber,PostDrawSubscriber {

	public static final Logger logger = LogManager.getLogger(WitchMod.class);

	private static final String MODNAME = "WitchMod";
	private static final String AUTHOR = "Gygrazok";
	private static final String DESCRIPTION = "v0.9\n Adds The Witch character";

	private static final Color WITCH_COLOR = CardHelper.getColor(90.0f, 90.0f, 100.0f);
	private static final String ASSETS_FOLDER = "images";

	private static final String ATTACK_CARD = "512/bg_attack_witch.png";
	private static final String SKILL_CARD = "512/bg_skill_witch.png";
	private static final String POWER_CARD = "512/bg_power_witch.png";

	private static final String ENERGY_ORB = "512/card_witch_orb.png";

	private static final String ATTACK_CARD_PORTRAIT = "1024/bg_attack_witch.png";
	private static final String SKILL_CARD_PORTRAIT = "1024/bg_skill_witch.png";
	private static final String POWER_CARD_PORTRAIT = "1024/bg_power_witch.png";
	private static final String ENERGY_ORB_PORTRAIT = "1024/card_witch_orb.png";

	private static final String CHAR_BUTTON = "charSelect/button.png";
	private static final String CHAR_PORTRAIT = "charSelect/portrait.png";
	public static final String CHAR_SHOULDER_1 = "char/shoulder.png";
	public static final String CHAR_SHOULDER_2 = "char/shoulder2.png";
	public static final String CHAR_CORPSE = "char/corpse.png";
	public static final String CHAR_SKELETON_ATLAS = "char/skeleton.atlas";
	public static final String CHAR_SKELETON_JSON = "char/skeleton.json";

	public static final String BADGE_IMG = "badge.png";


	public static final String getResourcePath(String resource) {
		return ASSETS_FOLDER + "/" + resource;
	}

	public WitchMod() {
		BaseMod.subscribe(this);
		



		BaseMod.addColor(AbstractCardEnum.WITCH,
				WITCH_COLOR, WITCH_COLOR, WITCH_COLOR, WITCH_COLOR, WITCH_COLOR, WITCH_COLOR, WITCH_COLOR,
				getResourcePath(ATTACK_CARD), getResourcePath(SKILL_CARD), getResourcePath(POWER_CARD),
				getResourcePath(ENERGY_ORB),
				getResourcePath(ATTACK_CARD_PORTRAIT), getResourcePath(SKILL_CARD_PORTRAIT), getResourcePath(POWER_CARD_PORTRAIT),
				getResourcePath(ENERGY_ORB_PORTRAIT));
	}

	public static void initialize() {
		logger.info("Initializing Witch Mod");
		new WitchMod();
	}

	public void receivePostInitialize() {
		Texture badgeTexture = new Texture(getResourcePath(BADGE_IMG));
		ModPanel settingsPanel = new ModPanel();
		settingsPanel.addUIElement(new ModLabel("No settings", 400.0f, 700.0f, settingsPanel, (me) -> {}));
		BaseMod.registerModBadge(badgeTexture, MODNAME, AUTHOR, DESCRIPTION, settingsPanel);

		Settings.isDailyRun = false;
		Settings.isTrial = false;
		Settings.isDemo = false;
	}


	public void receiveEditCharacters() {
		BaseMod.addCharacter(WitchCharacter.class, "The Witch", "WitchCharacter",
				AbstractCardEnum.WITCH, "The Witch",
				getResourcePath(CHAR_BUTTON), 
				getResourcePath(CHAR_PORTRAIT),
				WitchEnum.WITCH);
	}


	public void receiveEditRelics() {
		RelicLibrary.add(new BlackCat());
		BaseMod.addRelicToCustomPool(new BirdCage(),AbstractCardEnum.WITCH);
		BaseMod.addRelicToCustomPool(new WalkingCane(),AbstractCardEnum.WITCH);
		BaseMod.addRelicToCustomPool(new Scissors(),AbstractCardEnum.WITCH);
		BaseMod.addRelicToCustomPool(new ToyHorse(),AbstractCardEnum.WITCH);
	}

	public void receiveEditCards() {
		//BASIC
		BaseMod.addCard(new Strike_Witch());
		BaseMod.addCard(new Defend_Witch());
		BaseMod.addCard(new ZombieSpit());
		BaseMod.addCard(new Hexguard());
		//COMMON (21)
		//Attacks (11)
		BaseMod.addCard(new Demonfyre());
		BaseMod.addCard(new BleedOut());
		BaseMod.addCard(new Broomstick());
		BaseMod.addCard(new BlackBolt());
		BaseMod.addCard(new MercuryWand());
		BaseMod.addCard(new WretchedNails());
		BaseMod.addCard(new BoneCarving());
		BaseMod.addCard(new MagicFang());
		BaseMod.addCard(new MementoMori());
		BaseMod.addCard(new SkullFlask());
		BaseMod.addCard(new Thundercloud());
		//Skills (10)
		BaseMod.addCard(new BlackShield());
		BaseMod.addCard(new SaltCircle());
		BaseMod.addCard(new KarmaDrain());
		BaseMod.addCard(new WickedThoughts());
		BaseMod.addCard(new SoulBarrier());
		BaseMod.addCard(new Bewitch());
		BaseMod.addCard(new GnarledBody());
		BaseMod.addCard(new RoilingBarrier());
		BaseMod.addCard(new Decrepify());
		BaseMod.addCard(new Atonement());


		//UNCOMMON (28)
		//Attacks (10)
		BaseMod.addCard(new Athame());
		BaseMod.addCard(new PainBolt());
		BaseMod.addCard(new CursedBlade());
		BaseMod.addCard(new GhoulTouch());
		BaseMod.addCard(new MortusClaw());
		BaseMod.addCard(new LivingBomb());
		BaseMod.addCard(new RiteOfSummer());
		BaseMod.addCard(new Puncture());
		BaseMod.addCard(new Harmlessness());
		BaseMod.addCard(new Malady());
		//Skills (11)
		BaseMod.addCard(new Foresight());
		BaseMod.addCard(new Shrooms());
		BaseMod.addCard(new NighInvulnerability());
		BaseMod.addCard(new Pillage());
		BaseMod.addCard(new RiteOfAutumn());
		BaseMod.addCard(new CrystalResonance());
		BaseMod.addCard(new IllOmen());
		BaseMod.addCard(new RiteOfWinter());
		BaseMod.addCard(new BalefulWard());
		BaseMod.addCard(new CorruptBlood());
		BaseMod.addCard(new PlagueSpreader());
		//Powers (7)
		BaseMod.addCard(new TwistedMind());
		BaseMod.addCard(new Schadenfreude());
		BaseMod.addCard(new SummonOwlFamiliar());
		BaseMod.addCard(new SummonRatFamiliar());
		BaseMod.addCard(new SummonCatFamiliar());
		BaseMod.addCard(new SummonBatFamiliar());
		BaseMod.addCard(new GrimVengeance());


		//RARE (25)
		//Attacks (8)
		BaseMod.addCard(new ImpendingDoom());
		BaseMod.addCard(new Graveburst());
		BaseMod.addCard(new EternalThirst());
		BaseMod.addCard(new DireShriek());
		BaseMod.addCard(new FatalRay());
		BaseMod.addCard(new BloodSabbath());
		BaseMod.addCard(new Boline());
		BaseMod.addCard(new EvilEye());
		//Skills (9)
		BaseMod.addCard(new MysticUnburial());
		BaseMod.addCard(new UnnaturalEnergy());
		BaseMod.addCard(new UnluckySeven());
		BaseMod.addCard(new StrangeBrew());
		BaseMod.addCard(new RiteOfSpring());
		BaseMod.addCard(new Twitch());
		BaseMod.addCard(new RustWall());
		BaseMod.addCard(new WalpurgisNight());
		BaseMod.addCard(new VileEgg());
		//Powers (8)
		BaseMod.addCard(new Intelligence());
		BaseMod.addCard(new SummonToadFamiliar());
		BaseMod.addCard(new SummonRavenFamiliar());
		BaseMod.addCard(new TrollsBlood());
		BaseMod.addCard(new DarkProcession());
		BaseMod.addCard(new IllusionOfStrength());
		BaseMod.addCard(new ChosenOfTheMoon());
		BaseMod.addCard(new UnholyForm());
	}


	public void receiveEditStrings() {
		String relicStrings = Gdx.files.internal("strings/relic-strings.json").readString(String.valueOf(StandardCharsets.UTF_8));
		BaseMod.loadCustomStrings(RelicStrings.class, relicStrings);
	}


	public void receiveEditKeywords() {
		//String[] persistent = {"persistent"};
		//BaseMod.addKeyword(persistent, "Doesn't get discarded automatically at the end of the turn.");

		String[] recurrent = {"recurrent"};
		BaseMod.addKeyword(recurrent, "When this card is played it's shuffled in the draw pile instead of being discarded.");

		String[] cleanse = {"cleanse"};
		BaseMod.addKeyword(cleanse, "This card is cleansed if the condition is satisfied while in hand, turning into a powerful card for the rest of the combat.");

		String[] rot = {"rot"};
		BaseMod.addKeyword(rot, "Creatures afflicted by Rot lose HP at the start of their turn. Each turn Rot is increased by #b1.");

		String[] decrepit = {"decrepit"};
		BaseMod.addKeyword(decrepit, "Creatures afflicted by Decrepit suffer 1 extra damage from attacks for each stack. NL Each turn Decrepit is decreased by #b1.");

	}
	
	public void receivePostDraw(AbstractCard c) {
		AbstractPlayer player = AbstractDungeon.player;
		//custom callback for card draw on powers
		if (player instanceof WitchCharacter) {
			WitchCharacter witch = (WitchCharacter) player;
	        for (AbstractPower p : witch.powers) {
	        	if (p instanceof AbstractWitchPower) {
	        		((AbstractWitchPower)p).onCardDraw(c);
	        	}
	        }
	        witch.cardsDrawnThisTurn++;
	        witch.cardsDrawnTotal++;
	        if (c.type == CardType.CURSE) {
	        	witch.cursesDrawnTotal++;
	        }
		}

	}

}
