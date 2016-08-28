package me.matho.lonescout.weapon;

/**
 * Created by alex on 26/08/2016.
 */
public class Gun {

    public static final int MAX_DAMAGE = 400;

    private GunType type;

    private String name;
    private int levelGained;

    private int magazine;
    private int ammo;

    private float damage;
    private float fireRate;

    private boolean canHold;

    private static Gun generate(int playerLevel) {

        final GunType type = GunType.values()[(int) (Math.random()) * GunType.values().length];
        return new Gun(type, generateName(), playerLevel, generateMagSize(type, playerLevel),
                generateDamage(type, playerLevel), type.fireRate);

    }

    private static float generateDamage(GunType type, int level) {

        final float factor = (float) Math.max(1 + (level - 50) / 50, Math.random());
        return type.damagePercent * MAX_DAMAGE * factor;

    }

    private static int generateMagSize(GunType type, int level) {

        final double factor = Math.max(1 + (level - 50) / 50, Math.random());
        return (int) (factor * (type.upperMag - type.lowerMag) + type.lowerMag);

    }

    private static String generateName() {

        String[] pieces = {"A", "C", "E", "F", "G", "J", "K", "L", "M", "O", "P", "R", "S", "T", "-", " ", "0", "1",
                "2", "3", "4", "5", "6", "7", "8", "9"};

        StringBuilder name = new StringBuilder();

        name.append(pieces[(int) (Math.random() * pieces.length)]);
        while (name.charAt(0) == ' ' || name.charAt(0) == '-') {
            name.deleteCharAt(0);
            name.append(pieces[(int) (Math.random() * pieces.length)]);
        }

        final int nameLen = (int) (Math.random() * 7);
        for (int i = 0; i < nameLen; i++) {
            name.append(pieces[(int) (Math.random() * pieces.length)]);
        }

        return name.toString();

    }

    public Gun(GunType type, String name, int levelGained, int magazine, float damage, float fireRate) {

        this.type = type;
        this.name = name;
        this.levelGained = levelGained;
        this.magazine = magazine;
        this.damage = damage;
        this.fireRate = fireRate;
        this.canHold = type == GunType.ASSAULT_RIFLE || type == GunType.SMG;

        setAmmo(0);

    }

    public void setAmmo(int ammo) {

        this.ammo = ammo;

    }

    public String getTextureName() {

        return type.texname;

    }

}

enum GunType {

    BOLT_ACTION_RIFLE ("Bolt-action Rifle", "gun0",  1, 20, 0.4F, 1.0F),
    ASSAULT_RIFLE     ("Assault Rifle",     "gun0", 10, 80, 0.3F, 2.0F),
    SNIPER_RIFLE      ("Sniper Rifle",      "gun1",  1, 10, 0.8F, 0.5F),
    SMG               ("Sub-machine Gun",   "gun2", 20, 80, 0.2F, 2.5F),
    PISTOL            ("Pistol",            "gun3",  1, 10, 0.4F, 0.9F),
    ROCKET_LAUNCHER   ("Rocket Launcher",   "gun4",  1,  5, 1.0F, 0.1F),
    PULSAR_RIFLE      ("Pulsar Rifle",      "gun5",  5, 25, 0.5F, 0.9F),
    COMBAT_SHOTGUN    ("Combat Shotgun",    "gun6",  1, 12, 0.6F, 1.1F);

    public final String name;
    public final String texname;
    public final int lowerMag;
    public final int upperMag;
    public final float damagePercent;
    public final float fireRate;

    GunType(String name, String texname, int lowerMag, int upperMag, float damagePercent, float fireRate) {

        this.name = name;
        this.texname = texname;
        this.lowerMag = lowerMag;
        this.upperMag = upperMag;
        this.damagePercent = damagePercent;
        this.fireRate = fireRate;

    }

}
