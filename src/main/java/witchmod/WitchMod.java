package witchmod;

import java.nio.charset.StandardCharsets;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.helpers.RelicLibrary;
import com.megacrit.cardcrawl.localization.RelicStrings;

import basemod.BaseMod;
import basemod.ModPanel;
import basemod.interfaces.EditCardsSubscriber;
import basemod.interfaces.EditCharactersSubscriber;
import basemod.interfaces.EditKeywordsSubscriber;
import basemod.interfaces.EditRelicsSubscriber;
import basemod.interfaces.EditStringsSubscriber;
import basemod.interfaces.PostInitializeSubscriber;
import witchmod.cards.ArmorTheft;
import witchmod.cards.Athame;
import witchmod.cards.AutumnalRite;
import witchmod.cards.Bewitch;
import witchmod.cards.BlackBolt;
import witchmod.cards.BlackShield;
import witchmod.cards.BleedOut;
import witchmod.cards.BloodSabbath;
import witchmod.cards.Boline;
import witchmod.cards.BoneCarvedDagger;
import witchmod.cards.BroomstickSmash;
import witchmod.cards.CursedBlade;
import witchmod.cards.DarkProcession;
import witchmod.cards.Decrepify;
import witchmod.cards.Defend_Witch;
import witchmod.cards.Demonfyre;
import witchmod.cards.DoomBlade;
import witchmod.cards.EnfeeblingStrike;
import witchmod.cards.EternalThirst;
import witchmod.cards.EvilEye;
import witchmod.cards.SaltCircle;
import witchmod.cards.FatalRay;
import witchmod.cards.Foresight;
import witchmod.cards.GnarledBody;
import witchmod.cards.Graveburst;
import witchmod.cards.HandOfAkelarre;
import witchmod.cards.Hexdarts;
import witchmod.cards.Hexguard;
import witchmod.cards.IllusionOfStrength;
import witchmod.cards.Implosion;
import witchmod.cards.Intelligence;
import witchmod.cards.KarmaDrain;
import witchmod.cards.Malady;
import witchmod.cards.MercuryWand;
import witchmod.cards.MortusClaw;
import witchmod.cards.MysticUnburial;
import witchmod.cards.NighInvulnerability;
import witchmod.cards.PainBolt;
import witchmod.cards.BitterMemories;
import witchmod.cards.TwistedMind;
import witchmod.cards.Twitch;
import witchmod.cards.RoilingBarrier;
import witchmod.cards.Schadenfreude;
import witchmod.cards.Shrooms;
import witchmod.cards.SoulBarrier;
import witchmod.cards.SoulBurst;
import witchmod.cards.SoulStrike;
import witchmod.cards.SpringRite;
import witchmod.cards.StrangeBrew;
import witchmod.cards.Strike_Witch;
import witchmod.cards.SummonBatFamiliar;
import witchmod.cards.SummonCatFamiliar;
import witchmod.cards.SummonOwlFamiliar;
import witchmod.cards.SummonRatFamiliar;
import witchmod.cards.SummonToadFamiliar;
import witchmod.cards.TrollsBlood;
import witchmod.cards.UnluckySeven;
import witchmod.cards.UnnaturalEnergy;
import witchmod.cards.WickedInspiration;
import witchmod.cards.WindsOfPutrefaction;
import witchmod.cards.WretchedVengeance;
import witchmod.cards.ZombieSpit;
import witchmod.characters.WitchCharacter;
import witchmod.patches.AbstractCardEnum;
import witchmod.patches.WitchEnum;
import witchmod.relics.BlackCat;
import witchmod.relics.PetCage;


@SpireInitializer
public class WitchMod implements PostInitializeSubscriber, EditCardsSubscriber, EditStringsSubscriber, EditRelicsSubscriber, EditCharactersSubscriber, EditKeywordsSubscriber {

	public static final Logger logger = LogManager.getLogger(WitchMod.class);

	private static final String MODNAME = "WitchMod";
	private static final String AUTHOR = "Gygrazok";
	private static final String DESCRIPTION = "v0.1\n Adds The Witch character";

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
		BaseMod.subscribeToPostInitialize(this);

		BaseMod.subscribeToEditCharacters(this);

		BaseMod.subscribeToEditRelics(this);

		BaseMod.subscribeToEditCards(this);
		
		BaseMod.subscribeToEditStrings(this);

		BaseMod.subscribeToEditKeywords(this);


