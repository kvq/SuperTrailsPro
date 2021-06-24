package me.kvq.supertrailspro.trails;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import me.kvq.supertrailspro.ServerVersion;
import me.kvq.supertrailspro.Version;
import me.kvq.supertrailspro.packetlib.ParticleEffect;
import me.kvq.supertrailspro.utils.STUtils;

public enum ParticleTrailsEnum {
	
	
	
	HEART(1, "Heart", ParticleEffect.HEART, new ItemStack(Material.BONE) ),
	ANGRY(2, "Angry", ParticleEffect.VILLAGER_ANGRY,new ItemStack(Material.BLAZE_POWDER)),
	MAGIC(3, "Magic", ParticleEffect.CRIT_MAGIC,new ItemStack(Material.BOOK)),
	FUN(4, "Fun", ParticleEffect.REDSTONE, new ItemStack(Material.REDSTONE)),
	CLOUD(5, "Cloud", ParticleEffect.CLOUD, new ItemStack(Material.WEB)),
	WITCH(6, "Witch", ParticleEffect.SPELL_WITCH, new ItemStack(Material.SKULL_ITEM,1,(byte)0)),
	ENDER(7, "Ender", ParticleEffect.PORTAL, new ItemStack(Material.ENDER_PEARL)),
	GREEN(8, "Green", ParticleEffect.VILLAGER_HAPPY, new ItemStack(Material.EMERALD)),
	SPARK(9, "Spark", ParticleEffect.FIREWORKS_SPARK, new ItemStack(Material.FLINT)),
	FLAME(10, "Flame", ParticleEffect.FLAME, new ItemStack(Material.FLINT_AND_STEEL)),
	WHITE(11, "White", ParticleEffect.SPELL_INSTANT, new ItemStack(Material.SPIDER_EYE)),
	NOTE(12, "Note", ParticleEffect.NOTE, new ItemStack(Material.RECORD_3)),
	SNOW(13, "Snow", ParticleEffect.SNOW_SHOVEL, new ItemStack(Material.SNOW_BLOCK)),
	WATER(14, "Water", ParticleEffect.DRIP_WATER, new ItemStack(Material.WATER_BUCKET)),
	LAVA(15, "Lava", ParticleEffect.DRIP_LAVA, new ItemStack(Material.LAVA_BUCKET)),
	CRIT(16, "Crit", ParticleEffect.CRIT, new ItemStack(Material.IRON_SWORD)),
	SMOKE(17, "Smoke", ParticleEffect.SMOKE_NORMAL, new ItemStack(Material.COAL_BLOCK)),
	SPELL(18, "Spell", ParticleEffect.SPELL_MOB_AMBIENT, new ItemStack(Material.STICK)),
	ENCHANT(19, "Enchant", ParticleEffect.ENCHANTMENT_TABLE, new ItemStack(Material.ENCHANTMENT_TABLE)),
	SPLASH(20, "Splash", ParticleEffect.WATER_SPLASH, new ItemStack(Material.WHEAT)),
	SLIME(21, "Slime", ParticleEffect.SLIME, new ItemStack(Material.SLIME_BALL)),
	SNOWBALL(22, "Snowball", ParticleEffect.SNOWBALL, new ItemStack(Material.SNOW_BALL)),
	VOID(23, "Void", ParticleEffect.TOWN_AURA, new ItemStack(Material.ENDER_STONE)),
	LAVAPOP(24, "LavaPop", ParticleEffect.LAVA, new ItemStack(Material.BLAZE_ROD)),
	
	//1.9+ getMaterial used to access Materials that do not exist in older API version
	BREATH(25, "Breath", ParticleEffect.DRAGON_BREATH, new ItemStack(Material.getMaterial("DRAGONS_BREATH"))),
	DAMAGE(26, "Damage", ParticleEffect.DAMAGE, new ItemStack(Material.getMaterial("FERMENTED_SPIDER_EYE"))),
	ENDROD(27, "EndRod", ParticleEffect.END_ROD, new ItemStack(Material.getMaterial("END_ROD"))),
	
	//1.10+
	TOTEM(28, "Totem", ParticleEffect.TOTEM, new ItemStack(Material.getMaterial("TOTEM"))),
	
	//1.16+ refection used to bypass legacy Material API		
	SOUL(29, "Soul", ParticleEffect.SOUL, new ItemStack(STUtils.getDirrectMaterial("SOUL_LANTERN"))),
	SOULFIRE(30, "SoulFire", ParticleEffect.SOUL_FIRE, new ItemStack(STUtils.getDirrectMaterial("SOUL_CAMPFIRE")));
	

	private int id; 
	private String name; 
	private ParticleEffect effect; 
	private ItemStack item; Version v;
	ParticleTrailsEnum(int id, String name,ParticleEffect eff,ItemStack is) {
		this.id = id; this.name = name; this.effect = eff; this.item = is;
		this.v = ServerVersion.getOlder();
	}

	ParticleTrailsEnum(int id, String name,ParticleEffect eff,ItemStack is,boolean remove_flags) {
		this(id, name, eff, is);
		if (remove_flags)STUtils.removeFlags(this.item);
	}
	
	ParticleTrailsEnum(int id, String name,ParticleEffect eff,ItemStack is,boolean remove_flags,Version version) {
		this(id, name, eff, is,remove_flags);
		this.v = version;
	}
	
	
	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public ParticleEffect getEffect() {
		return effect;
	}

	public ItemStack getItem() {
		return item;
	}
	
	public Version getVersion() {
		return v;
	}
}
