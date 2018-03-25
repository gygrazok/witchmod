package witchmod.patches;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.rooms.MonsterRoom;


@SpirePatch(cls="com.megacrit.cardcrawl.rooms.MonsterRoom", method="getCardRarity")
public class MonsterRoomPatch {
    private static final Logger logger = LogManager.getLogger(MonsterRoomPatch.class);
	@SpireInsertPatch(rloc=4, localvars={"rareRate"})
	public static void Insert(MonsterRoom __obj_instance, int roll, int rareRate) {
		logger.info("Calling insert patch "+roll+"vs  "+rareRate+" "+AbstractDungeon.player.hasPower("AthamesOffering"));
		if (AbstractDungeon.player.hasPower("AthamesOffering") && AbstractDungeon.player.getPower("AthamesOffering").amount > 0){
			logger.info("If executed: "+AbstractDungeon.player.getPower("AthamesOffering").amount);
			rareRate *= AbstractDungeon.player.getPower("AthamesOffering").amount;
		}
		logger.info("Final rate = "+rareRate);
	}
}