		BaseMod.addColor(AbstractCardEnum.WITCH.toString(),
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

	@SuppressWarnings("deprecation")
	public void receivePostInitialize() {
		Texture badgeTexture = new Texture(getResourcePath(BADGE_IMG));
		ModPanel settingsPanel = new ModPanel();
		settingsPanel.addLabel("No settings", 400.0f, 700.0f, (me) -> {});
		BaseMod.registerModBadge(badgeTexture, MODNAME, AUTHOR, DESCRIPTION, settingsPanel);

		Settings.isDailyRun = false;
		Settings.isTrial = false;
		Settings.isDemo = false;
	}


	public void receiveEditCharacters() {
		BaseMod.addCharacter(WitchCharacter.class, "The Witch", "WitchCharacter",
				AbstractCardEnum.WITCH.toString(), "The Witch",
				getResourcePath(CHAR_BUTTON), 
				getResourcePath(CHAR_PORTRAIT),
				WitchEnum.WITCH.toString());
	}


	public void receiveEditRelics() {
		RelicLibrary.add(new BlackCat());
		RelicLibrary.add(new PetCage());
	}

	public void receiveEditCards() {
		//BASIC
		BaseMod.addCard(new Strike_Witch());
		BaseMod.addCard(new Defend_Witch());
		BaseMod.addCard(new Hexdarts());
		//COMMON
		//Attacks
		BaseMod.addCard(new Demonfyre());
		BaseMod.addCard(new BleedOut());
		BaseMod.addCard(new BroomstickSmash());
		BaseMod.addCard(new BlackBolt());
		BaseMod.addCard(new MercuryWand());
		BaseMod.addCard(new HandOfAkelarre());
		BaseMod.addCard(new BoneCarvedDagger());
		BaseMod.addCard(new ZombieSpit());
		//Skills
		BaseMod.addCard(new BlackShield());
		BaseMod.addCard(new SaltCircle());
		BaseMod.addCard(new KarmaDrain());
		BaseMod.addCard(new WickedInspiration());
		BaseMod.addCard(new SoulBarrier());
		BaseMod.addCard(new Bewitch());
		BaseMod.addCard(new GnarledBody());
		BaseMod.addCard(new RoilingBarrier());
		BaseMod.addCard(new Decrepify());

		
		//UNCOMMON
		//Attacks
		BaseMod.addCard(new Athame());
		BaseMod.addCard(new PainBolt());
		BaseMod.addCard(new CursedBlade());
		BaseMod.addCard(new EnfeeblingStrike());
		BaseMod.addCard(new SoulStrike());
		BaseMod.addCard(new MortusClaw());
		BaseMod.addCard(new WindsOfPutrefaction());
		BaseMod.addCard(new Implosion());
		//Skills
		BaseMod.addCard(new Foresight());
		BaseMod.addCard(new Hexguard());
		BaseMod.addCard(new Shrooms());
		BaseMod.addCard(new NighInvulnerability());
		BaseMod.addCard(new Malady());
		BaseMod.addCard(new ArmorTheft());
		BaseMod.addCard(new BitterMemories());
		BaseMod.addCard(new AutumnalRite());
		//Powers
		BaseMod.addCard(new TwistedMind());
		BaseMod.addCard(new Schadenfreude());
		BaseMod.addCard(new SummonOwlFamiliar());
		BaseMod.addCard(new SummonRatFamiliar());
		BaseMod.addCard(new SummonBatFamiliar());
		BaseMod.addCard(new WretchedVengeance());


		//RARE
		//Attacks
		BaseMod.addCard(new DoomBlade());
		BaseMod.addCard(new Graveburst());
		BaseMod.addCard(new EternalThirst());
		BaseMod.addCard(new SoulBurst());
		BaseMod.addCard(new FatalRay());
		BaseMod.addCard(new BloodSabbath());
		BaseMod.addCard(new Boline());
		BaseMod.addCard(new EvilEye());
		//Skills
		BaseMod.addCard(new MysticUnburial());
		BaseMod.addCard(new UnnaturalEnergy());
		BaseMod.addCard(new UnluckySeven());
		BaseMod.addCard(new StrangeBrew());
		BaseMod.addCard(new SpringRite());
		BaseMod.addCard(new Twitch());
		//Powers
		BaseMod.addCard(new Intelligence());
		BaseMod.addCard(new SummonToadFamiliar());
		BaseMod.addCard(new SummonCatFamiliar());
		BaseMod.addCard(new TrollsBlood());
		BaseMod.addCard(new DarkProcession());
		BaseMod.addCard(new IllusionOfStrength());
	}


	public void receiveEditStrings() {
		String relicStrings = Gdx.files.internal("strings/relic-strings.json").readString(String.valueOf(StandardCharsets.UTF_8));
		BaseMod.loadCustomStrings(RelicStrings.class, relicStrings);
	}


	public void receiveEditKeywords() {
		String[] persistent = {"persistent"};
		BaseMod.addKeyword(persistent, "Doesn't get discarded automatically at the end of the turn.");

		String[] recurrent = {"recurrent"};
		BaseMod.addKeyword(recurrent, "When played gets shuffled back into the deck instead of being discarded.");

		String[] cleanse = {"cleanse"};
		BaseMod.addKeyword(cleanse, "This card is cleansed if the condition is satisfied at the end of your turn while it is in your hand.");
		
		String[] cardType = {"card type", "card types"};
		BaseMod.addKeyword(cardType, "Card types are Attack, Skill, Power, Status and Curse.");
		
		String[] rot = {"rot", "contagious rot"};
		BaseMod.addKeyword(rot, "Creatures afflicted by Rot lose HP at the start of their turn. Each turn Rot is increased by #b2.");
		
		String[] decrepit = {"decrepit"};
		BaseMod.addKeyword(decrepit, "Creatures afflicted by Decrepit suffer 1 extra damage from attacks for each stack. Each turn Decrepit is decreased by #b1.");
		
	}

}
